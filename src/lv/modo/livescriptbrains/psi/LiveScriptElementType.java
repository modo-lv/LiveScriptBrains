package lv.modo.livescriptbrains.psi;

import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.LiveScriptLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LiveScriptElementType extends IElementType {
    public LiveScriptElementType(@NotNull @NonNls String debugName) {
        super(debugName, LiveScriptLanguage.INSTANCE);
    }
}