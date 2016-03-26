package lv.modo.livescriptbrains.lexer;

public class LexerState {
	public static final int NORMAL = 0;
	public static final int STATE_INDENT = 1;
	public static final int STRING = 2;
	public static final int HEREDOC = 3;
	public static final int INTERPOLATED = 4;
	public static final int INTERPOLATED_VAR = 5;
	public static final int DOT = 6;
	public static final int REGEX = 7;
}
