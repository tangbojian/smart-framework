package org.smart4j.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bjtang
 * @date   2017年11月22日  
 * @desc   转型操作工作类
 */
public class CastUtil {

	/**
	 * 转换为String类型
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj){
		return CastUtil.castString(obj, "");
	}
	
	public static String castString(Object obj, String defaultValue){
		return obj == null ? defaultValue : String.valueOf(obj);
	}
	
	/**
	 * 转换为Double类型
	 * @param obj
	 * @return
	 */
	public static double castDouble(Object obj){
		return CastUtil.castDouble(obj, 0);
	}
	
	public static double castDouble(Object obj, double defaultValue){
		double value = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)){
				try {
					value = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}
	
	/**
	 * 转换为Long类型
	 * @param obj
	 * @return
	 */
	public static long castLong(Object obj){
		return CastUtil.castLong(obj, 0L);
	}
	
	public static long castLong(Object obj, long defaultValue){
		long value = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)){
				try {
					value = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}
	
	/**
	 * 转换为int类型
	 * @param obj
	 * @return
	 */
	public static int castInt(Object obj){
		return CastUtil.castInt(obj, 0);
	}
	
	public static int castInt(Object obj, int defaultValue){
		int value = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtils.isNotEmpty(strValue)){
				try {
					value = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}
	
	/**
	 * 转换为boolean类型
	 * @param obj
	 * @return
	 */
	public static boolean castBoolean(Object obj){
		return CastUtil.castBoolean(obj, false);
	}
	
	public static boolean castBoolean(Object obj, boolean defaultValue){
		boolean value = defaultValue;
		if(obj != null){
			value = Boolean.parseBoolean(castString(obj));
		}
		return value;
	}
	
	
	
}
