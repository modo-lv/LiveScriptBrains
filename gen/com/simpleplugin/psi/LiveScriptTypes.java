// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType BLOCK_STATEMENT = new LiveScriptElementType("BLOCK_STATEMENT");
  IElementType EXPRESSION = new LiveScriptElementType("EXPRESSION");
  IElementType LITERAL_EXPRESSION = new LiveScriptElementType("LITERAL_EXPRESSION");
  IElementType OPERATION_EXPRESSION = new LiveScriptElementType("OPERATION_EXPRESSION");
  IElementType PAREN_EXPRESSION = new LiveScriptElementType("PAREN_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new LiveScriptElementType("REFERENCE_EXPRESSION");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
  IElementType STRING_EXPRESSION = new LiveScriptElementType("STRING_EXPRESSION");

  IElementType BACKSTRING = new LiveScriptTokenType("BACKSTRING");
  IElementType BLOCK_END = new LiveScriptTokenType("BLOCK_END");
  IElementType BLOCK_START = new LiveScriptTokenType("BLOCK_START");
  IElementType BOOLEAN = new LiveScriptTokenType("BOOLEAN");
  IElementType COMMENT = new LiveScriptTokenType("COMMENT");
  IElementType HEREDOC = new LiveScriptTokenType("HEREDOC");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType INDENT = new LiveScriptTokenType("INDENT");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NULL = new LiveScriptTokenType("NULL");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType OPERATOR = new LiveScriptTokenType("OPERATOR");
  IElementType PAREN_L = new LiveScriptTokenType("PAREN_L");
  IElementType PAREN_R = new LiveScriptTokenType("PAREN_R");
  IElementType STRING = new LiveScriptTokenType("STRING");
  IElementType STRING_END = new LiveScriptTokenType("STRING_END");
  IElementType STRING_START = new LiveScriptTokenType("STRING_START");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == BLOCK_STATEMENT) {
        return new LiveScriptBlockStatementImpl(node);
      }
      else if (type == EXPRESSION) {
        return new LiveScriptExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new LiveScriptLiteralExpressionImpl(node);
      }
      else if (type == OPERATION_EXPRESSION) {
        return new LiveScriptOperationExpressionImpl(node);
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
