package com.coonchen.core.service;

import com.coonchen.core.entity.UserGroup;
import com.coonchen.framework.web.page.PageBean;
import java.util.List;
import java.util.Map;

public interface UserGroupService {
    int insertSelective(UserGroup record);

    List<UserGroup> selectSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer sugid);

    int updateByPrimaryKeySelective(UserGroup record);

    int deleteByPrimaryKey(Integer sugid);

    int deleteByPrimaryKeys(String[] ids);

    Map<String, Object> selectPageList(Map<String, Object> searchMap, PageBean pageBean);
}