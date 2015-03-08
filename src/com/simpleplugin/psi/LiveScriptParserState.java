package com.simpleplugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptParser.TokenTree;
import com.simpleplugin.psi.LiveScriptParser.TreeToken;

import java.util.Stack;

public class LiveScriptParserState {
	public enum Types {
		Default,
		Error,

		Literal,
		Sum,

		CallArg,
		ArgList,

		AssignOp,
	}

	/**
	 * Parse states who's end condition does not look at the next token.
	 */
	public static Types[] SingleTokenStates = new Types[] {
		Types.Literal,
	};

	/**
	 * Type of this state.
	 */
	public Types Type;

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
	 * A state that <tt>IsSingleTokenState</tt> is a state that ends with the same
	 * token that it starts with.
	 * For example: "Literals" state, which "wraps" a single number, string or
	 * reserved value token.
	 */
	public boolean IsSingleTokenState = false;

	/**
	 * Error message, if any.
	 */
	public String ErrorMessage;

	/**
	 * Constructor.
	 */
	public LiveScriptParserState(TokenTree tokenTree, Types type) {
		ParserTree = tokenTree;
		Type = type;
		for (Types t : SingleTokenStates) {
			if (t == Type) {
				IsSingleTokenState = true;
				break;
			}
		}

		TokenStack = new Stack<TreeToken>();
	}


	public LiveScriptParserState(TokenTree tokenTree, Types type, int position, IElementType result) {
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
		LiveScriptParserState newState = this.IsSingleTokenState
			? null
			: StartStateIfNeeded(token, nextToken);

		if (newState != null && NewState.IsSingleTokenState)
			ParserTree.ParseTokenIndex--;

		if (newState == null) {
			TreeToken endState = EndStateIfNeeded(token, nextToken);

			if (endState == null) {
				TokenStack.push(token);
			}

			return endState;
		}

		return null;
	}


	/**
	 * Start a new state if necessary.
	 * @param token Current token.
	 * @param nextToken Next token (lookahead).
	 * @return <tt>true</tt> if a new state was started, <tt>false</tt> otherwise.
	 */
	private LiveScriptParserState StartStateIfNeeded(TreeToken token, TreeToken nextToken) {
		if (this.Type == Types.Error)
			return null;

		// Bad char
		if (token.TypeIsOneOf(TokenType.BAD_CHARACTER)) {
			return StartBadCharState(token);
		}

		// All the single-token states have been checked
		if (this.IsSingleTokenState)
			return null;

		// Sum
		if (this.Type != Types.Sum
			&& token.TypeIsOneOf(LiveScriptTypes.VALUE, LiveScriptTypes.SUM)
			&& nextToken.TypeIsOneOf(LiveScriptTypes.PLUS))
		{
			return StartState(Types.Sum, token, LiveScriptTypes.SUM);
		}

		// Assignment
		if (token.Type == LiveScriptTypes.IDENTIFIER && nextToken.TypeIsOneOf(LiveScriptTypes.ASSIGN))
			return StartState(Types.AssignOp, token, LiveScriptTypes.ASSIGN_OPERATION);


		// Function call (with arguments, without parenthesis)
		if (token.TypeIsOneOf(LiveScriptTypes.IDENTIFIER)
			&& !nextToken.TypeIsOneOf(LiveScriptTypes.OPERATOR)
			&& !IsEndOfStatement(token, nextToken))
		{
			// This type of function call is always followed by arguments,
			// so the argument list state starts at the same position as the function call state.
			StartState(Types.CallArg, token, LiveScriptTypes.CALL_ARG);
			return StartNextState(Types.ArgList, token, nextToken, LiveScriptTypes.ARGUMENT_LIST);
		}

		return null;
	}


	/**
	 * Ends the current state and creates a "parent" token if necessary.
	 * @param token Current token.
	 * @param nextToken Next token.
	 * @return New "parent" token if state ended, <tt>null</tt> otherwise.
	 */
	private TreeToken EndStateIfNeeded(TreeToken token, TreeToken nextToken) {
		boolean end = false;
		ParseError error = null;
		try {
			end = this.IsSingleTokenState || IsEndOfState(token, nextToken);
		} catch (ParseError e) {
			this.Type = Types.Error;
			this.Result = TokenType.ERROR_ELEMENT;
			this.ErrorMessage = e.getMessage();
		}

		if (end) {
			// When a state has been completed, we must:

			// 1) Add the current token to the stack
			if (this.IsSingleTokenState)
				token = TokenStack.peek();
			else
				TokenStack.push(token);


			// 2) Rewind the parse position to the start of the match.
			ParserTree.ParseTokenIndex -= TokenStack.size();

			// 3) Create the "parent" token that wil encompass all tokens in the state
			// If there was an error, the parent token must be of ErrorElement type.
			TreeToken parent = new TreeToken();
			parent.EndPosition = token.EndPosition;
			parent.Type = Result;
			if (parent.Type == TokenType.ERROR_ELEMENT)
				parent.Error = this.ErrorMessage;

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
		return null;
	}


	/**
	 * Check if an end of state is in order for a given token.
	 * @param token Current token.
	 * @param nextToken Next token.
	 * @return <tt>true</tt> if the state should be ended, <tt>false</tt> otherwise.
	 */
	public boolean IsEndOfState(TreeToken token, TreeToken nextToken) {
		switch (Type) {
			case Error:
				return IsEndOfStatement(token, nextToken);
			case Sum:
				if (token.TypeIsOneOf(LiveScriptTypes.PLUS))
					return false;
				if (token.TypeIsOneOf(LiveScriptTypes.VALUE, LiveScriptTypes.SUM))
					return true;
				throw new ParseError("Invalid sum expression.");
			case ArgList:
				if (nextToken.TypeIsOneOf(LiveScriptTypes.COMMA, LiveScriptTypes.VALUE))
					return false;
				else if (!this.TokenStack.empty()) {
					if (this.TokenStack.size() == 1)
						this.IsSingleTokenState = true;
					return true;
				}
				throw new ParseError("Invalid argument list.");
			case CallArg:
				if (token.TypeIsOneOf(LiveScriptTypes.ARGUMENT_LIST)
					&& IsEndOfStatement(token, nextToken))
				{
					return true;
				}
				throw new ParseError("Invalid function call.");
			case AssignOp:
				if (this.TokenStack.size() == 1 && token.Type == LiveScriptTypes.ASSIGN)
					return false;
				if (token.TypeIsOneOf(LiveScriptTypes.SUM, LiveScriptTypes.VALUE))
					return true;
				throw new ParseError("Invalid assign expression.");
			default: return false;
		}
	}


	/**
	 * Check if the current token is the last in a statement.
	 * @param token Current token.
	 * @param nextToken Next token (lookahead).
	 * @return <tt>true</tt> if the current token is last in a statement, <tt>false</tt> otherwise.
	 */
	private boolean IsEndOfStatement(TreeToken token, TreeToken nextToken) {
		return nextToken.TypeIsOneOf(
			LiveScriptTypes.SEMICOLON,
			LiveScriptTypes.EOF,
			LiveScriptTypes.NEWLINE,
			LiveScriptTypes.PAREN_R
		);
	}


	/**
	 * Start a new state.
	 * @param type State type.
	 * @param token Token that the new state starts with.
	 * @param result New state's result token type.
	 * @return The newly entered state.
	 */
	public LiveScriptParserState StartState(Types type, TreeToken token, IElementType result) {
		ParserTree.StateStack.push(this);
		NewState = new LiveScriptParserState(ParserTree, type, token.StartPosition, result);
		NewState.TokenStack.push(token);

		return NewState;
	}

	/**
	 * Start a new state immediately after one has already been started, at the same token position.
	 * Will use the new state (instead of <tt>this</tt>) as the "current" state to push to stack and
	 * push the next token (instead of current) to the new state's token stack.
	 * @param type State type.
	 * @param token Current token.
	 * @param nextToken Next token.
	 * @param result Resulting staet's token type.
	 * @return The newly entered state.
	 */
	public LiveScriptParserState StartNextState(Types type, TreeToken token, TreeToken nextToken, IElementType result) {
		ParserTree.StateStack.push(NewState);
		NewState = new LiveScriptParserState(ParserTree, type, token.StartPosition, result);
		NewState.TokenStack.push(nextToken);
		return NewState;
	}

	public LiveScriptParserState StartBadCharState(TreeToken token) {
		StartState(Types.Error, token, TokenType.ERROR_ELEMENT);
		NewState.IsSingleTokenState = true;
		NewState.ErrorMessage = "Unrecognized character.";
		return NewState;
	}


	@Override
	public String toString() {
		return Type.toString();
	}
}
