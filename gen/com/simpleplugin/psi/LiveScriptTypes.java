// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType BLOCK_STATEMENT = new LiveScriptElementType("BLOCK_STATEMENT");
  IElementType EXPRESSION = new LiveScriptElementType("EXPRESSION");
  IElementType INTERPOLATED_STRING_EXPRESSION = new LiveScriptElementType("INTERPOLATED_STRING_EXPRESSION");
  IElementType INTER_LINES = new LiveScriptElementType("INTER_LINES");
  IElementType INTER_STRING_EXPRESSION = new LiveScriptElementType("INTER_STRING_EXPRESSION");
  IElementType LITERAL_EXPRESSION = new LiveScriptElementType("LITERAL_EXPRESSION");
  IElementType NEWLINE_SEPARATOR = new LiveScriptElementType("NEWLINE_SEPARATOR");
  IElementType OBJ_DEF_EXPRESSION = new LiveScriptElementType("OBJ_DEF_EXPRESSION");
  IElementType OP_EXPRESSION = new LiveScriptElementType("OP_EXPRESSION");
  IElementType PROP_DEF_EXPRESSION = new LiveScriptElementType("PROP_DEF_EXPRESSION");
  IElementType SEPARATOR = new LiveScriptElementType("SEPARATOR");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
  IElementType STRING_EXPRESSION = new LiveScriptElementType("STRING_EXPRESSION");
  IElementType TEST_EXPRESSION = new LiveScriptElementType("TEST_EXPRESSION");

  IElementType BACKSTRING = new LiveScriptTokenType("BACKSTRING");
  IElementType COLON = new LiveScriptTokenType("COLON");
  IElementType COMMA = new LiveScriptTokenType("COMMA");
  IElementType COMMENT = new LiveScriptTokenType("COMMENT");
  IElementType CURL_L = new LiveScriptTokenType("CURL_L");
  IElementType CURL_R = new LiveScriptTokenType("CURL_R");
  IElementType DEDENT = new LiveScriptTokenType("DEDENT");
  IElementType HEREDOC = new LiveScriptTokenType("HEREDOC");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType INDENT = new LiveScriptTokenType("INDENT");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType OPERATOR = new LiveScriptTokenType("OPERATOR");
  IElementType RESERVED_LITERAL = new LiveScriptTokenType("RESERVED_LITERAL");
  IElementType SPACE = new LiveScriptTokenType("SPACE");
  IElementType STRING = new LiveScriptTokenType("STRING");
  IElementType STRING_END = new LiveScriptTokenType("STRING_END");
  IElementType STRING_INTER_END = new LiveScriptTokenType("STRING_INTER_END");
  IElementType STRING_INTER_START = new LiveScriptTokenType("STRING_INTER_START");
  IElementType STRING_START = new LiveScriptTokenType("STRING_START");
  IElementType TEST = new LiveScriptTokenType("TEST");
  IElementType WHITE_SPACE = new LiveScriptTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == BLOCK_STATEMENT) {
        return new LiveScriptBlockStatementImpl(node);
      }
      else if (type == EXPRESSION) {
        return new LiveScriptExpressionImpl(node);
      }
      else if (type == INTERPOLATED_STRING_EXPRESSION) {
        return new LiveScriptInterpolatedStringExpressionImpl(node);
      }
      else if (type == INTER_LINES) {
        return new LiveScriptInterLinesImpl(node);
      }
      else if (type == INTER_STRING_EXPRESSION) {
        return new LiveScriptInterStringExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new LiveScriptLiteralExpressionImpl(node);
      }
      else if (type == NEWLINE_SEPARATOR) {
        return new LiveScriptNewlineSeparatorImpl(node);
      }
      else if (type == OBJ_DEF_EXPRESSION) {
        return new LiveScriptObjDefExpressionImpl(node);
      }
      else if (type == OP_EXPRESSION) {
        return new LiveScriptOpExpressionImpl(node);
      }
      else if (type == PROP_DEF_EXPRESSION) {
        return new LiveScriptPropDefExpressionImpl(node);
      }
      else if (type == SEPARATOR) {
        return new LiveScriptSeparatorImpl(node);
      }
      else if (type == STATEMENT) {
        return new LiveScriptStatementImpl(node);
      }
      else if (type == STRING_EXPRESSION) {
        return new LiveScriptStringExpressionImpl(node);
      }
      else if (type == TEST_EXPRESSION) {
        return new LiveScriptTestExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
