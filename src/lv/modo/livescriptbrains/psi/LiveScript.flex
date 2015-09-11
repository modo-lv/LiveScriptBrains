package lv.modo.livescriptbrains.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;

import lv.modo.livescriptbrains.psi.LiveScriptTypes;

%%

%public %class LiveScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    System.out.println("End of file reached, clearing out state stack.");
    _states.clear();
    return;
%eof}

%{
    /**
     * Stack for keeping track of lexical states.
     */
    private Stack<Integer> _states = new Stack<Integer>();

    /**
     * Are indents tabs instead of spaces?
     */
    private static Boolean _tabIndents = null;

    /**
     * Current indent baseline.
     */
    private static int _currentIndent = 0;


    private void _switchToState(int state) {
        System.out.println("Switching to state " + _stateName(state) + ".");
        yybegin(state);
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        //System.out.println("Entering state " + _stateName(state) + ".");
        _states.push(yystate());
        yybegin(state);
    }

    /**
     * Leave a lexical state and return to the previous one (if any).
     * @returns boolean True if state was switched to a previous one, false if already at YYINITIAL.
     */
    private boolean _exitState() {
        if (_states.empty()) {
            //System.out.println("State stack empty, defaulting to YYINITIAL.");
            yybegin(YYINITIAL);
            return false;
        }
        else {
            int newState = _states.pop();
            //System.out.println("Exiting state " + _stateName(yystate()) + ", state is now " + _stateName(newState));
            yybegin(newState);
            return true;
        }
    }

    /**
     * Move the input position back to the start of the matched string.
     */
    private void _rewind() {
        yypushback(yylength());
    }

    /**
     * Move input position backwards.
     */
    private void _rewindBy(int count) {
        yypushback(count);
    }

    private void _advanceBy(int count) {
        _rewindBy(-count);
    }

    /**
     * Move the input position back to the first position of a given string
     */
    private boolean _rewindTo(String text) {
        final int position = yytext().toString().indexOf(text);

        if (position != -1) {
          _rewindBy(position);
          return true;
        }

        return false;
    }

    private IElementType _out(IElementType input) {
        //System.out.println("Matched [" + yytext() + "] as " + input);
        return input;
    }
%}


// Literals
BASED_NUMBER = ([0-9]|[1-2][0-9]|3[0-2])\~[0-9a-zA-Z]+
NUMBER = [0-9][0-9_]*\.?[0-9_]*[a-zA-Z0-9]*
EMPTY=null|void
BOOLEAN=true|false|yes|no|on|off

// Specials
THIS="this"
THIS_AT="@"

// Strings
BACKSTRING = \\[^,;\]\)\} \t\r\n]+
HEREDOC = \'\'\'~\'\'\'
SSS = \' // Simple string start
FSS = %\"|\" // Full string start
SSTRING = \'(\\\'|[^\'])*\'
ISTRING = (\\\"|[^\"#])

DSTRING = %?\"{ISTRING}*\"
DSTRING_START = %?\"{ISTRING}*
ISTRING_START = "#{"

TSTRING = \"\"\"


// Regex
REGEX = \/[^\/] ~\/g?m?i?
REGEX_MULTI_START = "//"
REGEX_MULTI_END = \/\/g?m?i?

// Operators
MATH_OP = [-*/]
PLUS = "+"
ASSIGN = "="
LOGIC_OP = or|and|\|\||&&|not|xor
MISC_OP = [<>\^~]

COLON = ":"
COMMA = ","
CURL_L = "{"
CURL_R = "}"
PAREN_L = "("
PAREN_R = ")"
BRACK_L = "["
BRACK_R = "]"
DOT = "."
SEMICOLON = ";"
BANG = "!"
YADA = "..."
Q = "?"

// Keywords
CLASS = "class"
KEYWORD = if|then|else|unless|otherwise|in|of|for|by|delete|do|while

IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

// Comments
COMMENT_LINE = #.*
COMMENT_BLOCK = \/\*~\*\/

COMMENT_BLOCK_START = "/*"

COMMENT_BLOCK_END = "*/"

// Whitespace
CRLF = [\r\n]
NEWLINE = \r\n|{CRLF}
SPACE = [\f\t ]

TEST = "!@#$%^&*()TEEEEEEEEESESESETESTESTETETTTT!!)*(!)@(*)*(##"
UNKNOWN=[:().]


%state INDENTED, BLOCK_STATEMENT, COMMENT_BLOCK
%state DSTRING, ISTRING, VSTRING, STRING_SUSPENDED, TSTRING
%state REGEX, SPLIT_OP, LIST, STRING_VAR, OBJECT

%{
    private String[] _stateNames = new String[] {
        "YYINITIAL",
        "INDENTED",
        "BLOCK_STATEMENT",
        "COMMENT_BLOCK",
        "DSTRING",
        "ISTRING",
        "VSTRING",
        "STRING_SUSPENDED",
        "TSTRING",
        "REGEX",
        "SPLIT_OP",
        "LIST",
        "STRING_VAR",
        "OBJECT",
    };
    private String _stateName(int state) {
        return _stateNames[state / 2];
    }
%}

%%

// Variable inside double-quoted string
<STRING_VAR> {
	{IDENTIFIER}			{ _exitState(); return _out(LiveScriptTypes.IDENTIFIER); }

	.						{ _exitState(); _rewindBy(1); }
}

// Inside a double-quoted string
<DSTRING> {
    {ISTRING_START}         { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING); }

    #			       		{ _enterState(STRING_VAR); return _out(LiveScriptTypes.ESCAPE_CHAR); }

    // Everything else is just regular string.
    \"    					{ _exitState(); return _out(LiveScriptTypes.STRING_END); }

    ({ISTRING}|#\{\})*      { return _out(LiveScriptTypes.STRING); }
}

<TSTRING> {
    {ISTRING_START}         { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING); }

    #			       		{ _enterState(STRING_VAR); return _out(LiveScriptTypes.ESCAPE_CHAR); }

    // Everything else is just regular string.
    \"\"\"					{ _exitState(); return _out(LiveScriptTypes.STRING_END); }

    {NEWLINE}|.				{ return _out(LiveScriptTypes.STRING); }
}

<ISTRING> {
    {CURL_R}                { _exitState(); return _out(LiveScriptTypes.ISTRING); }
}

// An operation that allows continuation on the next line even without a backslash
<SPLIT_OP> {
	// Treat all white space as white space, including newlines and what would otherwise be indents.
	{NEWLINE}{SPACE}*		{ return _out(TokenType.WHITE_SPACE); }

	.						{ _rewindBy(1); _exitState(); }
}

<LIST, OBJECT> {
	({SPACE}*{NEWLINE}{SPACE}*)+ 	{ return _out(LiveScriptTypes.SEPARATOR); }

	{COMMA}							{ return _out(LiveScriptTypes.SEPARATOR); }
}

<LIST> {
	{BRACK_R}	{ _exitState(); return _out(LiveScriptTypes.LIST_END); }
}

<OBJECT> {
    {CURL_R}	{ _exitState(); return _out(LiveScriptTypes.OBJ_END); }
}

<COMMENT_BLOCK> {
    {COMMENT_BLOCK_START}   { _enterState(COMMENT_BLOCK); return _out(LiveScriptTypes.COMMENT_BLOCK); }

    {COMMENT_BLOCK_END}     { _exitState(); return _out(LiveScriptTypes.COMMENT_BLOCK); }

    .|{NEWLINE}             { return _out(LiveScriptTypes.COMMENT_BLOCK); }
}

<YYINITIAL, ISTRING, LIST, OBJECT> {
    // Keywords
    {CLASS}					{ return _out(LiveScriptTypes.CLASS); }

    {KEYWORD}               { return _out(LiveScriptTypes.KEYWORD); }

    {THIS}|{THIS_AT}        { return _out(LiveScriptTypes.KEYWORD); }

    // Literals
    {EMPTY}                 { return _out(LiveScriptTypes.EMPTY); }

    {BOOLEAN}               { return _out(LiveScriptTypes.BOOLEAN); }

    {NUMBER}|{BASED_NUMBER} { return _out(LiveScriptTypes.NUMBER); }

    // Strings
    {HEREDOC}               { return _out(LiveScriptTypes.STRING); }
    {BACKSTRING}            { return _out(LiveScriptTypes.STRING); }
    {SSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING_START}         { _enterState(DSTRING); return _out(LiveScriptTypes.STRING_START); }
    {TSTRING}				{ _enterState(TSTRING); return _out(LiveScriptTypes.STRING_START); }

    {IDENTIFIER}            { return _out(LiveScriptTypes.IDENTIFIER); }


    // Operators & punctuation
    {MATH_OP}               { _enterState(SPLIT_OP); return _out(LiveScriptTypes.MATH_OP); }
    {PLUS}					{ _enterState(SPLIT_OP); return _out(LiveScriptTypes.PLUS); }

    {ASSIGN}                { return _out(LiveScriptTypes.ASSIGN); }

    {BRACK_L}				{ _enterState(LIST); return _out(LiveScriptTypes.LIST_START); }

    {CURL_L}				{ _enterState(OBJECT); return _out(LiveScriptTypes.OBJ_START); }

    {MISC_OP}               { return _out(LiveScriptTypes.MISC_OP); }
    {LOGIC_OP}              { return _out(LiveScriptTypes.LOGIC_OP); }

    {PAREN_L}				{ return _out(LiveScriptTypes.PAREN_L); }
    {PAREN_R}				{ return _out(LiveScriptTypes.PAREN_R); }

    {COMMA}					{ return _out(LiveScriptTypes.COMMA); }
    {BANG}					{ return _out(LiveScriptTypes.BANG); }

    {YADA}					{ return _out(LiveScriptTypes.YADA); }
    {Q}						{ return _out(LiveScriptTypes.Q); }
    {DOT}					{ return _out(LiveScriptTypes.DOT); }

    {COLON}					{ return _out(LiveScriptTypes.COLON); }

    {SEMICOLON}				{ return _out(LiveScriptTypes.SEMICOLON); }

    ^{SPACE}*{NEWLINE}		{ return _out(TokenType.WHITE_SPACE); }
    ^{SPACE}+				{ return _out(TokenType.WHITE_SPACE); }

	^{SPACE}+.				{ _rewindBy(1); return _out(LiveScriptTypes.INDENT); }

    // Non-code
    \\{SPACE}*{NEWLINE}{SPACE}*		{ return _out(TokenType.WHITE_SPACE); }


    {NEWLINE}               { return _out(LiveScriptTypes.NEWLINE); }

    {COMMENT_LINE}          { return _out(LiveScriptTypes.COMMENT_LINE); }

    //{COMMENT_BLOCK}			{ return _out(LiveScriptTypes.COMMENT_BLOCK); }
    {COMMENT_BLOCK_START}   { _enterState(COMMENT_BLOCK); return _out(LiveScriptTypes.COMMENT_BLOCK); }

    {SPACE}+                { return _out(TokenType.WHITE_SPACE); }

}

// Fall-through
.                       { System.out.println("State is " + _stateName(yystate())); return _out(TokenType.BAD_CHARACTER); }