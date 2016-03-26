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
	public static final TextAttributesKey BAD_CHAR =
		createTextAttributesKey("LS_BAD_CHAR", LiveScriptHighlighterColors.BAD_CHAR);
	public static final TextAttributesKey REGEX =
		createTextAttributesKey("LS_REGEX", LiveScriptHighlighterColors.REGEX);

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
		if (tokenType.equals(LexerTokens.BAD_CHAR))
			return getKeySetFor(BAD_CHAR);

		IElementType[] strings = {
			LexerTokens.STRING,
			LexerTokens.STRING_START,
			LexerTokens.STRING_END,
			LexerTokens.STRING_LITERAL
		};

		for (IElementType i : strings)
			if (tokenType.equals(i))
				return getKeySetFor(STRING);

		IElementType[] regex = {
			LexerTokens.REGEX,
			LexerTokens.REGEX_START,
			LexerTokens.REGEX_END,
			LexerTokens.REGEX_LITERAL,
		};

		for (IElementType i : regex)
			if (tokenType.equals(i))
				return getKeySetFor(REGEX);

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
			LexerTokens.NEW_KEYWORD,
			LexerTokens.IF,
			LexerTokens.THEN,
			LexerTokens.ELSE,
			LexerTokens.FROM,
			LexerTokens.TO,
			LexerTokens.TIL,
			LexerTokens.BY,
			LexerTokens.OF,
			LexerTokens.IN_KEYWORD,
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
			LexerTokens.EMPTY,
		};

		for (IElementType o : constants)
			if (tokenType.equals(o))
				return getKeySetFor(CONSTANTS);

		// OPERATORS
		IElementType[] operators = {
			LexerTokens.INTERPOLATED_VAR_START,
			LexerTokens.INTERPOLATION_START,
			LexerTokens.INTERPOLATION_END,
			LexerTokens.COLON,
			LexerTokens.COMMA,
			LexerTokens.DOT,
			LexerTokens.PARENTHESIS_START,
			LexerTokens.PARENTHESIS_END,
			LexerTokens.BRACKET_START,
			LexerTokens.BRACKET_END,
			LexerTokens.BRACE_START,
			LexerTokens.BRACE_END,
			LexerTokens.PLUS,
			LexerTokens.MINUS,
			LexerTokens.MULT,
			LexerTokens.PLUS_EQ,
			LexerTokens.MINUS_EQ,
			LexerTokens.MULT_EQ,
			LexerTokens.PLUS_PLUS,
			LexerTokens.MINUS_MINUS,
			LexerTokens.MULT_MULT,
			LexerTokens.EQ,
			LexerTokens.EQ_EQ,
			LexerTokens.EQ_EQ_EQ,
			LexerTokens.NE,
			LexerTokens.NOT_EQ_EQ,
			LexerTokens.SEMICOLON,
			LexerTokens.EXIST,
			LexerTokens.FUNCTION,
			LexerTokens.FUNCTION_BIND,
			LexerTokens.SEMICOLON,
			LexerTokens.PERC,

		};

		for (IElementType o : operators)
			if (tokenType.equals(o))
				return getKeySetFor(OPERATOR);

		return new TextAttributesKey[0];
	}
}
