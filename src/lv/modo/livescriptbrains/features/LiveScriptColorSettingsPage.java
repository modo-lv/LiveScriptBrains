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
			new AttributesDescriptor("String", LiveScriptSyntaxHighlighter.STRING),
			new AttributesDescriptor("Number", LiveScriptSyntaxHighlighter.NUMBER),
			new AttributesDescriptor("Line comment", LiveScriptSyntaxHighlighter.COMMENT_LINE),
			new AttributesDescriptor("Block comment", LiveScriptSyntaxHighlighter.COMMENT_BLOCK),
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
				"lines */";
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
