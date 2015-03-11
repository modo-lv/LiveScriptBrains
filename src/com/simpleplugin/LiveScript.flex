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
        yybegin(state);
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


// Regex
REGEX = \/[^\/] ~\/g?m?i?
REGEX_MULTI_START = "//"
REGEX_MULTI_END = \/\/g?m?i?

// Operators
MATH_OP = [-*/]
PLUS = "+"
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
SEMICOLON = ";"

IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

// Comments
COMMENT_LINE = #.*
COMMENT_BLOCK = \/\*(.|{CRLF})*\*\/

// Whitespace
CRLF = [\r\n]
NEWLINE = \r\n|{CRLF}
SPACE = [\f\t ]

TEST = "!@#$%^&*()TEEEEEEEEESESESETESTESTETETTTT!!)*(!)@(*)*(##"
UNKNOWN=[:().]


%state INDENTED, BLOCK_STATEMENT
%state DSTRING, ISTRING, VSTRING, STRING_SUSPENDED
%state REGEX

%{
    private String[] _stateNames = new String[] {
        "YYINITIAL",
        "INDENTED",
        "BLOCK_STATEMENT",
        "DSTRING",
        "ISTRING",
        "VSTRING",
        "STRING_SUSPENDED",
        "REGEX",
    };
    private String _stateName(int state) {
        return _stateNames[state / 2];
    }
%}

%%

// Inside a double-quoted string
<DSTRING> {
    {ISTRING_START}         { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING); }

    #{IDENTIFIER}           { return _out(LiveScriptTypes.IDENTIFIER); }

    // Everything else is just regular string.
    ({ISTRING}|#\{\})*\"    { _exitState(); return _out(LiveScriptTypes.STRING); }

    ({ISTRING}|#\{\})*      { return _out(LiveScriptTypes.STRING); }
}

<ISTRING> {
    {CURL_R}                { _exitState(); return _out(LiveScriptTypes.ISTRING); }
}

<YYINITIAL, ISTRING> {
    // Literals
    {EMPTY}                 { return _out(LiveScriptTypes.EMPTY); }

    {BOOLEAN}               { return _out(LiveScriptTypes.BOOLEAN); }

    {NUMBER}|{BASED_NUMBER} { return _out(LiveScriptTypes.NUMBER); }

    // Strings
    {HEREDOC}               { return _out(LiveScriptTypes.STRING); }
    {BACKSTRING}            { return _out(LiveScriptTypes.STRING); }
    {SSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING}               { return _out(LiveScriptTypes.STRING); }
    {DSTRING_START}         { _enterState(DSTRING); return _out(LiveScriptTypes.STRING); }

    {IDENTIFIER}            { return _out(LiveScriptTypes.IDENTIFIER); }

    // Operators & punctuation
    {MATH_OP}               { return _out(LiveScriptTypes.MATH_OP); }
    {PLUS}					{ return _out(LiveScriptTypes.PLUS); }

    {ASSIGN}                { return _out(LiveScriptTypes.ASSIGN); }
    
    {BRACK_L}				{ return _out(LiveScriptTypes.LIST_START); }
    {BRACK_R}				{ return _out(LiveScriptTypes.LIST_END); }
    
    {CURL_L}				{ return _out(LiveScriptTypes.OBJ_START); }
    {CURL_R}				{ return _out(LiveScriptTypes.OBJ_END); }

    {PAREN_L}				{ return _out(LiveScriptTypes.PAREN_L); }
    {PAREN_R}				{ return _out(LiveScriptTypes.PAREN_R); }

    {COMMA}					{ return _out(LiveScriptTypes.COMMA); }

    {COLON}					{ return _out(LiveScriptTypes.COLON); }

    {SEMICOLON}				{ return _out(LiveScriptTypes.SEMICOLON); }

    // Non-code
    {NEWLINE}               { return _out(LiveScriptTypes.NEWLINE); }

    {COMMENT_LINE}          { return _out(LiveScriptTypes.COMMENT_LINE); }

    {SPACE}+                { return _out(TokenType.WHITE_SPACE); }

}

// Fall-through
.                       { System.out.println("State is " + _stateName(yystate())); return _out(TokenType.BAD_CHARACTER); }