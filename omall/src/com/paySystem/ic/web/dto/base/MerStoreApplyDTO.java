package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MerStoreApplyDTO
 * @Description:商户入驻申请DTO
 * @date: 2014-11-18
 * @author: 毛智东
 * @version: V1.0
 */
public class MerStoreApplyDTO extends BaseDTO implements Serializable {

	/** 序列号 **/
	private static final long serialVersionUID = 4625980661246003197L;

	/** 主键id **/
	private Integer applyId;

	/** 姓名 **/
	private String name;

	/** 身份证号 **/
	private String cardNo;

	/** 邮箱 **/
	private String email;

	/** 手机 **/
	private String phone;

	/** 审核状态：0待审核 1审核通过 2审核不通过 **/
	private Integer status;

	/** 创建时间 **/
	private Date createTime;

	/** 操作人 **/
	private String operator;

	/** 操作时间 **/
	private Date operTime;

	/** 登录密码 **/
	private String pwd;

	/** 店铺名称 **/
	private String storeName;

	/** 所在地 **/
	private String areaCode;

	/** 电话 **/
	private String telephone;

	/** qq号码 **/
	private String qq;

	/** 微信号 **/
	private String weixin;

	/** 证件类型 **/
	private Integer credentialType;

	/** 营业范围 **/
	private Integer bussScope;

	/** 用户名 **/
	private String userName;

	/** 营业执照 **/
	private String bussPermit;

	/** 商铺logo **/
	private String logo;

	/** 店铺描述 **/
	private String description;

	/** 支付密码 **/
	private String payPwd;

	/** 申请人(会员编号) **/
	private Integer memId;

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(Integer credentialType) {
		this.credentialType = credentialType;
	}

	public Integer getBussScope() {
		return bussScope;
	}

	public void setBussScope(Integer bussScope) {
		this.bussScope = bussScope;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBussPermit() {
		return bussPermit;
	}

	public void setBussPermit(String bussPermit) {
		this.bussPermit = bussPermit;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

}
