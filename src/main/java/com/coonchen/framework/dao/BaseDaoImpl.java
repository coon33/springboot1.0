package com.coonchen.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* @ClassName: BaseDaoImpl 
* @Description:
* @author cw
* @date 2017年8月4日 上午11:05:01 
 */
public class BaseDaoImpl implements BaseDao {
	
	private Connection conn;
	
	public BaseDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(String sql,Object... values) throws SQLException {
		return update(sql,values);
	}
	
	@Override
	public int delete(String sql,Object... values) throws SQLException {
		return update(sql,values);
	}
	
	@Override
	public int update(String sql,Object... values) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int sum = values!=null?values.length:0;
		for(int i = 0;i<sum;i++) {
			pstmt.setObject(i+1, values[i]);
		}
		int ir = pstmt.executeUpdate();
		pstmt.close();
		return ir;
	}
	@Override
	public List<Map<String,Object>> getListMap(String sql,Object... values) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int sum = values!=null?values.length:0;
		for(int i = 0;i<sum;i++) {
			pstmt.setObject(i+1, values[i]);
		}
		ResultSet rs = pstmt.executeQuery();
		List<Map<String,Object>> lstData = DataAutobox.buildMap(rs);
		rs.close();
		pstmt.close();
		return lstData;
	}
	@Override
	public <T> List<T> getListBean(Class<T> cla,String sql,Object... values) throws Exception{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int sum = values!=null?values.length:0;
		for(int i = 0;i<sum;i++) {
			pstmt.setObject(i+1, values[i]);
		}
		ResultSet rs = pstmt.executeQuery();
		List<T> lstData = DataAutobox.buildBean(rs,cla);
		rs.close();
		pstmt.close();
		return lstData;
	}
	@Override
	public int getCount(String sql,Object... values) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int sum = values!=null?values.length:0;
		for(int i = 0;i<sum;i++) {
			pstmt.setObject(i+1, values[i]);
		}
		ResultSet rs = pstmt.executeQuery();
		int icount = 0;
		if(rs.next()) icount = rs.getInt(1);
		rs.close();
		pstmt.close();
		return icount;
	}
}
