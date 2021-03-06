package lv.modo.livescriptbrains.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import lv.modo.livescriptbrains.LiveScriptFileType;
import lv.modo.livescriptbrains.LiveScriptLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class LiveScriptFile extends PsiFileBase {
    public LiveScriptFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LiveScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return LiveScriptFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "LiveScript File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}