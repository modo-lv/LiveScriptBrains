/* The following code was generated by JFlex 1.4.3 on 15.12.3 19:29 */

package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.12.3 19:29 from the specification file
 * <tt>C:/home/martin/dev/misc/LiveScriptBrains/src/com/simpleplugin/LiveScript.flex</tt>
 */
public class LiveScriptLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int VSTRING = 10;
  public static final int STRING_SUSPENDED = 12;
  public static final int YYINITIAL = 0;
  public static final int BLOCK_STATEMENT = 4;
  public static final int REGEX = 14;
  public static final int INDENTED = 2;
  public static final int DSTRING = 6;
  public static final int ISTRING = 8;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  1,  1,  2,  2,  3,  3,  1,  1,  1,  1,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\30\1\54\1\0\1\56\1\55\22\0\1\30\1\51\1\33"+
    "\1\34\1\52\1\32\1\0\1\31\1\44\1\45\1\36\1\37\1\42"+
    "\1\53\1\10\1\36\1\4\2\2\1\3\6\1\1\41\1\50\1\0"+
    "\1\40\2\0\1\0\4\6\1\6\15\6\1\6\1\6\6\6\1\46"+
    "\1\27\1\47\1\0\1\7\1\0\1\24\2\6\1\17\1\22\1\23"+
    "\1\6\1\6\1\16\2\6\1\13\1\6\1\11\1\15\2\6\1\21"+
    "\1\25\1\20\1\12\1\14\2\6\1\26\1\6\1\35\1\0\1\43"+
    "\1\5\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\0\1\2\3\3\7\4\1\2\1\5"+
    "\2\2\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\2\25\2\1\1\26\1\2\1\27\1\3\1\0\2\3"+
    "\1\4\1\30\5\4\1\1\2\0\1\1\1\6\1\1"+
    "\1\0\1\26\1\31\4\4\1\1\1\0\1\1\1\0"+
    "\1\32\2\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\u011a\0\u0149"+
    "\0\u0178\0\u01a7\0\u01d6\0\u0205\0\u0234\0\u0263\0\u0292\0\u02c1"+
    "\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac\0\274\0\274\0\274"+
    "\0\274\0\274\0\274\0\274\0\274\0\274\0\274\0\274"+
    "\0\274\0\274\0\274\0\u03db\0\u040a\0\u0439\0\274\0\u0468"+
    "\0\274\0\u0497\0\u04c6\0\u04c6\0\u04f5\0\u0524\0\u0178\0\u0553"+
    "\0\u0582\0\u05b1\0\u05e0\0\u060f\0\u02c1\0\u063e\0\u066d\0\u069c"+
    "\0\u06cb\0\274\0\u06fa\0\u040a\0\u0729\0\u0758\0\u0787\0\u07b6"+
    "\0\u07e5\0\u063e\0\u0814\0\u037d\0\u0729\0\u0178\0\u0843\0\u0872";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\6\1\5\2\11\1\5"+
    "\1\12\2\11\1\13\1\14\2\11\1\15\2\11\1\16"+
    "\2\11\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\11\1\27\1\43"+
    "\1\44\1\21\54\5\1\0\2\5\27\45\1\46\3\45"+
    "\1\47\1\50\22\45\1\5\1\6\1\7\1\10\1\6"+
    "\1\5\2\11\1\5\1\12\2\11\1\13\1\14\2\11"+
    "\1\15\2\11\1\16\2\11\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\1\51\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\11\1\27\1\43\1\44\1\21\60\0\4\52\1\53"+
    "\1\54\1\52\1\55\16\54\31\0\4\6\1\53\1\54"+
    "\1\52\1\55\16\54\31\0\1\52\1\6\1\52\1\6"+
    "\1\53\1\54\1\52\1\55\16\54\31\0\4\11\1\0"+
    "\2\11\1\0\16\11\23\0\2\11\4\0\4\11\1\0"+
    "\2\11\1\0\1\11\1\56\2\11\1\57\11\11\23\0"+
    "\2\11\4\0\4\11\1\0\2\11\1\0\4\11\1\60"+
    "\11\11\23\0\2\11\4\0\4\11\1\0\2\11\1\0"+
    "\1\57\11\11\1\61\3\11\23\0\2\11\4\0\4\11"+
    "\1\0\2\11\1\0\10\11\1\62\5\11\23\0\2\11"+
    "\4\0\4\11\1\0\2\11\1\0\13\11\1\63\2\11"+
    "\23\0\2\11\4\0\4\11\1\0\2\11\1\0\11\11"+
    "\1\64\4\11\23\0\2\11\3\0\30\65\1\0\11\65"+
    "\2\0\1\65\1\0\1\65\2\0\3\65\2\0\1\65"+
    "\30\0\1\21\25\0\1\21\27\66\1\67\1\66\1\70"+
    "\25\66\33\0\1\24\23\0\27\24\1\71\3\24\1\72"+
    "\1\0\22\24\54\25\1\0\2\25\54\0\1\43\2\0"+
    "\27\45\1\46\3\45\1\47\1\73\51\45\1\46\3\45"+
    "\1\74\1\73\22\45\6\0\2\11\1\0\16\11\6\0"+
    "\1\75\14\0\1\11\5\0\4\52\1\0\1\54\1\52"+
    "\1\55\16\54\31\0\4\54\1\0\1\54\2\0\16\54"+
    "\31\0\4\55\1\0\1\54\1\55\1\0\16\54\31\0"+
    "\4\11\1\0\2\11\1\0\2\11\1\76\13\11\23\0"+
    "\2\11\4\0\4\11\1\0\2\11\1\0\5\11\1\77"+
    "\10\11\23\0\2\11\4\0\4\11\1\0\2\11\1\0"+
    "\12\11\1\57\3\11\23\0\2\11\4\0\4\11\1\0"+
    "\2\11\1\0\1\11\1\100\14\11\23\0\2\11\4\0"+
    "\4\11\1\0\2\11\1\0\2\11\1\101\13\11\23\0"+
    "\2\11\4\0\4\11\1\0\2\11\1\0\14\11\1\57"+
    "\1\11\23\0\2\11\3\0\27\66\1\67\1\66\1\72"+
    "\54\66\1\67\1\66\1\102\25\66\31\0\1\103\25\0"+
    "\27\24\1\71\3\24\1\104\1\0\22\24\35\0\1\105"+
    "\64\0\1\45\14\0\4\11\1\0\2\11\1\0\2\11"+
    "\1\106\13\11\23\0\2\11\4\0\4\11\1\0\2\11"+
    "\1\0\6\11\1\106\7\11\23\0\2\11\4\0\4\11"+
    "\1\0\2\11\1\0\11\11\1\57\4\11\23\0\2\11"+
    "\4\0\4\11\1\0\2\11\1\0\14\11\1\100\1\11"+
    "\23\0\2\11\3\0\31\103\1\107\25\103\31\0\1\110"+
    "\56\0\1\72\25\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2209];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\1\1\0\1\11\20\1\16\11\3\1\1\11"+
    "\1\1\1\11\1\1\1\0\12\1\2\0\2\1\1\11"+
    "\1\0\7\1\1\0\1\1\1\0\1\1\2\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[72];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    /**
     * Stack for keeping track of lexical states.
     */
    private Stack<Integer> _states = new Stack<Integer>();

    /**
     * Are indents tabs instead of spaces?
     */
    private static Boolean _tabIndents = null;

    /**
     * Indent stack to keep track of indentation levels.
     */
    private static Stack<Integer> _indents = new Stack<Integer>();

    /**
     * Current indent baseline.
     */
    private static int _currentIndent = 0;

    /**
     * Are we currently inside an indented block?
     */
    private static boolean _isIndented = false;

    /**
     * Start a new indented block (increase indent).
     */

    private IElementType _parseIndent(int count) {
/*
        if (count > _currentIndent) {
            _indents.push(_currentIndent);
            _currentIndent += count;
            _switchToState(INDENTED);
            return _out(LiveScriptTypes.INDENT);
        }
        else if (count < _currentIndent) {
            if (!_indents.empty())
                _currentIndent = _indents.pop();
            _exitState();
            if (_currentIndent < 1) {
                _isIndented = false;
            }
            return _out(LiveScriptTypes.DEDENT);
        }
*/
        return null;
    }

    private void _switchToState(int state) {
        System.out.println("Switching to state " + _stateName(state) + ".");
        yybegin(state);
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        System.out.println("Entering state " + _stateName(state) + ".");
        _states.push(yystate());
        yybegin(state);
    }

    /**
     * Leave a lexical state and return to the previous one (if any).
     * @returns boolean True if state was switched to a previous one, false if already at YYINITIAL.
     */
    private boolean _exitState() {
        if (_states.empty()) {
            System.out.println("State stack empty, defaulting to YYINITIAL.");
            yybegin(YYINITIAL);
            return false;
        }
        else {
            int newState = _states.pop();
            System.out.println("Exiting state " + _stateName(yystate()) + ", state is now " + _stateName(newState));
            yybegin(newState);
            return true;
        }
    }

    /**
     * Move the input position back to the start of the matched string.
     */
    private void _rewind() {
        yypushback(yylength());
    }

    /**
     * Move input position backwards.
     */
    private void _rewindBy(int count) {
        yypushback(count);
    }

    private void _advanceBy(int count) {
        _rewindBy(-count);
    }

    /**
     * Move the input position back to the first position of a given string
     */
    private boolean _rewindTo(String text) {
        final int position = yytext().toString().indexOf(text);

        if (position != -1) {
          _rewindBy(position);
          return true;
        }

        return false;
    }

    private IElementType _out(IElementType input) {
        //System.out.println("Matched [" + yytext() + "] as " + input);
        return input;
    }
    private String[] _stateNames = new String[] {
        "YYINITIAL",
        "INDENTED",
        "BLOCK_STATEMENT",
        "DSTRING",
        "ISTRING",
        "VSTRING",
        "STRING_SUSPENDED",
        "REGEX",
    };
    private String _stateName(int state) {
        return _stateNames[state / 2];
    }


  public LiveScriptLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public LiveScriptLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 144) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
        System.out.println("End of file reached, clearing out indentation stack.");
    _currentIndent = 0;
    _indents.clear();
    _states.clear();
    return;

    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 3: 
          { return _out(LiveScriptTypes.NUMBER);
          }
        case 27: break;
        case 24: 
          { return _out(LiveScriptTypes.BOOLEAN);
          }
        case 28: break;
        case 10: 
          { return _out(LiveScriptTypes.PLUS);
          }
        case 29: break;
        case 21: 
          { return _out(LiveScriptTypes.NEWLINE);
          }
        case 30: break;
        case 17: 
          { return _out(LiveScriptTypes.LIST_START);
          }
        case 31: break;
        case 1: 
          { return _out(LiveScriptTypes.STRING);
          }
        case 32: break;
        case 6: 
          { _enterState(DSTRING); return _out(LiveScriptTypes.STRING);
          }
        case 33: break;
        case 5: 
          { return _out(TokenType.WHITE_SPACE);
          }
        case 34: break;
        case 23: 
          { _exitState(); return _out(LiveScriptTypes.ISTRING);
          }
        case 35: break;
        case 26: 
          { return _out(LiveScriptTypes.EMPTY);
          }
        case 36: break;
        case 8: 
          { return _out(LiveScriptTypes.OBJ_START);
          }
        case 37: break;
        case 12: 
          { return _out(LiveScriptTypes.COLON);
          }
        case 38: break;
        case 25: 
          { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING);
          }
        case 39: break;
        case 15: 
          { return _out(LiveScriptTypes.PAREN_L);
          }
        case 40: break;
        case 13: 
          { return _out(LiveScriptTypes.COMMA);
          }
        case 41: break;
        case 18: 
          { return _out(LiveScriptTypes.LIST_END);
          }
        case 42: break;
        case 22: 
          { _exitState(); return _out(LiveScriptTypes.STRING);
          }
        case 43: break;
        case 2: 
          { System.out.println("State is " + _stateName(yystate())); return _out(TokenType.BAD_CHARACTER);
          }
        case 44: break;
        case 7: 
          { return _out(LiveScriptTypes.COMMENT_LINE);
          }
        case 45: break;
        case 9: 
          { return _out(LiveScriptTypes.MATH_OP);
          }
        case 46: break;
        case 20: 
          { return _out(LiveScriptTypes.BANG);
          }
        case 47: break;
        case 11: 
          { return _out(LiveScriptTypes.ASSIGN);
          }
        case 48: break;
        case 19: 
          { return _out(LiveScriptTypes.SEMICOLON);
          }
        case 49: break;
        case 16: 
          { return _out(LiveScriptTypes.PAREN_R);
          }
        case 50: break;
        case 4: 
          { return _out(LiveScriptTypes.IDENTIFIER);
          }
        case 51: break;
        case 14: 
          { return _out(LiveScriptTypes.OBJ_END);
          }
        case 52: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
