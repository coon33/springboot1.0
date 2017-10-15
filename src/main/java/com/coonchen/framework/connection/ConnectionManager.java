package com.coonchen.framework.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.coonchen.framework.utils.SpringUtil;

/**
* @ClassName: ConnectionManager 
* @Description: database connecton tool class
* @author cw
* @date 2017年8月3日 上午8:59:04 
 */
public class ConnectionManager {
	public static Connection getThreadConnection(){
		//Object obj = SpringUtil.getBean("spring.datasource");
		DataSource obj = SpringUtil.getBean(DataSource.class);
		if(obj==null)throw new RuntimeException("can't find dataSource.");
		ConnectionHolder sh = (ConnectionHolder) TransactionSynchronizationManager.getResource(obj);
		if(sh==null)throw new RuntimeException("this request is fail to get connection,check it.");
		return sh.getConnection();
	}
	
	public static Connection getNewConnection() throws SQLException {
		DataSource obj = SpringUtil.getBean(DataSource.class);
		if(obj==null)throw new RuntimeException("can't find dataSource.");
		return obj.getConnection();
	}
}
