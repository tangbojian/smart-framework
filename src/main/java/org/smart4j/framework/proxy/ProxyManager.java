package org.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author bjtang
 * @date   2017年11月28日  
 * @desc   代理管理器(用来创建所有的代理对象)
 */
public class ProxyManager {

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			
			public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams,
					MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
				
			}
		});
	}
	
}
