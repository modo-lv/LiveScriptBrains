// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LiveScriptListExpression extends LiveScriptExpression {

  @NotNull
  List<LiveScriptAnySeparator> getAnySeparatorList();

  @NotNull
  List<LiveScriptValueExpression> getValueExpressionList();

}
