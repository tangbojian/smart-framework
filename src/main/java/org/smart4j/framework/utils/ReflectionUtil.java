package org.smart4j.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   反射工具类
 */
public class ReflectionUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	
	/**
	 * 根据 class 文件 创建对象的实例
	 * @param clazz
	 * @return
	 */
	public static Object newInstance(Class<?> clazz){
		Object instance = null;
		try {
			instance = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * 调用方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, Method method, Object[] args){
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 设置成员变量的值
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj, Field field, Object value){
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field failure", e);
			throw new RuntimeException(e);
		}
	}
	
	
	
	
}
