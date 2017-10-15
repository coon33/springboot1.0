package com.coonchen.core.controller;

import com.coonchen.core.entity.User;
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
public class UserController extends BasicController {
    Logger logger = LogFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("admin/user/list.html")
    public Object list(ModelMap modelMap) {
        int pageon = paramInt("pageon", 1);
        int rownum = paramInt("rownum", 10);
        String nickmobile = paramString("nickmobile");

        PageBean pageBean = new PageBean(pageon, rownum);

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("nickmobile",nickmobile);
        mapParam.put("orderby","addtime");
        mapParam.put("sort","desc");
        Map<String, Object> map = userService.selectPageList(mapParam, pageBean);
        modelMap.putAll(map);
        return "admin/user/list";
    }

    @RequestMapping("admin/user/toEdit.html")
    public Object toEdit(ModelMap modelMap) {
        int userid = paramInt("userid", 0);
        if (userid != 0) {
            User user = userService.selectByPrimaryKey(userid);
            modelMap.put("user", user);
        }
        return "admin/user/edit";
    }

    @RequestMapping("admin/user/toView.html")
    public Object toView(ModelMap modelMap) {
        int userid = paramInt("id", 0);
        if (userid != 0) {
            User user = userService.selectByPrimaryKey(userid);
            modelMap.put("user", user);
        }
        return "admin/user/view";
    }

    @RequestMapping("admin/user/toPwd.html")
    public Object toPwd(ModelMap modelMap) {
        int id = paramInt("id", 0);
        modelMap.put("id", id);
        return "admin/user/pwd";
    }

    @RequestMapping("admin/user/save.do")
    @ResponseBody
    @ValidatorAnnotation(name = "mobile", explain = "手机号", rule = "required&number&size:11")
    @ValidatorAnnotation(name = "nickname", explain = "用户昵称", rule = "required&maxsize:16&minsize:2")
    public Object save(ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        int userid = paramInt("userid",0);
        String mobile = paramString("mobile");
        String nickname = paramString("nickname");

        User user = new User();
        user.setMobile(mobile);
        if(userid==0){
            List<User> lstUser = userService.selectSelective(user);
            if(lstUser!=null && !lstUser.isEmpty()){
                map.put("success", false);
                map.put("msg", "该手机号码已经注册");
                return map;
            }
        }

        String password = "123456";
        user.setUserid(userid);
        user.setNickname(nickname);
        String ipaddress = Tool.get_onlineip(getRequest());
        user.setIpaddress(ipaddress);
        if (userid == 0) {
            String securekey = Tool.getCharacterAndNumber(3);
            String encodePassword = MD5Util.getMd5(MD5Util.getMd5(password) + securekey);
            user.setSecurekey(securekey);
            user.setPassword(encodePassword);
            user.setAddtime(CommonUtil.sysdateInt()); // 注册时间
            user.setLastlogintime(0);
            user.setLogintime(0); // 登录次数
            user.setSugid(1);
            user.setVisable((short)0);
        }
        int iR = userService.updateByPrimaryKeySelective(user);

        if (iR > 0)
            map.put("success", true);
        else {
            map.put("success", false);
            map.put("msg", "登录失败");
        }
        return map;
    }

    @RequestMapping("admin/user/del.do")
    @ResponseBody
    @ValidatorAnnotation(name = "ids", explain = "用户ID", rule = "required")
    public Object del(ModelMap modelMap) {
        String userids = paramString("ids");
        int iR = 0;
        if(userids.indexOf(",")==-1){
            iR = userService.deleteByPrimaryKey(Integer.parseInt(userids));
        }else{
            iR = userService.deleteByPrimaryKeys(userids.split(","));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        if(iR<=0){
            map.put("success", false);
            map.put("msg", "删除失败");
        }
        return map;
    }


    @RequestMapping("admin/user/able.do")
    @ResponseBody
    @ValidatorAnnotation(name = "id", explain = "用户ID", rule = "required")
    @ValidatorAnnotation(name = "able", explain = "可用标识", rule = "required&number")
    public Object able() {
        int userid = paramInt("id");
        short able = paramShort("able");
        User record = new User();
        record.setUserid(userid);
        record.setVisable((short)(able==1?0:1));
        int iR = userService.updateByPrimaryKeySelective(record);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        if(iR<=0){
            map.put("success", false);
            map.put("msg", "更新失败");
        }
        return map;
    }

    @RequestMapping("admin/user/pwd.do")
    @ResponseBody
    @ValidatorAnnotation(name = "id", explain = "用户ID", rule = "required&number")
    @ValidatorAnnotation(name = "oldpwd", explain = "原密码", rule = "required&minsize6&maxsize:16")
    @ValidatorAnnotation(name = "newpwd", explain = "新密码", rule = "required&minsize6&maxsize:16")
    public Object pwd() {
        Map<String, Object> map = new HashMap<String, Object>();
        String oldpwd = paramString("oldpwd");
        String newpwd = paramString("newpwd");
        int id = paramInt("id");
        //判断旧密码是否正确
        User user = userService.selectByPrimaryKey(id);
        if(user==null){
            throw new RuntimeException("未取到该用户信息");
        }
        String encodePassword = MD5Util.getMd5(MD5Util.getMd5(oldpwd) + user.getSecurekey());
        if(!encodePassword.equals(user.getPassword())){
            map.put("success", false);
            map.put("msg", "原密码输入错误");
            return map;
        }

        User record = new User();
        record.setUserid(id);
        String newEncodePassword = MD5Util.getMd5(MD5Util.getMd5(newpwd) + user.getSecurekey());
        record.setPassword(newEncodePassword);
        int iR = userService.updateByPrimaryKeySelective(record);
        map.put("success", true);
        if(iR<=0){
            map.put("success", false);
            map.put("msg", "修改失败");
        }
        return map;
    }
}
