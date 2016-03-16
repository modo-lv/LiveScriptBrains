package lv.modo.livescriptbrains.psi.lexer;

/**
 * Represents a line of text to be processed for tokens.
 */
public class LexerLine {

	/**
	 * Index in the input text where this line starts.
	 */
	public int StartsAt;

	/**
	 * Text of the line.
	 */
	public String Text;

	/**
	 * Index at which the next token should be looked for.
	 */
	public int Index;


	/**
	 * Constructor
	 * @param text Line text.
	 */
	public LexerLine(int startsAt, String text) {
		this.StartsAt = startsAt;
		this.Text = text;
	}

	/**
	 * Get the remaining unprocessed text.
	 * @return
	 */
	public String RemainingText() {
		return this.Text.substring(this.Index);
	}

	/**
	 * Length of the remaining line still to be processed.
	 */
	public int RemainingLength() {
		return this.Text.length() - this.Index;
	}

	/**
	 * Are we at the end of the line?
	 */
	public boolean IsFinished() {
		return this.RemainingLength() < 1;
	}
}
