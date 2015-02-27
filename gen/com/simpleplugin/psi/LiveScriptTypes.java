// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType ASSIGNMENT_EXPRESSION = new LiveScriptElementType("ASSIGNMENT_EXPRESSION");
  IElementType EXPRESSION = new LiveScriptElementType("EXPRESSION");
  IElementType INTER_EXPRESSION = new LiveScriptElementType("INTER_EXPRESSION");
  IElementType LITERAL_EXPRESSION = new LiveScriptElementType("LITERAL_EXPRESSION");
  IElementType PAREN_EXPRESSION = new LiveScriptElementType("PAREN_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new LiveScriptElementType("REFERENCE_EXPRESSION");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
  IElementType STRING_EXPRESSION = new LiveScriptElementType("STRING_EXPRESSION");

  IElementType BACKSTRING = new LiveScriptTokenType("BACKSTRING");
  IElementType BOOLEAN = new LiveScriptTokenType("BOOLEAN");
  IElementType COMMENT = new LiveScriptTokenType("COMMENT");
  IElementType EQ = new LiveScriptTokenType("EQ");
  IElementType GLOBAL_EQ = new LiveScriptTokenType("GLOBAL_EQ");
  IElementType HEREDOC = new LiveScriptTokenType("HEREDOC");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType INTER_END = new LiveScriptTokenType("INTER_END");
  IElementType INTER_START = new LiveScriptTokenType("INTER_START");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NULL = new LiveScriptTokenType("NULL");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType PAREN_L = new LiveScriptTokenType("PAREN_L");
  IElementType PAREN_R = new LiveScriptTokenType("PAREN_R");
  IElementType STRING = new LiveScriptTokenType("STRING");
  IElementType STRING_END = new LiveScriptTokenType("STRING_END");
  IElementType STRING_START = new LiveScriptTokenType("STRING_START");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ASSIGNMENT_EXPRESSION) {
        return new LiveScriptAssignmentExpressionImpl(node);
      }
      else if (type == EXPRESSION) {
        return new LiveScriptExpressionImpl(node);
      }
      else if (type == INTER_EXPRESSION) {
        return new LiveScriptInterExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new LiveScriptLiteralExpressionImpl(node);
      }
      else if (type == PAREN_EXPRESSION) {
        return new LiveScriptParenExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new LiveScriptReferenceExpressionImpl(node);
      }
      else if (type == STATEMENT) {
        return new LiveScriptStatementImpl(node);
      }
      else if (type == STRING_EXPRESSION) {
        return new LiveScriptStringExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
