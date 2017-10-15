package com.coonchen.core.service;

import com.coonchen.core.entity.User;
import com.coonchen.framework.web.page.PageBean;
import java.util.List;
import java.util.Map;

public interface UserService {
    int insertSelective(User record);

    List<User> selectSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int deleteByPrimaryKey(Integer userid);

    int deleteByPrimaryKeys(String[] ids);

    Map<String, Object> selectPageList(Map<String, Object> searchMap, PageBean pageBean);
}