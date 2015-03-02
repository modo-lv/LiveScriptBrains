// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LiveScriptVisitor extends PsiElementVisitor {

  public void visitAnySeparator(@NotNull LiveScriptAnySeparator o) {
    visitPsiElement(o);
  }

  public void visitAssignOpExpression(@NotNull LiveScriptAssignOpExpression o) {
    visitExpression(o);
  }

  public void visitCom(@NotNull LiveScriptCom o) {
    visitPsiElement(o);
  }

  public void visitCurlObjDefExpression(@NotNull LiveScriptCurlObjDefExpression o) {
    visitExpression(o);
  }

  public void visitExplicitPropDefExpression(@NotNull LiveScriptExplicitPropDefExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull LiveScriptExpression o) {
    visitPsiElement(o);
  }

  public void visitImplicitListExpression(@NotNull LiveScriptImplicitListExpression o) {
    visitExpression(o);
  }

  public void visitImplicitPropDefExpression(@NotNull LiveScriptImplicitPropDefExpression o) {
    visitExpression(o);
  }

  public void visitInlineSeparator(@NotNull LiveScriptInlineSeparator o) {
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

  public void visitListExpression(@NotNull LiveScriptListExpression o) {
    visitExpression(o);
  }

  public void visitLiteralExpression(@NotNull LiveScriptLiteralExpression o) {
    visitExpression(o);
  }

  public void visitMultilineSeparator(@NotNull LiveScriptMultilineSeparator o) {
    visitPsiElement(o);
  }

  public void visitObjDefExpression(@NotNull LiveScriptObjDefExpression o) {
    visitExpression(o);
  }

  public void visitOpExpression(@NotNull LiveScriptOpExpression o) {
    visitExpression(o);
  }

  public void visitReferenceExpression(@NotNull LiveScriptReferenceExpression o) {
    visitExpression(o);
  }

  public void visitRegexExpression(@NotNull LiveScriptRegexExpression o) {
    visitExpression(o);
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

  public void visitValueExpression(@NotNull LiveScriptValueExpression o) {
    visitExpression(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
