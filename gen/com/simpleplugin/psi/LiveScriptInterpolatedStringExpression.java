// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LiveScriptInterpolatedStringExpression extends LiveScriptExpression {

  @NotNull
  LiveScriptStatement getStatement();

  @NotNull
  List<LiveScriptStringExpression> getStringExpressionList();

}
