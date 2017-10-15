package com.coonchen.core.validator;

import com.coonchen.framework.validator.impl.RuleOperator;
import com.coonchen.framework.validator.impl.StringRuleOperator;

public class DemoRule extends StringRuleOperator{
	public static final String RULEKEY="demo";
	public static final String REGEX ="(^-\\d+$)|(^\\d+$)";

	public DemoRule() {
		super(RULEKEY, null);
	}

	@Override
	public String validator(Object obj) {
		return "";
	}

	@Override
	public String message() {
		return "";
	}
}
