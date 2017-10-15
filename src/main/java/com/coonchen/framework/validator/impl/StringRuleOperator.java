package com.coonchen.framework.validator.impl;

import com.coonchen.framework.validator.RegexExecutor;
import com.coonchen.framework.validator.RegexFactory;
import com.coonchen.framework.validator.RuleRecorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringRuleOperator extends RuleOperator {

    public StringRuleOperator(String rule, String regex) {
        super(rule);
        if(regex != null) {
            RuleRecorder.mapStringPattern.put(rule, Pattern.compile(regex));
        }
        RuleRecorder.mapStringOperator.put(rule, this);
    }

    @Override
    public String validator(Object values, Matcher matcher){
        return null;
    }

    @Override
    public String validator(Object values) {
        if(values==null) return null;
        RegexExecutor regexExecutor = RegexFactory.getRegexExecutor(ruleKey);
        if(regexExecutor==null) return null;
        boolean flag=true;
        if(values instanceof String){
            flag = regexExecutor.validator(values);
        }else if(values instanceof String[]){
            for(String value : ((String[]) values)){
                if(value!=null){
                    flag = regexExecutor.validator(value);
                    if(!flag) break;
                }
            }
        }
        if(flag) return null;
        return message();
    }

    public abstract String message();
}
