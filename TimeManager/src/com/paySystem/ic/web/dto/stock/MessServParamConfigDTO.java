package com.paySystem.ic.web.dto.stock;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfigDTO
 * @Description:短信服务器参数配置数据传输类
 * @date: 2014-7-22下午02:03:25
 * @author: 徐凯强
 * @version: V1.0
 */
public class MessServParamConfigDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 9155757040671051308L;

	/** 账户号 */
	private String account;
	/** 账户号 -验证码 */
	private String account1;
	/** 账户号值 */
	private String accountValue;
	/** 账户号值 -验证码*/
	private String accountValue1;
	/** 手机 */
	private String mobile;
	/** 手机  -验证码*/
	private String mobile1;
	/** 手机值 */
	private String mobileValue;
	/** 手机值  -验证码*/
	private String mobileValue1;

	/** 参数ID */
	private Integer mspId;
	/** 验证码短信服务器配置信息 */
	/** 参数ID -验证码 */
	private Integer mspId1;
	/** 通道类型 */
	private Integer paramType;
	/** 通道类型 -验证码 */
	private Integer paramType1;

	/** 口令 */
	private String password;
	/** 口令 -验证码 */
	private String password1;
	/** 口令值 */
	private String passwordValue;
	/** 口令值 -验证码 */
	private String passwordValue1;
	/** 服务商ID（或名称） */
	private Integer serviceId;
	/** 服务商ID（或名称） -验证码 */
	private Integer serviceId1;
	/** 服务URL*/
	private String servUrl;
	/** 服务URL  -验证码*/
	private String servUrl1;

	/** 服务URL值 */
	private String servUrlValue;
	/** 服务URL值 -验证码 */
	private String servUrlValue1;
	/** 短信服务器状态1为开启0为停用 */
	private Integer status;
	/** 短信服务器状态1为开启0为停用 -验证码 */
	private Integer status1;
	/** 内容参数 */
	private String content;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccount() {
		return account;
	}

	public String getAccount1() {
		return account1;
	}

	public String getAccountValue() {
		return accountValue;
	}

	public String getAccountValue1() {
		return accountValue1;
	}

	public String getMobile() {
		return mobile;
	}

	public String getMobile1() {
		return mobile1;
	}

	public String getMobileValue() {
		return mobileValue;
	}

	public String getMobileValue1() {
		return mobileValue1;
	}

	public Integer getMspId() {
		return mspId;
	}

	public Integer getMspId1() {
		return mspId1;
	}

	public Integer getParamType() {
		return paramType;
	}

	public Integer getParamType1() {
		return paramType1;
	}

	public String getPassword() {
		return password;
	}

	public String getPassword1() {
		return password1;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public String getPasswordValue1() {
		return passwordValue1;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public Integer getServiceId1() {
		return serviceId1;
	}

	public String getServUrl() {
		return servUrl;
	}

	public String getServUrl1() {
		return servUrl1;
	}

	public String getServUrlValue() {
		return servUrlValue;
	}

	public String getServUrlValue1() {
		return servUrlValue1;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getStatus1() {
		return status1;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAccount1(String account1) {
		this.account1 = account1;
	}

	public void setAccountValue(String accountValue) {
		this.accountValue = accountValue;
	}

	public void setAccountValue1(String accountValue1) {
		this.accountValue1 = accountValue1;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public void setMobileValue(String mobileValue) {
		this.mobileValue = mobileValue;
	}

	public void setMobileValue1(String mobileValue1) {
		this.mobileValue1 = mobileValue1;
	}

	public void setMspId(Integer mspId) {
		this.mspId = mspId;
	}

	public void setMspId1(Integer mspId1) {
		this.mspId1 = mspId1;
	}

	public void setParamType(Integer paramType) {
		this.paramType = paramType;
	}

	public void setParamType1(Integer paramType1) {
		this.paramType1 = paramType1;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public void setPasswordValue1(String passwordValue1) {
		this.passwordValue1 = passwordValue1;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceId1(Integer serviceId1) {
		this.serviceId1 = serviceId1;
	}

	public void setServUrl(String servUrl) {
		this.servUrl = servUrl;
	}

	public void setServUrl1(String servUrl1) {
		this.servUrl1 = servUrl1;
	}

	public void setServUrlValue(String servUrlValue) {
		this.servUrlValue = servUrlValue;
	}

	public void setServUrlValue1(String servUrlValue1) {
		this.servUrlValue1 = servUrlValue1;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatus1(Integer status1) {
		this.status1 = status1;
	}
}
