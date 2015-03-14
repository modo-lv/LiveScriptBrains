package com.simpleplugin.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptParser.TreeToken;
import org.apache.velocity.runtime.directive.Parse;

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
	 * Used on {@link LiveScriptTypes#None} to check for leftover tokens on a statement line
	 * after the last of the states have been parsed.
	 */
	protected boolean StatementFinished = false;

	/**
	 * How many spaces/tabs this state wast started at.
	 */
	private int IndentSize;


	/**
	 * @param type        {@link #Type}
	 * @param inputStream {@link #InputStream}
	 */
	public LiveScriptParserState(IElementType type, List<TreeToken> inputStream) {
		this(type, inputStream, 0);
	}

	/**
	 * @param type            {@link #Type}
	 * @param inputStream     {@link #InputStream}
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
	 * Go through the input, parsing tokens.
	 *
	 * @return Self for method chaining.
	 */
	public LiveScriptParserState ParseInput() {
		boolean end = false;
		do {
			TreeToken errorToken = new TreeToken(TokenType.ERROR_ELEMENT);

			this.ParseToken();

			// Refresh current/next token references, in case parser has changed the tokens
			this.MoveToToken(this.TokenIndex);

			// *) Check for normal state finishers.
			try {
				end = this.EndReached();
			}
			catch (ParseError err) {
				errorToken.ErrorMessage = err.getMessage();
			}

			// Error: Bad character
			if (this.Type == TokenType.ERROR_ELEMENT
				&& this.ThisToken.TypeIsOneOf(TokenType.BAD_CHARACTER))
			{
				errorToken.ErrorMessage = "Unrecognized character.";
				end = true;
			}

			// Error: A statement has been parsed and finished, but there are more tokens after it.
			if (this.Type == LiveScriptTypes.None
				&& this.StatementFinished
				&& !end
				&& !this.ThisToken.TypeIsOneOf(TokenType.ERROR_ELEMENT)
				&& !this.IsEndOfStatement())
			{
				errorToken.ErrorMessage = "Stray token after a statement.";
				this.StartTokenIndex = this.TokenIndex;
				end = true;
			}


			// Error: Parser state is not done, but we've reached the end of the input tokens for the statement
			if (this.Type != LiveScriptTypes.None
				&& this.Type != TokenType.ERROR_ELEMENT
				&& this.AtEndOfStatement()
				&& !end)
			{
				this.StartTokenIndex = this.TokenIndex;
				errorToken.ErrorMessage = "Unexpected end to " + this.Type + " statement";
				end = true;
			}


			if (end) {
				// When a state has been completed, we must:

				// *) Create the "parent" token that wil encompass all tokens in the state
				// If there was an error, the parent token must be of ErrorElement type.
				TreeToken parent = new TreeToken();

				if (errorToken.ErrorMessage != null) {
					parent = errorToken;
				} else {
					parent.Type = this.Type;
				}

				// *) Determine the parent element's boundaries
				parent.StartPosition = this.InputStream.get(this.StartTokenIndex).StartPosition;
				parent.EndPosition = ThisToken.EndPosition;

				// *) Replace the encompassed tokens in the input stream with the parent token
				for (int a = this.TokenIndex; a >= this.StartTokenIndex; a--)
					this.InputStream.remove(a);
				this.InputStream.add(this.StartTokenIndex, parent);
				this.AddedTokens.add(parent);
			}


			// Set the end-of-statement marker so that any following tokens are recognized as strays,
			// and clear it on newlines.
			if (this.Type == LiveScriptTypes.None && !this.ThisToken.TypeIsOneOf(TokenType.ERROR_ELEMENT))
				this.StatementFinished = !this.IsEndOfStatement();


			// *) No matter what happens, we must end the parsing if we've reached the end of the input stream.
			end = this.AtLastToken() || (end && this.Type != LiveScriptTypes.None);

			if (!end) {
				this.MoveToNextToken();
			}

		} while (!end);

		return this;
	}


	/**
	 * Parse the token at the current input position, starting new parser states as needed.
	 *
	 * @return Self for method chaining.
	 */
	protected LiveScriptParserState ParseToken() {
		LiveScriptParserState newState = null;

		// Bad char
		// For some reason, unless it's wrapped in an error element, a BAD_CHARACTER
		// will eliminate all following markers, effectively stopping the parsed results from displaying
		if (this.Type != TokenType.ERROR_ELEMENT && this.ThisToken.TypeIsOneOf(TokenType.BAD_CHARACTER)) {
			// this.NewState moves the input token marker forward, but in case of bad char we want to keep it
			// at the same position so that it can end on the same token it starts.
			newState = this.NewState(TokenType.ERROR_ELEMENT, false);
		}

		else

		// Block
		if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.INDENT)
			&& this.ThisToken.Text.length() > this.IndentSize)
		{
			newState = this.NewState(LiveScriptTypes.Block);
			newState.IndentSize = this.ThisToken.Text.length();
		}

		else

		// Parenthesis
		if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.PAREN_L)) {
			newState = this.NewState(LiveScriptTypes.ParenOp);
		}

		else

		// Parameter-less function call means — don't check for anything else
		if (this.Type == LiveScriptTypes.FuncCall
			&& this.ThisToken.TypeIsOneOf(LiveScriptTypes.BANG))
		{
			return this;
		}

		else

		// Sum
		if (NextToken.TypeIsOneOf(LiveScriptTypes.PLUS)
			&& this.Type != LiveScriptTypes.SumOp
			&& ThisToken.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.SumOp))
		{
			newState = this.NewState(LiveScriptTypes.SumOp);
		}

		else

		// Assignment
		if (ThisToken.TypeIsOneOf(LiveScriptTypes.IDENTIFIER) && NextToken.TypeIsOneOf(LiveScriptTypes.ASSIGN))
			newState = this.NewState(LiveScriptTypes.ASSIGN_OPERATION);

		else

		// Function argument list
		if (this.Type == LiveScriptTypes.FuncCall) {
			newState = this.NewState(LiveScriptTypes.FuncArgList, false);
		}

		else

		// Function call with arguments
		if (this.Type != LiveScriptTypes.FuncCall
			&& this.ThisToken.TypeIsOneOf(LiveScriptTypes.IDENTIFIER)
			&& !this.AtEndOfStatement()
			&& !this.NextToken.TypeIsOneOf(LiveScriptTypes.COMMA))
		{
			newState = this.NewState(LiveScriptTypes.FuncCall);
		}



		if (newState != null) {
			// Run the new state's parse
			this.AddedTokens.addAll(newState.ParseInput().GiveAddedTokens());
		}

		return this;
	}


	/**
	 * Check if we are at and end of a parser state and should close it.
	 *
	 * @return <tt>true</tt> if an end-of-state condition is met, <tt>false</tt> otherwise.
	 */
	protected boolean EndReached() {
		// Expression with parenthesis
		if (this.Type == LiveScriptTypes.ParenOp) {
			return this.ThisToken.TypeIsOneOf(LiveScriptTypes.PAREN_R);
		}

		// Sum expression
		if (this.Type == LiveScriptTypes.SumOp) {
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.PLUS))
				return false;
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.SumOp)) {
				return true;
			}
			throw new ParseError("Invalid sum expression.");
		}

		// Assignment expression
		if (this.Type == LiveScriptTypes.ASSIGN_OPERATION) {
			if (this.ThisToken.Type == LiveScriptTypes.ASSIGN)
				return false;
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.SumOp, LiveScriptTypes.Value))
				return true;
			throw new ParseError("Invalid assign expression.");
		}

		// List definition expression
		if (this.Type == LiveScriptTypes.List) {
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.LIST_END))
				return true;
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.ARGUMENT_LIST))
				return false;
			throw new ParseError("Invalid list expression");
		}

		// Argument list
		if (this.Type == LiveScriptTypes.FuncArgList) {
			if ((this.ThisToken.TypeIsOneOf(LiveScriptTypes.Value) && this.NextToken.TypeIsOneOf(LiveScriptTypes.COMMA))
				|| (this.ThisToken.TypeIsOneOf(LiveScriptTypes.COMMA) && this.NextToken.TypeIsOneOf(LiveScriptTypes.Value, LiveScriptTypes.COMMA))) {
				return false;
			}
			return true;
		}

		// Function call
		if (this.Type == LiveScriptTypes.FuncCall) {
			if (this.ThisToken.TypeIsOneOf(LiveScriptTypes.BANG, LiveScriptTypes.FuncArgList))
				return true;
			throw new ParseError("Invalid function call");
		}

		else

		// Block statement
		if (this.Type == LiveScriptTypes.Block) {
			if (!this.NextToken.TypeIsOneOf(LiveScriptTypes.INDENT)
				|| this.NextToken.Text.length() < this.IndentSize
				|| this.NextToken.TypeIsOneOf(LiveScriptTypes.EOF))
			{
				return true;
			}
			return false;
		}


		return false;
	}

	/**
	 * Start a new state at the current token.
	 *
	 * @param type {@link #Type}
	 * @param moveToNextToken After creating the new state, should its token index be moved forward once?
	 * @return The newly created state.
	 */
	protected LiveScriptParserState NewState(IElementType type, boolean moveToNextToken) {
		LiveScriptParserState ns = new LiveScriptParserState(type, this.InputStream, this.TokenIndex);
		if (moveToNextToken)
			ns.MoveToNextToken();
		return ns;
	}

	protected LiveScriptParserState NewState(IElementType type) {
		return this.NewState(type, true);
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
	 *
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
	 *
	 * @return <tt>true</tt> if at the last token of input stream, <tt>false</tt> otherwise.
	 */
	protected boolean AtLastToken() {
		return this.NextToken.TypeIsOneOf(LiveScriptTypes.EOF);
	}


	/**
	 * Check if {@link #ThisToken} is the last token in a statement.
	 *
	 * @return <tt>true</tt> if it is, <tt>false</tt> otherwise.
	 */
	protected boolean AtEndOfStatement() {
		return this.IsEndOfStatement(this.NextToken);
	}

	/**
	 * Check if <em>token</em> is an end-of-statement token.
	 *
	 * @param token Token to check.
	 * @return
	 */
	protected boolean IsEndOfStatement(TreeToken token) {
		boolean result = token.TypeIsOneOf(
			LiveScriptTypes.SEMICOLON,
			LiveScriptTypes.EOF
		);

		if (this.Type != LiveScriptTypes.ParenOp)
			result = result || token.TypeIsOneOf(LiveScriptTypes.PAREN_R);

		if (this.Type != LiveScriptTypes.ARGUMENT_LIST)
			result = result || token.TypeIsOneOf(LiveScriptTypes.NEWLINE);

		return result;
	}

	/**
	 * Check if current token is an end-of-statement token.
	 * @return
	 */
	protected boolean IsEndOfStatement() {
		return this.IsEndOfStatement(this.ThisToken);
	}
}
