package org.smart4j.framework.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * @author bjtang
 * @date   2017年11月22日  
 * @desc   集合工具类
 */
public class CollectionUtil {

	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection){
		return CollectionUtils.isEmpty(collection);
	}
	
	/**
	 * 判断集合是否非空
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !CollectionUtils.isEmpty(collection);
	}
	
	/**
	 * 判断Map是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Map<?,?> map){
		return MapUtils.isEmpty(map);
	}
	
	/**
	 * 判断Map是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Map<?,?> map){
		return !MapUtils.isEmpty(map);
	}
	
}
