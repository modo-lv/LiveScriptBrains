// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType ASSIGN_OPERATION = new LiveScriptElementType("ASSIGN_OPERATION");
  IElementType COMMENT = new LiveScriptElementType("COMMENT");
  IElementType I_STRING_STATEMENT = new LiveScriptElementType("I_STRING_STATEMENT");
  IElementType LITERAL = new LiveScriptElementType("LITERAL");
  IElementType MATH_OPERATION = new LiveScriptElementType("MATH_OPERATION");
  IElementType OPERATION = new LiveScriptElementType("OPERATION");
  IElementType OPERATION_OR_VALUE = new LiveScriptElementType("OPERATION_OR_VALUE");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
  IElementType VALUE = new LiveScriptElementType("VALUE");

  IElementType ASSIGN = new LiveScriptTokenType("ASSIGN");
  IElementType BOOLEAN = new LiveScriptTokenType("BOOLEAN");
  IElementType COMMENT_LINE = new LiveScriptTokenType("COMMENT_LINE");
  IElementType EMPTY = new LiveScriptTokenType("EMPTY");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType ISTRING = new LiveScriptTokenType("ISTRING");
  IElementType MATH_OP = new LiveScriptTokenType("MATH_OP");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType STRING = new LiveScriptTokenType("STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ASSIGN_OPERATION) {
        return new LiveScriptAssignOperationImpl(node);
      }
      else if (type == COMMENT) {
        return new LiveScriptCommentImpl(node);
      }
      else if (type == I_STRING_STATEMENT) {
        return new LiveScriptIStringStatementImpl(node);
      }
      else if (type == LITERAL) {
        return new LiveScriptLiteralImpl(node);
      }
      else if (type == MATH_OPERATION) {
        return new LiveScriptMathOperationImpl(node);
      }
      else if (type == OPERATION) {
        return new LiveScriptOperationImpl(node);
      }
      else if (type == OPERATION_OR_VALUE) {
        return new LiveScriptOperationOrValueImpl(node);
      }
      else if (type == STATEMENT) {
        return new LiveScriptStatementImpl(node);
      }
      else if (type == VALUE) {
        return new LiveScriptValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
