package com.coonchen.framework.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coonchen.framework.validator.RegexExecutor;
import com.coonchen.framework.validator.RegexFactory;
import com.coonchen.framework.validator.RuleRecorder;


public abstract class RuleOperator {
	
	protected String ruleKey;
	public String getRuleKey() {
		return ruleKey;
	}
	public void setRuleKey(String ruleKey) {
		this.ruleKey = ruleKey;
	}
	
	public RuleOperator(String rule) {
		this.ruleKey=rule;
	}

	public abstract String validator(Object obj);
	public abstract String validator(Object obj, Matcher matcher);
}
