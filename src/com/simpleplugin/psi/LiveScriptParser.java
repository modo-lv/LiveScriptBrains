package com.simpleplugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LiveScriptParser implements PsiParser {

	/**
	 * A token in the token tree.
	 */
	public static class TreeToken {
		/**
		 * Token index of this token in the Lexer token stream.
		 */
		public int StartPosition;

		/**
		 * Token index in the Lexer token stream where this token ends.
		 * Used for parent node tokens.
		 */
		public int EndPosition;

		/**
		 * Token element type.
		 */
		public IElementType Type;

		/**
		 * Marker for this particular token when in the token tree.
		 */
		public PsiBuilder.Marker Marker;

		/**
		 * Token text.
		 */
		public String Text;

		/**
		 * Error text in case of error.
		 */
		public String ErrorMessage;

		public TreeToken() {
		}

		public TreeToken(IElementType type) {
			Type = type;
		}

		@Override
		public String toString() {
			return Type.toString();
		}

		/**
		 * Check if the type of this token is the same ase one of the given types.
		 * @param types Types to check against.
		 * @return <tt>true</tt> if a match was found, <tt>false</tt> otherwise.
		 */
		public boolean TypeIsOneOf(IElementType... types) {
			boolean result = false;
			for (IElementType type : types) {
				if (!result && type == LiveScriptTypes.Separator)
					result = this.TypeIsOneOf(LiveScriptTypes.COMMA, LiveScriptTypes.NEWLINE);
				if (!result && type == LiveScriptTypes.Value)
					result = this.TypeIsOneOf(LiveScriptTypes.IDENTIFIER, LiveScriptTypes.NUMBER, LiveScriptTypes.STRING, LiveScriptTypes.BOOLEAN, LiveScriptTypes.EMPTY);
				if (!result && type == LiveScriptTypes.LITERAL)
					result = this.TypeIsOneOf(LiveScriptTypes.STRING, LiveScriptTypes.BOOLEAN, LiveScriptTypes.NUMBER, LiveScriptTypes.EMPTY);
				if (!result && type == LiveScriptTypes.OPERATOR)
					result = this.TypeIsOneOf(LiveScriptTypes.PLUS, LiveScriptTypes.ASSIGN, LiveScriptTypes.MATH_OP);
				if (!result && type == LiveScriptTypes.Operation)
					result = Arrays.asList(LiveScriptTypes.OpElements).contains(this.Type);
				if (!result && type == this.Type)
					result = true;

				if (result)
					return true;
			}
			return false;
		}
	}

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


		/**
		 * Parse input token list and build a token tree.
		 * @return Self for method chaining.
		 */
		public TokenTree ParseAndBuild() {
			if (this.InputList.size() > 0) {
				// Create a default state
				LiveScriptParserState state = new LiveScriptParserState(LiveScriptTypes.None, this.InputList);

				List<TreeToken> newTokens = state.ParseInput().GiveAddedTokens();

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

				// Mark starting positions
				tokens = this.ByStartIndex.get(builder.getCurrentOffset());
				if (tokens != null) {
					// Markers must be set in reverse order because the marker that's added last must be closed first.
					for (int a = tokens.size()-1; a >= 0; a--) {
						tokens.get(a).Marker = builder.mark();
					}
				}

				// marker.done() ends the *previous* token so we must advance lexer here, after starting new
				// markers and before closing the old ones.
				builder.advanceLexer();

				tokens = this.ByEndIndex.get(builder.getCurrentOffset());

				if (tokens != null) {
					for (TreeToken token : tokens) {
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