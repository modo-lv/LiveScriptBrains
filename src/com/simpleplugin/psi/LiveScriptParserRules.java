package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiveScriptParserRules extends ArrayList<LiveScriptParserRules.Rule> {

	public class Rule {
		/**
		 * Type of element to create a result token of.
		 */
		public IElementType Result;

		/**
		 * A sequential list of token types to merge into
		 * this rule.
		 */
		public List<IElementType> TokenTypes;

		/**
		 * Constructor.
		 *
		 * @param result
		 * @param tokenTypes
		 */
		public Rule(IElementType result, IElementType[] tokenTypes) {
			Result = result;
			TokenTypes = new ArrayList<IElementType>();
			TokenTypes.addAll(Arrays.asList(tokenTypes));
		}
	}

	public LiveScriptParserRules() {
		Rule rule;

		rule = new Rule(LiveScriptTypes.MATH_OPERATION, new IElementType[]{
			LiveScriptTypes.NUMBER,
			LiveScriptTypes.MATH_OP,
			LiveScriptTypes.NUMBER,
		});

		this.add(rule);
	}


}
