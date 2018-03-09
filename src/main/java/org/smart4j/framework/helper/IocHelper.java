package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.utils.CollectionUtil;
import org.smart4j.framework.utils.ReflectionUtil;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   进行依赖注入
 */
public final class IocHelper {
	
	
	static {
		/***获取所有Bean类与Bean实例之间的映射关系(简称 Bean Map)**/
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			//遍历 bean map
			for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()){
				// 从 beanMap 中获取 bean类与bean的实例
				Class<?> beanClass = entry.getKey();
				Object bean = entry.getValue();
				//获取 此 bean 中的成员变量.
				Field[] fileds = beanClass.getDeclaredFields();
				if(!ArrayUtils.isEmpty(fileds)){
					//判断当前 bean 的filed 属性中是否有 @Inject 注解
					for(Field field : fileds){
						if(field.isAnnotationPresent(Inject.class)){
							//如果有此注解,那么将对此变量进行初始化.
							Class<?> fieldClass = field.getType();
							Object fieldInstance = beanMap.get(fieldClass);
							if(fieldInstance != null){
								ReflectionUtil.setField(bean, field, fieldInstance);
							}
						}
					}
				}
			}
		}
		
	}
 
	
	
}
