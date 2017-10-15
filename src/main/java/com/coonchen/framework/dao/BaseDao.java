package com.coonchen.framework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* @ClassName: BaseDao 
* @Description: 
* @author cw
* @date 2017年8月4日 上午11:05:10 
 */
public interface BaseDao {
	int insert(String sql,Object... values) throws SQLException;
	int update(String sql,Object... values) throws SQLException;
	int delete(String sql,Object... values) throws SQLException;
	List<Map<String,Object>> getListMap(String sql,Object... values) throws SQLException;
	<T> List<T> getListBean(Class<T> cla,String sql,Object... values) throws Exception;
	int getCount(String sql,Object... values) throws SQLException;
}
