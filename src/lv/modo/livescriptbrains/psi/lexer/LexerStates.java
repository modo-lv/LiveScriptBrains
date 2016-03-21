package lv.modo.livescriptbrains.psi.lexer;

public class LexerStates {
	public static final int STATE_NORMAL = 0;
	public static final int STATE_INDENT = 1;
	/**
	 * Double-quoted string with interpolation support
	 */
	public static final int STATE_STRING = 2;

	/**
	 * Multi-line double-quoted string with interpolation support.
	 */
	public static final int STATE_STRING_MULTILINE = 3;

	/**
	 * Lexer is inside an interpolation within a double-quoted string.
	 */
	public static final int STATE_INTERPOLATED = 4;

	/**
	 * Lexer is inside a simple interpolation, e.g. "some #variable inserted"
	 */
	public static final int STATE_INTERPOLATED_VARIABLE = 5;
}
