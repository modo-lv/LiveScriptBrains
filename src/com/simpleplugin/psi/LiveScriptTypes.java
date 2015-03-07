package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;

public class LiveScriptTypes {
	public static IElementType ASSIGN_OPERATION = new LiveScriptElementType("ASSIGN_OPERATION");
	public static IElementType COMMENT = new LiveScriptElementType("COMMENT");
	public static IElementType I_STRING_STATEMENT = new LiveScriptElementType("I_STRING_STATEMENT");
	public static IElementType LITERAL = new LiveScriptElementType("LITERAL");
	public static IElementType MATH_OPERATION = new LiveScriptElementType("MATH_OPERATION");
	public static IElementType OPERATION = new LiveScriptElementType("OPERATION");
	public static IElementType OPERATION_OR_VALUE = new LiveScriptElementType("OPERATION_OR_VALUE");
	public static IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
	public static IElementType VALUE = new LiveScriptElementType("VALUE");

	public static IElementType ASSIGN = new LiveScriptElementType("ASSIGN");
	public static IElementType BOOLEAN = new LiveScriptElementType("BOOLEAN");
	public static IElementType COMMENT_LINE = new LiveScriptElementType("COMMENT_LINE");
	public static IElementType EMPTY = new LiveScriptElementType("EMPTY");
	public static IElementType IDENTIFIER = new LiveScriptElementType("IDENTIFIER");
	public static IElementType ISTRING = new LiveScriptElementType("ISTRING");
	public static IElementType MATH_OP = new LiveScriptElementType("MATH_OP");
	public static IElementType NEWLINE = new LiveScriptElementType("NEWLINE");
	public static IElementType NUMBER = new LiveScriptElementType("NUMBER");
	public static IElementType STRING = new LiveScriptElementType("STRING");
	public static IElementType UNKNOWN = new LiveScriptElementType("UNKNOWN");

	public static IElementType EXPRESSION = new LiveScriptElementType("Expression");
	public static IElementType EXPRESSION_OR_VALUE = new LiveScriptElementType("ExpressionOrValue");
}