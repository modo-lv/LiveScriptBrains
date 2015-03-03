// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.simpleplugin.psi.LiveScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.simpleplugin.psi.*;

public class LiveScriptStatementImpl extends ASTWrapperPsiElement implements LiveScriptStatement {

  public LiveScriptStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiveScriptVisitor) ((LiveScriptVisitor)visitor).visitStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiveScriptIStringStatement getIStringStatement() {
    return findChildByClass(LiveScriptIStringStatement.class);
  }

  @Override
  @Nullable
  public LiveScriptOperationOrValue getOperationOrValue() {
    return findChildByClass(LiveScriptOperationOrValue.class);
  }

}
