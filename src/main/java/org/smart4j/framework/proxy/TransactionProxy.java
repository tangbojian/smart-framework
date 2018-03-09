package org.smart4j.framework.proxy;

import java.lang.reflect.Method;

import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DataBaseHelper;

/**
 * @author bjtang
 * @date   2017年11月30日  
 * @desc   事物代理
 */
public class TransactionProxy implements Proxy {
	
	/**
	 * 保证同一个线程中事物的相关控制只执行一次(如果没有的话,在事物方法的相互调用中,就会多次执行事物逻辑)
	 */
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		protected Boolean initialValue() {
			return false;
		};
	};

	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)){
			FLAG_HOLDER.set(true);
			try {
				DataBaseHelper.beginTransaction();
				System.err.println("开启事物");
				result = proxyChain.doProxyChain();
				DataBaseHelper.commitTransaction();
				System.out.println("提交事务");
			} catch (Exception e) {
				DataBaseHelper.rollbackTransaction();
				System.err.println("回滚事物");
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
			
		} else {
			result = proxyChain.doProxyChain();
		}
		return result;
	}

}
