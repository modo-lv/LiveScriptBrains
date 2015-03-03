package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;

%%

%public %class LiveScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    System.out.println("End of file reached, clearing out indentation stack.");
    _currentIndent = 0;
    _indents.clear();
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
     * Indent stack to keep track of indentation levels.
     */
    private static Stack<Integer> _indents = new Stack<Integer>();

    /**
     * Current indent baseline.
     */
    private static int _currentIndent = 0;

    /**
     * Are we currently inside an indented block?
     */
    private static boolean _isIndented = false;

    /**
     * Start a new indented block (increase indent).
     */

    private IElementType _parseIndent(int count) {
/*
        if (count > _currentIndent) {
            _indents.push(_currentIndent);
            _currentIndent += count;
            _switchToState(INDENTED);
            return _out(LiveScriptTypes.INDENT);
        }
        else if (count < _currentIndent) {
            if (!_indents.empty())
                _currentIndent = _indents.pop();
            _exitState();
            if (_currentIndent < 1) {
                _isIndented = false;
            }
            return _out(LiveScriptTypes.DEDENT);
        }
*/
        return null;
    }

    private void _switchToState(int state) {
        System.out.println("Switching to state " + _stateName(state) + ".");
        yybegin(INDENTED);
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        System.out.println("Entering state " + _stateName(state) + ".");
        _states.push(yystate());
        yybegin(state);
    }

    /**
     * Leave a lexical state and return to the previous one (if any).
     * @returns boolean True if state was switched to a previous one, false if already at YYINITIAL.
     */
    private boolean _exitState() {
        if (_states.empty()) {
            System.out.println("State stack empty, defaulting to YYINITIAL.");
            yybegin(YYINITIAL);
            return false;
        }
        else {
            int newState = _states.pop();
            System.out.println("Exiting state " + _stateName(yystate()) + ", state is now " + _stateName(newState));
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
        System.out.println("Matched [" + yytext() + "] as " + input);
        return input;
    }
%}


// Literals
BASED_NUMBER = ([0-9]|[1-2][0-9]|3[0-2])\~[0-9a-zA-Z]+
NUMBER = [0-9][0-9_]*\.?[0-9_]*[a-zA-Z]*
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
DSTRING = \"(\\\"|[^\"#])*\"
DSTRING_INT = \"(\\\"|[^\"#])*#\{   // Interrupted
DSTRING_CON = \}(\\\"|[^\"#])*#\{   // Continued (resumed + interrupted again)
DSTRING_RES = \}(\\\"|[^\"#])*\"    // Resumed [+interrupted]

// Regex
REGEX = \/[^\/] ~\/g?m?i?
REGEX_MULTI_START = "//"
REGEX_MULTI_END = \/\/g?m?i?

// Operators
MATH_OP = [-+*/]
ASSIGN = "="

COLON = ":"
COMMA = ","
CURL_L = "{"
CURL_R = "}"
PAREN_L = "("
PAREN_R = ")"
BRACK_L = "["
BRACK_R = "]"
DOT = "."

IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

// Comments
COMMENT_LINE = #.*
COMMENT_BLOCK = \/\*(.|{CRLF})*\*\/

// Whitespace
CRLF = [\r\n]
NEWLINE = \r\n|{CRLF}
SPACE = [\f\t ]

TEST = "!@#$%^&*()TEEEEEEEEESESESETESTESTETETTTT!!)*(!)@(*)*(##"


%state INDENTED, BLOCK_STATEMENT
%state SIMPLE_STRING_STARTED, INTERSTRING, STRING_VARIABLE, STRING_SUSPENDED
%state REGEX

%{
    private String[] _stateNames = new String[] {
        "YYINITIAL",
        "INDENTED",
        "BLOCK_STATEMENT",
        "SIMPLE_STRING_STARTED",
        "INTERSTRING",
        "STRING_VARIABLE",
        "STRING_SUSPENDED",
        "REGEX",
    };
    private String _stateName(int state) {
        return _stateNames[state / 2];
    }
%}

%%

<YYINITIAL> {
    {DSTRING_INT}           { _enterState(INTERSTRING); return _out(LiveScriptTypes.ISTRING); }
}


<YYINITIAL, INTERSTRING> {
    // Literals
    {EMPTY}                 { return _out(LiveScriptTypes.EMPTY); }

    {BOOLEAN}               { return _out(LiveScriptTypes.BOOLEAN); }

    {NUMBER}|{BASED_NUMBER} { return _out(LiveScriptTypes.NUMBER); }

    // Strings
    {HEREDOC}               { return _out(LiveScriptTypes.STRING); }
    {BACKSTRING}            { return _out(LiveScriptTypes.STRING); }
    {SSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING_CON}           { return _out(LiveScriptTypes.ISTRING); }

    // Operators & punctuation
    {MATH_OP}               { return _out(LiveScriptTypes.MATH_OP); }

    {ASSIGN}                { return _out(LiveScriptTypes.ASSIGN); }


    // Identifiers
    {IDENTIFIER}            { return _out(LiveScriptTypes.IDENTIFIER); }


    // Non-code
    {NEWLINE}               { return _out(LiveScriptTypes.NEWLINE); }

    {COMMENT_LINE}          { return _out(LiveScriptTypes.COMMENT_LINE); }

    {SPACE}+                { return _out(TokenType.WHITE_SPACE); }
}

<INTERSTRING> {
    {DSTRING_RES}           { _exitState(); return _out(LiveScriptTypes.ISTRING); }
}

// Fall-through
.                       { return _out(TokenType.BAD_CHARACTER); }

/*
<YYINITIAL, INDENTED> {
    // Literals
    {NO_VALUE}              { return _out(LiveScriptTypes.RESERVED_LITERAL); }

    {BOOLEAN}               { return _out(LiveScriptTypes.RESERVED_LITERAL); }

    {NUMBER}|{BASED_NUMBER} { return _out(LiveScriptTypes.NUMBER); }

    // Strings

    {BACKSTRING}            { return _out(LiveScriptTypes.BACKSTRING); }

    {HEREDOC}               { return _out(LiveScriptTypes.HEREDOC); }

    {SSS}                   { _enterState(SIMPLE_STRING_STARTED); return _out(LiveScriptTypes.STRING_START); }

    {FSS}                   { _enterState(FULL_STRING_STARTED); return _out(LiveScriptTypes.STRING_START); }


    // Regex
    {REGEX}                 { return _out(LiveScriptTypes.REGEX); }

    {REGEX_MULTI_START}     { _enterState(REGEX); return _out(LiveScriptTypes.REGEX); }
    

    // Identifiers
    {THIS}                  { return LiveScriptTypes.THIS; }

    {THIS_AT}               { return LiveScriptTypes.THIS_AT; }

    {IDENTIFIER}            { return _out(LiveScriptTypes.IDENTIFIER); }
    

    // Operators

    {OPERATOR}              { return _out(LiveScriptTypes.OPERATOR); }

    {EQ}{SPACE}*{NEWLINE}   { _enterState(BLOCK_STATEMENT); return _out(LiveScriptTypes.EQ); }

    {EQ}                    { return _out(LiveScriptTypes.EQ); }

    {COLON}                 { return _out(LiveScriptTypes.COLON); }

    {COMMA}{SPACE}*         { return _out(LiveScriptTypes.COMMA); }

    {PAREN_L}               { return _out(LiveScriptTypes.PAREN_L); }

    {PAREN_R}               { return _out(LiveScriptTypes.PAREN_R); }

    {CURL_L}                { return _out(LiveScriptTypes.CURL_L); }

    // Only rewind if we're actually moving up the state stack,
    // if we rewind with the stack empty we'll just get stuck
    // in an infinite loop.
    {CURL_R}                { if (_exitState()) _rewindBy(1); else return LiveScriptTypes.CURL_R; }

    {BRACK_L}               { return LiveScriptTypes.BRACK_L; }

    {BRACK_R}               { return LiveScriptTypes.BRACK_R; }

    {DOT}                   { return LiveScriptTypes.DOT; }


    // Non-code
    {SPACE}+                { return _out(TokenType.WHITE_SPACE); }

    {NEWLINE}               { return _out(LiveScriptTypes.NEWLINE); }

    {COMMENT_LINE}          { return _out(LiveScriptTypes.COMMENT_LINE); }

    {COMMENT_BLOCK}         { return LiveScriptTypes.COMMENT_BLOCK; }


    {TEST}                  { return LiveScriptTypes.TEST; }
}

<INDENTED, BLOCK_STATEMENT> {
    ^{SPACE}+[^\r\n\t ] {
        System.out.println("State is " + yystate() + ", text is [" + yytext() + "]");
        _rewindBy(1);
        IElementType result = _parseIndent(yylength());
        if (result != null) return result;
    }
}

<INDENTED> {
    ^{SPACE}+{COMMENT_LINE} { _rewindTo("#"); return TokenType.WHITE_SPACE; }

    ^[^\r\n\t ].* {
        System.out.println("I> state is " + yystate() + " text is [" + yytext() + "], dedenting");
        _rewind();
        return _parseIndent(0);
    }
}

<REGEX> {

    {COMMENT_LINE}                  { return _out(LiveScriptTypes.COMMENT_LINE);}

    {REGEX_MULTI_END}               { _exitState(); return _out(LiveScriptTypes.REGEX); }

    ~(#|"//") {
        char c = yycharat(yylength() - 1);
        if (c == '#')
            _rewindBy(1);
        else if (c == '/')
            _rewindBy(2);
        else
            throw new Error("Unexpected character: [" + c + "] at the end of regex line.");
        return _out(LiveScriptTypes.REGEX);
    }
}


<SIMPLE_STRING_STARTED> {

    (\\{SSS}|[^\'])+       { return _out(LiveScriptTypes.STRING); }

    {SSS}                  { _exitState(); return _out(LiveScriptTypes.STRING_END); }
}


<FULL_STRING_STARTED> {
    (\\{FSS}|[^\"#]|\\#)+  { return _out(LiveScriptTypes.STRING); }

    {FSS}                   { _exitState(); return _out(LiveScriptTypes.STRING_END); }

    "#{"                    { _enterState(STRING_SUSPENDED); return LiveScriptTypes.STRING_INTER_START; }

    "#"{IDENTIFIER}         { _rewind(); _rewindBy(-1); _enterState(STRING_VARIABLE); return LiveScriptTypes.STRING; }

    "#"                     { return LiveScriptTypes.STRING; }
}


<STRING_VARIABLE> {
    // Return simple variables.
    {IDENTIFIER}    { _exitState(); return LiveScriptTypes.IDENTIFIER; }
}


// Intermediary state between FULL_STRING_STARTED and YYINITIAL, to properly handle entry/exit
<STRING_SUSPENDED> {
    // Once the interpolation is closed, leave the "Suspended" state and resume normal string.
    "}"             { _exitState(); return LiveScriptTypes.STRING_INTER_END; }

    // This state can only be reached by opening or closing a string interpolation,
    // so if it's not closing (handled above), it must be opening.
    .               { _rewindBy(1); _enterState(YYINITIAL); }
}
*/