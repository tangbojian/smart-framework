package org.smart4j.framework.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author bjtang
 * @date   2017年11月30日  
 * @desc   servlet助手类
 */
@SuppressWarnings({"unchecked","unused"})
public class ServletHelper {

	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public static void init(HttpServletRequest request, HttpServletResponse response){
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
	}
	
	public static void destory(){
		SERVLET_HELPER_HOLDER.remove();
	}
	
	private static HttpServletRequest getRequest(){
		return SERVLET_HELPER_HOLDER.get().request;
	}
	
	private static HttpServletResponse getResponse(){
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	private static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	private static ServletContext getserServletContext(){
		return getRequest().getServletContext();
	}
	
	public static <T> void setRequestAttribute(String key, T value){
		getRequest().setAttribute(key, value);
	}
	
	public static <T> T getRequestAttribute(String key){
		return (T) getRequest().getAttribute(key);
	}
	
	public static void removeRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	public static <T> void setSessionAttribute(String key, T value){
		getSession().setAttribute(key, value);
	}
	
	public static <T> T getSessionAttribute(String key){
		return (T) getSession().getAttribute(key);
	}
	
	public static void removeSessionAttribute(String key){
		getSession().removeAttribute(key);
	}
	
	public static void invalidateSession(){
		getRequest().getSession().invalidate();
	}
	
}
