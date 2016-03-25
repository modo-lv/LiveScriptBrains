package lv.modo.livescriptbrains.psi;

import com.intellij.psi.tree.IElementType;

public class LiveScriptTypes {
	public static final IElementType DEDENT = new LiveScriptElementType("DEDENT");
	/**
	 * Parser types
	 */
	public static IElementType None = new LiveScriptElementType("None");
	
	public static IElementType AssignOperation = new LiveScriptElementType("AssignOperation");
	public static IElementType Block = new LiveScriptElementType("Block");
	public static IElementType EmptyClass = new LiveScriptElementType("EmptyClass");
	public static IElementType FuncArgList = new LiveScriptElementType("FuncArgList");
	public static IElementType FuncCall = new LiveScriptElementType("FuncCall");
	public static IElementType ImplicitList = new LiveScriptElementType("Implicit list");
	public static IElementType ArgumentList = new LiveScriptElementType("ArgumentList");
	public static IElementType EOF = new LiveScriptElementType("EOF");
	public static IElementType List = new LiveScriptElementType("List");
	public static IElementType Object = new LiveScriptElementType("Object");
	public static IElementType ParenOp = new LiveScriptElementType("ParenOp");
	public static IElementType PropertyAccess = new LiveScriptElementType("Property expression");
	public static IElementType PropDefOp = new LiveScriptElementType("PropDef expression");
	public static IElementType StringOp = new LiveScriptElementType("Double-quoted string");
	public static IElementType SumOp = new LiveScriptElementType("Sum expression");
	public static IElementType IfExists = new LiveScriptElementType("IfExists expression");
	


	/**
	 * Fake values (used in parsing but not in PSI or lexer)
	 */
	public static IElementType LITERAL = new LiveScriptElementType("LITERAL");
	public static IElementType Value = new LiveScriptElementType("Value");
	public static IElementType OPERATOR = new LiveScriptElementType("OPERATOR");
	public static IElementType Expression = new LiveScriptElementType("Expression");
	public static IElementType Separator = new LiveScriptElementType("Separator");


	/**
	 * Lexer types
	 */
	public static IElementType ASSIGN = new LiveScriptElementType("ASSIGN");
	public static IElementType BANG = new LiveScriptElementType("BANG");
	public static IElementType BOOLEAN = new LiveScriptElementType("BOOLEAN");
	public static IElementType BAD_CHAR = new LiveScriptElementType("BAD_CHAR");
	public static IElementType CLASS = new LiveScriptElementType("CLASS");
	public static IElementType COLON = new LiveScriptElementType("COLON");
	public static IElementType COMMA = new LiveScriptElementType("COMMA");
	public static IElementType COMMENT_BLOCK = new LiveScriptElementType("COMMENT_BLOCK");
	public static IElementType COMMENT_LINE = new LiveScriptElementType("COMMENT_LINE");
	public static IElementType DOT = new LiveScriptElementType("DOT");
	public static IElementType EMPTY = new LiveScriptElementType("EMPTY");
	public static IElementType ESCAPE_CHAR = new LiveScriptElementType("ESCAPE_CHAR");
	public static IElementType IDENTIFIER = new LiveScriptElementType("IDENTIFIER");
	public static IElementType INDENT = new LiveScriptElementType("INDENT");
	public static IElementType ISTRING = new LiveScriptElementType("ISTRING");
	public static IElementType LIST_START = new LiveScriptElementType("LIST_START");
	public static IElementType LIST_END = new LiveScriptElementType("LIST_END");
	public static IElementType LOGIC_OP = new LiveScriptElementType("LOGIC_OP");
	public static IElementType KEYWORD = new LiveScriptElementType("KEYWORD");
	public static IElementType MATH_OP = new LiveScriptElementType("MATH_OP");
	public static IElementType MISC_OP = new LiveScriptElementType("MISC_OP");
	public static IElementType NEWLINE = new LiveScriptElementType("NEWLINE");
	public static IElementType NUMBER = new LiveScriptElementType("NUMBER");
	public static IElementType OBJ_END = new LiveScriptElementType("OBJ_END");
	public static IElementType OBJ_START = new LiveScriptElementType("OBJ_START");
	public static IElementType PAREN_R = new LiveScriptElementType("PAREN_R");
	public static IElementType PAREN_L = new LiveScriptElementType("PAREN_L");
	public static IElementType PLUS = new LiveScriptElementType("PLUS");
	public static IElementType Q = new LiveScriptElementType("Q");
	public static IElementType SEPARATOR = new LiveScriptElementType("SEPARATOR");
	public static IElementType SEMICOLON = new LiveScriptElementType("SEMICOLON");
	public static IElementType STRING = new LiveScriptElementType("STRING");
	public static final IElementType STRING_BROKEN = new LiveScriptElementType("STRING_BROKEN");
	public static IElementType STRING_START = new LiveScriptElementType("STRING_START");
	public static IElementType STRING_END = new LiveScriptElementType("STRING_END");
	public static IElementType THIS = new LiveScriptElementType("THIS");
	public static IElementType YADA = new LiveScriptElementType("YADA");


	/**
	 * All operation (expression) elements.
	 */
	public static IElementType[] ExpressionTypes = new IElementType[] {
		AssignOperation,
		SumOp,
		IDENTIFIER,
		NUMBER,
		STRING,
		IfExists,
		PropertyAccess,
		EmptyClass
	};
}
