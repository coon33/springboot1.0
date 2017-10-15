package com.allwell.erp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.coonchen.core.service.UserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coonchen.core.controller.UserController;
import com.coonchen.core.entity.User;
import com.coonchen.framework.log.LogFactory;
@Controller
public class IndexController {
	
	Logger logger = LogFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/json")
	@ResponseBody
	public Object json(ModelMap modelMap) {
		logger.error("ddddddddddddddddddddddd");
		modelMap.addAttribute("aaa", "cw");
		Map map = new HashMap();
		map.put("aaa",  "cw");
		Map map1 = new HashMap();
		map1.put("bbb", map);
		return map1;
	}
	
	@RequestMapping("/index.html")
	public Object index(ModelMap modelMap) {
/*		int pageon = paramInt("pageon");
		int pageon1 = paramInt("pageon1");
		PageBean pageBean = new PageBean();
		pageBean.setCurrIndex(pageon);
		pageBean.setPageCount(20);
		pageBean.setRowNum(20);
		pageBean.setTotalCount(200);
		
		modelMap.addAttribute("pageBean", pageBean);*/
		User user = new User();
		user.setUserid(1);
		user.setNickname("cw111");
		int iC = userService.updateByPrimaryKeySelective(user);
		
		return "admin/index";
	}
}
