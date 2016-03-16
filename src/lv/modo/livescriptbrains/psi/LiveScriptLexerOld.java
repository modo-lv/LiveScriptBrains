/* The following code was generated by JFlex 1.4.3 on 15.16.10 13:53 */

package lv.modo.livescriptbrains.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.16.10 13:53 from the specification file
 * <tt>D:/home/martin/dev/misc/LiveScriptBrains/src/lv/modo/livescriptbrains/psi/LiveScript.flex</tt>
 */
public class LiveScriptLexerOld implements FlexLexer {

  private class Token {
    public IElementType Type;
    public int StartsAt;
    public int EndsAt;
  }

  private String _input = null;
  private int _state = 0;
	private Token _lastToken = null;
	private int _eofPosition = 0;

  /**
   * Current "cursor" position in the input.
   */
  private int _position = 0;

  /**
   * Position where the input ends.
   */
  private int _end = 0;



  @Override
  public void yybegin(int state) {
    this._state = state;
  }

  @Override
  public int yystate() {
    return this._state;
  }

  /**
   * Get the start position of the last returned token.
   * @return
   */
  @Override
  public int getTokenStart()
	{
		System.out.println("Token is " + this._lastToken.Type + ", starts at " + this._lastToken.StartsAt);
		return this._lastToken.StartsAt;
  }

  /**
   * Get the end position of the last returned token.
   * @return
   */
  @Override
  public int getTokenEnd() {
		System.out.println("Token is " + this._lastToken.Type + ", ends at " + this._lastToken.EndsAt);
		return this._lastToken.EndsAt;
  }

  @Override
  public IElementType advance() throws IOException {
    System.out.println("Advancing...");

		this._lastToken = new Token();

		if (this._position >= this._eofPosition) {
			System.out.println("EOF");
			this._lastToken.Type = null;
			this._lastToken.StartsAt = 0;
			this._lastToken.EndsAt = 0;
		}
		else {
			Pattern pattern = Pattern.compile("[0-9]+");
			Matcher matcher = pattern.matcher(this._input);
			Boolean found = matcher.find(this._position);

			if (found) {
				System.out.println(matcher.group() + " @ " + matcher.start() + " (" + matcher.end() + ")");
				this._lastToken.StartsAt = matcher.start();
				this._lastToken.EndsAt = matcher.end();
				this._lastToken.Type = LiveScriptTypes.NUMBER;
			} else {
				this._lastToken.StartsAt = this._position;
				this._lastToken.EndsAt = this._position + 1;
				this._lastToken.Type = TokenType.BAD_CHARACTER;
			}

			this._position = this._lastToken.EndsAt;
		}


		System.out.println("Returning " + this._lastToken.Type + " @ " + this._lastToken.StartsAt);
		return this._lastToken.Type;
  }

  @Override
  public void reset(CharSequence buf, int start, int end, int initialState) {
		System.out.println("Resetting at " + start + " to " + end);
    this._input = buf.toString();
    this._position = start;
		this._eofPosition = this._input.length() - 1;
    this._end = end;
    this._state = initialState;
		this._lastToken = new Token();
  }


  public LiveScriptLexerOld(Reader in) {
  }
}