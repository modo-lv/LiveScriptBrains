package com.simpleplugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;

import com.intellij.ui.GroupedElementsRenderer;
import com.simpleplugin.psi.CustomParser.*;
import com.sun.javafx.fxml.expression.Expression;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import org.apache.velocity.runtime.directive.Parse;

public class LiveScriptParserState {
	public enum StateTypes {
		Default,
		MathOp,
		AssignOp
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
		boolean end = false;
		ParseError error = null;
		try {
			end = EndOfState(token, nextToken);
		}
		catch (ParseError e) {
			end = true;
			error = e;
		}

		if (end) {
			// When a state has been completed, we must:

			// 1) Add the current token to the stack
			TokenStack.push(token);

			// 2) Rewind the parse position to the start of the match.
			ParserTree.ParseTokenIndex -= TokenStack.size();

			// 3) Create the "parent" token that wil encompass all tokens in the state
			// If there was an error, the parent token must be of ErrorElement type.
			TreeToken parent = new TreeToken();
			parent.EndPosition = token.EndPosition;
			parent.Type = error == null ? Result : TokenType.ERROR_ELEMENT;
			if (error != null)
				parent.Error = error.getMessage();

			// 4) Replace the encompassed tokens in the input stream with the "parent" token
			// (skip the last one because we need it for determining parent start pos.)
			while (TokenStack.size() > 1)
				ParserTree.InputList.remove(TokenStack.pop());
			ParserTree.InputList.add(ParserTree.ParseTokenIndex + 1, parent);

			// 5) Determine parent start position
			TreeToken lastToken = TokenStack.pop();
			parent.StartPosition = lastToken.StartPosition;
			ParserTree.InputList.remove(lastToken);

			// 6) Exit the state and let the parser know to go back to previous one
			NewState = ParserTree.StateStack.pop();

			return parent;
		}

		if (token.TypeIsOneOf(LiveScriptTypes.NUMBER, LiveScriptTypes.MATH_OPERATION) && nextToken.Type == LiveScriptTypes.MATH_OP) {
			EnterState(StateTypes.MathOp, token, LiveScriptTypes.MATH_OPERATION);
		}
		else if (token.Type == LiveScriptTypes.IDENTIFIER && nextToken.TypeIsOneOf(LiveScriptTypes.ASSIGN)) {
			EnterState(StateTypes.AssignOp, token, LiveScriptTypes.ASSIGN_OPERATION);
		}
		else
			TokenStack.push(token);
		return null;
	}

	public boolean EndOfState(TreeToken token, TreeToken nextToken) {
		switch (Type) {
			case MathOp:
				if (nextToken.Type == LiveScriptTypes.EOF) {
					// If the current token is a number, everything's fine
					if (token.Type == LiveScriptTypes.NUMBER)
						return true;
					// If not, it means we're at the "+" sign and there is no more input to parse.
					throw new ParseError("Incomplete math operation.");
				}
				return (nextToken.Type != LiveScriptTypes.NUMBER);
			case AssignOp:
				if (nextToken.Type == LiveScriptTypes.EOF) {
					if (token.TypeIsOneOf(LiveScriptTypes.MATH_OPERATION, LiveScriptTypes.ASSIGN_OPERATION))
						return true;
					throw new ParseError("Incomplete assign operation.");
				}
				return (nextToken.Type == LiveScriptTypes.NEWLINE);
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
