package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName:omall
 * @ClassName:MerStoreApply
 * @Description:商户入驻申请
 * @date: 2014-11-18
 * @author: 毛智东
 * @version: V1.0
 */
@Entity
@Table(name = "b_merStoreApply")
public class MerStoreApply implements Serializable {

	/** 序列号 **/
	private static final long serialVersionUID = 7014938695227729190L;

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

	/** 申请人(会员编号) **/
	private Integer memId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	@Column(length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(length = 60)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 15)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length = 15)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(length = 50)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(length = 30)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(length = 10)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(length = 13)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length = 15)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(length = 30)
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(Integer credentialType) {
		this.credentialType = credentialType;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getBussScope() {
		return bussScope;
	}

	public void setBussScope(Integer bussScope) {
		this.bussScope = bussScope;
	}

	@Column(length = 30)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length = 200)
	public String getBussPermit() {
		return bussPermit;
	}

	public void setBussPermit(String bussPermit) {
		this.bussPermit = bussPermit;
	}

	@Column(length = 200)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(length = 10)
	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

}
