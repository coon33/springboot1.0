package com.coonchen.framework.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.coonchen.framework.validator.impl.GroupRuleOperator;
import com.coonchen.framework.validator.impl.StringRuleOperator;

public class RuleRecorder {
	public static Map<String,Pattern> mapStringPattern = new HashMap<>();//正则表达式

	public static Map<Pattern,GroupRuleOperator> mapGroupOperator = new HashMap<>();
	public static Map<String,StringRuleOperator> mapStringOperator = new HashMap<>();
}
