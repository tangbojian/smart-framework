package org.smart4j.framework.helper;

import org.smart4j.framework.utils.ClassUtil;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   加载相应的 Helper 类
 */
public class HelperLoader {

	public static void init(){
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		
		for(Class<?> clazz : classList){
			ClassUtil.loadClass(clazz.getName(), true);
		}
	}
	
//	public static void main(String[] args) throws ClassNotFoundException {
//		/*System.out.println(ClassHelper.class.getName());
//		Class<?> class1 = Class.forName(ClassHelper.class.getName());
//		System.out.println(class1);*/
//		
//		Class<?> forName = Class.forName("org.smart4j.framework.helper.ClassHelper");
//		System.out.println(forName);
//	}
	
}
