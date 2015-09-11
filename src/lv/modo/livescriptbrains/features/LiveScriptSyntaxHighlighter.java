package lv.modo.livescriptbrains.features;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.psi.LiveScriptLexer;
import lv.modo.livescriptbrains.psi.LiveScriptTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LiveScriptSyntaxHighlighter extends SyntaxHighlighterBase {
	// Inherited
	public static final TextAttributesKey STRING =
			createTextAttributesKey("LS_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey NUMBER =
			createTextAttributesKey("LS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey COMMENT_LINE =
			createTextAttributesKey("LS_COMMENT_LINE", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey COMMENT_BLOCK =
			createTextAttributesKey("LS_COMMENT_BLOCK", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
	public static final TextAttributesKey OPERATOR =
			createTextAttributesKey("LS_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey CONSTANTS =
			createTextAttributesKey("LS_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey IDENTIFIER =
			createTextAttributesKey("LS_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
	public static final TextAttributesKey KEYWORD =
			createTextAttributesKey("LS_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

	// Custom




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
			LiveScriptTypes.STRING_START,
			LiveScriptTypes.STRING_END
		};

		for (IElementType i : strings)
			if (tokenType.equals(i))
				return getKeySetFor(STRING);

		Map<IElementType, TextAttributesKey> types = new HashMap<IElementType, TextAttributesKey>();
		types.put(LiveScriptTypes.COMMENT_LINE, COMMENT_LINE);
		types.put(LiveScriptTypes.NUMBER, NUMBER);
		types.put(LiveScriptTypes.COMMENT_BLOCK, COMMENT_BLOCK);
		types.put(LiveScriptTypes.IDENTIFIER, IDENTIFIER);

		for (IElementType e : types.keySet())
			if (tokenType.equals(e))
				return getKeySetFor(types.get(e));

		// KEYWORDS
		IElementType[] keywords = {
				LiveScriptTypes.CLASS,
				LiveScriptTypes.KEYWORD
		};

		for (IElementType o : keywords)
			if (tokenType.equals(o))
				return getKeySetFor(KEYWORD);


		// PREDEFINED VALUES & SYMBOLS
		IElementType[] constants = {
				LiveScriptTypes.BOOLEAN,
				LiveScriptTypes.EMPTY
		};

		for (IElementType o : constants)
			if (tokenType.equals(o))
				return getKeySetFor(CONSTANTS);

		// OPERATORS
		IElementType[] operators = {
				LiveScriptTypes.ASSIGN,
				LiveScriptTypes.BANG,
				LiveScriptTypes.COLON,
				LiveScriptTypes.COMMA,
				LiveScriptTypes.DOT,
				LiveScriptTypes.LIST_START,
				LiveScriptTypes.LIST_END,
				LiveScriptTypes.MATH_OP,
				LiveScriptTypes.OBJ_START,
				LiveScriptTypes.OBJ_END,
				LiveScriptTypes.OPERATOR,
				LiveScriptTypes.PAREN_L,
				LiveScriptTypes.PAREN_R,
				LiveScriptTypes.PLUS,
				LiveScriptTypes.SEMICOLON,
				LiveScriptTypes.YADA,
				LiveScriptTypes.MISC_OP,
				LiveScriptTypes.LOGIC_OP,
				LiveScriptTypes.Q
		};

		for (IElementType o : operators)
			if (tokenType.equals(o))
				return getKeySetFor(OPERATOR);

		return new TextAttributesKey[0];
	}
}
