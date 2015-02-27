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
    if (t == ASSIGNMENT_EXPRESSION) {
      r = AssignmentExpression(b, 0);
    }
    else if (t == EXPRESSION) {
      r = Expression(b, 0);
    }
    else if (t == INTER_EXPRESSION) {
      r = InterExpression(b, 0);
    }
    else if (t == LITERAL_EXPRESSION) {
      r = LiteralExpression(b, 0);
    }
    else if (t == PAREN_EXPRESSION) {
      r = ParenExpression(b, 0);
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
    else if (t == C) {
      r = c(b, 0);
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
    create_token_set_(ASSIGNMENT_EXPRESSION, EXPRESSION, INTER_EXPRESSION, LITERAL_EXPRESSION,
      PAREN_EXPRESSION, REFERENCE_EXPRESSION, STRING_EXPRESSION),
  };

  /* ********************************************************** */
  // ReferenceExpression (GLOBAL_EQ | EQ) Expression
  public static boolean AssignmentExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, null);
    r = ReferenceExpression(b, l + 1);
    r = r && AssignmentExpression_1(b, l + 1);
    r = r && Expression(b, l + 1);
    exit_section_(b, l, m, ASSIGNMENT_EXPRESSION, r, false, null);
    return r;
  }

  // GLOBAL_EQ | EQ
  private static boolean AssignmentExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignmentExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GLOBAL_EQ);
    if (!r) r = consumeToken(b, EQ);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // InterExpression
  //     | ParenExpression
  //     | AssignmentExpression
  //     | LiteralExpression
  //     | ReferenceExpression
  public static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<expression>");
    r = InterExpression(b, l + 1);
    if (!r) r = ParenExpression(b, l + 1);
    if (!r) r = AssignmentExpression(b, l + 1);
    if (!r) r = LiteralExpression(b, l + 1);
    if (!r) r = ReferenceExpression(b, l + 1);
    exit_section_(b, l, m, EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INTER_START Statement INTER_END
  public static boolean InterExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InterExpression")) return false;
    if (!nextTokenIs(b, INTER_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INTER_START);
    r = r && Statement(b, l + 1);
    r = r && consumeToken(b, INTER_END);
    exit_section_(b, m, INTER_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // NULL | BOOLEAN | NUMBER | StringExpression
  public static boolean LiteralExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LiteralExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<literal expression>");
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = StringExpression(b, l + 1);
    exit_section_(b, l, m, LITERAL_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PAREN_L Expression PAREN_R
  public static boolean ParenExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenExpression")) return false;
    if (!nextTokenIs(b, PAREN_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PAREN_L);
    r = r && Expression(b, l + 1);
    r = r && consumeToken(b, PAREN_R);
    exit_section_(b, m, PAREN_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean ReferenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReferenceExpression")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, REFERENCE_EXPRESSION, r);
    return r;
  }

  /* ********************************************************** */
  // Expression+
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
    r = Expression(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!Expression(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Statement", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, STATEMENT, r, false, recover_statement_parser_);
    return r;
  }

  /* ********************************************************** */
  // STRING_START STRING* (IDENTIFIER | STRING)* STRING_END | BACKSTRING | HEREDOC
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

  // STRING_START STRING* (IDENTIFIER | STRING)* STRING_END
  private static boolean StringExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_START);
    r = r && StringExpression_0_1(b, l + 1);
    r = r && StringExpression_0_2(b, l + 1);
    r = r && consumeToken(b, STRING_END);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING*
  private static boolean StringExpression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, STRING)) break;
      if (!empty_element_parsed_guard_(b, "StringExpression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (IDENTIFIER | STRING)*
  private static boolean StringExpression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!StringExpression_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StringExpression_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // IDENTIFIER | STRING
  private static boolean StringExpression_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringExpression_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT
  public static boolean c(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "c")) return false;
    if (!nextTokenIs(b, COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    exit_section_(b, m, C, r);
    return r;
  }

  /* ********************************************************** */
  // (Statement | NEWLINE)*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    int c = current_position_(b);
    while (true) {
      if (!file_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Statement | NEWLINE
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
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

  final static Parser recover_statement_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return recover_statement(b, l + 1);
    }
  };
}
