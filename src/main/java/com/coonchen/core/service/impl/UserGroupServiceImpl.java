package com.coonchen.core.service.impl;

import com.coonchen.core.dao.UserGroupMapper;
import com.coonchen.core.entity.UserGroup;
import com.coonchen.core.service.UserGroupService;
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
public class UserGroupServiceImpl implements UserGroupService {
    @Resource
    private UserGroupMapper userGroupMapper;

    private static final Logger logger = LogFactory.getLogger(UserGroupServiceImpl.class);

    public int insertSelective(UserGroup record) {
        return this.userGroupMapper.insertSelective(record);
    }

    public UserGroup selectByPrimaryKey(Integer sugid) {
        return this.userGroupMapper.selectByPrimaryKey(sugid);
    }

    public List<UserGroup> selectSelective(UserGroup record) {
        return this.userGroupMapper.selectSelective(record);
    }

    public int updateByPrimaryKeySelective(UserGroup record) {
        return this.userGroupMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(Integer sugid) {
        return this.userGroupMapper.deleteByPrimaryKey(sugid);
    }

    public int deleteByPrimaryKeys(String[] ids) {
        return this.userGroupMapper.deleteByPrimaryKeys(ids);
    }

    public Map<String, Object> selectPageList(Map<String, Object> searchMap, PageBean pageBean) {
        int iCount = userGroupMapper.selectPageListCount(searchMap);
        pageBean.setRowcountAndCompute(iCount);
        searchMap.put("page", pageBean);
        List<UserGroup> lstData = userGroupMapper.selectPageList(searchMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean", pageBean);
        map.put("lstData", lstData);
        map.put("searchMap", searchMap);
        return map;
    }
}