// This is a generated file. Not intended for manual editing.
package com.simpleplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.simpleplugin.psi.LiveScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
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
    if (t == BLOCK_STATEMENT) {
      r = BlockStatement(b, 0);
    }
    else if (t == EXPRESSION) {
      r = Expression(b, 0, -1);
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
    else if (t == OP_EXPRESSION) {
      r = Expression(b, 0, 0);
    }
    else if (t == STATEMENT) {
      r = Statement(b, 0);
    }
    else if (t == STRING_EXPRESSION) {
      r = StringExpression(b, 0);
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
    create_token_set_(EXPRESSION, INTERPOLATED_STRING_EXPRESSION, INTER_STRING_EXPRESSION, LITERAL_EXPRESSION,
      OP_EXPRESSION, STRING_EXPRESSION),
  };

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
  // (Statement | COMMENT | NEWLINE)* <<eof>>
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = file_0(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Statement | COMMENT | NEWLINE)*
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

  // Statement | COMMENT | NEWLINE
  private static boolean file_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
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
  // 0: ATOM(InterpolatedStringExpression)
  // 1: BINARY(OpExpression)
  // 2: ATOM(LiteralExpression)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = InterpolatedStringExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
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
      if (g < 1 && consumeTokenSmart(b, OPERATOR)) {
        r = Expression(b, l, 1);
        exit_section_(b, l, m, OP_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
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

  // RESERVED_LITERAL | IDENTIFIER | NUMBER | StringExpression
  public static boolean LiteralExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<literal expression>");
    r = consumeTokenSmart(b, RESERVED_LITERAL);
    if (!r) r = consumeTokenSmart(b, IDENTIFIER);
    if (!r) r = consumeTokenSmart(b, NUMBER);
    if (!r) r = StringExpression(b, l + 1);
    exit_section_(b, l, m, LITERAL_EXPRESSION, r, false, null);
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
