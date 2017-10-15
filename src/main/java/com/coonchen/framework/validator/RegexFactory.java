package com.coonchen.framework.validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.coonchen.framework.log.LogFactory;

public class RegexFactory {
	private static Map<String, RegexExecutor> regexFactory = new ConcurrentHashMap<>();

	public static RegexExecutor getRegexExecutor(String rule) {
		try {
			RegexExecutor regexExecutor = regexFactory.get(rule);
			if (regexExecutor != null)
				return regexExecutor;
			regexExecutor = new RegexExecutor(rule) {};
			regexFactory.put(rule, regexExecutor);
			return regexExecutor;
		} catch (Exception e) {
			LogFactory.getLogger(RegexFactory.class).error(e.getMessage());
			return null;
		}
	}
}
