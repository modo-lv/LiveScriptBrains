/* The following code was generated by JFlex 1.4.3 on 15.16.10 13:53 */

package lv.modo.livescriptbrains.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lexer is the first step of the parsing process — it creates semantic "tokens" from a piece of
 * plain text.
 * A token represents a context-less, atomic, smallest piece of the programming language,
 * which will later be combined with others into semantic structures by a parser.
 * For example, the letters "i" and "f" one after another (if they are not part of another word,
 * of course)
 * must be converted into an "if" token.
 */
public class LiveScriptLexer implements FlexLexer
{
	/**
	 * When a new lexer state is entered, the previous one is pushed
	 * on to the stack, and when the state is exited, it's popped
	 * off and resumed again.
	 * This allows for infinite depth of states, which can be
	 * necessary, for example in case of interpolated strings containing
	 * code that contains more interpolated strings and so on.
	 */
	private Stack<Integer> _stateStack;

	/**
	 * Current lexer state.
	 */
	private int _currentState;


	/**
	 * Character index that the lexer is currently at.
	 */
	private int _currentIndex;

	/**
	 * Length of the input text [fragment].
	 */
	private int _textLength;

	/**
	 * Index of the first character in the last token.
	 */
	private int _tokenStartIndex;

	/**
	 * Index of the next character after the token (i.e., last token character index + 1)
	 */
	private int _tokenEndIndex;

	/**
	 * Are tabs used as indents, instead of spaces?
	 */
	private boolean _indentTab;

	/**
	 * How many tabs/spaces wide is one indent.
	 */
	private int _indentSize;

	/**
	 * Text that we're processing.
	 */
	private String _text;

	/**
	 * How many characters the current token consists of.
	 */
	private int _tokenLength = 0;

	private Matcher _matcher = null;

	/**
	 * Found token type.
	 */
	private IElementType _tokenType = null;


	/**
	 * Must always return the position of the first character of whatever the last token returned by
	 * {@link #advance()}.
	 *
	 * @return
	 */
	@Override
	public int getTokenStart()
	{
		return this._tokenStartIndex;
	}

	/**
	 * Must always return the index of the last character in the last token returned by
	 * {@link #advance()}.
	 *
	 * @return
	 */
	@Override
	public int getTokenEnd()
	{
		return this._tokenEndIndex;
	}


	public LiveScriptLexer()
	{
		this._stateStack = new Stack<>();
	}

	/**
	 * Read/process input until a token can be returned, then return it.
	 * <p>This is the main lexer processor method. During the parsing process, this method is called
	 * over and over, to get
	 * the tokens one after another. It must return the next token every time, or <tt>null</tt> when
	 * the end of file
	 * is reached.</p>
	 * <p>
	 * We'll achieve this by taking the whole text buffer as a string, and trying to determine the
	 * first token in it.
	 * When determined, the token is returned, and its content cut from the text buffer, thus when
	 * the method is called
	 * next, it will be working on the text following the last token.
	 * Repeat until there is no more text to parse.
	 * </p>
	 *
	 * @return
	 * @throws IOException
	 */
	@Override
	public IElementType advance() throws IOException
	{
		// Reset/initialize variables
		this._tokenType = null;
		this._tokenLength = 0;

		// If we are at the end of the text, we're done.
		if (this._currentIndex >= this._textLength)
			return this._tokenType;

		LinkedList<Runnable> methods = new LinkedList<>();

		// Here we add all the token matching methods to be run through.
		// Each method tries to detect if there is a token at the start of the text (which gets cut
		// when
		// a token is found, so the start of the text is the index just after the last token).
		// If a token is detected, this._tokenType is set which breaks the loop and stops
		// further checking.
		methods.add(this::_tryExitInterpolation);
		methods.add(this::_tryComments);
		methods.add(this::_trySimpleValues);
		methods.add(this::_tryPlainStrings);
		methods.add(this::_tryFullString);
		methods.add(this::_tryId);
		methods.add(this::_tryWhitespace);
		methods.add(this::_tryDot);

		while (this._tokenType == null && !methods.isEmpty())
		{
			Runnable method = methods.removeFirst();
			method.run();

			if (this.yystate() == LexerState.DOT
				&& this._tokenType != null
				&& this._tokenType != LexerTokens.DOT
				&& this._tokenType != LexerTokens.WHITESPACE)
			{
				this._exitState();
			}
		}


		// If we haven't matched anything, mark the character as unknown/error.
		if (this._tokenType == null)
		{
			this._tokenType = LexerTokens.BAD_CHAR;
			this._tokenLength = 1;
		}
		else
		{
			// If we did match
			if (this._tokenLength == 0)
				this._tokenLength = this._matcher.end();
		}

		// Set the token positions and move the current index just after it.
		this._tokenStartIndex = this._currentIndex;
		this._tokenEndIndex = this._tokenStartIndex + this._tokenLength;
		this._currentIndex = this._tokenEndIndex;
		if (this._currentIndex < this._textLength)
			this._text = this._text.substring(this._tokenLength);

		return this._tokenType;
	}

	/**
	 * Dot is a special case because if it precedes a keyword, that keyword is interpreted as
	 * an object member instead. So we must mark
	 */
	private void _tryDot()
	{
		if (!this._isNormalState())
			return;

		if (this._tryMatch(LexerPatterns.DOT))
		{
			this._tokenType = LexerTokens.DOT;
			this._enterState(LexerState.DOT);
		}
	}

	private void _tryWhitespace()
	{
		if (!this._isNormalState())
			return;

		if (this._tryMatch(LexerPatterns.WHITESPACE))
			this._tokenType = LexerTokens.WHITESPACE;
	}

	/**
	 * Try a whole range of simple values - numbers, keywords
	 */
	private void _trySimpleValues()
	{
		if (!this._isNormalState())
			return;

		LinkedHashMap<String, IElementType> typeMap = new LinkedHashMap<>();

		// Numbers
		typeMap.put(LexerPatterns.BASED_NUMBER, LexerTokens.NUMBER);
		typeMap.put(LexerPatterns.NUMBER, LexerTokens.NUMBER);
		// Booleans
		typeMap.put(LexerPatterns.BOOLEANS, LexerTokens.BOOLEAN);
		if (this.yystate() != LexerState.DOT)
		{
			// "this"
			typeMap.put(LexerPatterns.THIS, LexerTokens.THIS);
		}


		for (Map.Entry<String, IElementType> token : typeMap.entrySet())
		{
			if (this._tryMatch(token.getKey()))
			{
				this._tokenType = token.getValue();
				return;
			}
		}
	}

	/**
	 * When inside the interpolation part of a string, check for when it's closed.
	 */
	private void _tryExitInterpolation()
	{
		if (this._stateIsOneOf(LexerState.INTERPOLATED) && this._tryMatch("^}"))
		{
			this._tokenType = LexerTokens.INTERPOLATION_END;
			this._exitState();
		}
	}

	/**
	 * Match comment tokens.
	 */
	private void _tryComments()
	{
		if (!this._isNormalState())
			return;

		if (this._tryMatch("^/\\*.*?\\*/", Pattern.DOTALL))
			this._tokenType = LexerTokens.COMMENT_BLOCK;
		else if (this._tryMatch("^#.*"))
			this._tokenType = LexerTokens.COMMENT_LINE;
	}

	private boolean _isNormalState()
	{
		return this._stateIsOneOf(LexerState.NORMAL, LexerState.INTERPOLATED, LexerState.DOT);
	}

	private void _tryFullString()
	{
		String suffix = this._stateIs(LexerState.HEREDOC) ? "\"{3}" : "\"";

		// First we check if we are inside a string, in which case everything up to interpolation /
		// end of string is a string.
		if (this._stateIsOneOf(LexerStateSet.STRINGS))
		{
			// Check if we're at an interpolation point
			if (this._tryMatch("^(#\\{|#)"))
			{
				if (this._matcher.group(1).equals("#{"))
				{
					this._enterState(LexerState.INTERPOLATED);
					this._tokenType = LexerTokens.INTERPOLATION_START;
				}
				else
				{
					this._enterState(LexerState.INTERPOLATED_VAR);
					this._tokenType = LexerTokens.INTERPOLATED_VAR_START;
				}
				return;
			}

			this._tokenType = LexerTokens.STRING;

			// Match until end or interpolation.
			if (this._tryMatch("^(?:\\\\\"|\\\\#|[^\"])*?(#\\{|#)", Pattern.DOTALL))
			{
				this._tokenLength = this._matcher.end() - this._matcher.group(1).length();
				return;
			}
			if (this._tryMatch("^(?:\\\\\"|\\\\#|[^#])*?(" + suffix + ")", Pattern.DOTALL))
			{
				this._tokenLength = this._matcher.end() - this._matcher.group(1).length();
			}
		}

		// Only if we're *not* inside a string already (or are at an end of one), do we check for
		// starting / ending.
		if (this._stateIsOneOf(LexerStateSet.STRINGS))
		{
			if (this._tryMatch("^" + suffix))
			{
				this._tokenType = LexerTokens.STRING_END;
				this._exitState();
			}
		}
		else if (this._tryMatch("^(\"{3}|\")"))
		{
			this._tokenType = LexerTokens.STRING_START;
			this._enterState(
				this._matcher.group(1).equals("\"")
					? LexerState.STRING
					: LexerState.HEREDOC);
		}


/*		boolean justStarted = false;

		// If we are already going through a string, we can't start another.
		if (!this._stateIsOneOf(LexerState.STRING, LexerState.STRING_MULTILINE)) {
			if (this._text.charAt(0) == '"') {
				justStarted = true;
				// We must find out if it's a single double-quote or a triple double-quote
				this._enterState(this._tryMatch("^\"{3}") ? LexerState.STRING_MULTILINE : LexerState
				.STRING);
			}
		}

		// At this point we must be inside a string.
		if (!this._stateIsOneOf(LexerState.STRING, LexerState.STRING_MULTILINE))
			return;

		// Since we are already in the string state, we know the token type will be string,
		// no matter what else happens.
		this._tokenType = LexerTokens.STRING;

		// Set up variables and initial values.
		boolean triple = this.yystate() == LexerState.STRING_MULTILINE;
		String boundary = triple ? "\"{3}" : "\"";
		int offset = justStarted ? (triple ? 3 : 1) : 0;
		this._tokenLength = offset;

		// We need to match the string up to the end or the first possible interpolation point.
		do {
			if (!this._tryMatch(".*?(#|" + boundary + ")", Pattern.DOTALL, offset)) {
				// If we didn't match this, it means it's a broken (unfinished) string so we must
				// match to the end of the line or file, and return that as a broken string.
				this._tryMatch("^.+", Pattern.DOTALL);
				this._tokenType = LexerTokens.STRING_BROKEN;
				this._exitState();
				return;
			}

			// Increase the match length to as far as we've gotten.
			this._tokenLength += this._matcher.end() - this._matcher.start();

			if (this._matcher.group(1).equals("#")) {
				// Move offset to the end of the match for any further matching.
				offset = this._matcher.end();

				// Here we must figure out if we have an interpolation on our hands or not.
				if (this._text.charAt(offset) == '{') {
					this._tokenLength++;
					this._enterState(LexerState.INTERPOLATED);
				} else if (this._tryMatch(LexerPatterns.ID, 0, offset)) {
					this._enterState(LexerState.INTERPOLATED_VAR_START);
				}

				// If none of the previous match, we do nothing.
				// The loop will repeat and try to match further.
			} else {
				// End of string reached.
				this._exitState();
			}
		} while (this._stateIsOneOf(LexerState.STRING, LexerState.STRING_MULTILINE));*/
	}

	private void _tryId()
	{
		if (!this._isNormalState() && !this._stateIsOneOf(LexerState.INTERPOLATED, LexerState
			.INTERPOLATED_VAR))
			return;

		if (this._tryMatch("^" + LexerPatterns.ID))
		{
			this._tokenType = LexerTokens.IDENTIFIER;
			if (this.yystate() == LexerState.INTERPOLATED_VAR)
				this._exitState();
		}
	}

	/**
	 * @param states
	 * @return
	 */
	private boolean _stateIsOneOf(int... states)
	{
		for (int state : states)
		{
			if (this.yystate() == state)
				return true;
		}
		return false;
	}

	private boolean _stateIsOneOf(Iterable<Integer> states)
	{
		for (Integer state : states)
		{
			if (this._stateIs(state))
				return true;
		}
		return false;
	}

	private boolean _stateIs(int state)
	{
		return this.yystate() == state;
	}

	/**
	 * Matches simple single-quoted strings.
	 */
	private void _tryPlainStrings()
	{
		if (this._stateIsOneOf(LexerStateSet.STRINGS))
			return;

		if (this._tryMatch(LexerPatterns.BACKSTRING)
			|| this._tryMatch("^'{3}.*?'{3}", Pattern.DOTALL)
			|| this._tryMatch("^'(?:\\\\'|.)*?'", Pattern.DOTALL))
		{
			this._tokenType = LexerTokens.STRING_LITERAL;
		}
	}

	private Character _getIndentChar()
	{
		return this._indentTab ? '\t' : ' ';
	}


	private boolean _tryMatch(String patternString, int flags)
	{
		return _tryMatch(patternString, flags, 0);
	}

	private boolean _tryMatch(String patternString, int flags, int startAt)
	{
		Pattern pattern = Pattern.compile(patternString, flags);
		this._matcher = pattern.matcher(this._text);
		return this._matcher.find(startAt);
	}

	private boolean _tryMatch(String patternString)
	{
		return this._tryMatch(patternString, 0, 0);
	}

	/**
	 * Commands the lexer to start over from the beginning.
	 * The lexing process starts with this entry point, receiving the text and its
	 *
	 * @param buf          Text to parse for tokens.
	 * @param start        The starting position of the text. Usually 0, but can be larger if only a
	 *                     fragment of text is processed.
	 * @param end          Ending position of the text. Usually text.length, but can be smaller if
	 *                     only a fragment is used.
	 * @param initialState ?
	 */
	@Override
	public void reset(CharSequence buf, int start, int end, int initialState)
	{
		this._currentIndex = start;
		this._textLength = end;
		this._text = buf.toString().substring(start, end);
		this._stateStack = new Stack<>();
		this._currentState = initialState;
	}


	/**
	 * Go back to the previous lexer state (or {@link LexerState#NORMAL} if
	 * there is no previous state.
	 */
	private void _exitState()
	{
		if (this._stateStack.empty())
		{
			this._currentState = LexerState.NORMAL;
			return;
		}
		this._currentState = this._stateStack.pop();
	}

	private void _enterState(int state)
	{
		this.yybegin(state);
	}


	/**
	 * Enter a new lexer state.
	 *
	 * @param state
	 */
	@Override
	public void yybegin(int state)
	{
		this._stateStack.push(this._currentState);
		this._currentState = state;
	}

	/**
	 * Return current lexer state.
	 *
	 * @return
	 */
	@Override
	public int yystate()
	{
		return this._currentState;
	}

}