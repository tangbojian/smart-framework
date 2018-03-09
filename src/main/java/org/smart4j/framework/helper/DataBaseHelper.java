package org.smart4j.framework.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author bjtang
 * @date   2017年11月30日  
 * @desc   数据库操作助手类
 */
public final class DataBaseHelper {
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final BasicDataSource DATA_SOURCE;
	
	static {
		DRIVER = ConfigHelper.getDriver();
		URL = ConfigHelper.getUrl();
		USERNAME = ConfigHelper.getUsername();
		PASSWORD = ConfigHelper.getPassword();
		DATA_SOURCE = new BasicDataSource();
	}
	
	private final static ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>(){
		protected Connection initialValue() {
			return initConnection();
		};
	};
	
	public static Connection initConnection(){
		try {
			DATA_SOURCE.setDriverClassName(DRIVER);
			DATA_SOURCE.setUrl(URL);
			DATA_SOURCE.setUsername(USERNAME);
			DATA_SOURCE.setPassword(PASSWORD);
			return DATA_SOURCE.getConnection();
		} catch (SQLException e) {
			System.err.println("数据库连接失败=>" +e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 开启事物 
	 */
	public static void beginTransaction(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				System.err.println("begin transaction failure => " + e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				System.err.println("commit transaction failure => " + e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	
	/**
	 * 回滚事物
	 */
	public static void rollbackTransaction(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				System.err.println("rollback transaction failure => " + e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	
}
