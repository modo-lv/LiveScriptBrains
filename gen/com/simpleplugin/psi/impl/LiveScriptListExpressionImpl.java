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

public class LiveScriptListExpressionImpl extends LiveScriptExpressionImpl implements LiveScriptListExpression {

  public LiveScriptListExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiveScriptVisitor) ((LiveScriptVisitor)visitor).visitListExpression(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LiveScriptAnySeparator> getAnySeparatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiveScriptAnySeparator.class);
  }

  @Override
  @NotNull
  public List<LiveScriptValueExpression> getValueExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LiveScriptValueExpression.class);
  }

}
