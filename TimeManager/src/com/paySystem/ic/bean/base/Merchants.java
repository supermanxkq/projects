package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:Merchants
 * @Description:商户实体Bean
 * @date: 2013-12-4下午05:05:28
 * @author: 谢洪飞
 * @version: V1.0n
 */
@Entity
@Table(name = "b_merchants")
public class Merchants implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	/** 商户号 */
	private String merId;
	/** 商户名 */
	private String merName;
	/** 刷红卡的手续费率 */
	private BigDecimal rakeRate = new BigDecimal(100);
	/** 区域号 */
	private String areaId;
	/** 商户状态0-新开户1-正常2-锁定 3撤销 9删除*/
	private Integer status;
	/** 代理商标志0-非代理商1-代理商 */
	private Integer agentSign;
	/** 代理商扣率 */
	private BigDecimal agentDiscRate;
	/** 充值限额标志0: 无充值上限1: 有充值上限 */
	private Integer tranLimitSign;
	/** 撤销标志 */
	private Integer revorkSign;
	/** 撤销原因 */
	private String revorkReason;
	/** 合同号 */
	private String conNo;
	/** 联系人姓名 */
	private String conPerName;
	/** 营业电话号码 */
	private String teleNo;
	/** 联系人电话号码 */
	private String conPerTeleNo;
	/** 商户开户银行 */
	private String bankName;
	/** 商户开户账号 */
	private String bankAccountNo;
	/** 开户人 */
	private String bankUser;
	/** 更新日期 */
	private Date updateTime;
	/** 地址 */
	private String address;
	/** 邮编 */
	private String zip;
	/** 创建时间 */
	private Date createTime;
	/**商户所属机构*/
	private Organs organs;
	/** 结算周期: 以天为单位 */
	private Integer settPeriod;
	/** 上次结算时间 */
	private Date lastSettTime;
	/** 是否开票 0否1是 */
	private Integer invSign;
	/** 是否私户 0否1是 */
	private Integer privateSign;
	/** 上级商户号 ==>上级经销商*/
	private String preMerId;
	/** 商户介绍 */
	private String intro;
	//经度
	private String lat;
	//纬度
	private String lng;
	/***********************************
	 * 
	 *    石油平台支付管理系统增加内容
	 * 
	 * *********************************/
	
	/**合作方式**/
	private Integer coopWay;
	
	/**是否开启推荐人标志*/
	private Integer refSign;
	
	/**商户等级*/
	private Integer merLevel;
	
	
	public Merchants() {
		super();
	}
	
	public Merchants(String merId) {
		this.merId=merId;
	}

	@Id
	@Column(length=15)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	@Column(length = 60)
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	@Column(columnDefinition="DECIMAL(9,2)")
	public BigDecimal getRakeRate() {
		return rakeRate;
	}
	public void setRakeRate(BigDecimal rakeRate) {
		this.rakeRate = rakeRate;
	}
	@Column(length = 10)
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(columnDefinition="char(1)")
	public Integer getAgentSign() {
		return agentSign;
	}
	public void setAgentSign(Integer agentSign) {
		this.agentSign = agentSign;
	}
	@Column(precision=5,scale=3)
	public BigDecimal getAgentDiscRate() {
		return agentDiscRate;
	}
	public void setAgentDiscRate(BigDecimal agentDiscRate) {
		this.agentDiscRate = agentDiscRate;
	}
	@Column(columnDefinition="char(1)")
	public Integer getTranLimitSign() {
		return tranLimitSign;
	}
	public void setTranLimitSign(Integer tranLimitSign) {
		this.tranLimitSign = tranLimitSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getRevorkSign() {
		return revorkSign;
	}
	public void setRevorkSign(Integer revorkSign) {
		this.revorkSign = revorkSign;
	}
	@Column(length=255)
	public String getRevorkReason() {
		return revorkReason;
	}
	public void setRevorkReason(String revorkReason) {
		this.revorkReason = revorkReason;
	}
	@Column(length=20)
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	@Column(length=30)
	public String getConPerName() {
		return conPerName;
	}
	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}
	@Column(length=13)
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	@Column(length=11)
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}
	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}
	@Column(length=40)
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(length=40)
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	@Column(length=30)
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=10)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
    @Column(columnDefinition="DECIMAL(3)")
	public Integer getSettPeriod() {
		return settPeriod;
	}
	public void setSettPeriod(Integer settPeriod) {
		this.settPeriod = settPeriod;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getLastSettTime() {
		return lastSettTime;
	}
	public void setLastSettTime(Date lastSettTime) {
		this.lastSettTime = lastSettTime;
	}
	@Column(columnDefinition="char(1)")
	public Integer getInvSign() {
		return invSign;
	}
	public void setInvSign(Integer invSign) {
		this.invSign = invSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getPrivateSign() {
		return privateSign;
	}
	public void setPrivateSign(Integer privateSign) {
		this.privateSign = privateSign;
	}
	@Column(length=15)
	public String getPreMerId() {
		return preMerId;
	}
	public void setPreMerId(String preMerId) {
		this.preMerId = preMerId;
	}
	@Column(length=255)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getCoopWay() {
		return coopWay;
	}

	public void setCoopWay(Integer coopWay) {
		this.coopWay = coopWay;
	}

	@Column(columnDefinition="char(1)")
	public Integer getRefSign() {
		return refSign;
	}

	
	public void setRefSign(Integer refSign) {
		this.refSign = refSign;
	}

	@Column(columnDefinition="char(2)")
	public Integer getMerLevel() {
		return merLevel;
	}

	public void setMerLevel(Integer merLevel) {
		this.merLevel = merLevel;
	}
	@Column(length=30)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	@Column(length=30)
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
	
	
}
