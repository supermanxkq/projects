package com.paySystem.ic.constants;

/**  
* @Title: ErrorMsg.java
* @Package: com.paySystem.ic.constants
* @Description: 错误信息综合管理类
* @Author: Jacky
* @Date: 2014-08-01
* @Version: V1.0  
*/
public class ErrorMsg {
	
	/**
	 * 商品品牌名称已经存在
	 */
	public static final String RECORD_EXISTED = "商品品牌名称已经存在";
	
	/**
	 * 商品品牌名称不能为空
	 */
	public static final String BRAND_NAME_NEEDED = "商品品牌名称不能为空";
	
	/**
	 * 商品品牌名称不能超过60个字
	 */
	public static final String BRAND_NAME_TOO_LONG = "商品品牌名称不能超过60个字";
	
	/**
	 * 品牌网址长度不能超过100个字符
	 */
	public static final String BRAND_URL_TOO_LONG = "品牌网址长度不能超过100个字符";
	
	/**
	 * 品牌描述长度不能超过255个字
	 */
	public static final String BRAND_DESC_TOO_LONG = "品牌描述长度不能超过255个字";
	
	/**
	 * 参数有误
	 */
	public static final String PARAM_ERROR = "参数有误";
	
	/**
	 * 无权新增
	 */
	public static final String AUTH_FAILED = "无权新增";
	
	/**
	 * 上传文件过大
	 */
	public static final String FILE_EXECEDE = "上传文件过大";
	
}
