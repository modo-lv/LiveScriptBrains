// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface LiveScriptTypes {

  IElementType ANY_SEPARATOR = new LiveScriptElementType("ANY_SEPARATOR");
  IElementType BLOCK_STATEMENT = new LiveScriptElementType("BLOCK_STATEMENT");
  IElementType COM = new LiveScriptElementType("COM");
  IElementType CURL_OBJ_DEF_EXPRESSION = new LiveScriptElementType("CURL_OBJ_DEF_EXPRESSION");
  IElementType EXPLICIT_PROP_DEF_EXPRESSION = new LiveScriptElementType("EXPLICIT_PROP_DEF_EXPRESSION");
  IElementType EXPRESSION = new LiveScriptElementType("EXPRESSION");
  IElementType IMPLICIT_PROP_DEF_EXPRESSION = new LiveScriptElementType("IMPLICIT_PROP_DEF_EXPRESSION");
  IElementType INLINE_SEPARATOR = new LiveScriptElementType("INLINE_SEPARATOR");
  IElementType INTERPOLATED_STRING_EXPRESSION = new LiveScriptElementType("INTERPOLATED_STRING_EXPRESSION");
  IElementType INTER_LINES = new LiveScriptElementType("INTER_LINES");
  IElementType INTER_STRING_EXPRESSION = new LiveScriptElementType("INTER_STRING_EXPRESSION");
  IElementType LITERAL_EXPRESSION = new LiveScriptElementType("LITERAL_EXPRESSION");
  IElementType OBJ_DEF_EXPRESSION = new LiveScriptElementType("OBJ_DEF_EXPRESSION");
  IElementType OP_EXPRESSION = new LiveScriptElementType("OP_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new LiveScriptElementType("REFERENCE_EXPRESSION");
  IElementType RIGHT_OP_EXPRESSION = new LiveScriptElementType("RIGHT_OP_EXPRESSION");
  IElementType STATEMENT = new LiveScriptElementType("STATEMENT");
  IElementType STRING_EXPRESSION = new LiveScriptElementType("STRING_EXPRESSION");
  IElementType TEST_EXPRESSION = new LiveScriptElementType("TEST_EXPRESSION");
  IElementType VALUE_EXPRESSION = new LiveScriptElementType("VALUE_EXPRESSION");

  IElementType BACKSTRING = new LiveScriptTokenType("BACKSTRING");
  IElementType COLON = new LiveScriptTokenType("COLON");
  IElementType COMMA = new LiveScriptTokenType("COMMA");
  IElementType COMMENT_BLOCK = new LiveScriptTokenType("COMMENT_BLOCK");
  IElementType COMMENT_LINE = new LiveScriptTokenType("COMMENT_LINE");
  IElementType CURL_L = new LiveScriptTokenType("CURL_L");
  IElementType CURL_R = new LiveScriptTokenType("CURL_R");
  IElementType DEDENT = new LiveScriptTokenType("DEDENT");
  IElementType DOT = new LiveScriptTokenType("DOT");
  IElementType HEREDOC = new LiveScriptTokenType("HEREDOC");
  IElementType IDENTIFIER = new LiveScriptTokenType("IDENTIFIER");
  IElementType INDENT = new LiveScriptTokenType("INDENT");
  IElementType NEWLINE = new LiveScriptTokenType("NEWLINE");
  IElementType NUMBER = new LiveScriptTokenType("NUMBER");
  IElementType OPERATOR = new LiveScriptTokenType("OPERATOR");
  IElementType PAREN_L = new LiveScriptTokenType("PAREN_L");
  IElementType PAREN_R = new LiveScriptTokenType("PAREN_R");
  IElementType RESERVED_LITERAL = new LiveScriptTokenType("RESERVED_LITERAL");
  IElementType RIGHT_OP = new LiveScriptTokenType("RIGHT_OP");
  IElementType SPACE = new LiveScriptTokenType("SPACE");
  IElementType STRING = new LiveScriptTokenType("STRING");
  IElementType STRING_END = new LiveScriptTokenType("STRING_END");
  IElementType STRING_INTER_END = new LiveScriptTokenType("STRING_INTER_END");
  IElementType STRING_INTER_START = new LiveScriptTokenType("STRING_INTER_START");
  IElementType STRING_START = new LiveScriptTokenType("STRING_START");
  IElementType TEST = new LiveScriptTokenType("TEST");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ANY_SEPARATOR) {
        return new LiveScriptAnySeparatorImpl(node);
      }
      else if (type == BLOCK_STATEMENT) {
        return new LiveScriptBlockStatementImpl(node);
      }
      else if (type == COM) {
        return new LiveScriptComImpl(node);
      }
      else if (type == CURL_OBJ_DEF_EXPRESSION) {
        return new LiveScriptCurlObjDefExpressionImpl(node);
      }
      else if (type == EXPLICIT_PROP_DEF_EXPRESSION) {
        return new LiveScriptExplicitPropDefExpressionImpl(node);
      }
      else if (type == EXPRESSION) {
        return new LiveScriptExpressionImpl(node);
      }
      else if (type == IMPLICIT_PROP_DEF_EXPRESSION) {
        return new LiveScriptImplicitPropDefExpressionImpl(node);
      }
      else if (type == INLINE_SEPARATOR) {
        return new LiveScriptInlineSeparatorImpl(node);
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
      else if (type == OBJ_DEF_EXPRESSION) {
        return new LiveScriptObjDefExpressionImpl(node);
      }
      else if (type == OP_EXPRESSION) {
        return new LiveScriptOpExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new LiveScriptReferenceExpressionImpl(node);
      }
      else if (type == RIGHT_OP_EXPRESSION) {
        return new LiveScriptRightOpExpressionImpl(node);
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
      else if (type == VALUE_EXPRESSION) {
        return new LiveScriptValueExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
