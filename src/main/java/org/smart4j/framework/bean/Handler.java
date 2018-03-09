package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/***
 * @author bjtang
 * @date 2017年11月23日
 * @desc 封装Action信息,就是Action和Class的对应(n : 1)
 */
public class Handler {

	/**
	 * Controller 类
	 */
	private Class<?> controllerClass;

	/***
	 * Action 方法
	 */
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		super();
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

}
