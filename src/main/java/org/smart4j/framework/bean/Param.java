package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.framework.utils.CastUtil;
import org.smart4j.framework.utils.CollectionUtil;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   请求参数对象
 */
public class Param {

	private Map<String, Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		super();
		this.paramMap = paramMap;
	}
	
	/**
	 * 根绝参数名获取 long 类型值
	 * @param name
	 * @return
	 */
	public long getLong (String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * 获取所有字段信息
	 * @return
	 */
	public Map<String, Object> getMap(){
		return paramMap;
	}
	
	public Object[] getObjectArray(){
		Object[] arr = null;
		if(CollectionUtil.isNotEmpty(paramMap)){
			arr = new Object[paramMap.size()]; 
			int i = 0;
			for(Map.Entry<String, Object> entry : paramMap.entrySet()){
				arr[i++] = entry.getValue();
			}
		}
		return arr;
	}
	
}
