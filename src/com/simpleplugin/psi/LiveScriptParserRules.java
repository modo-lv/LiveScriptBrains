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
		this.add(new Rule(LiveScriptTypes.VALUE, new IElementType[] {LiveScriptTypes.NUMBER}));

		this.add(new Rule(LiveScriptTypes.MATH_OPERATION, new IElementType[]{
			LiveScriptTypes.VALUE,
			LiveScriptTypes.MATH_OP,
			LiveScriptTypes.VALUE,
		}));

		this.add(new Rule(LiveScriptTypes.EXPRESSION, new IElementType[] {LiveScriptTypes.MATH_OPERATION}));

		this.add(new Rule(LiveScriptTypes.ASSIGN_OPERATION, new IElementType[] {
			LiveScriptTypes.IDENTIFIER,
			LiveScriptTypes.ASSIGN,
			LiveScriptTypes.EXPRESSION
		}));
	}


}
