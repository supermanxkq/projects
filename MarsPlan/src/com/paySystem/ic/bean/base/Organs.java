package com.paySystem.ic.bean.base;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:Organs
 * @Description:机构实体Bean
 * @date: 2013-12-4下午05:12:35
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name = "b_organs")
public class Organs implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	/** 机构ID */
	private String organId;
	/** 机构名称 */
	private String name;
	/** 所属上一级机构号  0代表顶级发卡机构*/
	private String parentId;
	/** 是否资金托管 0否 1是 */
	private Integer trustFundSign;
	/** 是否年服务费 0否 1是*/
	private Integer yearServSign;
	/** 联系人姓名 */
	private String conPerName;
	/** 地区ID */
	private Integer areaId;
	/** 电话 */
	private String teleNo;
	/** 状态：1为启用 0为禁用 9删除 */
	private Integer status;
	/** 地址 */
	private String address;
	/** 邮编 */
	private String zip;
	/** 结算周期: 以天为单位 */
	private Integer settPeriod;
	/** 上次结算时间 */
	private Date lastSettTime;
	/** 商户开户银行 */
	private String bankName;
	/** 商户开户账号 */
	private String bankAcctNo;
	/** 商户开户账号名称*/
	private String bankAccName;
	/** 联系人电话号码 */
	private String conPerTeleNo;
	/** 业务员提成比率 */
	private BigDecimal saleMRate;
	/** 机构类型 0预付卡系统1会员营销系统 */
	private Integer sysType;
	/** 开户人 */
	private String bankUser;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 网上支付返现比率 */
	/*private BigDecimal netPayRetRate;*/
	/** 紧急联系人电话号码 */
	private String emContPhone;
	/** 机构描述*/
	private String orgDesc;
	


	public Organs() {
		super();
	}

	public Organs(String orgId) {
		this.organId = orgId;
	}

	@Id
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	@Column(length = 60)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 8)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(columnDefinition="char(1)")
	public Integer getTrustFundSign() {
		return trustFundSign;
	}
	public void setTrustFundSign(Integer trustFundSign) {
		this.trustFundSign = trustFundSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getYearServSign() {
		return yearServSign;
	}
	public void setYearServSign(Integer yearServSign) {
		this.yearServSign = yearServSign;
	}
	@Column(length = 20)
	public String getConPerName() {
		return conPerName;
	}
	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}
	@Column
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	@Column(length = 20)
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(length = 100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length = 10)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Column
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
	@Column(length=40)
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(length = 40)
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	@Column(length=40)
	public String getBankAccName() {
		return bankAccName;
	}
	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}
	@Column(length = 20)
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}
	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}
	@Column(columnDefinition="DECIMAL(5,2)")
	public BigDecimal getSaleMRate() {
		return saleMRate;
	}
	public void setSaleMRate(BigDecimal saleMRate) {
		this.saleMRate = saleMRate;
	}
	@Column(columnDefinition="char(1)")
	public Integer getSysType() {
		return sysType;
	}
	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}
	@Column(length = 30)
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length = 200)
	public String getEmContPhone() {
		return emContPhone;
	}
	public void setEmContPhone(String emContPhone) {
		this.emContPhone = emContPhone;
	}
	@Column(length=255)
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST }, mappedBy = "organs", fetch = FetchType.EAGER)
	@OrderBy(value="merId ASC")
	public Set<Merchants> getMerchants() {
		return merchants;
	}
	public void setMerchants(Set<Merchants> merchants) {
		this.merchants = merchants;
	}
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST }, mappedBy = "organs", fetch = FetchType.EAGER)
	@OrderBy(value="cardNo ASC")
	public Set<CardNo> getCardNo() {
		return cardNo;
	}
	public void setCardNo(Set<CardNo> cardNo) {
		this.cardNo = cardNo;
	}
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST }, mappedBy = "organs", fetch = FetchType.EAGER)
	@OrderBy(value="binId ASC")
	public Set<CardBIN> getCardBIN() {
		return cardBIN;
	}
	public void setCardBIN(Set<CardBIN> cardBIN) {
		this.cardBIN = cardBIN;
	}*/

}
