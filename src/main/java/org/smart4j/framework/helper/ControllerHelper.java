package org.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.utils.CollectionUtil;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   控制器助手类
 */
public class ControllerHelper {

	/**
	 * 用于存放请求与处理器的映射关系(简称 Action Map)
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static {
		// 获取所有的 Controller 类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			for(Class<?> clazz : controllerClassSet){
				Method[] methods = clazz.getDeclaredMethods();
				if(ArrayUtils.isNotEmpty(methods)){
					for(Method method : methods){
						if(method.isAnnotationPresent(Action.class)){
							Action action = method.getAnnotation(Action.class);
							//String mapping = action.value();
							String requestMethod = action.type();
							String requestPath = action.path();
							Request request = new Request(requestMethod, requestPath);
							Handler handler = new Handler(clazz, method);
							ACTION_MAP.put(request, handler);
							/*if(mapping.matches("\\w+:/\\w*")){
								String[] array = mapping.split(":");
								if(ArrayUtils.isNotEmpty(array) && array.length == 2){
									//获取请求方法与请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(clazz, method);
									ACTION_MAP.put(request, handler);
								}
							}*/
						}
					}
				}
			}
		}
	}
	
	/**
	 * 根据请求路径和请求方法得到处理器
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
	
}
