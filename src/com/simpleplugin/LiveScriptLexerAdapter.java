package com.simpleplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class LiveScriptLexerAdapter extends FlexAdapter {
    public LiveScriptLexerAdapter() {
        super(new LiveScriptLexer((Reader) null));
    }
}