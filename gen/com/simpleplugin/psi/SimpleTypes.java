// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.impl.*;

public interface SimpleTypes {

  IElementType ELEMENT = new SimpleElementType("ELEMENT");
  IElementType STATEMENT = new SimpleElementType("STATEMENT");

  IElementType COMMENT = new SimpleTokenType("COMMENT");
  IElementType NEWLINE = new SimpleTokenType("NEWLINE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ELEMENT) {
        return new SimpleElementImpl(node);
      }
      else if (type == STATEMENT) {
        return new SimpleStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
