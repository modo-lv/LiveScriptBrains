// This is a generated file. Not intended for manual editing.
package com.simpleplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.simpleplugin.psi.LiveScriptTypes.*;
import static com.simpleplugin.psi.LiveScriptParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LiveScriptParser implements PsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ANY_SEPARATOR) {
      r = AnySeparator(b, 0);
    }
    else if (t == ASSIGN_OP_EXPRESSION) {
      r = AssignOpExpression(b, 0);
    }
    else if (t == COM) {
      r = Com(b, 0);
    }
    else if (t == CURL_OBJ_DEF_EXPRESSION) {
      r = CurlObjDefExpression(b, 0);
    }
    else if (t == EXPLICIT_PROP_DEF_EXPRESSION) {
      r = ExplicitPropDefExpression(b, 0);
    }
    else if (t == EXPRESSION) {
      r = Expression(b, 0, -1);
    }
    else if (t == IMPLICIT_LIST_EXPRESSION) {
      r = ImplicitListExpression(b, 0);
    }
    else if (t == IMPLICIT_PROP_DEF_EXPRESSION) {
      r = ImplicitPropDefExpression(b, 0);
    }
    else if (t == INLINE_SEPARATOR) {
      r = InlineSeparator(b, 0);
    }
    else if (t == INTER_LINES) {
      r = InterLines(b, 0);
    }
    else if (t == INTER_STRING_EXPRESSION) {
      r = InterStringExpression(b, 0);
    }
    else if (t == INTERPOLATED_STRING_EXPRESSION) {
      r = InterpolatedStringExpression(b, 0);
    }
    else if (t == LIST_EXPRESSION) {
      r = ListExpression(b, 0);
    }
    else if (t == LITERAL_EXPRESSION) {
      r = LiteralExpression(b, 0);
    }
    else if (t == MULTILINE_SEPARATOR) {
      r = MultilineSeparator(b, 0);
    }
    else if (t == OBJ_DEF_EXPRESSION) {
      r = ObjDefExpression(b, 0);
    }
    else if (t == OP_EXPRESSION) {
      r = Expression(b, 0, 1);
    }
    else if (t == REFERENCE_EXPRESSION) {
      r = ReferenceExpression(b, 0);
    }
    else if (t == REGEX_EXPRESSION) {
      r = RegexExpression(b, 0);
    }
    else if (t == STATEMENT) {
      r = Statement(b, 0);
    }
    else if (t == STRING_EXPRESSION) {
      r = StringExpression(b, 0);
    }
    else if (t == TEST_EXPRESSION) {
      r = TestExpression(b, 0);
    }
    else if (t == VALUE_EXPRESSION) {
      r = ValueExpression(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ASSIGN_OP_EXPRESSION, CURL_OBJ_DEF_EXPRESSION, EXPLICIT_PROP_DEF_EXPRESSION, EXPRESSION,
      IMPLICIT_LIST_EXPRESSION, IMPLICIT_PROP_DEF_EXPRESSION, INTERPOLATED_STRING_EXPRESSION, INTER_STRING_EXPRESSION,
      LIST_EXPRESSION, LITERAL_EXPRESSION, OBJ_DEF_EXPRESSION, OP_EXPRESSION,
      REFERENCE_EXPRESSION, REGEX_EXPRESSION, STRING_EXPRESSION, TEST_EXPRESSION,
      VALUE_EXPRESSION),
  };

  /* ********************************************************** */
  // MultilineSeparator | InlineSeparator
  public static boolean AnySeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnySeparator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<any separator>");
    r = MultilineSeparator(b, l + 1);
    if (!r) r = InlineSeparator(b, l + 1);
    exit_section_(b, l, m, ANY_SEPARATOR, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ReferenceExpression EQ NEWLINE?
  static boolean Assign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assign")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReferenceExpression(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && Assign_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean Assign_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assign_2")) return false;
    consumeToken(b, NEWLINE);
    return true;
  }

  /* ********************************************************** */
  // COMMENT_LINE | COMMENT_BLOCK
  public static boolean Com(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Com")) return false;
    if (!nextTokenIs(b, "<com>", COMMENT_BLOCK, COMMENT_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<com>");
    r = consumeToken(b, COMMENT_LINE);
    if (!r) r = consumeToken(b, COMMENT_BLOCK);
    exit_section_(b, l, m, COM, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ValueExpression COLON Expression
  public static boolean ExplicitPropDefExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExplicitPropDefExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<explicit prop def expression>");
    r = ValueExpression(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, EXPLICIT_PROP_DEF_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ("+"|"-")? ValueExpression
  public static boolean ImplicitPropDefExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplicitPropDefExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<implicit prop def expression>");
    r = ImplicitPropDefExpression_0(b, l + 1);
    r = r && ValueExpression(b, l + 1);
    exit_section_(b, l, m, IMPLICIT_PROP_DEF_EXPRESSION, r, false, null);
    return r;
  }

  // ("+"|"-")?
  private static boolean ImplicitPropDefExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplicitPropDefExpression_0")) return false;
    ImplicitPropDefExpression_0_0(b, l + 1);
    return true;
  }

  // "+"|"-"
  private static boolean ImplicitPropDefExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplicitPropDefExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, "-");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMA?
  public static boolean InlineSeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineSeparator")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<inline separator>");
    consumeToken(b, COMMA);
    exit_section_(b, l, m, INLINE_SEPARATOR, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // <<optionalParens Expression>>*
  public static boolean InterLines(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterLines")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<inter lines>");
    int c = current_position_(b);
    while (true) {
      if (!optionalParens(b, l + 1, Expression_parser_)) break;
      if (!empty_element_parsed_guard_(b, "InterLines", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, INTER_LINES, true, false, recover_inter_parser_);
    return true;
  }

  /* ********************************************************** */
  // STRING_START string STRING_INTER_START
  //     | STRING_INTER_END string STRING_INTER_START
  //     | STRING_INTER_END string STRING_END
  public static boolean InterStringExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterStringExpression")) return false;
    if (!nextTokenIs(b, "<inter string expression>", STRING_INTER_END, STRING_START)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<inter string expression>");
    r = InterStringExpression_0(b, l + 1);
    if (!r) r = InterStringExpression_1(b, l + 1);
    if (!r) r = InterStringExpression_2(b, l + 1);
    exit_section_(b, l, m, INTER_STRING_EXPRESSION, r, false, null);
    return r;
  }

  // STRING_START string STRING_INTER_START
  private static boolean InterStringExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterStringExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_START);
    r = r && string(b, l + 1);
    r = r && consumeToken(b, STRING_INTER_START);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_INTER_END string STRING_INTER_START
  private static boolean InterStringExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterStringExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_INTER_END);
    r = r && string(b, l + 1);
    r = r && consumeToken(b, STRING_INTER_START);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_INTER_END string STRING_END
  private static boolean InterStringExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterStringExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_INTER_END);
    r = r && string(b, l + 1);
    r = r && consumeToken(b, STRING_END);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // InterStringExpression+ InterLines InterStringExpression+
  public static boolean InterpolatedStringExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterpolatedStringExpression")) return false;
    if (!nextTokenIs(b, "<interpolated string expression>", STRING_INTER_END, STRING_START)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<interpolated string expression>");
    r = InterpolatedStringExpression_0(b, l + 1);
    r = r && InterLines(b, l + 1);
    r = r && InterpolatedStringExpression_2(b, l + 1);
    exit_section_(b, l, m, INTERPOLATED_STRING_EXPRESSION, r, false, null);
    return r;
  }

  // InterStringExpression+
  private static boolean InterpolatedStringExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterpolatedStringExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InterStringExpression(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!InterStringExpression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InterpolatedStringExpression_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // InterStringExpression+
  private static boolean InterpolatedStringExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterpolatedStringExpression_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InterStringExpression(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!InterStringExpression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InterpolatedStringExpression_2", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMA? NEWLINE+
  public static boolean MultilineSeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultilineSeparator")) return false;
    if (!nextTokenIs(b, "<multiline separator>", COMMA, NEWLINE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<multiline separator>");
    r = MultilineSeparator_0(b, l + 1);
    r = r && MultilineSeparator_1(b, l + 1);
    exit_section_(b, l, m, MULTILINE_SEPARATOR, r, false, null);
    return r;
  }

  // COMMA?
  private static boolean MultilineSeparator_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultilineSeparator_0")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // NEWLINE+
  private static boolean MultilineSeparator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultilineSeparator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "MultilineSeparator_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (THIS_AT + IDENTIFIER (DOT IDENTIFIER)*) | ((IDENTIFIER|THIS) (DOT IDENTIFIER)+)
  static boolean PropReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropReference_0(b, l + 1);
    if (!r) r = PropReference_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // THIS_AT + IDENTIFIER (DOT IDENTIFIER)*
  private static boolean PropReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropReference_0_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && PropReference_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // THIS_AT +
  private static boolean PropReference_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THIS_AT);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, THIS_AT)) break;
      if (!empty_element_parsed_guard_(b, "PropReference_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (DOT IDENTIFIER)*
  private static boolean PropReference_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!PropReference_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PropReference_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean PropReference_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IDENTIFIER|THIS) (DOT IDENTIFIER)+
  private static boolean PropReference_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropReference_1_0(b, l + 1);
    r = r && PropReference_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER|THIS
  private static boolean PropReference_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, THIS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (DOT IDENTIFIER)+
  private static boolean PropReference_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PropReference_1_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!PropReference_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PropReference_1_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT IDENTIFIER
  private static boolean PropReference_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PropReference_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<optionalParens Expression>>
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
    r = optionalParens(b, l + 1, Expression_parser_);
    exit_section_(b, l, m, STATEMENT, r, false, recover_statement_parser_);
    return r;
  }

  /* ********************************************************** */
  // STRING_START string STRING_END | BACKSTRING | HEREDOC
  public static boolean StringExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<string expression>");
    r = StringExpression_0(b, l + 1);
    if (!r) r = consumeToken(b, BACKSTRING);
    if (!r) r = consumeToken(b, HEREDOC);
    exit_section_(b, l, m, STRING_EXPRESSION, r, false, null);
    return r;
  }

  // STRING_START string STRING_END
  private static boolean StringExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_START);
    r = r && string(b, l + 1);
    r = r && consumeToken(b, STRING_END);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<optionalParens (ReferenceExpression | LiteralExpression)>>
  public static boolean ValueExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<value expression>");
    r = optionalParens(b, l + 1, ValueExpression_0_0_parser_);
    exit_section_(b, l, m, VALUE_EXPRESSION, r, false, null);
    return r;
  }

  // ReferenceExpression | LiteralExpression
  private static boolean ValueExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ValueExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ReferenceExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | THIS | THIS_AT
  static boolean VarReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarReference")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, THIS);
    if (!r) r = consumeToken(b, THIS_AT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (Statement | Com | NEWLINE)* <<eof>>
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = file_0(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Statement | Com | NEWLINE)*
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!file_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Statement | Com | NEWLINE
  private static boolean file_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    if (!r) r = Com(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INDENT <<param>>+ DEDENT?
  static boolean indented(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "indented")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && indented_1(b, l + 1, _param);
    r = r && indented_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // <<param>>+
  private static boolean indented_1(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "indented_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _param.parse(b, l);
    int c = current_position_(b);
    while (r) {
      if (!_param.parse(b, l)) break;
      if (!empty_element_parsed_guard_(b, "indented_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // DEDENT?
  private static boolean indented_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "indented_2")) return false;
    consumeToken(b, DEDENT);
    return true;
  }

  /* ********************************************************** */
  // <<param>> (InlineSeparator <<param>>)*
  static boolean inlineArgList(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "inlineArgList")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _param.parse(b, l);
    r = r && inlineArgList_1(b, l + 1, _param);
    exit_section_(b, m, null, r);
    return r;
  }

  // (InlineSeparator <<param>>)*
  private static boolean inlineArgList_1(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "inlineArgList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!inlineArgList_1_0(b, l + 1, _param)) break;
      if (!empty_element_parsed_guard_(b, "inlineArgList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // InlineSeparator <<param>>
  private static boolean inlineArgList_1_0(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "inlineArgList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InlineSeparator(b, l + 1);
    r = r && _param.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<param>> (AnySeparator <<param>>)*
  static boolean multiLineArgList(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "multiLineArgList")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _param.parse(b, l);
    r = r && multiLineArgList_1(b, l + 1, _param);
    exit_section_(b, m, null, r);
    return r;
  }

  // (AnySeparator <<param>>)*
  private static boolean multiLineArgList_1(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "multiLineArgList_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!multiLineArgList_1_0(b, l + 1, _param)) break;
      if (!empty_element_parsed_guard_(b, "multiLineArgList_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AnySeparator <<param>>
  private static boolean multiLineArgList_1_0(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "multiLineArgList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnySeparator(b, l + 1);
    r = r && _param.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PAREN_L <<param>> PAREN_R | <<param>>
  static boolean optionalParens(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "optionalParens")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = optionalParens_0(b, l + 1, _param);
    if (!r) r = _param.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  // PAREN_L <<param>> PAREN_R
  private static boolean optionalParens_0(PsiBuilder b, int l, final Parser _param) {
    if (!recursion_guard_(b, l, "optionalParens_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAREN_L);
    r = r && _param.parse(b, l);
    r = r && consumeToken(b, PAREN_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !(NEWLINE | STRING_INTER_END)
  static boolean recover_inter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_inter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_, null);
    r = !recover_inter_0(b, l + 1);
    exit_section_(b, l, m, null, r, false, null);
    return r;
  }

  // NEWLINE | STRING_INTER_END
  private static boolean recover_inter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_inter_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NEWLINE);
    if (!r) r = consumeToken(b, STRING_INTER_END);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !NEWLINE
  static boolean recover_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_, null);
    r = !consumeToken(b, NEWLINE);
    exit_section_(b, l, m, null, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING* (IDENTIFIER | STRING)*
  static boolean string(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = string_0(b, l + 1);
    r = r && string_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING*
  private static boolean string_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, STRING)) break;
      if (!empty_element_parsed_guard_(b, "string_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (IDENTIFIER | STRING)*
  private static boolean string_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!string_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "string_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // IDENTIFIER | STRING
  private static boolean string_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: ATOM(TestExpression)
  // 1: ATOM(AssignOpExpression)
  // 2: BINARY(OpExpression)
  // 3: ATOM(RegexExpression)
  // 4: ATOM(LiteralExpression)
  // 5: ATOM(ReferenceExpression)
  // 6: ATOM(CurlObjDefExpression)
  // 7: ATOM(ObjDefExpression)
  // 8: ATOM(ListExpression)
  // 9: ATOM(ImplicitListExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = TestExpression(b, l + 1);
    if (!r) r = AssignOpExpression(b, l + 1);
    if (!r) r = RegexExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
    if (!r) r = ReferenceExpression(b, l + 1);
    if (!r) r = CurlObjDefExpression(b, l + 1);
    if (!r) r = ObjDefExpression(b, l + 1);
    if (!r) r = ListExpression(b, l + 1);
    if (!r) r = ImplicitListExpression(b, l + 1);
    p = r;
    r = r && Expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 2 && OpExpression_0(b, l + 1)) {
        r = Expression(b, l, 2);
        exit_section_(b, l, m, OP_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // TEST
  public static boolean TestExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TestExpression")) return false;
    if (!nextTokenIsFast(b, TEST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, TEST);
    exit_section_(b, m, TEST_EXPRESSION, r);
    return r;
  }

  // Assign Expression
  public static boolean AssignOpExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOpExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<assign op expression>");
    r = Assign(b, l + 1);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, l, m, ASSIGN_OP_EXPRESSION, r, false, null);
    return r;
  }

  // OPERATOR NEWLINE?
  private static boolean OpExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OpExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OPERATOR);
    r = r && OpExpression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NEWLINE?
  private static boolean OpExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OpExpression_0_1")) return false;
    consumeTokenSmart(b, NEWLINE);
    return true;
  }

  // REGEX (COMMENT_LINE | REGEX)*
  public static boolean RegexExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegexExpression")) return false;
    if (!nextTokenIsFast(b, REGEX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, REGEX);
    r = r && RegexExpression_1(b, l + 1);
    exit_section_(b, m, REGEX_EXPRESSION, r);
    return r;
  }

  // (COMMENT_LINE | REGEX)*
  private static boolean RegexExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegexExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!RegexExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RegexExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // COMMENT_LINE | REGEX
  private static boolean RegexExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegexExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMENT_LINE);
    if (!r) r = consumeTokenSmart(b, REGEX);
    exit_section_(b, m, null, r);
    return r;
  }

  // RESERVED_LITERAL | ReferenceExpression | NUMBER | InterpolatedStringExpression | StringExpression
  public static boolean LiteralExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<literal expression>");
    r = consumeTokenSmart(b, RESERVED_LITERAL);
    if (!r) r = ReferenceExpression(b, l + 1);
    if (!r) r = consumeTokenSmart(b, NUMBER);
    if (!r) r = InterpolatedStringExpression(b, l + 1);
    if (!r) r = StringExpression(b, l + 1);
    exit_section_(b, l, m, LITERAL_EXPRESSION, r, false, null);
    return r;
  }

  // PropReference | VarReference
  public static boolean ReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<reference expression>");
    r = PropReference(b, l + 1);
    if (!r) r = VarReference(b, l + 1);
    exit_section_(b, l, m, REFERENCE_EXPRESSION, r, false, null);
    return r;
  }

  // CURL_L (<<multiLineArgList (ExplicitPropDefExpression | ImplicitPropDefExpression)>>)* CURL_R
  public static boolean CurlObjDefExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlObjDefExpression")) return false;
    if (!nextTokenIsFast(b, CURL_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, CURL_L);
    r = r && CurlObjDefExpression_1(b, l + 1);
    r = r && consumeToken(b, CURL_R);
    exit_section_(b, m, CURL_OBJ_DEF_EXPRESSION, r);
    return r;
  }

  // (<<multiLineArgList (ExplicitPropDefExpression | ImplicitPropDefExpression)>>)*
  private static boolean CurlObjDefExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlObjDefExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!CurlObjDefExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CurlObjDefExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // <<multiLineArgList (ExplicitPropDefExpression | ImplicitPropDefExpression)>>
  private static boolean CurlObjDefExpression_1_0(PsiBuilder b, int l) {
    return multiLineArgList(b, l + 1, CurlObjDefExpression_1_0_0_0_parser_);
  }

  // ExplicitPropDefExpression | ImplicitPropDefExpression
  private static boolean CurlObjDefExpression_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlObjDefExpression_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ExplicitPropDefExpression(b, l + 1);
    if (!r) r = ImplicitPropDefExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // <<multiLineArgList ExplicitPropDefExpression>>
  public static boolean ObjDefExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjDefExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<obj def expression>");
    r = multiLineArgList(b, l + 1, ExplicitPropDefExpression_parser_);
    exit_section_(b, l, m, OBJ_DEF_EXPRESSION, r, false, null);
    return r;
  }

  // BRACK_L <<multiLineArgList ValueExpression>> BRACK_R
  public static boolean ListExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListExpression")) return false;
    if (!nextTokenIsFast(b, BRACK_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, BRACK_L);
    r = r && multiLineArgList(b, l + 1, ValueExpression_parser_);
    r = r && consumeToken(b, BRACK_R);
    exit_section_(b, m, LIST_EXPRESSION, r);
    return r;
  }

  // Assign INDENT Expression Expression+ DEDENT
  public static boolean ImplicitListExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplicitListExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<implicit list expression>");
    r = Assign(b, l + 1);
    r = r && consumeToken(b, INDENT);
    r = r && Expression(b, l + 1, -1);
    r = r && ImplicitListExpression_3(b, l + 1);
    r = r && consumeToken(b, DEDENT);
    exit_section_(b, l, m, IMPLICIT_LIST_EXPRESSION, r, false, null);
    return r;
  }

  // Expression+
  private static boolean ImplicitListExpression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImplicitListExpression_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1, -1);
    int c = current_position_(b);
    while (r) {
      if (!Expression(b, l + 1, -1)) break;
      if (!empty_element_parsed_guard_(b, "ImplicitListExpression_3", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  final static Parser CurlObjDefExpression_1_0_0_0_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return CurlObjDefExpression_1_0_0_0(b, l + 1);
    }
  };
  final static Parser ExplicitPropDefExpression_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return ExplicitPropDefExpression(b, l + 1);
    }
  };
  final static Parser Expression_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return Expression(b, l + 1, -1);
    }
  };
  final static Parser ValueExpression_0_0_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return ValueExpression_0_0(b, l + 1);
    }
  };
  final static Parser ValueExpression_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return ValueExpression(b, l + 1);
    }
  };
  final static Parser recover_inter_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return recover_inter(b, l + 1);
    }
  };
  final static Parser recover_statement_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return recover_statement(b, l + 1);
    }
  };
}
