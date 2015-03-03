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

public class LiveScriptOperationOrValueImpl extends ASTWrapperPsiElement implements LiveScriptOperationOrValue {

  public LiveScriptOperationOrValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LiveScriptVisitor) ((LiveScriptVisitor)visitor).visitOperationOrValue(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LiveScriptOperation getOperation() {
    return findChildByClass(LiveScriptOperation.class);
  }

  @Override
  @Nullable
  public LiveScriptValue getValue() {
    return findChildByClass(LiveScriptValue.class);
  }

}
