package com.simpleplugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;
import com.simpleplugin.psi.CustomParser.*;

public class LiveScriptParserState {
	public enum StateTypes {
		Default,
		MathOp,
	}

	/**
	 * Type of this state.
	 */
	public StateTypes Type;

	/**
	 * The state to switch to after this one has parsed a token.
	 */
	public LiveScriptParserState NewState;

	/**
	 * Position where the state started at.
	 */
	public int Position;

	public Stack<TreeToken> TokenStack;

	/**
	 * The resulting element type
	 */
	public IElementType Result;

	/**
	 * ParserTree that this state is for.
	 */
	public TokenTree ParserTree;

	/**
	 * Constructor.
	 */
	public LiveScriptParserState(TokenTree tokenTree, StateTypes type) {
		ParserTree = tokenTree;
		Type = type;
		TokenStack = new Stack<TreeToken>();
	}

	public LiveScriptParserState(TokenTree tokenTree, StateTypes type, int position, IElementType result) {
		this(tokenTree, type);
		Position = position;
		Result = result;
	}

	/**
	 * Parse a token and return the token that should replace it, or <tt>null</tt> if it should not be replaced.
	 * @param token Token to parse.
	 * @param nextToken Next token that will be parsed (look-ahead).
	 * @return Replacement token or <tt>null</tt>.
	 */
	public TreeToken ParseToken(TreeToken token, TreeToken nextToken) {
/*
		try {
			if (EndOfState(token, nextToken)) {

				NewState = ParserTree.StateStack.pop();

				// Merge current token stack into the completed state
				TreeToken newToken = new TreeToken();
				newToken.StartPosition = this.Position;
				newToken.EndPosition = token.EndPosition;
				newToken.Type = this.Result;

				// Move parse position to the merged token
				ParserTree.ParseTokenIndex -= TokenStack.size() + 1;

				// Remove merged tokens from the input list.
				while (!TokenStack.empty()) {
					ParserTree.InputList.remove(TokenStack.pop());
				}

				return newToken;
			}
		}
		catch (Error e) {
			TreeToken t = new TreeToken();
			t.StartPosition = this.Position;
			t.EndPosition = this.Position;
			t.Type = TokenType.ERROR_ELEMENT;
			t.Error = e.getMessage();
			return t;
		}

		if (token.Type == LiveScriptTypes.NUMBER && nextToken.Type == LiveScriptTypes.MATH_OP) {
			EnterState(StateTypes.MathOp, token, LiveScriptTypes.MATH_OPERATION);
		}
		else
			TokenStack.push(token);
*/
		return null;
	}

	public boolean EndOfState(TreeToken token, TreeToken nextToken) {
		switch (Type) {
			case MathOp:
				if (nextToken == null)
					throw new Error("Incomplete math operation.");
				if (nextToken.Type != LiveScriptTypes.NUMBER)
					return true;
			default: return false;
		}
	}

	public void EnterState(StateTypes type, TreeToken token, IElementType result) {
		ParserTree.StateStack.push(this);
		NewState = new LiveScriptParserState(ParserTree, type, token.StartPosition, result);
		NewState.TokenStack.push(token);
	}

	@Override
	public String toString() {
		return Type.toString();
	}
}
