package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:Driver
 * @Description:司机信息实体bean
 * @date: 2014-5-14下午04:37:00
 * @author: 王月
 * @version: V1.0
 */
@Entity
@Table(name = "B_Drivers")
public class Drivers implements Serializable{	
	
	private static final long serialVersionUID = 2337348750067253781L;
	/**司机编号*/
	private String driverId;
	/**会员编号*/
	private String memId;
	/**车型*/
	private Integer carType;
	/**车牌号 */
	private String carNumber;
	/**驾驶证编号*/
	private String driverNo;
	/**紧密联系人*/
	private String contacts;
	/**紧密联系人电话*/
	private String contactsTel;
	/**合同编号*/
	private String contractNo;
	/**开户银行*/
	private String bank;
	/**开户账号*/
	private String bankAccount;
	/**结算周期*/
	private String settlement;
	/**创建时间*/
	private Date createTime;
	/**操作人*/
	private String operId;
	/** 司机状态 1：正常 2;禁用 9：删除 */
	private Integer status;
	
	
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	@Id
	@Column(length=15)
	public String getDriverId() {
		return driverId;
	}  

	
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	@Column(length = 30)
	public Integer getCarType() {
		return carType;
	}

	@Column(length = 30)
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	@Column(length = 30)
	public String getDriverNo() {
		return driverNo;
	}

	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(length = 20)
	public String getContacts() {
		return contacts;
	}

	
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	@Column(length = 30)
	public String getContractNo() {
		return contractNo;
	}

	
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Column(length = 30)
	public String getBank() {
		return bank;
	}
	
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Column(length = 20)
	public String getBankAccount() {
		return bankAccount;
	}

	
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	@Column(length = 10)
	public String getSettlement() {
		return settlement;
	}
	
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setOperId(String operId) {
		this.operId = operId;
	}
	@Column(length = 20)
	public String getOperId() {
		return operId;
	}
	
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Column(length = 15)
	public String getMemId() {
		return memId;
	}
	
	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	@Column(length = 15)
	public String getContactsTel() {
		return contactsTel;
	}
}