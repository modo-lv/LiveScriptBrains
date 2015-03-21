package lv.modo.livescriptbrains.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

import java.util.Arrays;

/**
 * A token in the token tree.
 */
public class TreeToken {
	/**
	 * Token index of this token in the Lexer token stream.
	 */
	public int StartPosition;

	/**
	 * Token index in the Lexer token stream where this token ends.
	 * Used for parent node tokens.
	 */
	public int EndPosition;

	/**
	 * Token element type.
	 */
	public IElementType Type;

	/**
	 * Marker for this particular token when in the token tree.
	 */
	public PsiBuilder.Marker Marker;

	/**
	 * Token text.
	 */
	public String Text;

	/**
	 * Error text in case of error.
	 */
	public String ErrorMessage;

	/**
	 * Number of non-space elements within this token.
	 * Used in Implicit list tokens.
	 */
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

	/**
	 * Check if the type of this token is the same ase one of the given types.
	 * @param types Types to check against.
	 * @return <tt>true</tt> if a match was found, <tt>false</tt> otherwise.
	 */
	public boolean TypeIsOneOf(IElementType... types) {
		boolean result = false;
		for (IElementType type : types) {
			if (!result && type == LiveScriptTypes.Separator)
				result = this.TypeIsOneOf(LiveScriptTypes.COMMA, LiveScriptTypes.NEWLINE);
			if (!result && type == LiveScriptTypes.Value)
				result = this.TypeIsOneOf(LiveScriptTypes.IDENTIFIER, LiveScriptTypes.NUMBER, LiveScriptTypes.STRING, LiveScriptTypes.BOOLEAN, LiveScriptTypes.EMPTY);
			if (!result && type == LiveScriptTypes.LITERAL)
				result = this.TypeIsOneOf(LiveScriptTypes.STRING, LiveScriptTypes.BOOLEAN, LiveScriptTypes.NUMBER, LiveScriptTypes.EMPTY);
			if (!result && type == LiveScriptTypes.OPERATOR)
				result = this.TypeIsOneOf(LiveScriptTypes.PLUS, LiveScriptTypes.ASSIGN, LiveScriptTypes.MATH_OP);
			if (!result && type == LiveScriptTypes.Expression)
				result = Arrays.asList(LiveScriptTypes.ExpressionTypes).contains(this.Type);
			if (!result && type == this.Type)
				result = true;

			if (result)
				return true;
		}
		return false;
	}
}
