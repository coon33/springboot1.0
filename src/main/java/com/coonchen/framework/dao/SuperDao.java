package com.coonchen.framework.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* @ClassName: SuperDao 
* @Description:
* @author cw
* @date 2017年8月4日 上午11:05:21 
 */
public class SuperDao {
	private BaseDao basedao;
	
	public SuperDao(Connection conn) {
		basedao = new BaseDaoImpl(conn);
	}
	
	public int insert(String sql,Object... values) throws SQLException  {
		return basedao.insert(sql, values);
	}
	
	public int update(String sql,Object... values) throws SQLException{
		return basedao.update(sql, values);
	}
	
	public List<Map<String,Object>> getListMap(String sql,Object... values) throws SQLException{
		return basedao.getListMap(sql, values);
	}
	public <T> List<T> getListBean(Class<T> cla,String sql,Object... values) throws Exception{
		return basedao.getListBean(cla,sql, values);
	}
	public int getCount(String sql,Object... values) throws SQLException{
		return basedao.getCount(sql, values);
	}
}
