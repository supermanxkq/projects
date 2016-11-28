package com.lucene.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  实体反射工具类
 * 
 * @ClassName:ReflectObject
 * @Description:实体放射工具类
 * @date: 2014-10-24上午11:35:41
 * @author: 谢洪飞
 * @version: V1.0
 */
public class ReflectObject {
	
	
	
	/**
	 *   获取对象的属性名称及属性值
	 * 
	 *@Title:reflectFromObject
	 *@Description:获取对象的属性名称及属性值
	 *@param:@param obj 要操作的实体对象
	 *@param:@return
	 *@param:@throws Exception
	 *@return:Map<String,Object> key/value Map
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public static Map<String , Object> reflectFromObject(Object obj) throws Exception{
		
		
		Map<String , Object> filedsValMap = new HashMap<String , Object>();
		
		String [] filedsArr = getFiledName(obj);
		//遍历数组
		for( String filed : filedsArr){
			if ( null != getFieldValueByName(filed,obj) ) {
				filedsValMap.put(filed, getFieldValueByName(filed,obj));				
			}
		}
		
		return filedsValMap;
	}	

	/**
	 *   获取声明的属性名称
	 * 
	 *@Title:getFiledName
	 *@Description:获取指定对象声明的属性名称
	 *@param:@param o   要获取的对象
	 *@param:@return
	 *@return:String[]  属性名称数组
	 *@author:  谢洪飞
	 *@Thorws:
	 */
	private static String[] getFiledName(Object o) {  
		
		Field[] fields = o.getClass().getDeclaredFields();  
		
		String[] fieldNames = new String[fields.length];    
		
		for (int i=0; i < fields.length; i++)
		{   
		    fieldNames[i] = fields[i].getName();    
		}    
		return fieldNames;   
	}    
	

	/**
	 *   使用反射根据属性名称获取属性值
	 *   
	 *@Title:getFieldValueByName
	 *@Description:获取属性值
	 *@param:@param fieldName
	 *@param:@param o 要操作的对象
	 *@param:@return
	 *@param:@throws Exception
	 *@return:Object
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	private static Object getFieldValueByName(String fieldName, Object o) throws Exception{     
		
		   Object value = null;
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();      
	       String getter = "get" + firstLetter + fieldName.substring(1); 
	       //获取所有定义的方法
	       Method[] methods = o.getClass().getDeclaredMethods();
	       for (int i = 0 ; i < methods.length ; i ++) {
	    	   if (getter == methods[i].getName() || getter.equals(methods[i].getName())) 
	    	    {
	    	       Method method = o.getClass().getMethod(getter, new Class[] {});   
	    	    	   value = method.invoke(o, new Object[] {});  
	    	    }
	       }
	       return value;      
	}    
	
	
	@SuppressWarnings({"unchecked" })
	public static Object TconvertMap(Class type,Map map)throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性 
        Object obj = type.newInstance(); // 创建 JavaBean 对象 
 
        // 给 JavaBean 对象的属性赋值 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
 
            try {
				if (map.containsKey(propertyName)) { 
				    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。 
				    Object value = map.get(propertyName); 
				    Object[] args = new Object[1]; 
				    args[0] = value; 
 
				    descriptor.getWriteMethod().invoke(obj, args); 
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
        } 
        return obj; 
	}
}
