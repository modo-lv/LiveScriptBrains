// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType ASSIGNMENT_EXPRESSION = new LiveScriptElementType("ASSIGNMENT_EXPRESSION");
  IElementType C = new LiveScriptElementType("C");
  IElementType EXPRESSION = new LiveScriptElementType("EXPRESSION");
  IElementType LITERAL_EXPRESSION = new LiveScriptElementType("LITERAL_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new LiveScriptElementType("REFERENCE_EXPRESSION");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");

  IElementType COMMENT = new LiveScriptTokenType("COMMENT");
  IElementType EQ = new LiveScriptTokenType("EQ");
  IElementType GLOBAL_EQ = new LiveScriptTokenType("GLOBAL_EQ");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType SIMPLE_STRING = new LiveScriptTokenType("SIMPLE_STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ASSIGNMENT_EXPRESSION) {
        return new LiveScriptAssignmentExpressionImpl(node);
      }
      else if (type == C) {
        return new LiveScriptCImpl(node);
      }
      else if (type == EXPRESSION) {
        return new LiveScriptExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new LiveScriptLiteralExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new LiveScriptReferenceExpressionImpl(node);
      }
      else if (type == STATEMENT) {
        return new LiveScriptStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
