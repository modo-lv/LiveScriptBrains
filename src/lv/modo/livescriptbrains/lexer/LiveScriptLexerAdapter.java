package lv.modo.livescriptbrains.lexer;

import com.intellij.lexer.FlexAdapter;

public class LiveScriptLexerAdapter extends FlexAdapter {
    public LiveScriptLexerAdapter() {
        super(new LiveScriptLexer());
    }
}