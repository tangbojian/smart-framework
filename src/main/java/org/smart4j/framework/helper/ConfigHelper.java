package org.smart4j.framework.helper;

import java.util.Properties;

import org.smart4j.framework.constant.ConfigConstant;
import org.smart4j.framework.utils.PropsUtil;

/**
 * @author bjtang
 * @date   2017年11月22日  
 * @desc   属性文件助手
 */
public final class ConfigHelper {

	private final static Properties PROPERTIES = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	/**
	 * 获取数据库驱动
	 * @return
	 */
	public static String getDriver(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.JDBC_DRIVER);
	}
	
	/***
	 * 获取 URL
	 * @return
	 */
	public static String getUrl(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.JDBC_URL);
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	public static String getUsername(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * 获取密码
	 * @return
	 */
	public static String getPassword(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * 获取应用基础包
	 * @return
	 */
	public static String getAppBasePackage(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/**
	 * 获取应用JSP路径
	 * @return
	 */
	public static String getAppJspPath(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view");
	}
	
	/**
	 * 获取应用静态资源路径
	 * @return
	 */
	public static String getAppAssetPath(){
		return PropsUtil.getString(PROPERTIES, ConfigConstant.APP_ASSET_PATH, "/asset/");
	}
	
}
