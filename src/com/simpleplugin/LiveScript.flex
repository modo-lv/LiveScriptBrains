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

%{
  private final Stack<Integer> stack = new Stack<Integer>();

  /**
   * Push the actual state on top of the stack
   */
  private void pushState() {
    stack.push(yystate());
  }

  /**
   * Push the actual state on top of the stack
   * and change into another state
   *
   * @param state The new state
   */
  private void enterState(int state) {
    stack.push(yystate());
    yybegin(state);
  }

  /**
   * Pop the last state from the stack and change to it.
   * If the stack is empty, go to YYINITIAL
   */
    private boolean leaveState() {
        if (!stack.empty()) {
            yybegin(stack.pop());
            return true;
        } else {
            yybegin(YYINITIAL);
            return false;
        }
    }


  /**
   * Push the stream back to the position before the text match
   *
   * @param text The text to match
   * @return true when matched
   */
  private boolean rewindTo(String text) {
    final int position = yytext().toString().indexOf(text);

    if (position != -1) {
      yypushback(yylength() - position);
      return true;
    }

    return false;
  }


    // Move the input stream read position back.
    private LiveScriptLexer rewindBy(int count) {
        yypushback(count);
        return this;
    }

    // Advance the read position in the stream by the given number.
    private LiveScriptLexer advanceBy(int count) {
        yypushback(-count);
        return this;
    }


    /**
     * Rewind the input stream back to the position before
     * the text match and leave current state.
     */
    private boolean rewindAndLeaveState(String text) {
        final boolean success = rewindTo(text);
        if (success) {
            leaveState();
        }
        return true;
    }

  /**
   * Push the stream back to the position before the text match
   * and change into the given state
   *
   * @param text The text to match
   * @param state The new state
   * @return true when matched
   */
  private boolean pushBackAndState(String text, int state) {
    final boolean success = rewindTo(text);

    if (success) {
      enterState(state);
    }

    return success;
  }
%}

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

BINARY_OP = [-+*/]

EQ = "="
GLOBAL_EQ = ":="
HEREDOC = \'\'\'(.|[\r\n])*\'\'\'

SIMPLE_STRING_START = "'"
FULL_STRING_START = "\""

PAREN_L = "("
PAREN_R = ")"

CURL_L = "{"
CURL_R = "}"


WHITE_SPACE = [\t\ ]+

/*
FIRST_VALUE_CHARACTER=[^ \n\r\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\r\t\f\\] | "\\"{CRLF} | "\\".
*/

%state SIMPLE_STRING_STARTED, FULL_STRING_STARTED, STRING_SUSPENDED, BACK_STRING_STARTED, STRING_VARIABLE
%state HEREDOC_STARTED

%%

<YYINITIAL> {
    {NULL}                                                      { return LiveScriptTypes.NULL; }

    {BOOLEAN}                                                   { return LiveScriptTypes.BOOLEAN; }

    {BASED_NUMBER}                                              { return LiveScriptTypes.NUMBER; }

    {NUMBER}                                                    { return LiveScriptTypes.NUMBER; }

    {PAREN_L}                                                   { return LiveScriptTypes.PAREN_L; }

    {PAREN_R}                                                   { return LiveScriptTypes.PAREN_R; }

    {IDENTIFIER}                                                { return LiveScriptTypes.IDENTIFIER; }

    {EQ}                                                        { return LiveScriptTypes.EQ; }

    {GLOBAL_EQ}                                                 { return LiveScriptTypes.GLOBAL_EQ; }

    {WHITE_SPACE}                                               { return TokenType.WHITE_SPACE; }

    {HEREDOC}       { return LiveScriptTypes.HEREDOC; }

    \'              { enterState(SIMPLE_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    \"|%\"          { enterState(FULL_STRING_STARTED); return LiveScriptTypes.STRING_START; }

    {BACKSTRING}    { return LiveScriptTypes.BACKSTRING; }
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

{NEWLINE}           { return LiveScriptTypes.NEWLINE; }

.                   { return TokenType.BAD_CHARACTER; }
