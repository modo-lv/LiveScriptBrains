package lv.modo.livescriptbrains.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LiveScriptParser implements PsiParser {
	/**
	 * Takes a PsiBuilder object containing lexer tokens and sets markers on it to show where a given element begins and
	 * ends.
	 * The way language parsing works here is that a PsiBuilder contains a linked list of lexer tokens. This parser
	 * then goes through the list, one by one, and sets a marker whenever the current token starts a new semantic element.
	 * For example, an "if" token would start a conditional element, so when that token is encountered,
	 * the parser would call builder.mark() to denote a start of a semantic element.
	 * The parser then goes to the next element and so on, and once it reaches the end of the conditional element,
	 * it calls builder.done(ConditionalElementType) to complete the marking and assign a type to it.
	 * Once all tokens have been processed and markers set, the parser calls builder.getTreeBuilt() and returns the
	 * resulting element tree.
	 * @param root There is always a root element for a file, this argument tells us the type of the root element.
	 * @param builder The builder that contains the tokens and that the markers are set on.
	 * @return The result of builder.getTreeBuilt() function call.
	 */
	@NotNull
	@Override
	public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
		// Before anything else, set the root element starting marker.
		// All tokens returned by the lexer must be inside this root element.
		PsiBuilder.Marker rootMarker = builder.mark();


		do {
			builder.advanceLexer();
		} while (builder.getTokenType() != null);


		// Once all tokens have been processed (marked) and we are at the end of the file,
		// close the root element and call the tree building process.
		rootMarker.done(root);

		return builder.getTreeBuilt();
	}
}