package lv.modo.livescriptbrains.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import lv.modo.livescriptbrains.LiveScriptLanguage;
import lv.modo.livescriptbrains.psi.LiveScriptLexer;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public class LiveScriptParserDefinition implements ParserDefinition{
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(LiveScriptTypes.COMMENT_LINE, LiveScriptTypes.COMMENT_BLOCK);
	public static LiveScriptParserDefinition INSTANCE;

    public static final IFileElementType FILE = new IFileElementType(Language.<LiveScriptLanguage>findInstance(LiveScriptLanguage.class));

	public LiveScriptParserDefinition() {
		INSTANCE = this;
	}

	@NotNull
    @Override
    public Lexer createLexer(Project project) {
		return new FlexAdapter(new LiveScriptLexer());
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new LiveScriptParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new LiveScriptFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
		PsiElement el = new ASTWrapperPsiElement(node);
		return el;
    }
}