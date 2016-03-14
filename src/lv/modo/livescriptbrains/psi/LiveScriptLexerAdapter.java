package lv.modo.livescriptbrains.psi;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class LiveScriptLexerAdapter extends FlexAdapter {
    public LiveScriptLexerAdapter() {
        super(new LiveScriptLexer());
    }
}