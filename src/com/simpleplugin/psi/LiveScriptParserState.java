package com.simpleplugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptParser.TreeToken;
import com.sun.javafx.fxml.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class LiveScriptParserState {
	/**
	 * What type is this state.
	 */
	protected IElementType Type;

	/**
	 * List of tokens to parse.
	 */
	protected List<TreeToken> InputStream;

	/**
	 * Token index in the {@link #InputStream} at which the state wast started.
	 */
	protected int StartTokenIndex = 0;

	/**
	 * Token index in the {@link #InputStream} that we're currently parsing.
	 */
	protected int TokenIndex = 0;

	/**
	 * Currently parsing token.
	 */
	protected TreeToken ThisToken = null;

	/**
	 * Next token in the input stream.
	 */
	protected TreeToken NextToken = null;

	/**
	 * Additional tokens created during parsing.
	 */
	protected List<TreeToken> AddedTokens = new ArrayList<TreeToken>();


	/**
	 * @param type        {@link #Type}
	 * @param inputStream {@link #InputStream}
	 */
	public LiveScriptParserState(IElementType type, List<TreeToken> inputStream) {
		this(type, inputStream, 0);
	}

	/**
	 * @param type          {@link #Type}
	 * @param inputStream   {@link #InputStream}
	 * @param startTokenIndex {@link #StartTokenIndex}
	 */
	public LiveScriptParserState(IElementType type, List<TreeToken> inputStream, int startTokenIndex) {
		this.Type = type;
		this.InputStream = inputStream;
		this.StartTokenIndex = startTokenIndex;
		this.TokenIndex = startTokenIndex;
		this.MoveToToken(this.TokenIndex);
	}


	/**
	 * Give additional tokens created during parsing.
	 *
	 * @return List of additional tokens.
	 */
	public List<TreeToken> GiveAddedTokens() {
		return AddedTokens;
	}


	/**
	 * Parse from the current input token until the state is finished.
	 *
	 * @return Self for method chaining.
	 */
	public LiveScriptParserState ParseInput() {
		boolean end = false;
		String errorMessage = null;
		do {
			this.ParseToken();

			end = this.AtLastToken();

			try
			{
				end = this.EndReached();
			}
			catch (ParseError err) {
				errorMessage = err.getMessage();
				this.Type = TokenType.ERROR_ELEMENT;
			}

			if (this.AtLastToken() && !end) {
				end = true;
			}
			else if (end) {
				if (!this.AtEndOfStatement())
				{
					errorMessage = "Unexpected extra characters after the end of " + this.Type + " statement.";
					this.Type = TokenType.ERROR_ELEMENT;
					this.MoveToNextToken();
				}
				// When a state has been completed, we must:

				// *) Create the "parent" token that wil encompass all tokens in the state
				// If there was an error, the parent token must be of ErrorElement type.
				TreeToken parent = new TreeToken();
				parent.Type = this.Type;
				if (parent.Type == TokenType.ERROR_ELEMENT)
					parent.Error = errorMessage;

				// *) Determine the parent element's boundaries
				parent.StartPosition = this.InputStream.get(this.StartTokenIndex).StartPosition;
				parent.EndPosition = ThisToken.EndPosition;

				// *) Replace the encompassed tokens in the input stream with the parent token
				for (int a = this.TokenIndex; a >= this.StartTokenIndex; a--)
					this.InputStream.remove(a);
				this.InputStream.add(this.StartTokenIndex, parent);
				this.AddedTokens.add(parent);
			}
			else
			{
				this.MoveToNextToken();
			}
		} while (!end);

		return this;
	}

	/**
	 * Parse the token at the current position.
	 *
	 * @return Self for method chaining.
	 */
	protected LiveScriptParserState ParseToken() {
		LiveScriptParserState newState = null;

		// Sum
		if (this.Type != LiveScriptTypes.SumOp
			&& ThisToken.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.SumOp)
			&& NextToken.TypeIsOneOf(LiveScriptTypes.PLUS))
		{
			newState = this.NewState(LiveScriptTypes.SumOp);
		}

		if (newState != null) {
			// Run the new state's parse
			this.AddedTokens.addAll(newState.ParseInput().GiveAddedTokens());
		}

		return this;
	}

	/**
	 * Have we reached the end of this state?
	 * @return <tt>true</tt> if an end-of-state condition is met, <tt>false</tt> otherwise.
	 */
	protected boolean EndReached() {
		if (this.Type == LiveScriptTypes.SumOp)
		{
			if (ThisToken.TypeIsOneOf(LiveScriptTypes.PLUS))
				return false;
			if (ThisToken.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.SumOp))
			{
				return true;
			}
			throw new ParseError("Invalid sum expression.");
		}
		return false;

/*
		switch (Type) {
			case Error:
				return IsEndOfStatement(token, nextToken);
			case ArgList:
				if (nextToken.TypeIsOneOf(LiveScriptTypes.COMMA, LiveScriptTypes.Value))
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
				if (token.TypeIsOneOf(LiveScriptTypes.SumOp, LiveScriptTypes.Value))
					return true;
				throw new ParseError("Invalid assign expression.");
			default: return false;
		}
*/
	}

	/**
	 * Start a new state at the current token.
	 * @param type {@link #Type}
	 * @return The newly created state.
	 */
	protected LiveScriptParserState NewState(IElementType type) {
		return new LiveScriptParserState(type, this.InputStream, this.TokenIndex).MoveToNextToken();
	}


	/**
	 * Move on to the next token in the input stream.
	 *
	 * @return Self for method chaining.
	 */
	protected LiveScriptParserState MoveToNextToken() {
		return this.MoveToToken(this.TokenIndex + 1);
	}

	/**
	 * Move to a specific token in the input stream.
	 * @param index Index of the token to move to.
	 * @return Self for method chaining.
	 */
	protected LiveScriptParserState MoveToToken(int index) {
		this.TokenIndex = index;
		this.ThisToken = this.InputStream.get(this.TokenIndex);
		this.NextToken = this.InputStream.size() - this.TokenIndex > 1
			? this.InputStream.get(this.TokenIndex + 1)
			: new TreeToken(LiveScriptTypes.EOF);

		return this;
	}

	/**
	 * Check if {@link #ThisToken} is the last token in the input stream.
	 * @return <tt>true</tt> if at the last token of input stream, <tt>false</tt> otherwise.
	 */
	protected boolean AtLastToken() {
		return this.NextToken.TypeIsOneOf(LiveScriptTypes.EOF);
	}


	/**
	 * Check if {@link #ThisToken} is the last in a statement.
	 * @return <tt>true</tt> if it is, <tt>false</tt> otherwise.
	 */
	protected boolean AtEndOfStatement() {
		return this.NextToken.TypeIsOneOf(
			LiveScriptTypes.SEMICOLON,
			LiveScriptTypes.EOF,
			LiveScriptTypes.NEWLINE,
			LiveScriptTypes.PAREN_R
		);
	}



	/*

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
			&& token.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.SumOp)
			&& nextToken.TypeIsOneOf(LiveScriptTypes.PLUS))
		{
			return StartState(Types.Sum, token, LiveScriptTypes.SumOp);
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


	*/
/**
 * Ends the current state and creates a "parent" token if necessary.
 * @param token Current token.
 * @param nextToken Next token.
 * @return New "parent" token if state ended, <tt>null</tt> otherwise.
 *//*

	private TreeToken EndStateIfNeeded(TreeToken token, TreeToken nextToken) {
		return null;
	}


	*/
/**
 * Check if an end of state is in order for a given token.
 * @param token Current token.
 * @param nextToken Next token.
 * @return <tt>true</tt> if the state should be ended, <tt>false</tt> otherwise.
 *//*

	public boolean IsEndOfState(TreeToken token, TreeToken nextToken) {
	}

	*/
/**
 * Start a new state.
 * @param type State type.
 * @param token Token that the new state starts with.
 * @param result New state's result token type.
 * @return The newly entered state.
 *//*

	public LiveScriptParserState StartState(Types type, TreeToken token, IElementType result) {
		ParserTree.StateStack.push(this);
		NewState = new LiveScriptParserState(ParserTree, type, token.StartPosition, result);
		NewState.TokenStack.push(token);

		return NewState;
	}

	*/
/**
 * Start a new state immediately after one has already been started, at the same token position.
 * Will use the new state (instead of <tt>this</tt>) as the "current" state to push to stack and
 * push the next token (instead of current) to the new state's token stack.
 * @param type State type.
 * @param token Current token.
 * @param nextToken Next token.
 * @param result Resulting staet's token type.
 * @return The newly entered state.
 *//*

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
*/
}
