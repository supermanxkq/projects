package com.paySystem.ic.bean.app;

import java.util.Date;

public class TransactionRecords {

	private static final long serialVersionUID = 1L;
	
	private Integer transactionId;

	/** app用户Id号  */
	private Integer orderId;
	
	/** app用户名称 */
	private String userName;
	
	/** 用户密码 */
	private String password;
	/** 身份验证 */
	private String token;

	/** 会员编号 */
	private String memId;
	
	/** 创建时间 */
	private Date createTime;
}
