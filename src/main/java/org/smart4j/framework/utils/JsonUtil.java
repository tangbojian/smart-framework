package org.smart4j.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   json 工具类
 */
public class JsonUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 将对象转换为json字符串
	 * @param model
	 * @return
	 */
	public static <T> String toJson(T model){
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			LOGGER.error("convert POJO to JSON failure", e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	/**
	 * 将 json 转换为 pojo
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type){
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("convert JSON to POJO failure", e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
	
}
