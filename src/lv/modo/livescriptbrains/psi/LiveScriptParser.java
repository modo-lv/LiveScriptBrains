package lv.modo.livescriptbrains.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LiveScriptParser implements PsiParser {

	/**
	 * A tree of tokens and sub-tokens parsed from the lexer input.
	 */
	public class TokenTree extends ArrayList<TreeToken> {
		public Stack<LiveScriptParserState> StateStack = new Stack<LiveScriptParserState>();

		public Map<Integer, List<TreeToken>> ByStartIndex = new HashMap<Integer, List<TreeToken>>();
		public Map<Integer, List<TreeToken>> ByEndIndex = new HashMap<Integer, List<TreeToken>>();
		public List<TreeToken> InputList;

		/**
		 * Which token we're currently parsing.
		 */
		public int ParseTokenIndex;

		/**
		 * Constructor
		 * @param inputList List of tokens from the lexer.
		 */
		public TokenTree(List<TreeToken> inputList) {
			InputList = new ArrayList<TreeToken>();
			InputList.addAll(inputList);
			for (TreeToken t : InputList)
				this.add(t);
		}

		/**
		 * Add a new token to the tree.
		 * @param token Token to add.
		 * @return Same as List.add().
		 */
		@Override
		public boolean add(TreeToken token) {
			ByStartIndex.putIfAbsent(token.StartPosition, new ArrayList<TreeToken>());
			ByEndIndex.putIfAbsent(token.EndPosition, new ArrayList<TreeToken>());

			ByStartIndex.get(token.StartPosition).add(token);
			ByEndIndex.get(token.EndPosition).add(token);
			
			return super.add(token);
		}

		@Override
		public boolean addAll(Collection<? extends TreeToken> c) {
			boolean result = true;
			for (TreeToken t : c) {
				result = result && this.add(t);
			}
			return result;
		}

		@Override
		public boolean remove(Object o) {
			TreeToken t = (TreeToken)o;
			this.ByStartIndex.get(t.StartPosition).remove(o);
			this.ByEndIndex.get(t.EndPosition).remove(o);
			return this.remove(o);
		}


		/**
		 * Parse input token list and build a token tree.
		 * @return Self for method chaining.
		 */
		public TokenTree ParseAndBuild() {
			if (this.InputList.size() > 0) {
				// Create a default state
				LiveScriptParserState state = new LiveScriptParserState(LiveScriptTypes.None, this.InputList);

				List<TreeToken> newTokens = state.ParseInput().GiveAddedTokens();

				// Implicit lists with only one element are not actually lists, so we need
				// to remove them and only leave the element as a simple value
				for (int a = newTokens.size()-1; a >= 0; a--) {
					if (newTokens.get(a).Type == LiveScriptTypes.ImplicitList
						&& newTokens.get(a).ElementCount < 2)
					{
						newTokens.remove(a);
					}
				}

				this.addAll(newTokens);
			}

			return this;
		}

		/**
		 * Go through the tree and set the markers on a PSI builder.
		 * @param builder Builder to set the markers on.
		 * @return Self for method chaining.
		 */
		public TokenTree SetMarkersOn(PsiBuilder builder) {
			while (!builder.eof()) {
				List<TreeToken> tokens;
				IElementType tokenType = builder.getTokenType();

				// Mark starting positions
				tokens = this.ByStartIndex.get(builder.getCurrentOffset());
				if (tokens != null) {
					// Markers must be set in reverse order because the marker that's added last must be closed first.
					for (int a = tokens.size()-1; a >= 0; a--) {

						// If a token's type matches the type of the element that it wraps, it means that it's not
						// actually a wrapping "parent" token, so we don't need to set any markers
						if (tokens.get(a).Type == tokenType)
							continue;

						tokens.get(a).Marker = builder.mark();
					}
				}

				// marker.done() ends the *previous* token so we must advance lexer here, after starting new
				// markers and before closing the old ones.
				builder.advanceLexer();

				tokens = this.ByEndIndex.get(builder.getCurrentOffset());

				if (tokens != null) {
					for (TreeToken token : tokens) {
						if (token.Type == tokenType)
							continue;
						if (token.Type == TokenType.ERROR_ELEMENT)
							token.Marker.error(token.ErrorMessage);
						else
							token.Marker.done(token.Type);
					}
				}
			}

			return this;
		}
	}

	@NotNull
	@Override
	public ASTNode parse(IElementType root, PsiBuilder builder) {
		builder.setDebugMode(true);

		PsiBuilder.Marker rootMarker = builder.mark();

		// First, read all the tokens into a list that we can then work with
		List<TreeToken> tokens = new ArrayList<TreeToken>();

		while (!builder.eof()) {
			TreeToken tt = new TreeToken();
			tt.StartPosition = builder.getCurrentOffset();
			tt.Type = builder.getTokenType();
			tt.Text = builder.getTokenText();
			tokens.add(tt);
			builder.advanceLexer();

			tt.EndPosition = builder.getCurrentOffset();
		}

		// Have to roll back because we've already gone through the builder
		// once and are at the end of the input stream.
		rootMarker.rollbackTo();
		rootMarker = builder.mark();

		TokenTree tree = new TokenTree(tokens);

		tree.ParseAndBuild().SetMarkersOn(builder);

		rootMarker.done(LiveScriptParserDefinition.FILE);

		return builder.getTreeBuilt();
	}
}