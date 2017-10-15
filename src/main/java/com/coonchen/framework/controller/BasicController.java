package com.coonchen.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BasicController {
	private ServletRequestAttributes getAttributes(){
		 return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}
	protected HttpServletRequest getRequest(){
		return getAttributes().getRequest();
	}
	
	protected String paramString(String paramname){
		return getRequest().getParameter(paramname);
	}
	
	protected int paramInt(String paramname) {
		try {
			return Integer.parseInt(paramString(paramname));
		} catch (Exception e) {
			return 0;
		}
	}

	protected int paramInt(String paramname, int defaultvalue) {
		try {
			return Integer.parseInt(paramString(paramname));
		} catch (Exception e) {
			return defaultvalue;
		}
	}
	
	protected long paramLong(String paramname) {
		try {
			return Long.parseLong(paramString(paramname));
		} catch (Exception e) {
			return 0;
		}
	}

	protected long paramLong(String paramname, long value) {
		try {
			return Long.parseLong(paramString(paramname));
		} catch (Exception e) {
			return value;
		}
	}

	protected short paramShort(String paramname) {
		try {
			return Short.parseShort(paramString(paramname));
		} catch (Exception e) {
			return 0;
		}
	}

	protected short paramShort(String paramname, short value) {
		try {
			return Short.parseShort(paramString(paramname));
		} catch (Exception e) {
			return value;
		}
	}

	protected byte paramByte(String paramname) {
		try {
			return Byte.parseByte(paramString(paramname));
		} catch (Exception e) {
			return 0;
		}
	}

	protected byte paramByte(String paramname, byte value) {
		try {
			return Byte.parseByte(paramString(paramname));
		} catch (Exception e) {
			return value;
		}
	}

	protected float paramFloat(String paramname, float value) {
		try {
			return Float.parseFloat(paramString(paramname));
		} catch (Exception e) {
			return value;
		}
	}

	protected float paramFloat(String paramname) {
		try {
			return Float.parseFloat(paramString(paramname));
		} catch (Exception e) {
			return 0.0f;
		}
	}

	protected double paramDouble(String paramname) {
		try {
			return Double.parseDouble(paramString(paramname));
		} catch (Exception e) {
			return 0.00d;
		}
	}

	protected double paramDouble(String paramname, double value) {
		try {
			return Double.parseDouble(paramString(paramname));
		} catch (Exception e) {
			return value;
		}
	}

	protected Boolean paramBoolean(String paramname) {
		try {
			paramname = paramString(paramname);
			if(paramname==null||(!paramname.trim().equals("false")&&!paramname.trim().equals("true")))
				return null;
			return Boolean.parseBoolean(paramname);
		} catch (Exception e) {
			return null;
		}
	}
	protected boolean paramboolean(String paramname) {
		try {
			return Boolean.parseBoolean(paramString(paramname));
		} catch (Exception e) {
			return false;
		}
	}
}
