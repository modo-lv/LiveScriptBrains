package lv.modo.livescriptbrains.features;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.lexer.LexerTokens;
import lv.modo.livescriptbrains.lexer.LiveScriptLexer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LiveScriptSyntaxHighlighter extends SyntaxHighlighterBase
{
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
	public static final TextAttributesKey THIS =
		createTextAttributesKey("LS_THIS", LiveScriptHighlighterColors.THIS);

	// Custom


	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new FlexAdapter(new LiveScriptLexer());
	}

	private TextAttributesKey[] getKeySetFor(TextAttributesKey taKey)
	{
		return new TextAttributesKey[]{taKey};
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		IElementType[] strings = {
			LexerTokens.STRING,
			LexerTokens.STRING_START,
			LexerTokens.STRING_END,
			LexerTokens.STRING_LITERAL
		};

		for (IElementType i : strings)
			if (tokenType.equals(i))
				return getKeySetFor(STRING);

		Map<IElementType, TextAttributesKey> types = new HashMap<IElementType, TextAttributesKey>();
		types.put(LexerTokens.COMMENT_LINE, COMMENT_LINE);
		types.put(LexerTokens.NUMBER, NUMBER);
		types.put(LexerTokens.COMMENT_BLOCK, COMMENT_BLOCK);
		types.put(LexerTokens.IDENTIFIER, IDENTIFIER);

		for (IElementType e : types.keySet())
			if (tokenType.equals(e))
				return getKeySetFor(types.get(e));

		// KEYWORDS
		IElementType[] keywords = {
			LexerTokens.CLASS,
			//LexerTokens.KEYWORD
		};

		for (IElementType o : keywords)
			if (tokenType.equals(o))
				return getKeySetFor(KEYWORD);

		// THIS
		if (tokenType.equals(LexerTokens.THIS))
			return getKeySetFor(THIS);


		// PREDEFINED VALUES & SYMBOLS
		IElementType[] constants = {
			LexerTokens.BOOLEAN,
			//LexerTokens.EMPTY,
		};

		for (IElementType o : constants)
			if (tokenType.equals(o))
				return getKeySetFor(CONSTANTS);

		// OPERATORS
		IElementType[] operators = {
			LexerTokens.INTERPOLATED_VAR_START,
			LexerTokens.INTERPOLATION_START,
			LexerTokens.INTERPOLATION_END,

/*
				LexerTokens.ASSIGN,
				LexerTokens.BANG,
*/
			LexerTokens.COLON,
			LexerTokens.COMMA,
			LexerTokens.DOT,
/*
				LexerTokens.LIST_START,
				LexerTokens.LIST_END,
				LexerTokens.MATH_OP,
				LexerTokens.OBJ_START,
				LexerTokens.OBJ_END,
				LexerTokens.OPERATOR,
				LexerTokens.PAREN_L,
				LexerTokens.PAREN_R,
*/
			LexerTokens.PLUS,
			LexerTokens.SEMICOLON,
/*
				LexerTokens.YADA,
				LexerTokens.MISC_OP,
				LexerTokens.LOGIC_OP,
				LexerTokens.Q
*/
		};

		for (IElementType o : operators)
			if (tokenType.equals(o))
				return getKeySetFor(OPERATOR);

		return new TextAttributesKey[0];
	}
}
