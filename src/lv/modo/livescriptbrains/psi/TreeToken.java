/*
package lv.modo.livescriptbrains.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import lv.modo.livescriptbrains.lexer.LexerTokens;

import java.util.Arrays;

*/
/**
 * A token in the token tree.
 *//*

public class TreeToken {
	*/
/**
	 * Token index of this token in the Lexer token stream.
	 *//*

	public int StartPosition;

	*/
/**
	 * Token index in the Lexer token stream where this token ends.
	 * Used for parent node tokens.
	 *//*

	public int EndPosition;

	*/
/**
	 * Token element type.
	 *//*

	public IElementType Type;

	*/
/**
	 * Marker for this particular token when in the token tree.
	 *//*

	public PsiBuilder.Marker Marker;

	*/
/**
	 * Token text.
	 *//*

	public String Text;

	*/
/**
	 * Error text in case of error.
	 *//*

	public String ErrorMessage;

	*/
/**
	 * Number of non-space elements within this token.
	 * Used in Implicit list tokens.
	 *//*

	public int ElementCount;


	public TreeToken() {
	}

	public TreeToken(IElementType type) {
		Type = type;
	}

	@Override
	public String toString() {
		return Type.toString();
	}

	*/
/**
	 * Check if the type of this token is the same ase one of the given types.
	 * @param types Types to check against.
	 * @return <tt>true</tt> if a match was found, <tt>false</tt> otherwise.
	 *//*

	public boolean TypeIsOneOf(IElementType... types) {
		boolean result = false;
		for (IElementType type : types) {
			if (!result && type == LexerTokens.Separator)
				result = this.TypeIsOneOf(LexerTokens.COMMA, LexerTokens.NEWLINE);
			if (!result && type == LexerTokens.Value)
				result = this.TypeIsOneOf(LexerTokens.IDENTIFIER, LexerTokens.NUMBER, LexerTokens.STRING, LexerTokens.BOOLEAN, LexerTokens.EMPTY);
			if (!result && type == LexerTokens.LITERAL)
				result = this.TypeIsOneOf(LexerTokens.STRING, LexerTokens.BOOLEAN, LexerTokens.NUMBER, LexerTokens.EMPTY);
			if (!result && type == LexerTokens.OPERATOR)
				result = this.TypeIsOneOf(LexerTokens.PLUS, LexerTokens.ASSIGN, LexerTokens.MATH_OP);
			if (!result && type == LexerTokens.Expression)
				result = Arrays.asList(LexerTokens.ExpressionTypes).contains(this.Type);
			if (!result && type == this.Type)
				result = true;

			if (result)
				return true;
		}
		return false;
	}
}
*/
