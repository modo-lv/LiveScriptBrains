package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.LiveScriptLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LiveScriptTokenType extends IElementType {
    public LiveScriptTokenType(@NotNull @NonNls String debugName) {
        super(debugName, LiveScriptLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "LiveScriptTokenType." + super.toString();
    }
}