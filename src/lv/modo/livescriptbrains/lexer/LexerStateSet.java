package lv.modo.livescriptbrains.lexer;

import java.util.ArrayDeque;
import java.util.Arrays;

public class LexerStateSet {
	public static final ArrayDeque<Integer> STRINGS = new ArrayDeque<>(Arrays.asList(
			LexerState.STRING, LexerState.HEREDOC
	));
}
