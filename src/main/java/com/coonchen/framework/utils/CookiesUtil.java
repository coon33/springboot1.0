package com.coonchen.framework.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {
	
	public static String getCookieValue(HttpServletRequest request,String cookieName){
		if(StringUtil.isNotEmpty(cookieName)){
			Cookie[] arryCookie = request.getCookies();
			if(arryCookie==null || arryCookie.length==0) return "";
			for(Cookie cookie : arryCookie){
				if(cookieName.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	public static void setCookie(HttpServletResponse response
			,String cookieName
			,String cookieValue
			,String cookieDomain
			,String cookiePath
			,int cookieMaxAge){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setDomain(cookieDomain);
		cookie.setPath(cookiePath);
		cookie.setMaxAge(cookieMaxAge);
		setCookie(response,cookieName,cookieValue,cookieDomain,cookiePath,cookieMaxAge,false,null,false,0);
	}
	
	
	public static void setCookie(HttpServletResponse response
			,String cookieName
			,String cookieValue
			,String cookieDomain
			,String cookiePath
			,int cookieMaxAge
			,boolean cookieHttpOnly
			,String cookiePurpose
			,boolean cookieSecure
			,int cookieVersion){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setDomain(cookieDomain);
		cookie.setPath(cookiePath);
		cookie.setMaxAge(cookieMaxAge);
		cookie.setHttpOnly(cookieHttpOnly);
		if(StringUtil.isNotEmpty(cookiePurpose)) cookie.setComment(cookiePurpose);
		cookie.setSecure(cookieSecure);
		if(cookieVersion!=0) cookie.setVersion(cookieVersion);
		response.addCookie(cookie);
	}
}
