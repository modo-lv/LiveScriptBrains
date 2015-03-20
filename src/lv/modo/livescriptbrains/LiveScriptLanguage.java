package lv.modo.livescriptbrains;

import com.intellij.lang.Language;

public class LiveScriptLanguage extends Language {
    public static final LiveScriptLanguage INSTANCE = new LiveScriptLanguage();

    private LiveScriptLanguage() {
        super("LiveScript");
    }
}