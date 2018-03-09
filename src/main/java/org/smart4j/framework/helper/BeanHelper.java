package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.utils.ReflectionUtil;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   bean助手类
 */
public final class BeanHelper {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

	
	/***
	 * 定义 Bean 映射 (用于存放 Bean 类 与 Bean 实例的映射关系)
	 */
	private final static Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass : beanClassSet){
			BEAN_MAP.put(beanClass, ReflectionUtil.newInstance(beanClass));
		}
	}
	
	/**
	 * 获取 Bean 的映射 Map
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	
	/***
	 * 根据 key 获取 bean实例
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		if(!BEAN_MAP.containsKey(clazz)){
			LOGGER.error("can not get bean by class : " + clazz);
			throw new RuntimeException("can not get bean by class : " + clazz);
		}
		return (T) BEAN_MAP.get(clazz);
	}
	
	/**
	 * 设置 Bean 实例 
	 * @param clazz
	 * @param obj
	 */
	public static void setBean(Class<?> clazz, Object obj){
		BEAN_MAP.put(clazz, obj);
	}
	
	
	
}
