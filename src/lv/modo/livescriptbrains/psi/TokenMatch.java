package lv.modo.livescriptbrains.psi;

import com.intellij.psi.tree.IElementType;


public class TokenMatch {
	public IElementType ElementType;
	public int StartIndex;
	public int EndIndex;

	public TokenMatch(IElementType elementType, int start, int end) {
		this.ElementType = elementType;
		this.StartIndex = start;
		this.EndIndex = end;
	}
}
