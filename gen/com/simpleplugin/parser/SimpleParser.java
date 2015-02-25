// This is a generated file. Not intended for manual editing.
package com.simpleplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.simpleplugin.psi.SimpleTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimpleParser implements PsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ELEMENT) {
      r = element(b, 0);
    }
    else if (t == STATEMENT) {
      r = statement(b, 0);
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
  // statement|COMMENT
  public static boolean element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<element>");
    r = statement(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, l, m, ELEMENT, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // element*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    int c = current_position_(b);
    while (true) {
      if (!element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ("x = 2"|"y = 3"|"if x + 2 == 4"|"  "|"do-something()")NEWLINE
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<statement>");
    r = statement_0(b, l + 1);
    r = r && consumeToken(b, NEWLINE);
    exit_section_(b, l, m, STATEMENT, r, false, null);
    return r;
  }

  // "x = 2"|"y = 3"|"if x + 2 == 4"|"  "|"do-something()"
  private static boolean statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "x = 2");
    if (!r) r = consumeToken(b, "y = 3");
    if (!r) r = consumeToken(b, "if x + 2 == 4");
    if (!r) r = consumeToken(b, "  ");
    if (!r) r = consumeToken(b, "do-something()");
    exit_section_(b, m, null, r);
    return r;
  }

}
