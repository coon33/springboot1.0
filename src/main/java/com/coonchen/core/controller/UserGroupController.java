package com.coonchen.core.controller;

import com.coonchen.core.entity.User;
import com.coonchen.core.entity.UserGroup;
import com.coonchen.core.service.UserGroupService;
import com.coonchen.core.service.UserService;
import com.coonchen.core.utils.CommonUtil;
import com.coonchen.framework.annotation.ValidatorAnnotation;
import com.coonchen.framework.controller.BasicController;
import com.coonchen.framework.log.LogFactory;
import com.coonchen.framework.utils.MD5Util;
import com.coonchen.framework.utils.Tool;
import com.coonchen.framework.web.page.PageBean;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserGroupController extends BasicController {
    Logger logger = LogFactory.getLogger(UserGroupController.class);

    @Resource
    private UserGroupService userGroupService;

    @RequestMapping("admin/ugroup/list.html")
    public Object list(ModelMap modelMap) {
        int pageon = paramInt("pageon", 1);
        int rownum = paramInt("rownum", 10);
        String sugname = paramString("sugname");

        PageBean pageBean = new PageBean(pageon, rownum);

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("sugname",sugname);
        mapParam.put("orderby","addtime");
        mapParam.put("sort","desc");
        Map<String, Object> map = userGroupService.selectPageList(mapParam, pageBean);
        modelMap.putAll(map);
        return "admin/user/list";
    }

    @RequestMapping("admin/ugroup/toEdit.html")
    public Object toEdit(ModelMap modelMap) {
        int sugid = paramInt("sugid", 0);
        if (sugid != 0) {
            UserGroup userGroup = userGroupService.selectByPrimaryKey(sugid);
            modelMap.put("userGroup", userGroup);
        }
        return "admin/user/edit";
    }

    @RequestMapping("admin/ugroup/save.do")
    @ResponseBody
    @ValidatorAnnotation(name = "sugname", explain = "用户组名称", rule = "required&maxsize:32&minsize:2")
    public Object save(ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        int sugid = paramInt("sugid",0);
        String sugname = paramString("sugname");

        UserGroup userGroup = new UserGroup();
        userGroup.setSugname(sugname.trim());
        if(sugid!=0){
            userGroup.setSugid(sugid);
        }
        int iR = userGroupService.updateByPrimaryKeySelective(userGroup);
        if (iR > 0)
            map.put("success", true);
        else {
            map.put("success", false);
            map.put("msg", "保存失败");
            logger.error("用户组管理-保存组失败");
        }
        return map;
    }

    @RequestMapping("admin/ugroup/del.do")
    @ResponseBody
    @ValidatorAnnotation(name = "ids", explain = "用户组ID", rule = "required")
    public Object del(ModelMap modelMap) {
        String ids = paramString("ids");
        int iR = 0;
        if(ids.indexOf(",")==-1){
            iR = userGroupService.deleteByPrimaryKey(Integer.parseInt(ids));
        }else{
            iR = userGroupService.deleteByPrimaryKeys(ids.split(","));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        if(iR<=0){
            map.put("success", false);
            map.put("msg", "删除失败");
            logger.error("用户组管理-删除组失败");
        }
        return map;
    }
}
