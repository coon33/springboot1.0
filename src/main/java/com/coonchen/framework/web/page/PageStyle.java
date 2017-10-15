package com.coonchen.framework.web.page;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.coonchen.framework.log.LogFactory;
import com.coonchen.framework.utils.StringUtil;

public class PageStyle {
	
	public static Logger logger = LogFactory.getLogger(PageStyle.class);
	
	private static PageStyle pageStyle;
	
	private static Map<String,PageStyleBean> mapPageStyle = new HashMap<String,PageStyleBean>();
	
	
	static {
		PageStyleBean pcont = new PageStyleBean();
		pcont.setFirstPage("<a href=\"~content_mark\">首页</a> ");
		pcont.setPreviousPage("<a href=\"~content_mark\">上一页</a> ");
		pcont.setOtherPage("<a href=\"~content_mark\">~p</a> ");
		pcont.setCurrentPage("~p");
		pcont.setNextPage("<a href=\"~content_mark\">下一页</a>");
		pcont.setLastPage("<a href=\"~content_mark\">尾页</a>");
		PageStyle.importPageStyle("default", pcont);
	}
	
	public static synchronized PageStyle getInstance() {
		if(pageStyle==null)
			pageStyle = new PageStyle();
		return pageStyle;
	}
	
	public static void importPageStyle(String style,PageStyleBean pageStyleBean) {
		if(mapPageStyle==null || StringUtil.isEmpty(style)) {
			logger.error("分页样式导入失败");
			return;
		}
		mapPageStyle.put(style, pageStyleBean);
	}
	
	public static String generatePageInfo(int currIndex, int pageCount,String url,String style) {
		StringBuilder sbPage = new StringBuilder();
		if(mapPageStyle.get(style)==null) {
			String msgerror = "请在系统启动类中调用【PageStyle.importPageStyle】配置分页样式";
			logger.error(msgerror);
			return msgerror;
		}
		
		PageStyleBean pageStyleBean = mapPageStyle.get(style);
		int pageNum = pageStyleBean.getPageMaxNum();
		
		int start;
		int end;
		if(currIndex==0) currIndex = 1;
		int flg = pageNum%2;
		int pn = pageNum/2;
		start = currIndex-pn;
		end = currIndex+pn;
		if(flg==0) end--;
		if(start<=0) {
			end=end-start+1;
			start = 1;
		}
		if(end>=pageCount) {
			start=start-(end-pageCount);
			end = pageCount;
		}
		if(start<=0) start = 1;
		
		sbPage.append(pageStyleBean.getStartPage()==null?"":pageStyleBean.getStartPage());
		
		if(currIndex>1) {
			sbPage.append(pageStyleBean.getFirstPage().replace("~content_mark", url.replace("~p", "1")));
			sbPage.append(pageStyleBean.getPreviousPage().replace("~content_mark", url.replace("~p", StringUtil.nullToStr(currIndex-1))));
		}
		
		for(int i = start;i<=end;i++) {
			if(i==currIndex){
				if(pageCount>1)
					sbPage.append(pageStyleBean.getCurrentPage().replace("~p", StringUtil.nullToStr(currIndex)));
			}
			else
				sbPage.append(pageStyleBean.getOtherPage().replace("~content_mark", url.replace("~p", StringUtil.nullToStr(i))).replace("~p", StringUtil.nullToStr(i)));
		}
		
		if(pageCount>1 && currIndex<pageCount) {
			sbPage.append(pageStyleBean.getNextPage().replace("~content_mark", url.replace("~p", StringUtil.nullToStr(currIndex+1))));
			sbPage.append(pageStyleBean.getLastPage().replace("~content_mark", url.replace("~p", StringUtil.nullToStr(pageCount))));
		}
		sbPage.append(pageStyleBean.getEndPage()==null?"":pageStyleBean.getEndPage());
		return sbPage.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(PageStyle.generatePageInfo(8, 12, "index.html?pageon=~p&row=10", "default"));
	}
}
