package com.coonchen.framework.validator.impl;

public class RequiredRule extends StringRuleOperator{
	public static final String RULEKEY="required";

	public RequiredRule() {
		super(RULEKEY, null);
	}

	@Override
	public String validator(Object obj) {
		boolean flag = true;
		if(obj==null) flag = false;
		if(obj instanceof String){
			flag = !"".equals(((String) obj).trim());
		}else if(obj instanceof String[]){
			String[] values = (String[])obj;
			for (String v : values){
				if(v==null || "".equals(v)){
					flag = false;
					break;
				}
			}
		}
		if(!flag) return message();
		return null;
	}

	@Override
	public String message() {
		return "不能为空";
	}
}
