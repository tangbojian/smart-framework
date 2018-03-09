package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.utils.ClassUtil;

/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   类操作助手类
 */
public final class ClassHelper {

	/**
	 * 定义类集合(用于存放所加载的类)
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取应用包下的所有类
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获取应用包下所有被注解为service类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			/**如果一个指定注解存在于此类上,那么返回true,否则返回false**/
			if(clazz.isAnnotationPresent(Service.class)){
				classSet.add(clazz);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包下所有被注解为controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			/**如果一个指定注解存在于此类上,那么返回true,否则返回false**/
			if(clazz.isAnnotationPresent(Controller.class)){
				classSet.add(clazz);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包下所有的类 （包括： Service、 Controller 等）
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
	
	/**
	 * 获取应用包名下某父类 (或接口) 的所有子类 (或实现类)
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			if(superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)){
				classSet.add(clazz);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下带有某注解的类
	 * @param annotationClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			if(clazz.isAnnotationPresent(annotationClass)){
				classSet.add(clazz);
			}
		}
		return classSet;
	}
	
	
}
