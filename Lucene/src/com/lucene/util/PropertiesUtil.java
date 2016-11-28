package com.lucene.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * properties 文件操作工具类
 * 
 * @ClassName:PropertiesUtil
 * @Description:TODO
 * @date: 2014-10-23下午04:47:22
 * @author: 谢洪飞
 * @version: V1.0
 */
public class PropertiesUtil {

	String contextPath = this.getClass().getClassLoader().getResource("/")
			.getPath();

	
	
	/**
	 *  获取Properties文件
	 * 
	 *@Title:getProperties
	 *@Description:   获取名为file的Properties文件
	 *@param:@param   file 文件名称
	 *@param:@return  properties对象
	 *@return:Properties prop
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public static Properties getProperties(String file) {

		Properties prop = null;

		FileInputStream fis = null;

		try {
			  fis = new FileInputStream(new File(file));
			  prop = new Properties();
			  prop.load(fis);
		    }
		catch (Exception e)
		    {
			  e.printStackTrace();
		    }
		finally
		    {//最后关闭流
			  if (fis != null)
			  {
				 try 
				 {
				 	 fis.close();
				 }
			   catch (IOException e)
			   {
					e.printStackTrace();
				}
			  }
		    }

		return prop;
	}

	/**
	 *  向Properties文件中新增内容(追加)
	 *  
	 *@Title:addPropValue
	 *@Description:       追加Properties文件内容
	 *@param:@param key   key值
	 *@param:@param value value值
	 *@param:@param file  文件名
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public static void addPropValue( String key [] , String value [] , String file){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		//获取Properties文件
		Properties prop = getProperties(file);
		FileOutputStream fos = null;
		try
		{
			prop.put(key, value);
			fos = new FileOutputStream ( file , true);
			//增加注释内容
            prop.store(fos, "Modify prop # Date:"+sdf.format(new Date()));
		}
	   catch (Exception e)
	   {
			e.printStackTrace();
		}
	   finally
	   {
		   if(null != fos)
		   {
			 try 
			 {
				fos.close();
			 }
			 catch (IOException e)
			 {
				e.printStackTrace();
			 }   
		   }
	   }
		
	}
	
	/**
	 *  向 Properties 文件中增加内容
	 * 
	 *@Title:saveProperties
	 *@Description: 向Properties文件中增加内容
	 *@param:@param prop 要操作的Properties对象
	 *@param:@param file 文件名
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public static void saveProperties ( Properties prop , String file ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		
		if(null == prop){
		   return;	
		}
		
		FileOutputStream fos = null;
		
		  try
		   {
			  fos = new FileOutputStream(new File(file), false);
			  prop.store(fos, "modify prop # Date:"+sdf.format(new Date()));
		   }
		 catch (Exception e)
		   {
			 e.printStackTrace();
		   }
		 finally
		   {
			  if(null != fos)
			  {
				 try
				 {
					fos.close();
				 }
				catch (IOException e)
				{
					e.printStackTrace();
				}  
			  }
		   }
	}
	
	/**
	 *  更新 Properties 文件内容 
	 * 
	 *@Title:updateProperties
	 *@Description:更新 Properties 中的内容
	 *@param:@param key    key值
	 *@param:@param value  value值
	 *@param:@param file   文件名称
	 *@Return:void
	 *@author:   谢洪飞
	 *@Thorws:
	 */
	public static void updateProperties( String key , String value , String file ){
		
		if( null == key || "".equalsIgnoreCase(key) )
		{
		    	return ;
		}
		
		Properties prop = getProperties(file);
		
		if(null == prop)
		{
			prop = new Properties();
		}
		
		prop.put(key, value);
		//保存到properties文件中
		saveProperties(prop, file);
	
	}
	
	/**
	 * 获取系统路径
	 * 
	 *@Title:path
	 *@Description:获取系统路径
	 *@param:@return 系统路径
	 *@param:@throws UnsupportedEncodingException
	 *@return:String
	 *@author:谢洪飞
	 *@Thorws:
	 */
	public static String path() throws UnsupportedEncodingException {

		PropertiesUtil prop = new PropertiesUtil();
		return URLDecoder.decode(prop.contextPath, "UTF-8");
	}

}
