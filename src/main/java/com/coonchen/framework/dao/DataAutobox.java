package com.coonchen.framework.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


/**
* @ClassName: DataAutobox 
* @Description: 
* @author cw
* @date 2017年8月4日 上午10:04:48 
 */
public class DataAutobox {
	public static List<Map<String,Object>> buildMap(ResultSet rs) throws SQLException{
		List<Map<String,Object>> lstData = new ArrayList<>();
		if(rs==null) return lstData;
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			int iColumnCount = rsmd.getColumnCount();
			Map<String,Object> mapData = new LinkedHashMap<>();
			for(int i = 1;i<=iColumnCount;i++) {
				String strColumnLabel = rsmd.getColumnLabel(i);
				Object obj = rs.getObject(strColumnLabel);
				if (obj != null) {
					mapData.put(strColumnLabel, obj);
				}
			}
			lstData.add(mapData);
		}
		return lstData;
	}
	
	public static <T> List<T> buildBean(ResultSet rs,Class<T> cla) throws Exception{
		List<T> lstData = new ArrayList<>();
		if(rs==null) return lstData;
		ResultSetMetaData rsmd = rs.getMetaData();
		Field[] files = cla.getDeclaredFields();
		while (rs.next()) {
			T bean = (T) cla.newInstance();
			int iColumnCount = rsmd.getColumnCount();
			for(int i = 1;i<=iColumnCount;i++) {
				String strColumnLabel = rsmd.getColumnLabel(i);
				Object columnValue = rs.getObject(strColumnLabel);
				if (columnValue != null) {
					for(Field field : files) {
						String columnName = field.getName();
						if(strColumnLabel.equalsIgnoreCase(columnName)) {
							BeanUtils.setProperty(bean, columnName, columnValue);
							break;
						}
					}
				}
			}
			lstData.add(bean);
		}
		return lstData;
	}
}
