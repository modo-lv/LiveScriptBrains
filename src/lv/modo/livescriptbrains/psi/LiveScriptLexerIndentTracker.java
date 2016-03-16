package lv.modo.livescriptbrains.psi;

import com.intellij.psi.tree.IElementType;

/**
 * Class that tracks the state of the indentation while lexing.
 */
public class LiveScriptLexerIndentTracker {
	/**
	 * Are the current indents - indents, dedents or newlines?
	 */
	public IElementType IndentType;

	/**
	 * Has current line been checked and its indents counted?
	 */
	public boolean LineProcessed;

	/**
	 * The total number of indents on the current line.
	 */
	public int IndentCount;

	/**
	 * Which indent is currently being processed.
	 */
	public int CurrentIndent;

	public LiveScriptLexerIndentTracker() {
		this.LineProcessed = false;
	}
}
