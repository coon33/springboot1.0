package com.coonchen.core.dao;

import com.coonchen.core.entity.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertSelective(User record);

    List<User> selectSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int deleteByPrimaryKey(Integer userid);

    int deleteByPrimaryKeys(String[] ids);

    List<User> selectPageList(Map<String, Object> record);

    int selectPageListCount(Map<String, Object> record);
}