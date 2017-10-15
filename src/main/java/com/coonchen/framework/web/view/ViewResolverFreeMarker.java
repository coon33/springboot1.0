package com.coonchen.framework.web.view;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.coonchen.framework.utils.PropertiesUtil;
import com.coonchen.framework.web.page.PageDirectiveFreeMarker;
@Configuration
@EnableWebMvc
public class ViewResolverFreeMarker extends FreeMarkerWebConfiguration{
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver resolver = super.freeMarkerViewResolver();
		resolver.getAttributesMap().put("page", new PageDirectiveFreeMarker());	
		String tagGroup = "freemarker.tag.";
		Map<String,String> mapPro = PropertiesUtil.getPropertiesGroupByTag(tagGroup);
		if(mapPro!=null && !mapPro.isEmpty()) {
			for(Entry<String, String> entry : mapPro.entrySet()) {
				try {
					Class cla = Class.forName(entry.getValue());
					resolver.getAttributesMap().put(entry.getKey().replace(tagGroup, ""), cla.newInstance());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return resolver;
	}
}
