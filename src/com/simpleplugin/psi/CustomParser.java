package com.simpleplugin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomParser implements PsiParser {
	PsiBuilder.Marker _rootMarker;
	Lexer _lexer;

	@NotNull
	@Override
	public ASTNode parse(IElementType root, PsiBuilder builder) {
		builder.setDebugMode(true);
		_rootMarker = builder.mark();

		doParse(builder);

		_rootMarker.done(LiveScriptTypes.STRING);

		return builder.getTreeBuilt();
	}

	public class Token {
		public int LexerPosition;
		public ArrayList<PsiBuilder.Marker> Markers = new ArrayList<PsiBuilder.Marker>();
		public IElementType Type;
	}

	public class StartLexerPosition implements LexerPosition {
		@Override
		public int getOffset() {
			return 0;
		}

		@Override
		public int getState() {
			return 0;
		}
	}

	private void doParse(PsiBuilder builder) {
		Map<Integer, Token> tokens = new HashMap<Integer, Token>();
		_lexer = LiveScriptParserDefinition.INSTANCE.createLexer(builder.getProject());
		_lexer.start(builder.getOriginalText());

		do {
			Token token = new Token();
			token.LexerPosition = _lexer.getTokenStart();
			token.Type = _lexer.getTokenType();
			tokens.put(token.LexerPosition, token);
			_lexer.advance();
		}
		while (_lexer.getTokenType() != null);

		while (!builder.eof()) {
			int pos = builder.getCurrentOffset();
			Token t = tokens.get(pos);
			if (t.Type == TokenType.BAD_CHARACTER)
				builder.mark().error("Bad char");
			else
				builder.mark().done(t.Type);
			builder.advanceLexer();
		}

/*
		_lexer.restore(new StartLexerPosition());

		while (!builder.eof()) {
			PsiBuilder.Marker mark = builder.mark();
			if (builder.getTokenType() == TokenType.BAD_CHARACTER)
				mark.error("Bad char");
			else
				mark.done(builder.getTokenType());
			builder.advanceLexer();
		}
*/
	}
}
