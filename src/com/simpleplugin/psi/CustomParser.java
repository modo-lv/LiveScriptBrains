package com.simpleplugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.WhitespacesAndCommentsBinder;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.GroupedElementsRenderer;
import jdk.internal.util.xml.impl.Input;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CustomParser implements PsiParser {
	/**
	 * A token in the token tree.
	 */
	public class TreeToken {
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
	}

	/**
	 * A tree of tokens and sub-tokens parsed from the lexer input.
	 */
	public class TokenTree extends ArrayList<TreeToken> {
		public Map<Integer, List<TreeToken>> ByStartIndex = new HashMap<Integer, List<TreeToken>>();
		public Map<Integer, List<TreeToken>> ByEndIndex = new HashMap<Integer, List<TreeToken>>();
		public List<TreeToken> InputList;

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

		/**
		 * Parse input token list and build a token tree.
		 * @return Self for method chaining.
		 */
		public TokenTree ParseAndBuild() {
			LiveScriptParserRules rules = new LiveScriptParserRules();

			List<TreeToken> parsed = new ArrayList<TreeToken>();
			for (LiveScriptParserRules.Rule rule : rules) {
				while (RuleParsed(rule));
			}

			return this;
		}

		/**
		 * Parse a specific rule and update this tree accordingly.
		 * @param rule Rule to parse.
		 * @return true if at least one match was found and parsed, false otherwise.
		 */
		public boolean RuleParsed(LiveScriptParserRules.Rule rule) {
			Queue<TreeToken> queue = new LinkedList<TreeToken>();
			for (TreeToken token : InputList) {
				if (token.Type == rule.TokenTypes.get(queue.size()) || (token.Type == rule.Result && rule.TokenTypes.size() > 1)) {
					queue.add(token);

					// do we have a full match?
					if (queue.size() == rule.TokenTypes.size()) {
						// Create a new token that encompasses the matched chain
						// and remove the individual tokens from the input (replace
						// by the "parent" token.

						TreeToken t = new TreeToken();
						t.Type = rule.Result;

						TreeToken first = queue.remove();

						InputList.add(InputList.indexOf(first), t);

						t.StartPosition = first.StartPosition;
						InputList.remove(first);
						while (queue.size() > 1) {
							InputList.remove(queue.remove());
						}

						if (queue.size() > 0) {
							TreeToken last = queue.remove();
							t.EndPosition = last.EndPosition;
							InputList.remove(last);
						}
						else
							t.EndPosition = first.EndPosition;

						this.add(t);
						return true;
					}
				}
				else {
					// If even a single token does not match, it breaks the
					// match chain — discard the chain and start over.
					queue.clear();
				}
			}
			return false;
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

				tokens = this.ByEndIndex.get(builder.getCurrentOffset());

				// marker.done() ends the *previous* token so we must advance lexer here, after starting new
				// markers and before closing the old ones.
				builder.advanceLexer();

				if (tokens != null) {
					for (TreeToken token : tokens) {
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
			tt.EndPosition = tt.StartPosition;
			tt.Type = builder.getTokenType();
			tokens.add(tt);
			builder.advanceLexer();
		}

		// Have to roll back because we've already gone through the builder
		// once and are at the end of the input stream.
		rootMarker.rollbackTo();
		rootMarker = builder.mark();

		TokenTree tree = new TokenTree(tokens);

		tree.ParseAndBuild().SetMarkersOn(builder);

		rootMarker.done(LiveScriptTypes.UNKNOWN);

		return builder.getTreeBuilt();
	}
}