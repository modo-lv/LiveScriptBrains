package lv.modo.livescriptbrains.features;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import lv.modo.livescriptbrains.LiveScriptIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class LiveScriptColorSettingsPage implements ColorSettingsPage {
	public static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("Line comment", LiveScriptSyntaxHighlighter.COMMENT_LINE),
			new AttributesDescriptor("Block comment", LiveScriptSyntaxHighlighter.COMMENT_BLOCK),
			new AttributesDescriptor("String", LiveScriptSyntaxHighlighter.STRING),
			new AttributesDescriptor("Number", LiveScriptSyntaxHighlighter.NUMBER),
			new AttributesDescriptor("Operation sign", LiveScriptSyntaxHighlighter.OPERATOR),
			new AttributesDescriptor("Predefined constant", LiveScriptSyntaxHighlighter.CONSTANTS),
			new AttributesDescriptor("Identifier", LiveScriptSyntaxHighlighter.IDENTIFIER),
			new AttributesDescriptor("Keyword", LiveScriptSyntaxHighlighter.KEYWORD),
			new AttributesDescriptor("'this' keyword", LiveScriptSyntaxHighlighter.THIS),
			new AttributesDescriptor("Bad character", LiveScriptSyntaxHighlighter.BAD_CHAR),
	};

	@Nullable
	@Override
	public Icon getIcon() {
		return LiveScriptIcons.FILE;
	}

	@NotNull
	@Override
	public SyntaxHighlighter getHighlighter() {
		return new LiveScriptSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText() {
		return "\"Interpolated string has #{a = 2 + 2} expression in it.\"\n" +
				"# Line comment\n" +
				"/* Block comment\n" +
				"can span multiple\n" +
				"lines */\n" +
				"\n" +
				"x = true; y = off\n" +
				"undef-var = undefined; empty-var = null; unset-var = void\n" +
				"class Klass\n" +
				"	(@test) ->";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return null;
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors() {
		return DESCRIPTORS;
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName() {
		return "LiveScript";
	}
}
