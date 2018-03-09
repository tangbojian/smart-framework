package org.smart4j.framework.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   流操作工作类
 */
public class StreamUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 从输入流中获取字符串
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is){
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (Exception e) {
			LOGGER.error("get String failure", e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
}
