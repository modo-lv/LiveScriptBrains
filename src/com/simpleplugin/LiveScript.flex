package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;

%%

%class LiveScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    System.out.println("End of file reached, clearing out indentation stack.");
    _currentIndent = 0;
    _indents.clear();
    return;
%eof}

%{
    /**
     * Stack for keeping track of lexical states.
     */
    private static Stack<Integer> _states = new Stack<Integer>();

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
        if (count > _currentIndent) {
            _indents.push(_currentIndent);
            _currentIndent += count;
            _enterState(INDENTED);
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
        return null;
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        System.out.println("Entering state " + state);
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
            System.out.println("Exiting state...");
            yybegin(_states.pop());
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
NO_VALUE=null|void
BOOLEAN=true|false|yes|no|on|off

// Strings
BACKSTRING = \\[^,;\]\)\} \t\r\n]+
HEREDOC = \'\'\'(.|[\r\n])*\'\'\'
SSS = \' // Simple string start
FSS = %\"|\" // Full string start



OPERATOR = [-+*/=]
IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

// Comments
COMMENT_LINE = #.*
COMMENT_BLOCK = \/\*(.|{CRLF})*\*\/

// Whitespace
CRLF = [\r\n]
NEWLINE = \r\n|{CRLF}
SPACE = [\f\t ]


%state INDENTED, BLOCK_STATEMENT
%state SIMPLE_STRING_STARTED, FULL_STRING_STARTED, STRING_VARIABLE, STRING_SUSPENDED

%%

<YYINITIAL> {
    {NO_VALUE}              { return LiveScriptTypes.RESERVED_LITERAL; }

    {BOOLEAN}               { return LiveScriptTypes.RESERVED_LITERAL; }

    {IDENTIFIER}            { return _out(LiveScriptTypes.IDENTIFIER); }

    // Strings

    {BACKSTRING}            { return LiveScriptTypes.BACKSTRING; }

    {HEREDOC}               { return LiveScriptTypes.HEREDOC; }

    {SSS}                   { _enterState(SIMPLE_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    {FSS}                   { _enterState(FULL_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    {OPERATOR}              { return LiveScriptTypes.OPERATOR; }

    {NUMBER}|{BASED_NUMBER} { return LiveScriptTypes.NUMBER; }

    // Non-code
    {SPACE}+                { return _out(TokenType.WHITE_SPACE); }

    {NEWLINE}               { return _out(LiveScriptTypes.NEWLINE); }

    {COMMENT_LINE}          { return LiveScriptTypes.COMMENT; }

    {COMMENT_BLOCK}         { return LiveScriptTypes.COMMENT; }
}


<SIMPLE_STRING_STARTED> {

    (\\\'|[^\'])+       { return LiveScriptTypes.STRING; }

    \'                  { _exitState(); return LiveScriptTypes.STRING_END; }
}


<FULL_STRING_STARTED> {
    (\\\"|[^\"#]|\\#)+  { return LiveScriptTypes.STRING; }

    "\""                { _exitState(); return LiveScriptTypes.STRING_END; }

    "#{"                { _enterState(STRING_SUSPENDED); return LiveScriptTypes.STRING_END; }

    "#"{IDENTIFIER}     { _rewind(); _rewindBy(-1); _enterState(STRING_VARIABLE); return LiveScriptTypes.STRING; }

    "#"                 { return LiveScriptTypes.STRING; }
}

<STRING_VARIABLE> {
    // Return simple variables.
    {IDENTIFIER}    { _exitState(); return LiveScriptTypes.IDENTIFIER; }
}

// Intermediary state between FULL_STRING_STARTED and YYINITIAL, to properly handle entry/exit
<STRING_SUSPENDED> {
    // Once the interpolation is closed, leave the "Suspended" state and resume normal string.
    "}"             { _exitState(); return LiveScriptTypes.STRING_START; }

    // This state can only be reached by opening or closing a string interpolation,
    // so if it's not closing (handled above), it must be opening.
    .               { _rewindBy(1); _enterState(YYINITIAL); }
}


<BLOCK_STATEMENT> {
    ^{SPACE}+[^\r\n\t ] {
        System.out.println("State is " + yystate() + ", text is [" + yytext() + "]");
        _rewindBy(1);
        IElementType result = _parseIndent(yylength());
        if (result != null) return result;
    }

    <INDENTED> {
        // Line comments don't affect indentation.
        ^{SPACE}*{COMMENT_LINE} { _rewindTo("#"); _exitState(); return TokenType.WHITE_SPACE; }

        ^[^\r\n\t ].* {
            System.out.println("I> text is [" + yytext() + "], dedenting");
            _rewind();
            return _parseIndent(0);
        }
    }
}

// Only rewind if we're actually moving up the state stack,
// if we rewind with the stack empty we'll just get stuck
// in an infinite loop.
"}" { if (_exitState()) _rewindBy(1); }

.   { return TokenType.BAD_CHARACTER; }





/*
%type IElementType
%eof{  return;
%eof}

NEWLINE = \r\n|[\r\n]

NULL=null|void
BOOLEAN = true|false|on|off|yes|no

BASED_NUMBER = ([0-9]|[1-2][0-9]|3[0-2])\~[0-9a-zA-Z]+
NUMBER = [0-9][0-9_]*\.?[0-9_]*[a-zA-Z]*

IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*
BACKSTRING = \\[^,;\]\)\} \t\r\n]+

// Normal operators - will take the operand(s) from the next line if necessary
OP = [-+*//*
]

// Operators that require block indent if the operand is on the next line
BLOCK_OP = [=:]|:=


HEREDOC = \'\'\'(.|[\r\n])*\'\'\'

PAREN_L = "("
PAREN_R = ")"


WHITE_SPACE = [\t\ ]+

%state SIMPLE_STRING_STARTED, FULL_STRING_STARTED, STRING_SUSPENDED, BACK_STRING_STARTED, STRING_VARIABLE
%state INDENTED, BLOCK_OP, CHECK_BLOCK_END

%%

<YYINITIAL> {
    {NULL}          { return LiveScriptTypes.NULL; }

    {BOOLEAN}       { return LiveScriptTypes.BOOLEAN; }

    {BASED_NUMBER}  { return LiveScriptTypes.NUMBER; }

    {NUMBER}        { return LiveScriptTypes.NUMBER; }

    {PAREN_L}       { return LiveScriptTypes.PAREN_L; }

    {PAREN_R}       { return LiveScriptTypes.PAREN_R; }

    {IDENTIFIER}    { return LiveScriptTypes.IDENTIFIER; }

    {OP}            { return LiveScriptTypes.OPERATOR; }

    {BLOCK_OP}      { enterState(BLOCK_OP); return LiveScriptTypes.OPERATOR; }

    {WHITE_SPACE}   { return TokenType.WHITE_SPACE; }

    {HEREDOC}       { return LiveScriptTypes.HEREDOC; }

    \'              { enterState(SIMPLE_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    \"|%\"          { enterState(FULL_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    {BACKSTRING}    { return LiveScriptTypes.BACKSTRING; }

}

*/
/**
 * Check if the next statements are in a function block.
 *//*

<BLOCK_OP> {
    */
/**
     * Block operator followed immediately by newline means a new block has started
     *//*

    {NEWLINE}   {
                    enterNewBlock();
                    changeStateTo(INDENTED);
                    return LiveScriptTypes.BLOCK_START;
                }

    */
/**
     * Anything other than a newline means no block, continue on the same line.
     *//*

    .           { leaveState(); rewindBy(1); }
}

// Track indentation.
<INDENTED> {

    {NEWLINE}   {
                    if (currentIndent <= currentBlockIndent) {
                        exitBlock();
                        currentIndent = 0;
                        return LiveScriptTypes.BLOCK_END;
                    }
                    currentIndent = 0;
                    return LiveScriptTypes.NEWLINE;
                }

    \t          {
                    if (tabIndents == null) tabIndents = true;
                    if (tabIndents) currentIndent++;
                    if (currentIndent == 1)
                        return LiveScriptTypes.INDENT;
                }

    " "         {
                    if (tabIndents == null) tabIndents = false;
                    if (!tabIndents) currentIndent++;
                    if (currentIndent == 1)
                        return LiveScriptTypes.INDENT;
                }

    .           {
                    enterState(YYINITIAL);
                    rewindBy(1);
                }

}


<SIMPLE_STRING_STARTED> {

    (\\\'|[^\'])+       { return LiveScriptTypes.STRING; }

    \'                  { leaveState(); return LiveScriptTypes.STRING_END; }
}

<FULL_STRING_STARTED> {
    (\\\"|[^\"#]|\\#)+  { return LiveScriptTypes.STRING; }

    "\""                { leaveState(); return LiveScriptTypes.STRING_END; }

    "#{"                { enterState(STRING_SUSPENDED); return LiveScriptTypes.STRING_END; }

    "#"{IDENTIFIER}     { rewindTo("#"); advanceBy(1); enterState(STRING_VARIABLE); return LiveScriptTypes.STRING; }

    "#"                 { return LiveScriptTypes.STRING; }
}

<STRING_VARIABLE> {
    // Return simple variables.
    {IDENTIFIER}    { leaveState(); return LiveScriptTypes.IDENTIFIER; }
}

// Intermediary state between FULL_STRING_STARTED and YYINITIAL, to properly handle entry/exit
<STRING_SUSPENDED> {
    // Once the interpolation is closed, leave the "Suspended" state and resume normal string.
    "}"             { leaveState(); return LiveScriptTypes.STRING_START; }

    // This state can only be reached by opening or closing a string interpolation,
    // so if it's not closing (handled above), it must be opening.
    .               { rewindBy(1); enterState(YYINITIAL); }
}


//{CURL_L}            { return LiveScriptTypes.CURL_L; }

// Only rewind if we're actually moving up the state stack,
// if we rewind with the stack empty we'll just get stuck
// in an infinite loop.
"}"                 { if (leaveState()) rewindBy(1); }

{NEWLINE}           {
                        // If we are in a special state, rewind the position so that
                        // the newline gets processed by the previous state.
                        if (leaveState()) rewind();

                        return LiveScriptTypes.NEWLINE;
                    }

.                   { return TokenType.BAD_CHARACTER; }
*/
