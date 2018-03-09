package org.smart4j.framework.proxy;

/**
 * @author bjtang
 * @date   2017年11月28日  
 * @desc   代理接口
 */ 
public interface Proxy {

	/**
	 * 执行链式代理
	 * @param proxyChain
	 * @return
	 * @throws Throwable
	 */
	public Object doProxy(ProxyChain proxyChain) throws Throwable;
	
}
