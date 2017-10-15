package com.coonchen.framework.validator.impl;

import com.coonchen.framework.validator.RuleRecorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EqualSizeRule extends GroupRuleOperator{
	public static final String RULEKEY ="^size:([1-9]{1}\\d*$)";

	public EqualSizeRule() {
		super(RULEKEY);
	}

	@Override
	public String validator(Object obj,Matcher matcher) {
		if(obj==null || "".equals(obj.toString()))
			return null;
		int i = Integer.parseInt(matcher.group(1));
		String error = "应为"+i+"位!";
		if(obj instanceof String){
			if(((String) obj).trim().length()!=i)
				return error;
		}else if(obj instanceof String[]){
			String[] values = (String[])obj;
			for(String v : values){
				if(v.trim().length()!=i)
					return error;
			}
		}
		return null;
	}

	@Override
	public String message() {
		return "不等于";
	}
}
