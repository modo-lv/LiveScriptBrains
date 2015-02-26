package com.simpleplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LiveScriptFileType extends LanguageFileType {
    public static final LiveScriptFileType INSTANCE = new LiveScriptFileType();

    private LiveScriptFileType() {
        super(LiveScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "LiveScript file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "LiveScript language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "ls";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return LiveScriptIcons.FILE;
    }
}