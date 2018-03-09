package org.smart4j.framework.bean;


/**
 * @author bjtang
 * @date   2017年11月23日  
 * @desc   返回数据对象 
 */
public class Data {

	/**
	 * 模型数据
	 */
	private Object model;

	public Data(Object model) {
		super();
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
	
}
