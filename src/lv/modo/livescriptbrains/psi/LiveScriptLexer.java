/* The following code was generated by JFlex 1.4.3 on 15.21.3 01:26 */

package lv.modo.livescriptbrains.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.Stack;

import lv.modo.livescriptbrains.psi.LiveScriptTypes;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 15.21.3 01:26 from the specification file
 * <tt>C:/Home/Martin/dev/misc/LiveScriptBrains/src/lv/modo/livescriptbrains/psi/LiveScript.flex</tt>
 */
public class LiveScriptLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int ISTRING = 8;
  public static final int SPLIT_OP = 16;
  public static final int OBJECT = 22;
  public static final int DSTRING = 6;
  public static final int STRING_SUSPENDED = 12;
  public static final int REGEX = 14;
  public static final int LIST = 18;
  public static final int YYINITIAL = 0;
  public static final int STRING_VAR = 20;
  public static final int INDENTED = 2;
  public static final int BLOCK_STATEMENT = 4;
  public static final int VSTRING = 10;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  1,  2,  2,  2,  2,  3,  3,  4,  5,  2,  2,  2,  2,  2,  2, 
     6,  6,  7,  8,  9,  9, 10, 11
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\30\1\57\1\0\1\61\1\60\22\0\1\30\1\52\1\33"+
    "\1\34\1\55\1\32\1\0\1\31\1\45\1\46\1\37\1\40\1\43"+
    "\1\56\1\10\1\36\1\4\2\2\1\3\6\1\1\42\1\51\1\0"+
    "\1\41\1\0\1\53\1\0\4\6\1\6\15\6\1\6\1\6\6\6"+
    "\1\47\1\27\1\50\1\0\1\7\1\0\1\24\1\6\1\54\1\17"+
    "\1\22\1\23\1\6\1\6\1\16\2\6\1\13\1\6\1\11\1\15"+
    "\2\6\1\21\1\25\1\20\1\12\1\14\2\6\1\26\1\6\1\35"+
    "\1\0\1\44\1\5\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\10\0\1\2\3\3\1\4\1\5\6\4"+
    "\1\2\1\6\2\2\1\7\1\10\1\11\2\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\4\2\25\3\6\2\1\1\26\1\27\1\30"+
    "\1\31\2\6\1\32\1\33\1\32\1\6\1\34\1\35"+
    "\1\36\1\3\1\0\2\3\1\0\1\4\1\37\5\4"+
    "\1\1\1\0\1\1\2\0\1\1\1\7\1\1\1\0"+
    "\1\4\1\40\1\0\1\41\1\42\4\4\1\1\1\0"+
    "\1\1\1\0\1\4\1\0\1\43\1\0\1\44\1\4"+
    "\1\0\1\45";

  private static int [] zzUnpackAction() {
    int [] result = new int[106];
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
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u015e"+
    "\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc\0\u02ee"+
    "\0\u0320\0\u0352\0\u0384\0\u03b6\0\u03e8\0\u041a\0\u044c\0\u047e"+
    "\0\u04b0\0\u04e2\0\u0514\0\u0546\0\u0578\0\u05aa\0\u0258\0\u05dc"+
    "\0\u0258\0\u0258\0\u0258\0\u0258\0\u0258\0\u0258\0\u0258\0\u0258"+
    "\0\u0258\0\u0258\0\u0258\0\u060e\0\u0258\0\u0640\0\u0672\0\u0258"+
    "\0\u06a4\0\u06d6\0\u0708\0\u0258\0\u073a\0\u0258\0\u0258\0\u076c"+
    "\0\u079e\0\u0258\0\u0258\0\u07d0\0\u0802\0\u0258\0\u0834\0\u0258"+
    "\0\u0866\0\u0898\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u0320\0\u0960"+
    "\0\u0992\0\u09c4\0\u09f6\0\u0a28\0\u0a5a\0\u0a8c\0\u04b0\0\u0abe"+
    "\0\u0af0\0\u0b22\0\u0b54\0\u0258\0\u0b86\0\u0bb8\0\u0258\0\u0bea"+
    "\0\u0c1c\0\u0258\0\u0c4e\0\u0c80\0\u0cb2\0\u0ce4\0\u0abe\0\u0d16"+
    "\0\u0578\0\u0d48\0\u0d7a\0\u0c1c\0\u0320\0\u0dac\0\u0258\0\u0dde"+
    "\0\u0e10\0\u0320";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[106];
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
    "\1\15\1\16\1\17\1\20\1\16\1\15\2\21\1\22"+
    "\1\23\2\21\1\24\1\25\2\21\1\26\2\21\1\27"+
    "\2\21\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\15"+
    "\1\46\1\47\1\50\1\15\1\51\1\52\1\53\1\54"+
    "\1\21\1\41\1\55\1\56\1\32\1\15\1\16\1\17"+
    "\1\20\1\16\1\15\2\21\1\22\1\23\2\21\1\24"+
    "\1\25\2\21\1\26\2\21\1\27\2\21\1\30\1\31"+
    "\1\57\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\15\1\46\1\47\1\50"+
    "\1\15\1\51\1\52\1\53\1\54\1\21\1\41\1\60"+
    "\1\61\1\57\57\15\1\0\2\15\27\62\1\63\3\62"+
    "\1\64\1\65\25\62\1\15\1\16\1\17\1\20\1\16"+
    "\1\15\2\21\1\22\1\23\2\21\1\24\1\25\2\21"+
    "\1\26\2\21\1\27\2\21\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43"+
    "\1\44\1\45\1\66\1\46\1\47\1\50\1\15\1\51"+
    "\1\52\1\53\1\54\1\21\1\41\1\55\1\56\1\32"+
    "\1\15\1\16\1\17\1\20\1\16\1\15\2\21\1\22"+
    "\1\23\2\21\1\24\1\25\2\21\1\26\2\21\1\27"+
    "\2\21\1\30\1\31\1\57\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\66"+
    "\1\46\1\47\1\50\1\15\1\51\1\52\1\53\1\54"+
    "\1\21\1\41\1\60\1\61\1\57\57\67\1\32\1\70"+
    "\1\67\1\15\1\16\1\17\1\20\1\16\1\15\2\21"+
    "\1\22\1\23\2\21\1\24\1\25\2\21\1\26\2\21"+
    "\1\27\2\21\1\30\1\31\1\71\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\72"+
    "\1\15\1\46\1\47\1\50\1\73\1\51\1\52\1\53"+
    "\1\54\1\21\1\41\2\74\1\71\1\15\1\16\1\17"+
    "\1\20\1\16\1\15\2\21\1\22\1\23\2\21\1\24"+
    "\1\25\2\21\1\26\2\21\1\27\2\21\1\30\1\31"+
    "\1\75\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\72\1\15\1\46\1\47\1\50"+
    "\1\73\1\51\1\52\1\53\1\54\1\21\1\41\2\74"+
    "\1\75\6\76\2\77\1\76\16\77\25\76\2\77\1\76"+
    "\1\0\2\76\1\15\1\16\1\17\1\20\1\16\1\15"+
    "\2\21\1\22\1\23\2\21\1\24\1\25\2\21\1\26"+
    "\2\21\1\27\2\21\1\30\1\31\1\71\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\72\1\100\1\46\1\47\1\50\1\15\1\51\1\52"+
    "\1\53\1\54\1\21\1\41\2\74\1\71\1\15\1\16"+
    "\1\17\1\20\1\16\1\15\2\21\1\22\1\23\2\21"+
    "\1\24\1\25\2\21\1\26\2\21\1\27\2\21\1\30"+
    "\1\31\1\75\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\72\1\100\1\46\1\47"+
    "\1\50\1\15\1\51\1\52\1\53\1\54\1\21\1\41"+
    "\2\74\1\75\63\0\4\101\1\102\1\103\1\101\1\104"+
    "\16\103\25\0\1\103\6\0\4\16\1\102\1\103\1\101"+
    "\1\104\16\103\25\0\1\103\6\0\1\101\1\16\1\101"+
    "\1\16\1\102\1\103\1\101\1\104\16\103\25\0\1\103"+
    "\6\0\4\21\1\0\2\21\1\0\16\21\25\0\3\21"+
    "\13\0\1\105\52\0\4\21\1\0\2\21\1\0\1\21"+
    "\1\106\2\21\1\107\11\21\25\0\3\21\4\0\4\21"+
    "\1\0\2\21\1\0\4\21\1\110\11\21\25\0\3\21"+
    "\4\0\4\21\1\0\2\21\1\0\1\107\11\21\1\111"+
    "\3\21\25\0\3\21\4\0\4\21\1\0\2\21\1\0"+
    "\10\21\1\112\5\21\25\0\3\21\4\0\4\21\1\0"+
    "\2\21\1\0\13\21\1\113\2\21\25\0\3\21\4\0"+
    "\4\21\1\0\2\21\1\0\11\21\1\114\4\21\25\0"+
    "\3\21\3\0\30\115\1\116\12\115\2\0\1\115\1\0"+
    "\1\115\2\0\5\115\1\32\1\70\1\117\30\0\1\32"+
    "\30\0\1\32\27\120\1\121\1\120\1\122\30\120\33\0"+
    "\1\35\26\0\27\35\1\123\3\35\1\124\1\0\25\35"+
    "\57\36\1\0\2\36\37\0\1\125\23\0\4\21\1\0"+
    "\2\21\1\0\2\21\1\126\13\21\25\0\3\21\62\0"+
    "\1\55\2\0\30\127\1\57\26\127\1\60\1\61\1\57"+
    "\57\0\1\60\2\0\27\62\1\63\3\62\1\0\1\130"+
    "\54\62\1\63\4\62\1\130\25\62\35\0\1\131\54\0"+
    "\1\32\26\0\1\32\1\0\1\32\30\0\1\71\26\0"+
    "\2\74\1\71\30\0\1\74\26\0\3\74\30\127\1\75"+
    "\26\127\2\74\1\75\1\0\4\77\1\0\2\77\1\0"+
    "\16\77\25\0\3\77\4\0\4\101\1\0\1\103\1\101"+
    "\1\104\16\103\25\0\1\103\6\0\4\103\1\0\1\103"+
    "\2\0\16\103\25\0\1\103\6\0\4\104\1\0\1\103"+
    "\1\104\1\0\16\103\25\0\1\103\15\0\1\132\52\0"+
    "\4\21\1\0\2\21\1\0\2\21\1\133\13\21\25\0"+
    "\3\21\4\0\4\21\1\0\2\21\1\0\5\21\1\134"+
    "\10\21\25\0\3\21\4\0\4\21\1\0\2\21\1\0"+
    "\12\21\1\107\3\21\25\0\3\21\4\0\4\21\1\0"+
    "\2\21\1\0\1\21\1\135\14\21\25\0\3\21\4\0"+
    "\4\21\1\0\2\21\1\0\2\21\1\136\13\21\25\0"+
    "\3\21\4\0\4\21\1\0\2\21\1\0\14\21\1\107"+
    "\1\21\25\0\3\21\3\0\30\115\1\0\12\115\2\0"+
    "\1\115\1\0\1\115\2\0\5\115\2\0\1\115\30\0"+
    "\1\116\26\0\1\32\1\70\1\116\27\120\1\121\1\120"+
    "\1\124\57\120\1\121\1\120\1\137\30\120\31\0\1\140"+
    "\30\0\27\35\1\123\3\35\1\141\1\0\25\35\37\125"+
    "\1\142\22\125\1\0\4\21\1\0\2\21\1\0\13\21"+
    "\1\143\2\21\25\0\3\21\40\0\1\144\70\0\1\62"+
    "\16\0\4\21\1\0\2\21\1\0\2\21\1\145\13\21"+
    "\25\0\3\21\4\0\4\21\1\0\2\21\1\0\6\21"+
    "\1\145\7\21\25\0\3\21\4\0\4\21\1\0\2\21"+
    "\1\0\11\21\1\107\4\21\25\0\3\21\4\0\4\21"+
    "\1\0\2\21\1\0\14\21\1\135\1\21\25\0\3\21"+
    "\3\0\31\140\1\146\30\140\36\0\1\147\24\0\4\21"+
    "\1\0\2\21\1\0\14\21\1\150\1\21\25\0\3\21"+
    "\34\0\1\151\31\0\4\21\1\0\2\21\1\0\14\21"+
    "\1\152\1\21\25\0\3\21\34\0\1\124\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3650];
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
    "\3\0\1\1\10\0\1\11\21\1\1\11\1\1\13\11"+
    "\1\1\1\11\2\1\1\11\3\1\1\11\1\1\2\11"+
    "\2\1\2\11\2\1\1\11\1\1\1\11\1\1\1\0"+
    "\2\1\1\0\10\1\1\0\1\1\2\0\2\1\1\11"+
    "\1\0\1\1\1\11\1\0\1\1\1\11\5\1\1\0"+
    "\1\1\1\0\1\1\1\0\1\1\1\0\1\11\1\1"+
    "\1\0\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[106];
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
     * Current indent baseline.
     */
    private static int _currentIndent = 0;


    private void _switchToState(int state) {
        System.out.println("Switching to state " + _stateName(state) + ".");
        yybegin(state);
    }

    /**
     * Enter a lexical state and push the previous one to the stack.
     */
    private void _enterState(int state) {
        //System.out.println("Entering state " + _stateName(state) + ".");
        _states.push(yystate());
        yybegin(state);
    }

    /**
     * Leave a lexical state and return to the previous one (if any).
     * @returns boolean True if state was switched to a previous one, false if already at YYINITIAL.
     */
    private boolean _exitState() {
        if (_states.empty()) {
            //System.out.println("State stack empty, defaulting to YYINITIAL.");
            yybegin(YYINITIAL);
            return false;
        }
        else {
            int newState = _states.pop();
            //System.out.println("Exiting state " + _stateName(yystate()) + ", state is now " + _stateName(newState));
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
        "SPLIT_OP",
        "LIST",
        "STRING_VAR",
        "OBJECT"
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
    while (i < 148) {
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
        System.out.println("End of file reached, clearing out state stack.");
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
        case 12: 
          { return _out(LiveScriptTypes.ASSIGN);
          }
        case 38: break;
        case 20: 
          { return _out(LiveScriptTypes.Q);
          }
        case 39: break;
        case 29: 
          { _exitState(); return _out(LiveScriptTypes.IDENTIFIER);
          }
        case 40: break;
        case 26: 
          { return _out(LiveScriptTypes.SEPARATOR);
          }
        case 41: break;
        case 5: 
          { return _out(LiveScriptTypes.DOT);
          }
        case 42: break;
        case 17: 
          { _enterState(LIST); return _out(LiveScriptTypes.LIST_START);
          }
        case 43: break;
        case 32: 
          { _rewindBy(1); return _out(LiveScriptTypes.INDENT);
          }
        case 44: break;
        case 15: 
          { return _out(LiveScriptTypes.PAREN_L);
          }
        case 45: break;
        case 11: 
          { _enterState(SPLIT_OP); return _out(LiveScriptTypes.PLUS);
          }
        case 46: break;
        case 18: 
          { return _out(LiveScriptTypes.SEMICOLON);
          }
        case 47: break;
        case 14: 
          { return _out(LiveScriptTypes.COMMA);
          }
        case 48: break;
        case 24: 
          { _exitState(); return _out(LiveScriptTypes.ISTRING);
          }
        case 49: break;
        case 4: 
          { return _out(LiveScriptTypes.IDENTIFIER);
          }
        case 50: break;
        case 27: 
          { _exitState(); return _out(LiveScriptTypes.LIST_END);
          }
        case 51: break;
        case 34: 
          { return _out(LiveScriptTypes.YADA);
          }
        case 52: break;
        case 37: 
          { return _out(LiveScriptTypes.CLASS);
          }
        case 53: break;
        case 21: 
          { return _out(LiveScriptTypes.NEWLINE);
          }
        case 54: break;
        case 16: 
          { return _out(LiveScriptTypes.PAREN_R);
          }
        case 55: break;
        case 8: 
          { return _out(LiveScriptTypes.COMMENT_LINE);
          }
        case 56: break;
        case 35: 
          { return _out(LiveScriptTypes.EMPTY);
          }
        case 57: break;
        case 3: 
          { return _out(LiveScriptTypes.NUMBER);
          }
        case 58: break;
        case 2: 
          { System.out.println("State is " + _stateName(yystate())); return _out(TokenType.BAD_CHARACTER);
          }
        case 59: break;
        case 1: 
          { return _out(LiveScriptTypes.STRING);
          }
        case 60: break;
        case 25: 
          { _rewindBy(1); _exitState();
          }
        case 61: break;
        case 30: 
          { _exitState(); return _out(LiveScriptTypes.OBJ_END);
          }
        case 62: break;
        case 7: 
          { _enterState(DSTRING); return _out(LiveScriptTypes.STRING_START);
          }
        case 63: break;
        case 23: 
          { _enterState(STRING_VAR); return _out(LiveScriptTypes.ESCAPE_CHAR);
          }
        case 64: break;
        case 36: 
          { return _out(LiveScriptTypes.COMMENT_BLOCK);
          }
        case 65: break;
        case 6: 
          { return _out(TokenType.WHITE_SPACE);
          }
        case 66: break;
        case 22: 
          { _exitState(); return _out(LiveScriptTypes.STRING_END);
          }
        case 67: break;
        case 31: 
          { return _out(LiveScriptTypes.BOOLEAN);
          }
        case 68: break;
        case 13: 
          { return _out(LiveScriptTypes.COLON);
          }
        case 69: break;
        case 33: 
          { _enterState(ISTRING); return _out(LiveScriptTypes.ISTRING);
          }
        case 70: break;
        case 9: 
          { _enterState(OBJECT); return _out(LiveScriptTypes.OBJ_START);
          }
        case 71: break;
        case 28: 
          { _exitState(); _rewindBy(1);
          }
        case 72: break;
        case 19: 
          { return _out(LiveScriptTypes.BANG);
          }
        case 73: break;
        case 10: 
          { _enterState(SPLIT_OP); return _out(LiveScriptTypes.MATH_OP);
          }
        case 74: break;
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
