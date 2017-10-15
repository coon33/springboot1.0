package com.coonchen.core.dao;

import com.coonchen.core.entity.UserGroup;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupMapper {
    int insertSelective(UserGroup record);

    List<UserGroup> selectSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer sugid);

    int updateByPrimaryKeySelective(UserGroup record);

    int deleteByPrimaryKey(Integer sugid);

    int deleteByPrimaryKeys(String[] ids);

    List<UserGroup> selectPageList(Map<String, Object> record);

    int selectPageListCount(Map<String, Object> record);
}