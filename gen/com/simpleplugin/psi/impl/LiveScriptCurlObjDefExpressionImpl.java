// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.simpleplugin.psi.LiveScriptTypes.*;
import com.simpleplugin.psi.*;

public class LiveScriptCurlObjDefExpressionImpl extends LiveScriptExpressionImpl implements LiveScriptCurlObjDefExpression {

  public LiveScriptCurlObjDefExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiveScriptVisitor) ((LiveScriptVisitor)visitor).visitCurlObjDefExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiveScriptExpression getExpression() {
    return findChildByClass(LiveScriptExpression.class);
  }

}
