/*
package lv.modo.livescriptbrains.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.lexer.LexerTokens;

import java.util.ArrayList;
import java.util.List;

public class LiveScriptParserState {
	*/
/**
	 * What type is this state.
	 *//*

	protected IElementType Type;

	*/
/**
	 * List of tokens to parse.
	 *//*

	protected List<TreeToken> InputStream;

	*/
/**
	 * Token index in the {@link #InputStream} at which the state wast started.
	 *//*

	protected int StartTokenIndex = 0;

	*/
/**
	 * Token index in the {@link #InputStream} that we're currently parsing.
	 *//*

	protected int TokenIndex = 0;

	*/
/**
	 * Currently parsing token.
	 *//*

	protected TreeToken ThisToken = null;

	*/
/**
	 * Next token in the input stream.
	 *//*

	protected TreeToken NextToken = null;

	*/
/**
	 * Additional tokens created during parsing.
	 *//*

	protected List<TreeToken> AddedTokens = new ArrayList<TreeToken>();

	*/
/**
	 * Used on {@link LexerTokens#None} to check for leftover tokens on a statement line
	 * after the last of the states have been parsed.
	 *//*

	protected boolean StatementFinished = false;

	*/
/**
	 * How many spaces/tabs this state wast started at.
	 *//*

	private int IndentSize;

	*/
/**
	 * Tracker of how many elements are in this state.
	 * Currently only used in ImplicitList to figure out alter if it's really a list
	 * or just a value (i.e., has a single element).
	 *//*

	private int ElementCount = 0;


	*/
/**
	 * @param type        {@link #Type}
	 * @param inputStream {@link #InputStream}
	 *//*

	public LiveScriptParserState(IElementType type, List<TreeToken> inputStream) {
		this(type, inputStream, 0);
	}

	*/
/**
	 * @param type            {@link #Type}
	 * @param inputStream     {@link #InputStream}
	 * @param startTokenIndex {@link #StartTokenIndex}
	 *//*

	public LiveScriptParserState(IElementType type, List<TreeToken> inputStream, int startTokenIndex) {
		this.Type = type;
		this.InputStream = inputStream;
		this.StartTokenIndex = startTokenIndex;
		this.TokenIndex = startTokenIndex;
		this.MoveToToken(this.TokenIndex);
	}


	*/
/**
	 * Give additional tokens created during parsing.
	 *
	 * @return List of additional tokens.
	 *//*

	public List<TreeToken> GiveAddedTokens() {
		return AddedTokens;
	}


	*/
/**
	 * Go through the input, parsing tokens.
	 *
	 * @return Self for method chaining.
	 *//*

	public LiveScriptParserState ParseInput() {
		boolean end = false;
		boolean parsed = false;
*/
/*
		do {
			TreeToken errorToken = new TreeToken(TokenType.ERROR_ELEMENT);

			parsed = this.ParseToken();

			// Refresh current/next token references, in case parser has changed the tokens
			this.MoveToToken(this.TokenIndex);

			if (this.Type == LexerTokens.ImplicitList
				&& this.ThisToken.TypeIsOneOf(LexerTokens.INDENT))
			{
				this.ElementCount++;
			}

			// *) Check for normal state finishers.
			if (this.Type != LexerTokens.None) {
				try {
					end = this.EndReached();
				}
				catch (ParseError err) {
					errorToken.ErrorMessage = err.getMessage();
				}
			}

			// Error: Indent outside of a correct statement
			if (this.Type != LexerTokens.ImplicitList
				&& this.ThisToken.TypeIsOneOf(LexerTokens.INDENT))
			{
				errorToken.ErrorMessage = "Unexpected indent.";
				this.StartTokenIndex = this.TokenIndex;
				end = true;
			}

			else

			// Error: Bad character
			if (this.Type == TokenType.ERROR_ELEMENT
				&& this.ThisToken.TypeIsOneOf(TokenType.BAD_CHARACTER))
			{
				errorToken.ErrorMessage = "Unrecognized character.";
				end = true;
			}

			// Error: A statement has been parsed and finished, but there are more tokens after it.
			if (this.Type == LexerTokens.None
				&& this.StatementFinished
				&& !end
				&& !this.ThisToken.TypeIsOneOf(TokenType.ERROR_ELEMENT)
				&& !this.IsEndOfStatement())
			{
				errorToken.ErrorMessage = "Stray token after a statement.";
				this.StartTokenIndex = this.TokenIndex;
				end = true;
			}


*//*

*/
/*
			// Error: Parser state is not done, but we've reached the end of the input tokens for the statement
			if (this.Type != LexerTokens.None
				&& this.Type != TokenType.ERROR_ELEMENT
				&& this.AtEndOfStatement()
				&& !end)
			{
				this.StartTokenIndex = this.TokenIndex;
				errorToken.ErrorMessage = "Unexpected end to " + this.Type + " statement";
				end = true;
			}
*//*
*/
/*



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

				if (this.Type == LexerTokens.ImplicitList) {
					// Indented statements are a bit of a special case because the last newline is parsed
					// when it is "this" token instead of "next". This implicitly makes it part of
					// the statement, so we must move the "parent" boundary back to leave the
					// newline outside the statement and give the other states access to it.
					this.MoveToToken(--this.TokenIndex);
				}

				parent.EndPosition = ThisToken.EndPosition;

				// *) In case of implicit list, we also need to save how many elments it has
				if (this.Type == LexerTokens.ImplicitList)
					parent.ElementCount = this.ElementCount;

				// *) Replace the encompassed tokens in the input stream with the parent token
				for (int a = this.TokenIndex; a >= this.StartTokenIndex; a--)
					this.InputStream.remove(a);
				this.InputStream.add(this.StartTokenIndex, parent);
				this.AddedTokens.add(parent);

			}


			// Set the end-of-statement marker so that any following tokens are recognized as strays,
			// and clear it on newlines.
			if (this.Type == LexerTokens.None && !this.ThisToken.TypeIsOneOf(TokenType.ERROR_ELEMENT))
				this.StatementFinished = this.AtEndOfStatement();


			// *) No matter what happens, we must end the parsing if we've reached the end of the input stream.
			end = this.AtLastToken() || (end && this.Type != LexerTokens.None);

			if (!end && !parsed) {
				this.MoveToNextToken();
			}

		} while (!end);
*//*


		return this;
	}


	*/
/**
	 * Parse the token at the current input position, starting new parser states as needed.
	 *
	 * @return <tt>true</tt> if a new state was started and parsed, <tt>false</tt> otherwise.
	 *//*

	protected boolean ParseToken() {
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

		// Class without content
		if (ThisToken.TypeIsOneOf(LexerTokens.CLASS)
			&& NextToken.TypeIsOneOf(LexerTokens.IDENTIFIER))
		{
			newState = NewState(LexerTokens.EmptyClass);
		}

		else

		// D-string
		if (this.ThisToken.TypeIsOneOf(LexerTokens.STRING_START)) {
			newState = this.NewState(LexerTokens.StringOp);
		}

		else

		// Explicit list
		if (this.ThisToken.TypeIsOneOf(LexerTokens.LIST_START)) {
			newState = this.NewState(LexerTokens.List);
		}

		else

		// Implicit list
		if ((this.Type == LexerTokens.AssignOperation)
			&& this.AtIndent())
		{
			newState = this.NewState(LexerTokens.ImplicitList);
			newState.IndentSize = this.NextToken.Text.length();
		}

		else

		// Parenthesis
		if (this.ThisToken.TypeIsOneOf(LexerTokens.PAREN_L)) {
			newState = this.NewState(LexerTokens.ParenOp);
		}

		else

		// Property access
		if (this.ThisToken.TypeIsOneOf(LexerTokens.Expression)
			&& this.NextToken.TypeIsOneOf(LexerTokens.DOT))
		{
			newState = this.NewState(LexerTokens.PropertyAccess);
		}

		else

		// Parameter-less function call
		if (this.Type == LexerTokens.FuncCall
			&& this.ThisToken.TypeIsOneOf(LexerTokens.BANG))
		{
			// We don't need to check for anything else
			return true;
		}

		else

		// Sum
		if (ThisToken.TypeIsOneOf(LexerTokens.Value, LexerTokens.SumOp)
			&& NextToken.TypeIsOneOf(LexerTokens.PLUS))
		{
			newState = this.NewState(LexerTokens.SumOp);
		}

		else

		// If-exists
		if (ThisToken.TypeIsOneOf(LexerTokens.IDENTIFIER) &&
			NextToken.TypeIsOneOf(LexerTokens.Q))
		{
			newState = this.NewState(LexerTokens.IfExists);
		}

		else

		// Assignment
		if (!this.TypeIsOneOf(LexerTokens.PropertyAccess)
			&& ThisToken.TypeIsOneOf(LexerTokens.IDENTIFIER, LexerTokens.PropertyAccess)
			&& NextToken.TypeIsOneOf(LexerTokens.ASSIGN))
		{
			newState = this.NewState(LexerTokens.AssignOperation);
		}

		else

		// Explicit object
		if (this.ThisToken.TypeIsOneOf(LexerTokens.OBJ_START)) {
			newState = this.NewState(LexerTokens.Object);
		}

		else

		// Function argument list
		if (this.Type == LexerTokens.FuncCall) {
			newState = this.NewState(LexerTokens.FuncArgList, false);
		}

		else

		// Explicit property definition
		if (this.ThisToken.TypeIsOneOf(LexerTokens.Expression)
			&& this.NextToken.TypeIsOneOf(LexerTokens.COLON))
		{
			newState = this.NewState(LexerTokens.PropDefOp);
		}

		else

		// Function call with arguments
		if (!this.TypeIsOneOf(LexerTokens.FuncCall, LexerTokens.StringOp)
			&& this.ThisToken.TypeIsOneOf(LexerTokens.IDENTIFIER)
			&& !this.IsEndOfStatement(this.NextToken, LexerTokens.FuncCall)
			&& this.NextToken.TypeIsOneOf(LexerTokens.Expression))
		{
			newState = this.NewState(LexerTokens.FuncCall);
		}


		if (newState != null) {
			// Run the new state's parse
			this.AddedTokens.addAll(newState.ParseInput().GiveAddedTokens());
			return true;
		}

		return false;
	}

	*/
/**
	 * Check if this state's type is among a given set.
	 * @param types Set of types to check against.
	 * @return <tt>true</tt> if found, <tt>false</tt> otherwise.
	 *//*

	private boolean TypeIsOneOf(IElementType... types) {
		boolean result = false;
		for (IElementType type : types) {
			if (!result && type == this.Type)
				result = true;

			if (result)
				return true;
		}
		return false;
	}

	*/
/**
	 * Check if we are at and end of a parser state and should close it.
	 *
	 * @return <tt>true</tt> if an end-of-state condition is met, <tt>false</tt> otherwise.
	 *//*

	protected boolean EndReached() {
		// Expression with parenthesis
		if (this.Type == LexerTokens.ParenOp) {
			return this.ThisToken.TypeIsOneOf(LexerTokens.PAREN_R);
		}

		// Empty class
		if (this.Type == LexerTokens.EmptyClass)
			return true;

		// String
		if (this.Type == LexerTokens.StringOp) {
			return this.ThisToken.TypeIsOneOf(LexerTokens.STRING_END);
		}

		// Explicit list
		if (this.Type == LexerTokens.List) {
			return this.ThisToken.TypeIsOneOf(LexerTokens.LIST_END);
		}

		// Explicit object
		if (this.Type == LexerTokens.Object) {
			return this.ThisToken.TypeIsOneOf(LexerTokens.OBJ_END);
		}

		// Explicit property definition
		if (this.Type == LexerTokens.PropDefOp) {
			// First colon is part of the statement
			if (this.ThisToken.TypeIsOneOf(LexerTokens.COLON) && this.StartTokenIndex == this.TokenIndex)
				return false;
			if (this.ThisToken.TypeIsOneOf(LexerTokens.Expression))
				return true;
			throw new ParseError("Unexpected token in " + this.Type.toString());
		}

		// Property access expression
		if (this.Type == LexerTokens.PropertyAccess) {
			// Property state only starts when there is a property accessor,
			// and ends at the same, so it always ends by the time this check
			// is performed.
			return !ThisToken.TypeIsOneOf(LexerTokens.DOT);
		}

		// IfExists expression
		if (this.Type == LexerTokens.IfExists) {
			return true;
		}

		// Sum expression
		if (this.Type == LexerTokens.SumOp) {
			if (this.ThisToken.TypeIsOneOf(LexerTokens.PLUS))
				return false;
			if (this.ThisToken.TypeIsOneOf(LexerTokens.Value, LexerTokens.SumOp)) {
				return true;
			}
			throw new ParseError("Invalid sum expression.");
		}

		// Assignment expression
		if (this.Type == LexerTokens.AssignOperation) {
			if (this.ThisToken.Type == LexerTokens.ASSIGN)
				return false;if (this.ThisToken.TypeIsOneOf(LexerTokens.Expression))
				return true;
			throw new ParseError("Invalid assign expression.");
		}

		// List definition expression
		if (this.Type == LexerTokens.List) {
			if (this.ThisToken.TypeIsOneOf(LexerTokens.LIST_END))
				return true;
			if (this.ThisToken.TypeIsOneOf(LexerTokens.Value, LexerTokens.SEPARATOR))
				return false;
			throw new ParseError("Invalid list expression");
		}

		// Implied list expression
		if (this.Type == LexerTokens.ImplicitList) {
			return this.AtDedent();
		}

		// Argument list
		if (this.Type == LexerTokens.FuncArgList) {
			if ((this.ThisToken.TypeIsOneOf(LexerTokens.Value) && this.NextToken.TypeIsOneOf(LexerTokens.COMMA))
				|| (this.ThisToken.TypeIsOneOf(LexerTokens.COMMA) && this.NextToken.TypeIsOneOf(LexerTokens.Value, LexerTokens.COMMA))) {
				return false;
			}
			return true;
		}

		// Function call
		if (this.Type == LexerTokens.FuncCall) {
			if (this.ThisToken.TypeIsOneOf(LexerTokens.BANG, LexerTokens.FuncArgList))
				return true;
			throw new ParseError("Invalid function call");
		}

		else

		return false;
	}

	*/
/**
	 * Start a new state at the current token.
	 *
	 * @param type {@link #Type}
	 * @param moveToNextToken After creating the new state, should its token index be moved forward once?
	 * @return The newly created state.
	 *//*

	protected LiveScriptParserState NewState(IElementType type, boolean moveToNextToken) {
		LiveScriptParserState ns = new LiveScriptParserState(type, this.InputStream, this.TokenIndex);
		if (moveToNextToken)
			ns.MoveToNextToken();
		return ns;
	}

	protected LiveScriptParserState NewState(IElementType type) {
		return this.NewState(type, true);
	}


	*/
/**
	 * Move on to the next token in the input stream.
	 *
	 * @return Self for method chaining.
	 *//*

	protected LiveScriptParserState MoveToNextToken() {
		return this.MoveToToken(this.TokenIndex + 1);
	}

	*/
/**
	 * Move to a specific token in the input stream.
	 *
	 * @param index Index of the token to move to.
	 * @return Self for method chaining.
	 *//*

	protected LiveScriptParserState MoveToToken(int index) {
		this.TokenIndex = index;
		this.ThisToken = this.InputStream.get(this.TokenIndex);
		this.NextToken = this.InputStream.size() - this.TokenIndex > 1
			? this.InputStream.get(this.TokenIndex + 1)
			: new TreeToken(LexerTokens.EOF);

		return this;
	}

	*/
/**
	 * Check if the current parsing token index is at a position where the next token
	 * will start a new indentation level.
	 * @return
	 *//*

	private boolean AtIndent() {
		return (this.ThisToken.Type == LexerTokens.NEWLINE &&
			(this.NextToken.Type == LexerTokens.INDENT && this.NextToken.Text.length() > this.IndentSize));
	}

	*/
/**
	 * Check if the the next token will be dedented.
	 * @return
	 *//*

	private boolean AtDedent() {
		if (this.NextToken.Type == LexerTokens.EOF) {
			this.TokenIndex++;
			return true;
		}

		return (this.ThisToken.Type == LexerTokens.NEWLINE &&
			(this.NextToken.Type != LexerTokens.INDENT || this.NextToken.Text.length() < this.IndentSize));
	}

	*/
/**
	 * Check if {@link #ThisToken} is the last token in the input stream.
	 *
	 * @return <tt>true</tt> if at the last token of input stream, <tt>false</tt> otherwise.
	 *//*

	protected boolean AtLastToken() {
		return this.NextToken.TypeIsOneOf(LexerTokens.EOF);
	}


	*/
/**
	 * Check if {@link #ThisToken} is the last token in a statement.
	 *
	 * @return <tt>true</tt> if it is, <tt>false</tt> otherwise.
	 *//*

	protected boolean AtEndOfStatement() {
		return this.IsEndOfStatement(this.NextToken, this.Type);
	}

	*/
/**
	 * Check if <em>token</em> is an end-of-statement token.
	 *
	 * @param token Token to check.
	 * @param statementType What kind of statement to check the end of.
	 * @return
	 *//*

	protected boolean IsEndOfStatement(TreeToken token, IElementType statementType) {
		boolean result = token.TypeIsOneOf(
			LexerTokens.SEMICOLON,
			LexerTokens.EOF
		);

		if (statementType != LexerTokens.ParenOp)
			result = result || token.TypeIsOneOf(LexerTokens.PAREN_R);

		if (statementType == LexerTokens.Block)
			result = result || (!this.NextToken.TypeIsOneOf(LexerTokens.INDENT) || this.NextToken.Text.length() < this.IndentSize);

		return result;
	}

	*/
/**
	 * Check if current token is an end-of-statement token.
	 * @return
	 *//*

	protected boolean IsEndOfStatement() {
		return this.IsEndOfStatement(this.ThisToken, this.Type);
	}
}
*/
