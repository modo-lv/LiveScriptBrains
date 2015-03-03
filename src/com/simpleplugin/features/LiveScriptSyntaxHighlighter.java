package com.simpleplugin.features;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.simpleplugin.LiveScriptLexer;
import com.simpleplugin.psi.LiveScriptTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LiveScriptSyntaxHighlighter extends SyntaxHighlighterBase {
	// Strings
	public static final TextAttributesKey STRING =
			createTextAttributesKey("LS_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey NUMBER =
			createTextAttributesKey("LS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey COMMENT_LINE =
			createTextAttributesKey("LS_COMMENT_LINE", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey COMMENT_BLOCK =
			createTextAttributesKey("LS_COMMENT_BLOCK", DefaultLanguageHighlighterColors.BLOCK_COMMENT);


	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new FlexAdapter(new LiveScriptLexer((Reader) null));
	}

	private TextAttributesKey[] getKeySetFor(TextAttributesKey taKey) {
		return new TextAttributesKey[] {taKey};
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		IElementType[] strings = {
				LiveScriptTypes.STRING,
				LiveScriptTypes.ISTRING,
		};

		for (IElementType i : strings)
			if (tokenType.equals(i))
				return getKeySetFor(STRING);

/*
		Map<IElementType, TextAttributesKey> types = new HashMap<IElementType, TextAttributesKey>();
		types.put(LiveScriptTypes.NUMBER, NUMBER);
		types.put(LiveScriptTypes.COMMENT_LINE, COMMENT_LINE);
		types.put(LiveScriptTypes.COMMENT_BLOCK, COMMENT_BLOCK);

		for (IElementType e : types.keySet())
			if (tokenType.equals(e))
				return getKeySetFor(types.get(e));

*/

		return new TextAttributesKey[0];
	}
}
