package org.smart4j.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.HelperLoader;
import org.smart4j.framework.helper.ServletHelper;
import org.smart4j.framework.utils.CodeUtil;
import org.smart4j.framework.utils.JsonUtil;
import org.smart4j.framework.utils.ReflectionUtil;
import org.smart4j.framework.utils.StreamUtil;
import org.smart4j.framework.utils.StringUtil;


/***
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   请求转发器
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -1335381642038914492L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		//初始化相关Helper类
		HelperLoader.init();
		//获取servletContext 对象(用于注册 Servlet)
		ServletContext servletContext = config.getServletContext();
		//注册 JSP 的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			ServletHelper.init(req, resp);
			/***
			 * 获取请求方法和请求路径
			 */
			String requestMethod = req.getMethod().toLowerCase();
//		String requestPath = req.getPathInfo();
			String requestPath = req.getRequestURI();
			
			/**
			 * 获取 Action 处理器
			 */
			Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
			if(handler != null){
				// 获取 controller 及其 bean 实例
				Class<?> controllerClass = handler.getControllerClass();
				Object controllerBean = BeanHelper.getBean(controllerClass);
				// 获取请求参数对象
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Enumeration<String> paramNames = req.getParameterNames();
				while(paramNames.hasMoreElements()){
					String paramName = paramNames.nextElement();
					Object paramValue = req.getParameter(paramName);
					paramMap.put(paramName, paramValue);
				}
				String body = CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
				if(StringUtil.isNotEmpty(body)){
					String[] params = StringUtils.split(body, "&");
					if(ArrayUtils.isNotEmpty(params)){
						for(String param : params){
							String[] array = StringUtils.split(param, "=");
							if(ArrayUtils.isNotEmpty(array) && array.length == 2){
								String paramName = array[0];
								String paramValue = array[1];
								paramMap.put(paramName, paramValue);
							}
						}
					}
				}
				Param param = new Param(paramMap);
				//调用 Action 方法
				Method method = handler.getActionMethod();
				Object result = ReflectionUtil.invokeMethod(controllerBean, method, param.getObjectArray());
				//处理Action方法返回值
				if(result instanceof View){
					//返回 JSP 页面
					View view = (View) result;
					String path = view.getPath();
					if(StringUtil.isNotEmpty(path)){
						if(path.startsWith("/")){
							resp.sendRedirect(req.getServletContext() + path);
						}else{
							Map<String, Object> model = view.getModel();
							for(Map.Entry<String, Object> entry : model.entrySet()){
								req.setAttribute(entry.getKey(), entry.getValue());
							}
							req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
						}
					}
				} else if(result instanceof Data){
					// 返回 JSON 数据
					Data data = (Data) result;
					Object model = data.getModel();
					if(model != null){
						resp.setContentType("application/json");
						resp.setCharacterEncoding("UTF-8");
						PrintWriter writer = resp.getWriter();
						String json = JsonUtil.toJson(model);
						writer.write(json);
						writer.flush();
						writer.close();
					}
				}
			}
		} catch (Exception e) {
			
		} finally {
			ServletHelper.destory();
		}
	}
	
	
}
