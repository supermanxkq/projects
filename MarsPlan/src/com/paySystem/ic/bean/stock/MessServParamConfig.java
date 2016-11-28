package com.paySystem.ic.bean.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:MessServParamConfig
 * @Description:短信服务器参数配置实体类
 * @date: 2014-7-22上午11:59:12
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "B_MessServParam")
public class MessServParamConfig implements Serializable {

	private static final long serialVersionUID = -4195660940303134201L;
	/** 参数ID */
	private Integer mspId;
	/** 服务商ID（或名称） */
	private Integer serviceId;
	/** 通道类型 1为短信服务器、2为验证码服务器 */
	private Integer paramType;
	/** 账户号 */
	private String account;
	/** 口令 */
	private String password;
	/** 手机 */
	private String mobile;
	/** 短信服务器状态1为开启0为停用 */
	private Integer status;

	/** 账户号值 */
	private String accountValue;
	/** 口令值 */
	private String passwordValue;
	/** 服务URL值 */
	private String servUrlValue;
	/** 内容参数 */
	private String content;

	@Column(length = 20,nullable=true)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(length = 50,nullable=true)
	public String getAccount() {
		return account;
	}

	@Column(length = 50,nullable=true)
	public String getAccountValue() {
		return accountValue;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 8)
	public Integer getMspId() {
		return mspId;
	}

	@Column(length = 1,nullable=true)
	public Integer getParamType() {
		return paramType;
	}

	@Column(length = 20,nullable=true)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(length = 30,nullable=true)
	public String getPassword() {
		return password;
	}

	@Column(length = 50,nullable=true)
	public String getPasswordValue() {
		return passwordValue;
	}

	@Column(length = 8,nullable=true)
	public Integer getServiceId() {
		return serviceId;
	}

	@Column(length = 50,nullable=true)
	public String getServUrlValue() {
		return servUrlValue;
	}

	@Column(length = 8,nullable=true)
	public Integer getStatus() {
		return status;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAccountValue(String accountValue) {
		this.accountValue = accountValue;
	}

	public void setMspId(Integer mspId) {
		this.mspId = mspId;
	}

	public void setParamType(Integer paramType) {
		this.paramType = paramType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public void setServUrlValue(String servUrlValue) {
		this.servUrlValue = servUrlValue;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
