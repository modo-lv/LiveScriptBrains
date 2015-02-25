// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SimpleVisitor extends PsiElementVisitor {

  public void visitElement(@NotNull SimpleElement o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull SimpleStatement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
