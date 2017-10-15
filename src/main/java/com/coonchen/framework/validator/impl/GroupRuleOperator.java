package com.coonchen.framework.validator.impl;

import com.coonchen.framework.validator.RuleRecorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GroupRuleOperator extends RuleOperator {

    public GroupRuleOperator(String rule) {
        super(rule);
        Pattern pattern = Pattern.compile(rule);
        if(pattern!=null)
            RuleRecorder.mapGroupOperator.put(pattern, this);
    }
    @Override
    public String validator(Object obj){
        return null;
    }
    public abstract String validator(Object obj,Matcher matcher);
    public abstract String message();
}
