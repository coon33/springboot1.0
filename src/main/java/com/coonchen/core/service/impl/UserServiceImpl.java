package com.coonchen.core.service.impl;

import com.coonchen.core.dao.UserMapper;
import com.coonchen.core.entity.User;
import com.coonchen.core.service.UserService;
import com.coonchen.framework.log.LogFactory;
import com.coonchen.framework.web.page.PageBean;
import java.util.HashMap;;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    private static final Logger logger = LogFactory.getLogger(UserServiceImpl.class);

    public int insertSelective(User record) {
        return this.userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(Integer userid) {
        return this.userMapper.selectByPrimaryKey(userid);
    }

    public List<User> selectSelective(User record) {
        return this.userMapper.selectSelective(record);
    }

    public int updateByPrimaryKeySelective(User record) {
        if(record.getUserid()==0) return userMapper.insertSelective(record);
        else return userMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(Integer userid) {
        return this.userMapper.deleteByPrimaryKey(userid);
    }

    public int deleteByPrimaryKeys(String[] ids) {
        return this.userMapper.deleteByPrimaryKeys(ids);
    }

    public Map<String, Object> selectPageList(Map<String, Object> searchMap, PageBean pageBean) {
        int iCount = userMapper.selectPageListCount(searchMap);
        pageBean.setRowcountAndCompute(iCount);
        searchMap.put("page", pageBean);
        List<User> lstData = userMapper.selectPageList(searchMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean", pageBean);
        map.put("lstData", lstData);
        map.put("searchMap", searchMap);
        return map;
    }
}