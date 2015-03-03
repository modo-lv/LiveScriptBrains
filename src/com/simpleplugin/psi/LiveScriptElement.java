package com.simpleplugin.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class LiveScriptElement extends ASTWrapperPsiElement {
	private final ASTNode node;

	public LiveScriptElement(ASTNode node) {
		super(node);
		this.node = node;
	}

	public String toString() {
		return "LS:" + node.getElementType().toString();
	}
}
