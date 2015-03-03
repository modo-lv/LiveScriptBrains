/* The following code was generated by JFlex 1.4.3 on 15.3.3 22:47 */

package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.3.3 22:47 from the specification file
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
    "\11\0\1\50\1\45\1\0\1\47\1\46\22\0\1\50\1\0\1\33"+
    "\1\34\1\43\1\32\1\0\1\31\1\40\1\41\1\36\1\36\1\41"+
    "\1\44\1\10\1\36\1\4\2\2\1\3\6\1\1\40\1\30\1\0"+
    "\1\37\2\0\1\0\4\6\1\6\15\6\1\6\1\6\6\6\1\0"+
    "\1\27\1\30\1\0\1\7\1\0\1\24\2\6\1\17\1\22\1\23"+
    "\1\6\1\6\1\16\2\6\1\13\1\6\1\11\1\15\2\6\1\21"+
    "\1\25\1\20\1\12\1\14\2\6\1\26\1\6\1\35\1\0\1\42"+
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
    "\2\0\1\1\1\0\1\2\3\3\1\4\1\5\6\4"+
    "\3\2\1\6\1\7\1\10\1\11\2\12\1\13\2\1"+
    "\1\14\1\2\1\15\1\3\1\0\2\3\1\4\1\16"+
    "\5\4\1\1\2\0\1\1\1\6\1\1\1\0\1\14"+
    "\1\17\1\3\4\4\1\1\1\0\1\1\1\0\1\20"+
    "\2\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[63];
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
    "\0\0\0\51\0\122\0\173\0\244\0\315\0\366\0\u011f"+
    "\0\u0148\0\244\0\u0171\0\u019a\0\u01c3\0\u01ec\0\u0215\0\u023e"+
    "\0\u0267\0\u0290\0\u02b9\0\u02e2\0\u030b\0\244\0\244\0\244"+
    "\0\u0334\0\u035d\0\u0386\0\u03af\0\244\0\u03d8\0\244\0\u0401"+
    "\0\u042a\0\u0453\0\u047c\0\u04a5\0\u0148\0\u04ce\0\u04f7\0\u0520"+
    "\0\u0549\0\u0572\0\u0267\0\u059b\0\u05c4\0\u05ed\0\u0616\0\244"+
    "\0\u063f\0\u0386\0\u0668\0\u042a\0\u0691\0\u06ba\0\u06e3\0\u070c"+
    "\0\u059b\0\u0735\0\u02e2\0\u0668\0\u0148\0\u075e\0\u0787";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[63];
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
    "\1\5\1\6\1\7\1\10\1\6\1\5\2\11\1\12"+
    "\1\13\2\11\1\14\1\15\2\11\1\16\2\11\1\17"+
    "\2\11\1\20\1\21\1\5\1\22\1\23\1\24\1\25"+
    "\1\12\1\26\1\27\3\12\1\11\1\26\1\30\1\31"+
    "\2\32\45\5\1\0\3\5\27\33\1\34\3\33\1\35"+
    "\1\36\14\33\1\5\1\6\1\7\1\10\1\6\1\5"+
    "\2\11\1\12\1\13\2\11\1\14\1\15\2\11\1\16"+
    "\2\11\1\17\2\11\1\20\1\21\1\5\1\22\1\23"+
    "\1\24\1\25\1\12\1\26\1\27\2\12\1\37\1\11"+
    "\1\26\1\30\1\31\2\32\52\0\4\40\1\41\1\42"+
    "\1\40\1\43\16\42\23\0\4\6\1\41\1\42\1\40"+
    "\1\43\16\42\23\0\1\40\1\6\1\40\1\6\1\41"+
    "\1\42\1\40\1\43\16\42\23\0\4\11\1\0\2\11"+
    "\1\0\16\11\14\0\2\11\5\0\4\11\1\0\2\11"+
    "\1\0\1\11\1\44\2\11\1\45\11\11\14\0\2\11"+
    "\5\0\4\11\1\0\2\11\1\0\4\11\1\46\11\11"+
    "\14\0\2\11\5\0\4\11\1\0\2\11\1\0\1\45"+
    "\11\11\1\47\3\11\14\0\2\11\5\0\4\11\1\0"+
    "\2\11\1\0\10\11\1\50\5\11\14\0\2\11\5\0"+
    "\4\11\1\0\2\11\1\0\13\11\1\51\2\11\14\0"+
    "\2\11\5\0\4\11\1\0\2\11\1\0\11\11\1\52"+
    "\4\11\14\0\2\11\4\0\30\53\1\0\10\53\2\0"+
    "\2\53\2\0\1\53\1\0\27\54\1\55\1\54\1\56"+
    "\17\54\33\0\1\24\15\0\27\24\1\57\3\24\1\60"+
    "\1\0\14\24\45\25\1\0\3\25\45\0\1\30\52\0"+
    "\2\32\27\33\1\34\3\33\1\35\1\61\43\33\1\34"+
    "\3\33\1\62\1\61\14\33\6\0\2\11\1\0\16\11"+
    "\6\0\1\63\5\0\1\11\6\0\4\40\1\0\1\42"+
    "\1\40\1\43\16\42\23\0\4\64\1\0\1\64\2\0"+
    "\16\64\30\0\1\42\2\0\16\42\23\0\4\43\1\0"+
    "\1\42\1\43\1\0\16\42\23\0\4\11\1\0\2\11"+
    "\1\0\2\11\1\65\13\11\14\0\2\11\5\0\4\11"+
    "\1\0\2\11\1\0\5\11\1\66\10\11\14\0\2\11"+
    "\5\0\4\11\1\0\2\11\1\0\12\11\1\45\3\11"+
    "\14\0\2\11\5\0\4\11\1\0\2\11\1\0\1\11"+
    "\1\67\14\11\14\0\2\11\5\0\4\11\1\0\2\11"+
    "\1\0\2\11\1\70\13\11\14\0\2\11\5\0\4\11"+
    "\1\0\2\11\1\0\14\11\1\45\1\11\14\0\2\11"+
    "\4\0\27\54\1\55\1\54\1\60\46\54\1\55\1\54"+
    "\1\71\17\54\31\0\1\72\17\0\27\24\1\57\3\24"+
    "\1\73\1\0\14\24\35\0\1\74\55\0\1\33\7\0"+
    "\4\11\1\0\2\11\1\0\2\11\1\75\13\11\14\0"+
    "\2\11\5\0\4\11\1\0\2\11\1\0\6\11\1\75"+
    "\7\11\14\0\2\11\5\0\4\11\1\0\2\11\1\0"+
    "\11\11\1\45\4\11\14\0\2\11\5\0\4\11\1\0"+
    "\2\11\1\0\14\11\1\67\1\11\14\0\2\11\4\0"+
    "\31\72\1\76\17\72\31\0\1\77\50\0\1\60\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1968];
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
    "\2\0\1\1\1\0\1\11\4\1\1\11\13\1\3\11"+
    "\4\1\1\11\1\1\1\11\1\1\1\0\12\1\2\0"+
    "\2\1\1\11\1\0\10\1\1\0\1\1\1\0\1\1"+
    "\2\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[63];
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
        case 16: 
          { return _out(LiveScriptTypes.EMPTY);
          }
        case 17: break;
        case 15: 
          { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING);
          }
        case 18: break;
        case 14: 
          { return _out(LiveScriptTypes.BOOLEAN);
          }
        case 19: break;
        case 6: 
          { _enterState(DSTRING); return _out(LiveScriptTypes.STRING);
          }
        case 20: break;
        case 5: 
          { return _out(LiveScriptTypes.UNKNOWN);
          }
        case 21: break;
        case 1: 
          { return _out(LiveScriptTypes.STRING);
          }
        case 22: break;
        case 8: 
          { return _out(LiveScriptTypes.MATH_OP);
          }
        case 23: break;
        case 13: 
          { _exitState(); return _out(LiveScriptTypes.ISTRING);
          }
        case 24: break;
        case 3: 
          { return _out(LiveScriptTypes.NUMBER);
          }
        case 25: break;
        case 2: 
          { System.out.println("State is " + _stateName(yystate())); return _out(TokenType.BAD_CHARACTER);
          }
        case 26: break;
        case 11: 
          { return _out(TokenType.WHITE_SPACE);
          }
        case 27: break;
        case 9: 
          { return _out(LiveScriptTypes.ASSIGN);
          }
        case 28: break;
        case 4: 
          { return _out(LiveScriptTypes.IDENTIFIER);
          }
        case 29: break;
        case 12: 
          { _exitState(); return _out(LiveScriptTypes.STRING);
          }
        case 30: break;
        case 7: 
          { return _out(LiveScriptTypes.COMMENT_LINE);
          }
        case 31: break;
        case 10: 
          { return _out(LiveScriptTypes.NEWLINE);
          }
        case 32: break;
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
