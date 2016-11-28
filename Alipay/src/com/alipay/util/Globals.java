package com.alipay.util;

public class Globals {
	/** 每页显示记录数 */
	public static final int PAGE_NUM = 20;
	/** 是否验证权限*/
	public static final boolean IS_VERIFY = true;
	/** 是否正式环境*/
	/** 是否验证UKEY*/
	/** 是否发送短信*/
	
	
	
	public static final String USER_SESSION = "user_session";
	public static final String PAGE_TITLE = "title";
	public static final String USER_PRIVILEGES = "user_privileges";
	public static final String FATHER_MODULES = "father_modules";
	public static final String CHILD_MODULES_MAP = "child_modules_map";
	public static final String QUERY_PARM = "query_parm";

	public static final String TITLE = "半仙儿的网站";
	/** 初始化密码 */
	public static final String PASSWORD = "123456";
	/** 是否初始化模块权限 */
	/** 是否清除模块权限 */
	public static final Boolean ISREMOVE_MODULE_PRIVILEGE = true;
	/** 是否创建用户 */
	public static final Boolean ISCREATE_ADMIN_ROLE_USER = true;
	/** 初始化密钥 */
	public static final String ISINIT_SIGN = "TYT57GFR68IJGT8EOMFMU8WR87326GFS2015XUKAIQIANG";
	/** 初始化用户名 */
	public static final String USER_NAME = "administrator";
	/** 初始化系统管理员KEY */
	public static final String USER_KEYID = "406F6E72F7BC91FC";
	/** 初始化角色编号 */
	public static final String ROLE_CODE = "0001";
	/** 初始化角色名称 */
	public static final String ROLE_NAME = "系统管理员";
	/** 初始化角色编号 */
	public static final String MER_ROLE_CODE = "0002";
	/** 初始化角色名称 */
	public static final String MER_ROLE_NAME = "商户管理员";
	
	/** 积分账户类型编号 */
	public static final Integer POINTS_ACCKINDID= 1;
	/** 积分账户类型名称 */
	public static final String POINTS_ACCKINDNAME = "积分账户";
	
	/** 交易类的KEYID */
	public static final String ENCRYPT_KEYID_TRANSA = "9cb010d54609d403";
	/** 代理商登陆的KEYID */
	public static final String ENCRYPT_KEYID_DLS = "408e9af0fa6413fc";
	/** 编辑 */
	public static final String IMG_EDIT = "<img src='images/edit.gif'/>";
	/**查看 */
	public static final String IMG_CHECK="<img src='images/fd.jpg'>";
	/** 删除 */
	public static final String IMG_DELETE = "<img src='images/delete.gif'/>";
	/** 搜索 */
	public static final String IMG_VIEW = "<img src='images/search.gif'/>";
	/** 确认 */
	public static final String IMG_AUDIT = "<img src='images/ok.gif'/>";
	/** 刷新图片*/
	public static final String IMG_FLUSH = "<img src='images/flush.gif'/>";
	/** 禁用图片*/
	public static final String IMG_FORBID= "<img src='images/forbid.gif'/>";
	/** 读卡序列号密钥 */
	public static final String READ_CARDSERINO_KEY = "ReadCardSerialKey";
	/** 写卡密钥 */
	public static final String READ_CARDNO_KEY = "ReadCardNoKey";
	/** 读卡密钥 */
	public static final String WRITE_CARDNO_KEY = "WriteCardNoKey";
	/** M1卡密钥 */
	public static final String M1CARD_KEY = "298a82b69c72a73c8d9e8f6a6a49d730";
	/** 图片上传地址 */
	public static final String UPLOAD_URL = "upload\\images\\";
	/** .net通信DES密钥 */
	public static final String DES_KEY = "";
	
	/** 银联在线支付商户号 */
	public static final String CHINAPAY_MERID = "808080071788562";
	/** 银联在线支付商户密钥 */
	public static final String CHINAPAY_KEYPATH = "D:\\MerPrK_808080071788562_20130717172555.key";
	/** 银联在线支付公钥 */
	public static final String CHINAPAY_PUB_KEYPATH = "D:\\PgPubk.key";

	public static final String CMBC_PARTNERID = "1002201305272380";
	
	public static final String CMBC_SERVICE = "create_direct_pay_by_user";
	
	public static final String CMBC_SERVICE_EMAIL = "cmbc188@163.com";
	
	public static final String CMBC_KEY = "d1738053d38616dc27d72a2642a1c0bb";
}