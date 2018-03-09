package org.smart4j.framework.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   用于编码和解码操作
 */
public class CodeUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);
	
	/**
	 * 将 URL 编码
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source){
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * 将 URL 解码
	 */
	public static String decodeURL(String source){
		String target;
		try {
			target = URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
}
