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


NUMBER = (([1-9]|[1-2]\d?|3[0-2])~[a-zA-Z\d]+|\d[_\d]*\.?[_\d]+[a-zA-Z]*)
IDENTIFIER = [$_a-zA-Z][-$_a-zA-Z0-9]*

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


{IDENTIFIER}                                                { return LiveScriptTypes.IDENTIFIER; }

{NUMBER}                                                    { return LiveScriptTypes.NUMBER; }

{EQ}                                                        { return LiveScriptTypes.EQ; }

{GLOBAL_EQ}                                                 { return LiveScriptTypes.GLOBAL_EQ; }

{WHITE_SPACE}                                               { return TokenType.WHITE_SPACE; }

{NEWLINE}                                                   { return LiveScriptTypes.NEWLINE; }

.                                                           { return TokenType.BAD_CHARACTER; }