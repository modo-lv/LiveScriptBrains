package lv.modo.livescriptbrains.lexer;

import java.util.ArrayDeque;
import java.util.Arrays;

public class LexerStateSet {
	public static final ArrayDeque<Integer> STRINGS = new ArrayDeque<>(Arrays.asList(
			LexerState.STRING, LexerState.HEREDOC, LexerState.REGEX
	));
	public static final ArrayDeque<Integer> NORMAL = new ArrayDeque<>(Arrays.asList(
		LexerState.NORMAL, LexerState.INTERPOLATED
	));
	public static final ArrayDeque<Integer> NORMAL_DOT = new ArrayDeque<>(Arrays.asList(
		LexerState.NORMAL, LexerState.INTERPOLATED, LexerState.DOT
	));
}
