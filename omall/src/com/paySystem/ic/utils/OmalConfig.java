package com.paySystem.ic.utils;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

/**
 * 银联接口参数信息
 * 
 * @ClassName:UpmpConfig
 * @Description:定义及初始银联接口参数信息
 * @date: 2014-10-10下午12:11:06
 * @author: 谢洪飞
 * @version: V1.0
 */
public class OmalConfig {

	public static String TRADE_URL;

	public static String QUERY_URL;

	public static String PAYMENT_URL;

	public static String SECURITY_KEY;

	public static String RECEIPT_URL;

	// 生成支付卡号url
	public static String MEMREGISTER_URL;

	private static final String KEY_TRADE_URL = "upmp.trade.url";
	private static final String KEY_QUERY_URL = "upmp.query.url";

	public static final String KEY_PAYMENT_URL = "upmp.payment.url";

	private static final String KEY_SECURITY_KEY = "security.key";

	private static final String KEY_RECEIPT_URL = "upmp.receipt.url";

	// IP地址
	public static String IP_ADDRESS;
	// 端口号
	public static String PORT;
	// 项目路径
	public static String PROJECT_URL;
	// 查询账户信息方法名
	public static String QUERYACCOUNT_METHOD;
	// 修改支付密码方法名
	public static String UPDATEPWDCHANGE_METHOD;

	// 去人收货的方法
	public static String CONFIRMRECEIPT_METHOD;
	// 去人收货的方法
	public static String OMALLPAY_METHOD;
	// 生成支付卡号方法
	public static String MEMREGISTER_METHOD;

	// 去人收货的方法
	public static String KEY_CONFIRMRECEIPT_METHOD = "confirmReceipt.method";
	// 去人收货的方法
	public static String KEY_OMALLPAY_METHOD = "omallPay.method";

	// 配置文件名称
	private static final String CONF_FILE_NAME = "omal.properties";
	// IP地址KEY值
	private static final String KEY_IP_ADDRESS = "ip.address";
	// 端口号KEY值
	public static final String KEY_PORT = "port";
	// 项目路径KEY值
	public static final String KEY_PROJECT_URL = "project.url";
	// 查询账户接口方法名KEY值
	private static final String KEY_QUERYACCOUNT_METHOD = "account.query.method";
	// 修改支付密码接口方法名KEY值
	private static final String KEY_UPDATEPWDCHANGE_METHOD = "account.query.method";
	// 会员生成支付卡号方法名KEY值
	private static final String KEY_MEMREGISTER_METHOD = "memRegister.method";

	static {

		try {
			InputStream ins = OmalConfig.class.getClassLoader()
					.getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(ins);
			/*
			 * Properties prop = new Properties(); prop.load(ins);
			 */

			/** 读取配置 */
			TRADE_URL = props.getString(KEY_TRADE_URL);
			QUERY_URL = props.getString(KEY_QUERY_URL);
			SECURITY_KEY = props.getString(KEY_SECURITY_KEY);
			PAYMENT_URL = props.getString(KEY_PAYMENT_URL);
			RECEIPT_URL = props.getString(KEY_RECEIPT_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {

		try {
			InputStream ins = OmalConfig.class.getClassLoader()
					.getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(ins);
			/*
			 * Properties prop = new Properties(); prop.load(ins);
			 */
			/** 读取配置 */
			IP_ADDRESS = props.getString(KEY_IP_ADDRESS);// IP地址
			PORT = props.getString(KEY_PORT);// 端口号
			PROJECT_URL = props.getString(KEY_PROJECT_URL);// 项目名称
			QUERYACCOUNT_METHOD = props.getString(KEY_QUERYACCOUNT_METHOD);// 查询账户接口方法名
			UPDATEPWDCHANGE_METHOD = props
					.getString(KEY_UPDATEPWDCHANGE_METHOD);// 修改支付密码调用接口方法名
			CONFIRMRECEIPT_METHOD = props.getString(KEY_CONFIRMRECEIPT_METHOD);// 确认收货的方法
			OMALLPAY_METHOD = props.getString(KEY_OMALLPAY_METHOD);// 确认收货的方法
			MEMREGISTER_METHOD = props.getString(KEY_MEMREGISTER_METHOD);// 生成支付卡号方法
			MEMREGISTER_URL = IP_ADDRESS + PORT + PROJECT_URL
					+ MEMREGISTER_METHOD;// 生成支付卡号url
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
