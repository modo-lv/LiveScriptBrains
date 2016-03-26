package lv.modo.livescriptbrains.lexer;

public class LexerPatterns {
	/**
	 * A name of a variable, class, method or any other symbol.
	 */
	public static final String ID = "[\\p{IsAlphabetic}$_][\\p{IsAlphabetic}\\p{IsDigit}_-]*";

	/**
	 * Normal numbers.
	 */
	public static final String NUMBER = "^[0-9][0-9_]*\\.?[0-9_]*[a-zA-Z0-9]*";

	/**
	 * Numbers with a non-decimal base.
	 */
	public static final String BASED_NUMBER = "^(?:[0-9]|[1-2][0-9]|3[0-2])\\~[0-9a-zA-Z]+";


	public static final String BOOLEANS = "^(?:true|false|yes|no|on|off)\\b";


	/**
	 * Backstrings, such as "\word".
	 */
	public static final String BACKSTRING = "^\\\\.[^,\\]\\)\\} \\t\\r\\n]*\\b";

	/**
	 * Keywords meaning no value.
	 */
	public static final String EMPTY = "^(?:null|void)\\b";

	/**
	 * All keywords. Will be split into separate tokens as needed.
	 */
	public static final String KEYWORD = "^(?:class|extends|if|then|else|unless|otherwise|in|of|for|from|to|til|by|delete|do|while|new)\\b";

	/**
	 * The "this." token.
	 */
	public static final String THIS = "^(?:this\\b|@)";

	/**
	 * All operators.
	 */
	public static final String OPERATOR = "^(?:[!~=]=|(?:or|and|&&|not|xor)\\b|\\.\\.\\.|[/\\|*+=<>\\^~:;{}()\\[\\]!?-])";

	public static final String WHITESPACE = "^\\s+";

	public static final String DOT = "^\\.";
}
