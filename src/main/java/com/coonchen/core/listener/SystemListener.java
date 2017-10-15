package com.coonchen.core.listener;

import com.coonchen.core.cache.CacheManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.coonchen.core.validator.DemoRule;
import com.coonchen.framework.validator.ValidatorExecutor;
import com.coonchen.framework.web.page.PageStyle;
import com.coonchen.framework.web.page.PageStyleBean;


@Component
@Order(value=1)
public class SystemListener implements CommandLineRunner{
	@Override
	public void run(String... arg0) throws Exception {
		//启用后台验证框架
		ValidatorExecutor.startup(DemoRule.class);
		//启用缓存
		CacheManager.getInstance();
		//初始化后台分页样式
		PageStyleBean pcont = new PageStyleBean();
		pcont.setStartPage("<div style=\"float:right;margin:10px 0\">");
		pcont.setFirstPage("<a style=\"margin-left:5px\" href=\"~content_mark\">首页</a>");
		pcont.setPreviousPage("<a style=\"margin-left:5px\" href=\"~content_mark\">上一页</a>");
		pcont.setOtherPage("<a style=\"margin-left:5px\" href=\"~content_mark\">~p</a>");
		pcont.setCurrentPage("<span style=\"margin-left:5px;color:blue\">~p</span>");
		pcont.setNextPage("<a style=\"margin-left:5px\" href=\"~content_mark\">下一页</a>");
		pcont.setLastPage("<a style=\"margin-left:5px\" href=\"~content_mark\">尾页</a>");
		pcont.setEndPage("</div>");
		PageStyle.importPageStyle("admin", pcont);
	}
}
