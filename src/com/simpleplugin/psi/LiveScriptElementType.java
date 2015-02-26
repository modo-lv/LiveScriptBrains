package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.LiveScriptLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LiveScriptElementType extends IElementType {
    public LiveScriptElementType(@NotNull @NonNls String debugName) {
        super(debugName, LiveScriptLanguage.INSTANCE);
    }
}