package com.coonchen.framework.web.page;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.coonchen.framework.utils.StringUtil;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class PageDirectiveFreeMarker implements TemplateDirectiveModel {

	@Override
	public void execute(Environment environment, Map map, TemplateModel[] atemplatemodel,
			TemplateDirectiveBody templatedirectivebody) throws TemplateException, IOException {

		Writer out = null;
		try {
			if (templatedirectivebody == null) {// 自定义标签必须有内容，即自定义 开始标签与结束标签之间必须有 内容
				throw new TemplateModelException("freemarker directive null body");
			} else {
				out = environment.getOut();
				TemplateModel templateModel = environment.getVariable("pageBean");
				StringModel pageBean = (StringModel) templateModel;
				int currIndex = StringUtil.nullToInt(pageBean.get("currIndex"));
				int pageCount = StringUtil.nullToInt(pageBean.get("pageCount"));
				String url = StringUtil.nullToStr(map.get("url"));
				String style=StringUtil.nullToStr(map.get("style"));
				out.write(PageStyle.generatePageInfo(currIndex,pageCount,url,style));
				templatedirectivebody.render(out);
			}
		} catch (TemplateModelException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * @Override public Object exec(List arguments) throws TemplateModelException {
	 * String key = String.valueOf(arguments.get(0)); try { switch (key) { case
	 * "source": return getString(); default: return null; } } catch (Exception e) {
	 * return null; } }
	 * 
	 * public static String getString() { return "<font color='red'>我是分页123</font>";
	 * }
	 */
}
