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
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ASSIGN_OPERATION) {
      r = AssignOperation(b, 0);
    }
    else if (t == COMMENT) {
      r = Comment(b, 0);
    }
    else if (t == I_STRING_STATEMENT) {
      r = IStringStatement(b, 0);
    }
    else if (t == LITERAL) {
      r = Literal(b, 0);
    }
    else if (t == MATH_OPERATION) {
      r = MathOperation(b, 0);
    }
    else if (t == OPERATION) {
      r = Operation(b, 0);
    }
    else if (t == OPERATION_OR_VALUE) {
      r = OperationOrValue(b, 0);
    }
    else if (t == STATEMENT) {
      r = Statement(b, 0);
    }
    else if (t == VALUE) {
      r = Value(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  /* ********************************************************** */
  // IDENTIFIER ASSIGN (OperationOrValue)
  public static boolean AssignOperation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOperation")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokens(b, 2, IDENTIFIER, ASSIGN);
    p = r; // pin = 2
    r = r && AssignOperation_2(b, l + 1);
    exit_section_(b, l, m, ASSIGN_OPERATION, r, p, null);
    return r || p;
  }

  // (OperationOrValue)
  private static boolean AssignOperation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOperation_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = OperationOrValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT_LINE
  public static boolean Comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comment")) return false;
    if (!nextTokenIs(b, COMMENT_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT_LINE);
    exit_section_(b, m, COMMENT, r);
    return r;
  }

  /* ********************************************************** */
  // ISTRING (OperationOrValue ISTRING)+
  public static boolean IStringStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStringStatement")) return false;
    if (!nextTokenIs(b, ISTRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ISTRING);
    r = r && IStringStatement_1(b, l + 1);
    exit_section_(b, m, I_STRING_STATEMENT, r);
    return r;
  }

  // (OperationOrValue ISTRING)+
  private static boolean IStringStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStringStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IStringStatement_1_0(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!IStringStatement_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IStringStatement_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // OperationOrValue ISTRING
  private static boolean IStringStatement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStringStatement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = OperationOrValue(b, l + 1);
    r = r && consumeToken(b, ISTRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN | NUMBER | EMPTY | STRING
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<literal>");
    r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, EMPTY);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, LITERAL, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Value MATH_OP OperationOrValue
  public static boolean MathOperation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MathOperation")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<math operation>");
    r = Value(b, l + 1);
    r = r && consumeToken(b, MATH_OP);
    p = r; // pin = 2
    r = r && OperationOrValue(b, l + 1);
    exit_section_(b, l, m, MATH_OPERATION, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // AssignOperation | MathOperation
  public static boolean Operation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<operation>");
    r = AssignOperation(b, l + 1);
    if (!r) r = MathOperation(b, l + 1);
    exit_section_(b, l, m, OPERATION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Operation | Value
  public static boolean OperationOrValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OperationOrValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<operation or value>");
    r = Operation(b, l + 1);
    if (!r) r = Value(b, l + 1);
    exit_section_(b, l, m, OPERATION_OR_VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IStringStatement | OperationOrValue
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
    r = IStringStatement(b, l + 1);
    if (!r) r = OperationOrValue(b, l + 1);
    exit_section_(b, l, m, STATEMENT, r, false, recover_parser_);
    return r;
  }

  /* ********************************************************** */
  // Literal | IDENTIFIER
  public static boolean Value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<value>");
    r = Literal(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, VALUE, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Statement | Comment | NEWLINE)*
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

  // Statement | Comment | NEWLINE
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement(b, l + 1);
    if (!r) r = Comment(b, l + 1);
    if (!r) r = consumeToken(b, NEWLINE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !NEWLINE
  static boolean recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_, null);
    r = !consumeToken(b, NEWLINE);
    exit_section_(b, l, m, null, r, false, null);
    return r;
  }

  final static Parser recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return recover(b, l + 1);
    }
  };
}
