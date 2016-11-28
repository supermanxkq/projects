package com.paySystem.ic.utils;

public class Globals {
	/** 每页显示记录数 */
	public static final int PAGE_NUM = 20;
	/** 是否验证权限*/
	public static final boolean IS_VERIFY = true;
	/** 是否正式环境*/
	public static final boolean IS_FORMAL = Boolean.valueOf(ReadInit.read("IS_FORMAL"));
	/** 是否验证UKEY*/
	public static final boolean IS_VERIFY_UKEY = Boolean.valueOf(ReadInit.read("IS_VERIFY_UKEY"));
	/** 是否发送短信*/
	public static final boolean IS_SEND_MSG = Boolean.valueOf(ReadInit.read("IS_SEND_MSG"));
	
	public static final String USER_SESSION = "user_session";
	public static final String PAGE_TITLE = "title";
	public static final String USER_PRIVILEGES = "user_privileges";
	public static final String FATHER_MODULES = "father_modules";
	public static final String CHILD_MODULES_MAP = "child_modules_map";
	public static final String QUERY_PARM = "query_parm";

	public static final String TITLE = "徐强零件管理系统";
	/** 初始化密码 */
	public static final String PASSWORD = "123456";
	/** 是否初始化模块权限 */
	public static final Boolean ISINIT_MODULE_PRIVILEGE = Boolean.valueOf(ReadInit.read("IS_INIT"));
	/** 是否清除模块权限 */
	public static final Boolean ISREMOVE_MODULE_PRIVILEGE = true;
	/** 是否创建用户 */
	public static final Boolean ISCREATE_ADMIN_ROLE_USER = true;
	/** 初始化密钥 */
	public static final String ISINIT_SIGN = "TYT57GFR68IJGT8EOMFMU8WR87326GFS";
	/** 初始化用户名 */
	public static final String USER_NAME = "administrator";
	/** 初始化系统管理员KEY */
	public static final String USER_KEYID = "406F6E72F7BC91FC";
	/** 初始化角色编号 */
	public static final String ROLE_CODE = "0001";
	/** 初始化角色名称 */
	public static final String ROLE_NAME = "系统管理员";
	/** 总发卡机构编号 */
	public static final String MAIN_ORGANID = "00000001";
	/** 总发卡机构名称 */
	public static final String MAIN_ORGANNAME = "平台机构";
	/** 总发卡机构编号 */
	public static final String MAIN_ORGANID_1 = "00000003";
	/** 卡厂机构编号 */
	public static final String FACTORY_ORGANID = "99999999";
	/** 卡厂机构名称 */
	public static final String FACTORY_ORGANNAME = "卡厂";
	
	/** 现金账户类型编号 */
	public static final Integer CASH_ACCKINDID = 0;
	/** 现金账户类型名称 */
	public static final String CASH_ACCKINDNAME = "油币账户";
	
	/** 一般账户类型编号 */
	public static final Integer SINGLE_ACCKINDID  = 1;
	/** 一般账户类型名称 */
	public static final String SINGLE_ACCKINDNAME = "一般账户";
	
	/** 积分账户类型编号 */
	public static final Integer POINTS_ACCKINDID= 2;
	/** 积分账户类型名称 */
	public static final String POINTS_ACCKINDNAME = "积分账户";
	
	/** 保证金账户类型编号 */
	public static final Integer BAILS_ACCKINDID= 3;
	/** 保证金账户类型名称 */
	public static final String BAILS_ACCKINDNAME = "保证金账户";
	

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
	/**打印*/
	public static final String IMG_PRINT = "<img src='images/print.png'/>";
	public static final String IMG_SHOPPING = "<img src='images/shopping.png'/>";
	public static final String IMG_PAY = "<img src='images/pay.png'/>";
	/** 确认 */
	public static final String IMG_AUDIT = "<img src='images/ok.gif'/>";
	/** 低优先级 */
	public static final String IMG_LOW = "<img src='images/low.png'/>";
	/** 中优先级*/
	public static final String IMG_MIDDLE = "<img src='images/middle.png'/>";
	/** 高优先级*/
	public static final String IMG_HIGH = "<img src='images/high.png'/>";
	/** 刷新图片*/
	public static final String IMG_FLUSH = "<img src='images/flush.gif'/>";
	/** 读卡序列号密钥 */
	public static final String READ_CARDSERINO_KEY = "ReadCardSerialKey";
	/** 写卡密钥 */
	public static final String READ_CARDNO_KEY = "ReadCardNoKey";
	/** 读卡密钥 */
	public static final String WRITE_CARDNO_KEY = "WriteCardNoKey";
	/** M1卡密钥 */
	public static final String M1CARD_KEY = "298a82b69c72a73c8d9e8f6a6a49d730";
	/** 图片上传地址 */
	public static final String UPLOAD_URL = IS_FORMAL?"http://www.redpass.com.cn:8000/img/":"http://www.redpass.com.cn:9999/img/";
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
