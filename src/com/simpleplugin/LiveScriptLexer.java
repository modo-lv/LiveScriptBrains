/* The following code was generated by JFlex 1.4.3 on 15.27.2 19:35 */

package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.27.2 19:35 from the specification file
 * <tt>C:/home/martin/dev/misc/LiveScriptBrains/src/com/simpleplugin/LiveScript.flex</tt>
 */
class LiveScriptLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int BACK_STRING_STARTED = 8;
  public static final int STRING_SUSPENDED = 6;
  public static final int YYINITIAL = 0;
  public static final int SIMPLE_STRING_STARTED = 2;
  public static final int FULL_STRING_STARTED = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\45\1\2\2\0\1\1\22\0\1\45\1\0\1\40\1\46"+
    "\1\31\2\0\1\37\1\41\1\42\2\0\1\34\1\32\1\30\1\0"+
    "\1\24\2\22\1\23\6\21\1\36\1\34\1\0\1\35\3\0\32\26"+
    "\1\0\1\33\1\34\1\0\1\27\1\0\1\16\2\26\1\11\1\14"+
    "\1\15\2\26\1\10\2\26\1\5\1\26\1\3\1\7\2\26\1\13"+
    "\1\17\1\12\1\4\1\6\2\26\1\20\1\26\1\43\1\0\1\44"+
    "\1\25\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\1\1\2\2\7\3\3\4\1\1\1\5\1\1"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\2\14\1\15"+
    "\2\14\1\1\2\16\1\17\1\3\1\20\5\3\2\4"+
    "\1\0\1\4\1\21\1\22\1\23\4\3\1\4\1\24";

  private static int [] zzUnpackAction() {
    int [] result = new int[56];
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
    "\0\0\0\47\0\116\0\165\0\234\0\303\0\352\0\303"+
    "\0\u0111\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4\0\u01fb\0\u0222"+
    "\0\u0249\0\u0270\0\u0297\0\303\0\u02be\0\303\0\303\0\303"+
    "\0\303\0\303\0\u02e5\0\u030c\0\u0333\0\303\0\u035a\0\u0381"+
    "\0\u03a8\0\303\0\352\0\303\0\u03cf\0\u0138\0\u03f6\0\u041d"+
    "\0\u0444\0\u046b\0\u0492\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0297"+
    "\0\303\0\303\0\u0555\0\u057c\0\u05a3\0\u05ca\0\u0507\0\u0138";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[56];
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
    "\1\6\1\7\1\10\1\11\2\12\1\13\1\14\2\12"+
    "\1\15\2\12\1\16\2\12\1\17\1\20\1\21\1\22"+
    "\1\20\1\6\2\12\1\6\1\12\1\6\1\23\1\6"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\6\1\32"+
    "\1\33\1\6\33\34\1\35\3\34\1\36\7\34\33\37"+
    "\1\40\4\37\1\36\5\37\1\41\1\42\1\43\1\10"+
    "\41\42\1\44\2\42\1\6\1\7\1\10\41\6\1\32"+
    "\2\6\51\0\1\10\47\0\1\12\1\45\2\12\1\46"+
    "\15\12\1\0\2\12\1\0\2\12\17\0\22\12\1\0"+
    "\2\12\1\0\2\12\17\0\4\12\1\47\15\12\1\0"+
    "\2\12\1\0\2\12\17\0\1\46\11\12\1\50\7\12"+
    "\1\0\2\12\1\0\2\12\17\0\10\12\1\51\11\12"+
    "\1\0\2\12\1\0\2\12\17\0\13\12\1\52\6\12"+
    "\1\0\2\12\1\0\2\12\17\0\11\12\1\53\10\12"+
    "\1\0\2\12\1\0\2\12\17\0\16\54\4\55\1\56"+
    "\1\54\1\55\1\57\21\0\16\54\4\20\1\56\1\54"+
    "\1\55\1\57\21\0\16\54\1\55\1\20\1\55\1\20"+
    "\1\56\1\54\1\55\1\57\16\0\1\60\2\0\31\60"+
    "\1\0\5\60\1\0\1\60\2\0\1\60\35\0\1\61"+
    "\56\0\1\33\1\0\33\34\1\35\3\34\1\0\42\34"+
    "\1\35\13\34\33\37\1\40\4\37\1\0\5\37\1\0"+
    "\33\37\1\40\13\37\43\0\1\62\6\0\2\12\1\63"+
    "\17\12\1\0\2\12\1\0\2\12\17\0\5\12\1\64"+
    "\14\12\1\0\2\12\1\0\2\12\17\0\12\12\1\46"+
    "\7\12\1\0\2\12\1\0\2\12\17\0\1\12\1\65"+
    "\20\12\1\0\2\12\1\0\2\12\17\0\2\12\1\66"+
    "\17\12\1\0\2\12\1\0\2\12\17\0\14\12\1\46"+
    "\5\12\1\0\2\12\1\0\2\12\17\0\16\54\5\0"+
    "\1\54\23\0\16\54\4\55\1\0\1\54\1\55\1\57"+
    "\21\0\22\67\1\0\1\67\23\0\16\54\4\57\1\0"+
    "\1\54\1\57\22\0\2\12\1\70\17\12\1\0\2\12"+
    "\1\0\2\12\17\0\6\12\1\70\13\12\1\0\2\12"+
    "\1\0\2\12\17\0\11\12\1\46\10\12\1\0\2\12"+
    "\1\0\2\12\17\0\14\12\1\65\5\12\1\0\2\12"+
    "\1\0\2\12\14\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1521];
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
    "\5\0\1\11\1\1\1\11\13\1\1\11\1\1\5\11"+
    "\3\1\1\11\3\1\1\11\1\1\1\11\11\1\1\0"+
    "\2\1\2\11\6\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[56];
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
  private final Stack<Integer> stack = new Stack<Integer>();

  /**
   * Push the actual state on top of the stack
   */
  private void pushState() {
    stack.push(yystate());
  }

  /**
   * Push the actual state on top of the stack
   * and change into another state
   *
   * @param state The new state
   */
  private void enterState(int state) {
    stack.push(yystate());
    yybegin(state);
  }

  /**
   * Pop the last state from the stack and change to it.
   * If the stack is empty, go to YYINITIAL
   */
    private boolean leaveState() {
        if (!stack.empty()) {
            yybegin(stack.pop());
            return true;
        } else {
            yybegin(YYINITIAL);
            return false;
        }
    }


  /**
   * Push the stream back to the position before the text match
   *
   * @param text The text to match
   * @return true when matched
   */
  private boolean rewindTo(String text) {
    final int position = yytext().toString().indexOf(text);

    if (position != -1) {
      yypushback(yylength() - position - 1);
      return true;
    }

    return false;
  }


  private void rewindBy(int count) {
    yypushback(count);
  }


    /**
     * Rewind the input stream back to the position before
     * the text match and leave current state.
     */
    private boolean rewindAndLeaveState(String text) {
        final boolean success = rewindTo(text);
        if (success) {
            leaveState();
        }
        return true;
    }

  /**
   * Push the stream back to the position before the text match
   * and change into the given state
   *
   * @param text The text to match
   * @param state The new state
   * @return true when matched
   */
  private boolean pushBackAndState(String text, int state) {
    final boolean success = rewindTo(text);

    if (success) {
      enterState(state);
    }

    return success;
  }


  LiveScriptLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  LiveScriptLexer(java.io.InputStream in) {
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
    while (i < 124) {
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
        case 15: 
          { leaveState(); return LiveScriptTypes.STRING_START;
          }
        case 21: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 22: break;
        case 3: 
          { return LiveScriptTypes.IDENTIFIER;
          }
        case 23: break;
        case 14: 
          { rewindBy(1); enterState(YYINITIAL);
          }
        case 24: break;
        case 6: 
          { enterState(SIMPLE_STRING_STARTED); return LiveScriptTypes.STRING_START;
          }
        case 25: break;
        case 12: 
          { return LiveScriptTypes.STRING;
          }
        case 26: break;
        case 7: 
          { enterState(FULL_STRING_STARTED); return LiveScriptTypes.STRING_START;
          }
        case 27: break;
        case 4: 
          { return LiveScriptTypes.NUMBER;
          }
        case 28: break;
        case 19: 
          { enterState(STRING_SUSPENDED); return LiveScriptTypes.STRING_END;
          }
        case 29: break;
        case 11: 
          { return TokenType.WHITE_SPACE;
          }
        case 30: break;
        case 8: 
          { return LiveScriptTypes.PAREN_L;
          }
        case 31: break;
        case 9: 
          { return LiveScriptTypes.PAREN_R;
          }
        case 32: break;
        case 10: 
          { if (leaveState()) rewindBy(1);
          }
        case 33: break;
        case 2: 
          { return LiveScriptTypes.NEWLINE;
          }
        case 34: break;
        case 16: 
          { return LiveScriptTypes.BOOLEAN;
          }
        case 35: break;
        case 20: 
          { return LiveScriptTypes.NULL;
          }
        case 36: break;
        case 13: 
          { leaveState(); return LiveScriptTypes.STRING_END;
          }
        case 37: break;
        case 17: 
          { return LiveScriptTypes.BACKSTRING;
          }
        case 38: break;
        case 18: 
          { return LiveScriptTypes.GLOBAL_EQ;
          }
        case 39: break;
        case 5: 
          { return LiveScriptTypes.EQ;
          }
        case 40: break;
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
