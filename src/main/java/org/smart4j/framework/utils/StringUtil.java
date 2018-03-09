package org.smart4j.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bjtang
 * @date   2017年11月22日  
 * @desc   字符串工具类
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空.在原有类的基础上进行了再一次的封装
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 判断是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
}
