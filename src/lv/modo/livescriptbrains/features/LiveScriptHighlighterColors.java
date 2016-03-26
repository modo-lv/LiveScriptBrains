package lv.modo.livescriptbrains.features;

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

public class LiveScriptHighlighterColors
{
	public static final TextAttributesKey THIS = TextAttributesKey.createTextAttributesKey
		("LIVESCRIPT_THIS", HighlighterColors.TEXT);

	public static final TextAttributesKey BAD_CHAR = TextAttributesKey.createTextAttributesKey
		("LIVESCRIPT_BAD_CHAR", HighlighterColors.BAD_CHARACTER);

	public static final TextAttributesKey REGEX = TextAttributesKey.createTextAttributesKey
		("LIVESCRIPT_REGEX", HighlighterColors.TEXT);
}
