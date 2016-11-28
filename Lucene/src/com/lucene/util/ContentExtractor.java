package com.lucene.util;

/**
 *  从Html中抽取纯文本内容
 * 
 * @ClassName:ContentExtractor
 * @Description:从Html中抽取纯文本内容
 * @date: 2014-10-24下午12:08:44
 * @author: 谢洪飞
 * @version: V1.0
 */
public class ContentExtractor{
	
	public static String extract(String v){
		String v1 = v.replaceAll("<(.)*>", "");
		String v2 = v1.replaceAll("[\\s]+", " ");
		return v2.trim().replace(" ", "").replace("&nbsp;", "");
	}

}