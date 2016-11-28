package com.blog.util;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

/**
 * @ProjectName:omall
 * @ClassName:OmalConfig
 * @Description:调用接口信息
 * @date: 2014-11-26
 * @author: 张军磊
 * @version: V1.0
 */
public class PublicConfig {
    //IP地址
	public static String IP_ADDRESS;
	//端口号
	public static String PORT;
	
    //查询账户信息方法名
	public static String ACCOUNTQUERY_URL;
    //修改支付密码方法名
	public static String PWDCHANGEUPDATE_URL;
	 //发送短信验证码
	public static String CHECKSENDPHONECHECKNUM_URL;

	
	
	//配置文件名称
	private static final String CONF_FILE_NAME = "publicInterface.properties";
	//IP地址KEY值
	private static final String KEY_IP_ADDRESS = "ip.address";
	//端口号KEY值
	public static final String KEY_PORT = "port";
	
	//查询账户接口方法名KEY值
	private static final String KEY_ACCOUNTQUERY_URL = "account.query.url";
	//修改支付密码接口方法名KEY值
	private static final String KEY_PWDCHANGEUPDATE_URL = "pwdChange.update.url";
	//发送短信验证码
	private static final String KEY_CHECKSENDPHONECHECKNUM_URL = "check.sendPhoneCheckNum.url";

	static {

		try {
			InputStream ins = PublicConfig.class.getClassLoader()
					.getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(ins);
			/*
			 * Properties prop = new Properties(); prop.load(ins);
			 */

			/** 读取配置 */
			IP_ADDRESS = props.getString(KEY_IP_ADDRESS);//IP地址
			PORT = props.getString(KEY_PORT);//端口号
			
			ACCOUNTQUERY_URL=props.getString(KEY_ACCOUNTQUERY_URL);//查询账户接口方法名
			PWDCHANGEUPDATE_URL=props.getString(KEY_PWDCHANGEUPDATE_URL);//修改支付密码调用接口方法名
			CHECKSENDPHONECHECKNUM_URL=props.getString(KEY_CHECKSENDPHONECHECKNUM_URL);//发送短信验证码
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
