package lv.modo.livescriptbrains.psi.lexer;

public class LexerStates {
	public static final int NORMAL = 0;
	public static final int STATE_INDENT = 1;
	/**
	 * Double-quoted string with interpolation support
	 */
	public static final int STRING = 2;

	/**
	 * Multi-line double-quoted string with interpolation support.
	 */
	public static final int STRING_MULTILINE = 3;

	/**
	 * Lexer is inside an interpolation within a double-quoted string.
	 */
	public static final int INTERPOLATED = 4;

	/**
	 * Lexer is inside a simple interpolation, e.g. "some #variable inserted"
	 */
	public static final int INTERPOLATED_VARIABLE = 5;

	/**
	 * Lexer has encountered a dot.
	 */
	public static final int DOT = 6;
}
