package com.supermanxkq.util;

/**
 * 字符串工具类
 * @author bolt
 *
 */
public class StringUtil {
	/**
	 * 判断字符串空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		return false;
	}
}
