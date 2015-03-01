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
    else if (t == BLOCK_STATEMENT) {
      r = BlockStatement(b, 0);
    }
    else if (t == COM) {
      r = Com(b, 0);
    }
    else if (t == CURL_OBJ_DEF_EXPRESSION) {
      r = CurlObjDefExpression(b, 0);
    }
    else if (t == EXPRESSION) {
      r = Expression(b, 0, -1);
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
    else if (t == LITERAL_EXPRESSION) {
      r = LiteralExpression(b, 0);
    }
    else if (t == OBJ_DEF_EXPRESSION) {
      r = Expression(b, 0, 4);
    }
    else if (t == OP_EXPRESSION) {
      r = Expression(b, 0, 2);
    }
    else if (t == PAREN_EXPRESSION) {
      r = ParenExpression(b, 0);
    }
    else if (t == PROP_DEF_EXPRESSION) {
      r = Expression(b, 0, 5);
    }
    else if (t == REFERENCE_EXPRESSION) {
      r = ReferenceExpression(b, 0);
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
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CURL_OBJ_DEF_EXPRESSION, EXPRESSION, INTERPOLATED_STRING_EXPRESSION, INTER_STRING_EXPRESSION,
      LITERAL_EXPRESSION, OBJ_DEF_EXPRESSION, OP_EXPRESSION, PAREN_EXPRESSION,
      PROP_DEF_EXPRESSION, REFERENCE_EXPRESSION, STRING_EXPRESSION, TEST_EXPRESSION),
  };

  /* ********************************************************** */
  // InlineSeparator| SPACE* NEWLINE SPACE*
  public static boolean AnySeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnySeparator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<any separator>");
    r = InlineSeparator(b, l + 1);
    if (!r) r = AnySeparator_1(b, l + 1);
    exit_section_(b, l, m, ANY_SEPARATOR, r, false, null);
    return r;
  }

  // SPACE* NEWLINE SPACE*
  private static boolean AnySeparator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnySeparator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnySeparator_1_0(b, l + 1);
    r = r && consumeToken(b, NEWLINE);
    r = r && AnySeparator_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SPACE*
  private static boolean AnySeparator_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnySeparator_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "AnySeparator_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SPACE*
  private static boolean AnySeparator_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AnySeparator_1_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "AnySeparator_1_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // INDENT Statement+ DEDENT?
  public static boolean BlockStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockStatement")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && BlockStatement_1(b, l + 1);
    r = r && BlockStatement_2(b, l + 1);
    exit_section_(b, m, BLOCK_STATEMENT, r);
    return r;
  }

  // Statement+
  private static boolean BlockStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BlockStatement_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // DEDENT?
  private static boolean BlockStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockStatement_2")) return false;
    consumeToken(b, DEDENT);
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
  // COMMA | SPACE+
  public static boolean InlineSeparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineSeparator")) return false;
    if (!nextTokenIs(b, "<inline separator>", COMMA, SPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<inline separator>");
    r = consumeToken(b, COMMA);
    if (!r) r = InlineSeparator_1(b, l + 1);
    exit_section_(b, l, m, INLINE_SEPARATOR, r, false, null);
    return r;
  }

  // SPACE+
  private static boolean InlineSeparator_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineSeparator_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SPACE);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, SPACE)) break;
      if (!empty_element_parsed_guard_(b, "InlineSeparator_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression*
  public static boolean InterLines(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterLines")) return false;
    Marker m = enter_section_(b, l, _NONE_, "<inter lines>");
    int c = current_position_(b);
    while (true) {
      if (!Expression(b, l + 1, -1)) break;
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
  // Expression
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
    r = Expression(b, l + 1, -1);
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
  // 1: PREFIX(ParenExpression)
  // 2: ATOM(InterpolatedStringExpression)
  // 3: BINARY(OpExpression)
  // 4: PREFIX(CurlObjDefExpression)
  // 5: POSTFIX(ObjDefExpression)
  // 6: BINARY(PropDefExpression)
  // 7: ATOM(LiteralExpression)
  // 8: ATOM(ReferenceExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = TestExpression(b, l + 1);
    if (!r) r = ParenExpression(b, l + 1);
    if (!r) r = InterpolatedStringExpression(b, l + 1);
    if (!r) r = CurlObjDefExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
    if (!r) r = ReferenceExpression(b, l + 1);
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
      if (g < 3 && OpExpression_0(b, l + 1)) {
        r = Expression(b, l, 3);
        exit_section_(b, l, m, OP_EXPRESSION, r, true, null);
      }
      else if (g < 5 && leftMarkerIs(b, PROP_DEF_EXPRESSION) && ObjDefExpression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, OBJ_DEF_EXPRESSION, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, COLON)) {
        r = Expression(b, l, 6);
        exit_section_(b, l, m, PROP_DEF_EXPRESSION, r, true, null);
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

  public static boolean ParenExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenExpression")) return false;
    if (!nextTokenIsFast(b, PAREN_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, PAREN_L);
    p = r;
    r = p && Expression(b, l, 1);
    r = p && report_error_(b, consumeToken(b, PAREN_R)) && r;
    exit_section_(b, l, m, PAREN_EXPRESSION, r, p, null);
    return r || p;
  }

  // InterStringExpression+ InterLines InterStringExpression+
  public static boolean InterpolatedStringExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterpolatedStringExpression")) return false;
    if (!nextTokenIsFast(b, STRING_INTER_END, STRING_START)) return false;
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

  public static boolean CurlObjDefExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlObjDefExpression")) return false;
    if (!nextTokenIsFast(b, CURL_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, CURL_L);
    p = r;
    r = p && Expression(b, l, 4);
    r = p && report_error_(b, consumeToken(b, CURL_R)) && r;
    exit_section_(b, l, m, CURL_OBJ_DEF_EXPRESSION, r, p, null);
    return r || p;
  }

  // (AnySeparator PropDefExpression)*
  private static boolean ObjDefExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjDefExpression_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ObjDefExpression_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ObjDefExpression_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AnySeparator PropDefExpression
  private static boolean ObjDefExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjDefExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AnySeparator(b, l + 1);
    r = r && Expression(b, l + 1, 5);
    exit_section_(b, m, null, r);
    return r;
  }

  // RESERVED_LITERAL | ReferenceExpression | NUMBER | StringExpression
  public static boolean LiteralExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<literal expression>");
    r = consumeTokenSmart(b, RESERVED_LITERAL);
    if (!r) r = ReferenceExpression(b, l + 1);
    if (!r) r = consumeTokenSmart(b, NUMBER);
    if (!r) r = StringExpression(b, l + 1);
    exit_section_(b, l, m, LITERAL_EXPRESSION, r, false, null);
    return r;
  }

  // IDENTIFIER (DOT IDENTIFIER)*
  public static boolean ReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression")) return false;
    if (!nextTokenIsFast(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    r = r && ReferenceExpression_1(b, l + 1);
    exit_section_(b, m, REFERENCE_EXPRESSION, r);
    return r;
  }

  // (DOT IDENTIFIER)*
  private static boolean ReferenceExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ReferenceExpression_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ReferenceExpression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean ReferenceExpression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

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
