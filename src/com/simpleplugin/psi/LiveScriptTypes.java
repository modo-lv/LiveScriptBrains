package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.List;

public class LiveScriptTypes {
	/**
	 * Parser types
	 */
	public static IElementType None = new LiveScriptElementType("None");
	
	public static IElementType ASSIGN_OPERATION = new LiveScriptElementType("ASSIGN_OPERATION");
	public static IElementType Block = new LiveScriptElementType("Block");
	public static IElementType FuncArgList = new LiveScriptElementType("FuncArgList");
	public static IElementType FuncCall = new LiveScriptElementType("FuncCall");
	public static IElementType ARGUMENT_LIST = new LiveScriptElementType("ARGUMENT_LIST");
	public static IElementType EOF = new LiveScriptElementType("EOF");
	public static IElementType List = new LiveScriptElementType("List");
	public static IElementType ParenOp = new LiveScriptElementType("ParenOp");
	public static IElementType SumOp = new LiveScriptElementType("SumOp");


	/**
	 * Fake values (used in parsing but not in PSI or lexer)
	 */
	public static IElementType LITERAL = new LiveScriptElementType("LITERAL");
	public static IElementType Value = new LiveScriptElementType("Value");
	public static IElementType OPERATOR = new LiveScriptElementType("OPERATOR");
	public static IElementType Operation = new LiveScriptElementType("Operation");
	public static IElementType Separator = new LiveScriptElementType("Separator");


	/**
	 * Lexer types
	 */
	public static IElementType ASSIGN = new LiveScriptElementType("ASSIGN");
	public static IElementType BANG = new LiveScriptElementType("BANG");
	public static IElementType BOOLEAN = new LiveScriptElementType("BOOLEAN");
	public static IElementType COMMA = new LiveScriptElementType("COMMA");
	public static IElementType COMMENT_LINE = new LiveScriptElementType("COMMENT_LINE");
	public static IElementType EMPTY = new LiveScriptElementType("EMPTY");
	public static IElementType IDENTIFIER = new LiveScriptElementType("IDENTIFIER");
	public static IElementType INDENT = new LiveScriptElementType("INDENT");
	public static IElementType ISTRING = new LiveScriptElementType("ISTRING");
	public static IElementType LIST_START = new LiveScriptElementType("LIST_START");
	public static IElementType LIST_END = new LiveScriptElementType("LIST_END");
	public static IElementType MATH_OP = new LiveScriptElementType("MATH_OP");
	public static IElementType NEWLINE = new LiveScriptElementType("NEWLINE");
	public static IElementType NUMBER = new LiveScriptElementType("NUMBER");
	public static IElementType OBJ_END = new LiveScriptElementType("OBJ_END");
	public static IElementType OBJ_START = new LiveScriptElementType("OBJ_START");
	public static IElementType PAREN_R = new LiveScriptElementType("PAREN_R");
	public static IElementType PAREN_L = new LiveScriptElementType("PAREN_L");
	public static IElementType PLUS = new LiveScriptElementType("PLUS");
	public static IElementType SEMICOLON = new LiveScriptElementType("SEMICOLON");
	public static IElementType COLON = new LiveScriptElementType("COLON");
	public static IElementType STRING = new LiveScriptElementType("STRING");


	public static IElementType EXPRESSION = new LiveScriptElementType("Expression");
	public static IElementType EXPRESSION_OR_VALUE = new LiveScriptElementType("ExpressionOrValue");

	/**
	 * All operation (expression) elements.
	 */
	public static IElementType[] OpElements = new IElementType[] {
		ASSIGN_OPERATION,
		SumOp,
		IDENTIFIER,
		NUMBER,
		STRING,
	};
}
