package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class OrgansDTO  extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;
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
	/** 系统目前为：  开户名称*/
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
	/** 帮助标志*/
	private String helpSign;
	
	
	
	public String getHelpSign() {
		return helpSign;
	}
	public void setHelpSign(String helpSign) {
		this.helpSign = helpSign;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getTrustFundSign() {
		return trustFundSign;
	}
	public void setTrustFundSign(Integer trustFundSign) {
		this.trustFundSign = trustFundSign;
	}
	public Integer getYearServSign() {
		return yearServSign;
	}
	public void setYearServSign(Integer yearServSign) {
		this.yearServSign = yearServSign;
	}
	public String getConPerName() {
		return conPerName;
	}
	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getSettPeriod() {
		return settPeriod;
	}
	public void setSettPeriod(Integer settPeriod) {
		this.settPeriod = settPeriod;
	}
	public Date getLastSettTime() {
		return lastSettTime;
	}
	public void setLastSettTime(Date lastSettTime) {
		this.lastSettTime = lastSettTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	public String getBankAccName() {
		return bankAccName;
	}
	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}
	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}
	public BigDecimal getSaleMRate() {
		return saleMRate;
	}
	public void setSaleMRate(BigDecimal saleMRate) {
		this.saleMRate = saleMRate;
	}
	public Integer getSysType() {
		return sysType;
	}
	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}
	public String getBankUser() {
		return bankUser;
	}
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getEmContPhone() {
		return emContPhone;
	}
	public void setEmContPhone(String emContPhone) {
		this.emContPhone = emContPhone;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
	
}
