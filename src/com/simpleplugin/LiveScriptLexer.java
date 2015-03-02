/* The following code was generated by JFlex 1.4.3 on 15.2.3 21:28 */

package com.simpleplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.psi.LiveScriptTypes;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.2.3 21:28 from the specification file
 * <tt>C:/home/martin/dev/misc/LiveScriptBrains/src/com/simpleplugin/LiveScript.flex</tt>
 */
public class LiveScriptLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING_SUSPENDED = 12;
  public static final int YYINITIAL = 0;
  public static final int BLOCK_STATEMENT = 4;
  public static final int REGEX = 14;
  public static final int SIMPLE_STRING_STARTED = 6;
  public static final int INDENTED = 2;
  public static final int STRING_VARIABLE = 10;
  public static final int FULL_STRING_STARTED = 8;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  2,  1,  3,  4,  4,  5,  5,  6,  6,  7,  7,  8, 8
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\60\1\34\1\0\1\57\1\35\22\0\1\60\1\61\1\37"+
    "\1\55\1\53\1\36\1\63\1\33\1\51\1\52\1\56\1\43\1\46"+
    "\1\54\1\10\1\40\1\4\2\2\1\3\6\1\1\45\1\32\1\0"+
    "\1\44\2\0\1\30\4\6\1\65\15\6\1\66\1\64\6\6\1\0"+
    "\1\31\1\32\1\62\1\7\1\0\1\24\2\6\1\17\1\22\1\23"+
    "\1\41\1\27\1\16\2\6\1\13\1\42\1\11\1\15\2\6\1\21"+
    "\1\25\1\20\1\12\1\14\2\6\1\26\1\6\1\47\1\0\1\50"+
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
    "\11\0\1\1\3\2\1\3\1\4\6\3\1\5\1\1"+
    "\1\6\2\7\1\1\1\10\2\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\1\1\23"+
    "\1\24\1\23\1\1\1\23\1\1\2\25\1\26\4\25"+
    "\1\27\1\30\1\31\1\1\1\0\1\1\1\32\1\2"+
    "\1\0\2\2\1\3\1\33\6\3\1\34\2\0\1\35"+
    "\3\0\5\36\1\0\1\25\1\37\1\40\1\0\1\41"+
    "\1\42\1\2\5\3\1\0\2\43\1\0\1\41\3\42"+
    "\1\44\1\0\3\43\1\0\3\43\3\0\1\45\1\0"+
    "\1\46\61\0\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[170];
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
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb\0\u01ef\0\u0302"+
    "\0\u0339\0\u0370\0\u03a7\0\u03de\0\u0415\0\u01ef\0\u044c\0\u0483"+
    "\0\u01ef\0\u04ba\0\u04f1\0\u01ef\0\u0528\0\u01ef\0\u01ef\0\u01ef"+
    "\0\u055f\0\u01ef\0\u01ef\0\u01ef\0\u01ef\0\u0596\0\u05cd\0\u0604"+
    "\0\u063b\0\u0672\0\u06a9\0\u06e0\0\u0717\0\u074e\0\u0785\0\u07bc"+
    "\0\u01ef\0\u07f3\0\u082a\0\u0861\0\u0898\0\u08cf\0\u01ef\0\u01ef"+
    "\0\u0906\0\u0906\0\u093d\0\u0974\0\u09ab\0\u09e2\0\u0a19\0\u0a50"+
    "\0\u0a87\0\u02cb\0\u0abe\0\u0af5\0\u0b2c\0\u0b63\0\u0b9a\0\u0bd1"+
    "\0\u044c\0\u0c08\0\u0c3f\0\u01ef\0\u0c76\0\u0cad\0\u06e0\0\u063b"+
    "\0\u0672\0\u0717\0\u01ef\0\u074e\0\u074e\0\u0ce4\0\u0d1b\0\u01ef"+
    "\0\u0d52\0\u01ef\0\u0d89\0\u09e2\0\u0dc0\0\u0df7\0\u0e2e\0\u0e65"+
    "\0\u0e9c\0\u0ed3\0\u0f0a\0\u0f41\0\u0f78\0\u0faf\0\u01ef\0\u0fe6"+
    "\0\u101d\0\u02cb\0\u1054\0\u01ef\0\u108b\0\u10c2\0\u10f9\0\u10f9"+
    "\0\u1130\0\u1167\0\u119e\0\u11d5\0\u120c\0\u10f9\0\u1243\0\u120c"+
    "\0\u127a\0\u12b1\0\u12e8\0\u131f\0\u1356\0\u138d\0\u13c4\0\u13fb"+
    "\0\u1432\0\u1469\0\u14a0\0\u14d7\0\u150e\0\u1545\0\u157c\0\u15b3"+
    "\0\u15ea\0\u1621\0\u1658\0\u168f\0\u16c6\0\u16fd\0\u1734\0\u176b"+
    "\0\u17a2\0\u17d9\0\u1810\0\u1847\0\u187e\0\u18b5\0\u18ec\0\u1923"+
    "\0\u195a\0\u1991\0\u19c8\0\u19ff\0\u1a36\0\u1a6d\0\u1aa4\0\u1adb"+
    "\0\u1b12\0\u1b49\0\u1b80\0\u1bb7\0\u1bee\0\u1c25\0\u1c5c\0\u1c93"+
    "\0\u1cca\0\u01ef";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[170];
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
    "\1\12\1\13\1\14\1\15\1\13\1\12\2\16\1\17"+
    "\1\20\2\16\1\21\1\22\2\16\1\23\2\16\1\24"+
    "\2\16\1\25\1\16\1\26\1\27\1\12\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\2\16\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\16\1\36\1\46"+
    "\1\36\2\47\1\50\2\12\3\16\34\12\1\0\32\12"+
    "\34\51\1\0\1\12\17\51\1\52\1\51\1\53\1\54"+
    "\42\51\1\0\1\12\17\51\1\52\1\51\1\55\1\56"+
    "\6\51\31\57\1\60\1\57\1\61\33\57\31\62\1\63"+
    "\4\62\1\64\1\61\15\62\1\65\11\62\6\12\2\66"+
    "\1\12\17\66\4\12\1\0\4\12\2\66\10\12\1\66"+
    "\10\12\3\66\34\67\1\0\13\67\1\70\16\67\34\71"+
    "\1\72\3\71\1\73\14\71\1\74\11\71\70\0\4\75"+
    "\1\76\1\77\1\75\1\100\17\77\11\0\2\77\21\0"+
    "\3\77\1\0\4\13\1\76\1\77\1\75\1\100\17\77"+
    "\11\0\2\77\21\0\3\77\1\0\1\75\1\13\1\75"+
    "\1\13\1\76\1\77\1\75\1\100\17\77\11\0\2\77"+
    "\21\0\3\77\1\0\4\16\1\0\2\16\1\0\17\16"+
    "\11\0\2\16\10\0\2\16\7\0\3\16\1\0\4\16"+
    "\1\0\2\16\1\0\1\16\1\101\2\16\1\102\12\16"+
    "\11\0\2\16\10\0\2\16\7\0\3\16\1\0\4\16"+
    "\1\0\2\16\1\0\4\16\1\103\12\16\11\0\2\16"+
    "\10\0\2\16\7\0\3\16\1\0\4\16\1\0\2\16"+
    "\1\0\1\102\11\16\1\104\4\16\11\0\2\16\10\0"+
    "\2\16\7\0\3\16\1\0\4\16\1\0\2\16\1\0"+
    "\10\16\1\105\5\16\1\106\11\0\2\16\10\0\2\16"+
    "\7\0\3\16\1\0\4\16\1\0\2\16\1\0\13\16"+
    "\1\107\3\16\11\0\2\16\10\0\2\16\7\0\3\16"+
    "\1\0\4\16\1\0\2\16\1\0\11\16\1\110\5\16"+
    "\11\0\2\16\10\0\2\16\7\0\3\16\32\111\1\0"+
    "\1\111\2\0\10\111\1\0\1\111\1\0\1\111\1\0"+
    "\5\111\1\0\6\111\33\0\1\112\67\0\1\31\71\0"+
    "\1\34\27\0\40\113\1\114\15\113\1\115\10\113\57\0"+
    "\2\41\6\0\34\46\1\0\32\46\57\0\2\47\36\0"+
    "\1\116\36\0\34\51\1\0\32\51\34\52\1\0\32\52"+
    "\34\51\1\0\20\51\1\52\1\51\2\53\6\51\55\0"+
    "\1\52\1\0\2\117\6\0\34\120\1\0\1\51\17\120"+
    "\1\121\1\120\1\122\1\55\6\120\34\123\2\0\17\123"+
    "\1\121\1\123\1\124\1\125\6\123\31\57\1\60\1\57"+
    "\1\0\64\57\1\60\35\57\31\62\1\63\5\62\1\0"+
    "\15\62\1\0\42\62\1\63\4\62\1\126\61\62\1\63"+
    "\5\62\1\61\15\62\1\0\11\62\6\0\2\127\1\0"+
    "\17\127\11\0\2\127\4\0\1\130\3\0\1\127\10\0"+
    "\3\127\1\0\4\66\1\0\2\66\1\0\17\66\11\0"+
    "\2\66\10\0\2\66\7\0\3\66\40\72\1\131\14\72"+
    "\1\132\51\72\1\133\14\72\1\132\11\72\34\74\1\0"+
    "\32\74\1\0\4\75\1\0\1\77\1\75\1\100\17\77"+
    "\11\0\2\77\21\0\3\77\1\0\4\134\1\0\1\134"+
    "\2\0\17\134\11\0\2\134\21\0\3\134\6\0\1\77"+
    "\2\0\17\77\11\0\2\77\21\0\3\77\1\0\4\100"+
    "\1\0\1\77\1\100\1\0\17\77\11\0\2\77\21\0"+
    "\3\77\1\0\4\16\1\0\2\16\1\0\2\16\1\135"+
    "\14\16\11\0\2\16\10\0\2\16\7\0\3\16\1\0"+
    "\4\16\1\0\2\16\1\0\5\16\1\136\11\16\11\0"+
    "\2\16\10\0\2\16\7\0\3\16\1\0\4\16\1\0"+
    "\2\16\1\0\12\16\1\102\4\16\11\0\2\16\10\0"+
    "\2\16\7\0\3\16\1\0\4\16\1\0\2\16\1\0"+
    "\1\16\1\137\15\16\11\0\2\16\10\0\2\16\7\0"+
    "\3\16\1\0\4\16\1\0\2\16\1\0\5\16\1\140"+
    "\11\16\11\0\2\16\10\0\2\16\7\0\3\16\1\0"+
    "\4\16\1\0\2\16\1\0\2\16\1\141\14\16\11\0"+
    "\2\16\10\0\2\16\7\0\3\16\1\0\4\16\1\0"+
    "\2\16\1\0\14\16\1\102\2\16\11\0\2\16\10\0"+
    "\2\16\7\0\3\16\33\0\1\142\33\0\40\113\1\143"+
    "\26\113\40\115\1\144\26\115\55\0\1\145\11\0\31\62"+
    "\1\63\23\62\1\0\11\62\1\0\4\127\1\0\2\127"+
    "\1\0\17\127\11\0\2\127\10\0\2\127\7\0\3\127"+
    "\40\72\1\146\14\72\1\132\11\72\16\0\1\147\21\0"+
    "\1\132\1\150\1\151\25\0\4\16\1\0\2\16\1\0"+
    "\2\16\1\102\14\16\11\0\2\16\10\0\2\16\7\0"+
    "\3\16\1\0\4\16\1\0\2\16\1\0\6\16\1\102"+
    "\10\16\11\0\2\16\10\0\2\16\7\0\3\16\1\0"+
    "\4\16\1\0\2\16\1\0\11\16\1\102\5\16\11\0"+
    "\2\16\10\0\2\16\7\0\3\16\1\0\4\16\1\0"+
    "\2\16\1\0\14\16\1\152\2\16\11\0\2\16\10\0"+
    "\2\16\7\0\3\16\1\0\4\16\1\0\2\16\1\0"+
    "\14\16\1\137\2\16\11\0\2\16\10\0\2\16\7\0"+
    "\3\16\33\142\1\153\33\142\16\0\1\154\22\0\1\155"+
    "\1\156\24\0\16\157\1\160\22\157\1\161\1\162\13\157"+
    "\1\163\10\157\53\0\1\164\53\0\1\132\44\0\1\147"+
    "\23\0\1\151\42\0\1\147\50\0\33\142\1\165\33\142"+
    "\16\0\1\154\23\0\1\156\42\0\1\154\50\0\56\157"+
    "\1\163\26\157\1\160\23\157\1\162\13\157\1\163\26\157"+
    "\1\160\37\157\1\163\50\157\1\166\15\157\1\163\10\157"+
    "\36\0\1\167\30\0\33\142\1\170\33\142\62\0\1\171"+
    "\67\0\1\172\61\0\1\173\61\0\1\174\67\0\1\175"+
    "\100\0\1\176\67\0\1\177\66\0\1\200\66\0\1\201"+
    "\66\0\1\202\66\0\1\203\66\0\1\204\66\0\1\205"+
    "\66\0\1\206\66\0\1\207\67\0\1\210\65\0\1\211"+
    "\67\0\1\212\65\0\1\213\67\0\1\214\65\0\1\215"+
    "\65\0\1\216\67\0\1\217\67\0\1\220\64\0\1\221"+
    "\67\0\1\222\67\0\1\223\64\0\1\224\67\0\1\225"+
    "\65\0\1\226\67\0\1\227\65\0\1\230\66\0\1\231"+
    "\66\0\1\232\66\0\1\233\63\0\1\234\66\0\1\235"+
    "\57\0\1\236\72\0\1\237\61\0\1\240\76\0\1\241"+
    "\57\0\1\242\44\0\1\243\107\0\1\244\73\0\1\245"+
    "\62\0\1\246\72\0\1\247\61\0\1\250\72\0\1\251"+
    "\66\0\1\252\11\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7425];
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
    "\11\0\1\11\4\1\1\11\6\1\1\11\2\1\1\11"+
    "\2\1\1\11\1\1\3\11\1\1\4\11\13\1\1\11"+
    "\5\1\2\11\1\1\1\0\3\1\1\0\13\1\2\0"+
    "\1\11\3\0\3\1\1\11\1\1\1\0\2\1\1\11"+
    "\1\0\1\11\7\1\1\0\2\1\1\0\1\1\1\11"+
    "\3\1\1\0\1\11\2\1\1\0\3\1\3\0\1\1"+
    "\1\0\1\1\61\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[170];
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
        if (count > _currentIndent) {
            _indents.push(_currentIndent);
            _currentIndent += count;
            _enterState(INDENTED);
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
        return null;
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        System.out.println("Entering state " + state);
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
            System.out.println("Exiting state...");
            yybegin(_states.pop());
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
        System.out.println("Matched [" + yytext() + "] as " + input);
        return input;
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

      if (zzMarkedPosL > zzStartRead) {
        switch ((zzBufferArrayL != null ? zzBufferArrayL[zzMarkedPosL-1] : zzBufferL.charAt(zzMarkedPosL-1))) {
        case '\n':
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          zzAtBOL = true;
          break;
        case '\r': 
          if (zzMarkedPosL < zzEndReadL)
            zzAtBOL = (zzBufferArrayL != null ? zzBufferArrayL[zzMarkedPosL] : zzBufferL.charAt(zzMarkedPosL)) != '\n';
          else if (zzAtEOF)
            zzAtBOL = false;
          else {
            boolean eof = zzRefill();
            zzMarkedPosL = zzMarkedPos;
            zzEndReadL = zzEndRead;
            zzBufferL = zzBuffer;
            if (eof) 
              zzAtBOL = false;
            else 
              zzAtBOL = (zzBufferArrayL != null ? zzBufferArrayL[zzMarkedPosL] : zzBufferL.charAt(zzMarkedPosL)) != '\n';
          }
          break;
        default:
          zzAtBOL = false;
        }
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      if (zzAtBOL)
        zzState = ZZ_LEXSTATE[zzLexicalState+1];
      else
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
        case 11: 
          { return LiveScriptTypes.COLON;
          }
        case 40: break;
        case 37: 
          { return LiveScriptTypes.COMMENT_BLOCK;
          }
        case 41: break;
        case 15: 
          { return LiveScriptTypes.PAREN_L;
          }
        case 42: break;
        case 17: 
          { return LiveScriptTypes.COMMENT_LINE;
          }
        case 43: break;
        case 21: 
          { return LiveScriptTypes.STRING;
          }
        case 44: break;
        case 16: 
          { return LiveScriptTypes.PAREN_R;
          }
        case 45: break;
        case 31: 
          { _rewind(); _rewindBy(-1); _enterState(STRING_VARIABLE); return LiveScriptTypes.STRING;
          }
        case 46: break;
        case 38: 
          { return LiveScriptTypes.HEREDOC;
          }
        case 47: break;
        case 25: 
          { _exitState(); return LiveScriptTypes.STRING_INTER_END;
          }
        case 48: break;
        case 3: 
          { return _out(LiveScriptTypes.IDENTIFIER);
          }
        case 49: break;
        case 28: 
          { return LiveScriptTypes.BACKSTRING;
          }
        case 50: break;
        case 30: 
          { System.out.println("State is " + yystate() + ", text is [" + yytext() + "]");
        _rewindBy(1);
        IElementType result = _parseIndent(yylength());
        if (result != null) return result;
          }
        case 51: break;
        case 13: 
          { return LiveScriptTypes.CURL_L;
          }
        case 52: break;
        case 36: 
          { return LiveScriptTypes.THIS;
          }
        case 53: break;
        case 10: 
          { return LiveScriptTypes.RIGHT_OP;
          }
        case 54: break;
        case 4: 
          { return LiveScriptTypes.DOT;
          }
        case 55: break;
        case 7: 
          { return _out(LiveScriptTypes.NEWLINE);
          }
        case 56: break;
        case 8: 
          { _enterState(FULL_STRING_STARTED); return LiveScriptTypes.STRING_START;
          }
        case 57: break;
        case 26: 
          { return _out(LiveScriptTypes.COMMENT_LINE);
          }
        case 58: break;
        case 29: 
          { _enterState(REGEX); return _out(LiveScriptTypes.REGEX);
          }
        case 59: break;
        case 27: 
          { return LiveScriptTypes.RESERVED_LITERAL;
          }
        case 60: break;
        case 6: 
          { _enterState(SIMPLE_STRING_STARTED); return LiveScriptTypes.STRING_START;
          }
        case 61: break;
        case 20: 
          { _rewindTo("#"); _exitState(); return TokenType.WHITE_SPACE;
          }
        case 62: break;
        case 1: 
          { return TokenType.BAD_CHARACTER;
          }
        case 63: break;
        case 33: 
          { char c = yycharat(yylength() - 1);
        if (c == '#')
            _rewindBy(1);
        else if (c == '/')
            _rewindBy(2);
        else
            throw new Error("Unexpected character: [" + c + "] at the end of regex line.");
        return _out(LiveScriptTypes.REGEX);
          }
        case 64: break;
        case 39: 
          { return LiveScriptTypes.TEST;
          }
        case 65: break;
        case 2: 
          { return LiveScriptTypes.NUMBER;
          }
        case 66: break;
        case 22: 
          { _exitState(); return LiveScriptTypes.STRING_END;
          }
        case 67: break;
        case 32: 
          { _enterState(STRING_SUSPENDED); return LiveScriptTypes.STRING_INTER_START;
          }
        case 68: break;
        case 24: 
          { _rewindBy(1); _enterState(YYINITIAL);
          }
        case 69: break;
        case 18: 
          { return _out(TokenType.WHITE_SPACE);
          }
        case 70: break;
        case 34: 
          { _exitState(); return _out(LiveScriptTypes.REGEX);
          }
        case 71: break;
        case 9: 
          { return LiveScriptTypes.OPERATOR;
          }
        case 72: break;
        case 35: 
          { return _out(LiveScriptTypes.REGEX);
          }
        case 73: break;
        case 19: 
          { System.out.println("I> text is [" + yytext() + "], dedenting");
            _rewind();
            return _parseIndent(0);
          }
        case 74: break;
        case 23: 
          { _exitState(); return LiveScriptTypes.IDENTIFIER;
          }
        case 75: break;
        case 14: 
          { if (_exitState()) _rewindBy(1); else return LiveScriptTypes.CURL_R;
          }
        case 76: break;
        case 12: 
          { return LiveScriptTypes.COMMA;
          }
        case 77: break;
        case 5: 
          { return LiveScriptTypes.THIS_AT;
          }
        case 78: break;
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
