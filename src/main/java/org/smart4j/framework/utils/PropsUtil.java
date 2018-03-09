package org.smart4j.framework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @author bjtang
 * @date   2017年11月22日  
 * @desc   加载properties文件,并根据key获取value值 
 */
public class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	
	/***
	 * 将文件加载并转换为properties
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName){
		Properties properties = null;
		InputStream is = null;
		try {
			is = PropsUtil.class.getClassLoader().getResourceAsStream(fileName);
			if(is == null){
				throw new FileNotFoundException(fileName + " file is not found");
			}
			properties = new Properties();
			properties.load(is);
		} catch (IOException e) {
			LOGGER.error("load properties file failure", e);
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return properties;
	}
	
	/**
	 * 获取字符类型的value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getString(Properties properties, String key){
		return getString(properties, key, "");
	}

	public static String getString(Properties properties, String key,
			String defaultValue) {
		String value = defaultValue;
		if(properties.containsKey(key)){
			value = properties.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 获取数值类型的value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static int getInt(Properties properties, String key){
		return getInt(properties, key, 0);
	}

	public static int getInt(Properties properties, String key,
			int defaultValue) {
		int value = defaultValue;
		if(properties.containsKey(key)){
			value = CastUtil.castInt(properties.getProperty(key));
		}
		return value;
	}
	
	/**
	 * 获取布尔类型的value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties properties, String key){
		return getBoolean(properties, key, false);
	}

	private static boolean getBoolean(Properties properties, String key,
			boolean defaultValue) {
		boolean value = defaultValue;
		if(properties.containsKey(key)){
			value = CastUtil.castBoolean(properties.getProperty(key));
		}
		return value;
	}
	
}
