// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LiveScriptVisitor extends PsiElementVisitor {

  public void visitBlockStatement(@NotNull LiveScriptBlockStatement o) {
    visitPsiElement(o);
  }

  public void visitExpression(@NotNull LiveScriptExpression o) {
    visitPsiElement(o);
  }

  public void visitLiteralExpression(@NotNull LiveScriptLiteralExpression o) {
    visitExpression(o);
  }

  public void visitOperationExpression(@NotNull LiveScriptOperationExpression o) {
    visitExpression(o);
  }

  public void visitParenExpression(@NotNull LiveScriptParenExpression o) {
    visitExpression(o);
  }

  public void visitReferenceExpression(@NotNull LiveScriptReferenceExpression o) {
    visitExpression(o);
  }

  public void visitStatement(@NotNull LiveScriptStatement o) {
    visitPsiElement(o);
  }

  public void visitStringExpression(@NotNull LiveScriptStringExpression o) {
    visitExpression(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
