package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;

%%

%class LiveScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


BASED_NUMBER = ([1-2][0-9]|3[0-2])\~[0-9a-zA-Z]+
NUMBER = [0-9][0-9_]*\.?[0-9_]*[a-zA-Z]*
IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

SIMPLE_STRING = '(\\'|[^'])*'

EQ = "="
GLOBAL_EQ = ":="

NEWLINE = \r\n|[\r\n]
WHITE_SPACE = [\t\ ]+

/*
FIRST_VALUE_CHARACTER=[^ \n\r\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\r\f\\] | "\\"{CRLF} | "\\".
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\r\t\f\\] | "\\"{CRLF} | "\\".
*/

//%state WAITING_VALUE

%%

{SIMPLE_STRING}                                             { return LiveScriptTypes.SIMPLE_STRING; }

{IDENTIFIER}                                                { return LiveScriptTypes.IDENTIFIER; }

{BASED_NUMBER}                                              { return LiveScriptTypes.NUMBER; }

{NUMBER}                                                    { return LiveScriptTypes.NUMBER; }

{EQ}                                                        { return LiveScriptTypes.EQ; }

{GLOBAL_EQ}                                                 { return LiveScriptTypes.GLOBAL_EQ; }

{WHITE_SPACE}                                               { return TokenType.WHITE_SPACE; }

{NEWLINE}                                                   { return LiveScriptTypes.NEWLINE; }

.                                                           { return TokenType.BAD_CHARACTER; }