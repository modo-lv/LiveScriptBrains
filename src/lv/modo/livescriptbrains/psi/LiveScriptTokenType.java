package lv.modo.livescriptbrains.psi;

import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.LiveScriptLanguage;
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