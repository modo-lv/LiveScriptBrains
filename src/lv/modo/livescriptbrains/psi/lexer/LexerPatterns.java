package lv.modo.livescriptbrains.psi.lexer;

public class LexerPatterns {
	/**
	 * A name of a variable, class, method or any other symbol.
	 */
	public static final String ID = "\\p{IsAlphabetic}[\\p{IsAlphabetic}\\p{IsDigit}_-]*";
}
