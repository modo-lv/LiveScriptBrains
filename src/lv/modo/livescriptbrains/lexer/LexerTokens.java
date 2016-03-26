package lv.modo.livescriptbrains.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.psi.LiveScriptElementType;

public class LexerTokens
{
	public static final IElementType BAD_CHAR = TokenType.BAD_CHARACTER;
	public static final IElementType ERROR_ELEMENT = TokenType.ERROR_ELEMENT;

	public static final IElementType WHITESPACE = TokenType.WHITE_SPACE;
	public static final IElementType SKIP_EOL = new LiveScriptElementType("SKIP_EOL");
	public static final IElementType INDENT = new LiveScriptElementType("INDENT");

	public static final IElementType TERMINATOR = new LiveScriptElementType("TERMINATOR");

	public static final IElementType DOT = new LiveScriptElementType("DOT");
	public static final IElementType COMMA = new LiveScriptElementType("COMMA");
	public static final IElementType COLON = new LiveScriptElementType("COLON");
	public static final IElementType SEMICOLON = new LiveScriptElementType("SEMICOLON");

	public static final IElementType IDENTIFIER = new LiveScriptElementType("IDENTIFIER");

	public static final IElementType NUMBER = new LiveScriptElementType("NUMERIC_LITERAL");
	public static final IElementType BOOLEAN = new LiveScriptElementType("BOOLEAN");
	public static final IElementType EMPTY = new LiveScriptElementType("EMPTY");

	public static final IElementType ESCAPE_SEQUENCE = new LiveScriptElementType("ESCAPE_SEQUENCE");

	public static final IElementType STRING_LITERAL = new LiveScriptElementType("STRING_LITERAL");
	public static final IElementType STRING_START = new LiveScriptElementType("STRING_START");
	public static final IElementType STRING = new LiveScriptElementType("STRING");
	public static final IElementType STRING_END = new LiveScriptElementType("STRING_END");

	public static final IElementType REGEX_LITERAL = new LiveScriptElementType("REGEX_LITERAL");
	public static final IElementType REGEX_START = new LiveScriptElementType("REGEX_START");
	public static final IElementType REGEX = new LiveScriptElementType("REGEX");
	public static final IElementType REGEX_END = new LiveScriptElementType("REGEX_END");

	public static final IElementType INTERPOLATION_START = new LiveScriptElementType
		("INTERPOLATION_START");
	public static final IElementType INTERPOLATION_END = new LiveScriptElementType
		("INTERPOLATION_END");
	public static final IElementType INTERPOLATED_VAR_START = new LiveScriptElementType
		("INTERPOLATED_VAR_START");


	public static final IElementType JAVASCRIPT_LITERAL = new LiveScriptElementType
		("JAVASCRIPT_LITERAL");
	public static final IElementType JAVASCRIPT = new LiveScriptElementType("JAVASCRIPT");

	public static final IElementType COMMENT_LINE = new LiveScriptElementType("COMMENT_LINE");
	public static final IElementType COMMENT_BLOCK = new LiveScriptElementType("COMMENT_BLOCK");

	public static final IElementType PARENTHESIS_START = new LiveScriptElementType("LPAR");
	public static final IElementType PARENTHESIS_END = new LiveScriptElementType("RPAR");

	public static final IElementType BRACKET_START = new LiveScriptElementType("LBRACKET");
	public static final IElementType BRACKET_END = new LiveScriptElementType("RBRACKET");

	public static final IElementType BRACE_START = new LiveScriptElementType("LBRACE");
	public static final IElementType BRACE_END = new LiveScriptElementType("RBRACE");

	public static final IElementType EQ = new LiveScriptElementType("EQ");


	public static final IElementType GT = new LiveScriptElementType("GT");
	public static final IElementType GE = new LiveScriptElementType("GE");
	public static final IElementType LT = new LiveScriptElementType("LT");
	public static final IElementType LE = new LiveScriptElementType("LE");
	public static final IElementType EQ_EQ = new LiveScriptElementType("EQEQ");
	public static final IElementType EQ_EQ_EQ = new LiveScriptElementType("EQEQEQ");
	public static final IElementType NOT_EQ_EQ = new LiveScriptElementType("NEQEQ");
	public static final IElementType NE = new LiveScriptElementType("NE");
	public static final IElementType INSTANCEOF_KEYWORD = new LiveScriptElementType("INSTANCEOF_KEYWORD");
	public static final IElementType YIELD_KEYWORD = new LiveScriptElementType("YIELD_KEYWORD");


	public static final IElementType AND = new LiveScriptElementType("AND");
	public static final IElementType AND_AND = new LiveScriptElementType("ANDAND");
	public static final IElementType OR = new LiveScriptElementType("OR");
	public static final IElementType OR_OR = new LiveScriptElementType("OROR");
	public static final IElementType XOR = new LiveScriptElementType("XOR");

	public static final IElementType PLUS = new LiveScriptElementType("PLUS");
	public static final IElementType MINUS = new LiveScriptElementType("MINUS");
	public static final IElementType MULT = new LiveScriptElementType("MULT");
	public static final IElementType DIV = new LiveScriptElementType("DIV");
	public static final IElementType PERC = new LiveScriptElementType("PERC");

	public static final IElementType EXIST = new LiveScriptElementType("EXIST");
	public static final IElementType LT_LT = new LiveScriptElementType("LTLT");
	public static final IElementType GT_GT = new LiveScriptElementType("GTGT");
	public static final IElementType GT_GT_GT = new LiveScriptElementType("GTGTGT");

	public static final IElementType POWER = new LiveScriptElementType("POWER");
	public static final IElementType POWER_EQ = new LiveScriptElementType("POWER_EQ");
	public static final IElementType FLOOR = new LiveScriptElementType("FLOOR");
	public static final IElementType FLOOR_EQ = new LiveScriptElementType("FLOOR_EQ");
	public static final IElementType MODULO = new LiveScriptElementType("MODULO");
	public static final IElementType MODULO_EQ = new LiveScriptElementType("MODULO_EQ");


	public static final IElementType AND_EQ = new LiveScriptElementType("ANDEQ");
	public static final IElementType AND_AND_EQ = new LiveScriptElementType("AND_AND_EQ");
	public static final IElementType OR_EQ = new LiveScriptElementType("OREQ");
	public static final IElementType OR_OR_EQ = new LiveScriptElementType("OR_OR_EQ");
	public static final IElementType XOR_EQ = new LiveScriptElementType("XOREQ");
	public static final IElementType LT_LT_EQ = new LiveScriptElementType("LTLTEQ");
	public static final IElementType GT_GT_EQ = new LiveScriptElementType("GTGTEQ");
	public static final IElementType GT_GT_GT_EQ = new LiveScriptElementType("GTGTGTEQ");

	public static final IElementType PLUS_EQ = new LiveScriptElementType("PLUSEQ");
	public static final IElementType MINUS_EQ = new LiveScriptElementType("MINUSEQ");
	public static final IElementType MULT_EQ = new LiveScriptElementType("MULTEQ");
	public static final IElementType DIV_EQ = new LiveScriptElementType("DIVEQ");
	public static final IElementType PERC_EQ = new LiveScriptElementType("PERCEQ");
	public static final IElementType EXIST_EQ = new LiveScriptElementType("EXIST_EQ");


	public static final IElementType RANGE = new LiveScriptElementType("RANGE");
	public static final IElementType SPLAT = new LiveScriptElementType("SPLAT");
	public static final IElementType THIS = new LiveScriptElementType("THIS");
	public static final IElementType PROTOTYPE = new LiveScriptElementType("PROTOTYPE");
	public static final IElementType FUNCTION = new LiveScriptElementType("FUNCTION");
	public static final IElementType FUNCTION_BIND = new LiveScriptElementType("FUNCTION_BIND");


	public static final IElementType CLASS = new LiveScriptElementType("CLASS");
	public static final IElementType EXTENDS = new LiveScriptElementType("EXTENDS");
	public static final IElementType IF = new LiveScriptElementType("IF");
	public static final IElementType ELSE = new LiveScriptElementType("ELSE_KEYWORD");
	public static final IElementType THEN = new LiveScriptElementType("THEN");
	public static final IElementType UNLESS = new LiveScriptElementType("UNLESS");
	public static final IElementType FOR = new LiveScriptElementType("FOR");
	public static final IElementType IN_KEYWORD = new LiveScriptElementType("IN_KEYWORD");
	public static final IElementType OWN = new LiveScriptElementType("OWN");
	public static final IElementType FROM = new LiveScriptElementType("FROM");
	public static final IElementType TO = new LiveScriptElementType("TO");
	public static final IElementType TIL = new LiveScriptElementType("TIL");
	public static final IElementType BY = new LiveScriptElementType("BY");
	public static final IElementType OF = new LiveScriptElementType("OF");
	public static final IElementType WHILE = new LiveScriptElementType("WHILE");
	public static final IElementType LOOP = new LiveScriptElementType("LOOP");
	public static final IElementType UNTIL = new LiveScriptElementType("UNTIL");
	public static final IElementType SWITCH = new LiveScriptElementType("SWITCH");
	public static final IElementType WHEN = new LiveScriptElementType("WHEN");
	public static final IElementType TRY = new LiveScriptElementType("TRY");
	public static final IElementType CATCH = new LiveScriptElementType("CATCH");
	public static final IElementType THROW = new LiveScriptElementType("THROW");
	public static final IElementType FINALLY = new LiveScriptElementType("FINALLY_KEYWORD");
	public static final IElementType BREAK = new LiveScriptElementType("BREAK");
	public static final IElementType CONTINUE = new LiveScriptElementType("CONTINUE");
	public static final IElementType DEBUGGER = new LiveScriptElementType("DEBUGGER");
	public static final IElementType RETURN = new LiveScriptElementType("RETURN");
	public static final IElementType SUPER = new LiveScriptElementType("SUPER");
	public static final IElementType DO_KEYWORD = new LiveScriptElementType("DO");
	public static final IElementType NEW_KEYWORD = new LiveScriptElementType("NEW_KEYWORD");
	public static final IElementType DELETE_KEYWORD = new LiveScriptElementType("DELETE_KEYWORD");

	public static final IElementType TYPEOF_KEYWORD = new LiveScriptElementType("TYPEOF_KEYWORD");
	public static final IElementType EXCL = new LiveScriptElementType("EXCL");
	public static final IElementType TILDE = new LiveScriptElementType("TILDE");
	public static final IElementType PLUS_PLUS = new LiveScriptElementType("PLUS_PLUS");
	public static final IElementType MINUS_MINUS = new LiveScriptElementType("MINUS_MINUS");
	public static final IElementType MULT_MULT = new LiveScriptElementType("MULT_MULT");


}
