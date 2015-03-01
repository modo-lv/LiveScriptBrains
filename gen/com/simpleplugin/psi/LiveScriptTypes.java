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
  IElementType OP_EXPRESSION = new LiveScriptElementType("OP_EXPRESSION");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");

  IElementType COMMENT = new LiveScriptTokenType("COMMENT");
  IElementType DEDENT = new LiveScriptTokenType("DEDENT");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType INDENT = new LiveScriptTokenType("INDENT");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType OPERATOR = new LiveScriptTokenType("OPERATOR");

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
      else if (type == OP_EXPRESSION) {
        return new LiveScriptOpExpressionImpl(node);
      }
      else if (type == STATEMENT) {
        return new LiveScriptStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
