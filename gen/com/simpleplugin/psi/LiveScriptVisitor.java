// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LiveScriptVisitor extends PsiElementVisitor {

  public void visitBlockStatement(@NotNull LiveScriptBlockStatement o) {
    visitPsiElement(o);
  }

  public void visitCurlObjDefExpression(@NotNull LiveScriptCurlObjDefExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull LiveScriptExpression o) {
    visitPsiElement(o);
  }

  public void visitInterLines(@NotNull LiveScriptInterLines o) {
    visitPsiElement(o);
  }

  public void visitInterStringExpression(@NotNull LiveScriptInterStringExpression o) {
    visitExpression(o);
  }

  public void visitInterpolatedStringExpression(@NotNull LiveScriptInterpolatedStringExpression o) {
    visitExpression(o);
  }

  public void visitLiteralExpression(@NotNull LiveScriptLiteralExpression o) {
    visitExpression(o);
  }

  public void visitNewlineSeparator(@NotNull LiveScriptNewlineSeparator o) {
    visitPsiElement(o);
  }

  public void visitObjDefExpression(@NotNull LiveScriptObjDefExpression o) {
    visitExpression(o);
  }

  public void visitOpExpression(@NotNull LiveScriptOpExpression o) {
    visitExpression(o);
  }

  public void visitPropDefExpression(@NotNull LiveScriptPropDefExpression o) {
    visitExpression(o);
  }

  public void visitSeparator(@NotNull LiveScriptSeparator o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull LiveScriptStatement o) {
    visitPsiElement(o);
  }

  public void visitStringExpression(@NotNull LiveScriptStringExpression o) {
    visitExpression(o);
  }

  public void visitTestExpression(@NotNull LiveScriptTestExpression o) {
    visitExpression(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
