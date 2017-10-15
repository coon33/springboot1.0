package com.coonchen.framework.validator.impl;

public class NumberRule extends StringRuleOperator{
	public static final String RULEKEY = "number";
	public static final String REGEX = "(^-\\d+$)|(^\\d+$)";

	public NumberRule() {
		super(RULEKEY, REGEX);
	}

	@Override
	public String validator(Object obj) {
		return super.validator(obj);
	}

	@Override
	public String message() {
		return "请输入数字";
	}
}
