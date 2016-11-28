package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class DriverDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 司机编号 */
	private String driverId;
	/** 会员编号 */
	private String memId;
	/** 会员真实姓名 */
	private String memRealName;
	/** 会员手机号 */
	private String teleNo;
	/** 会员身份证号 */
	private String personId;
	/** 车型 */
	private Integer carType;
	/** 车牌号 */
	private String carNumber;
	/** 驾驶证编号 */
	private String driverNo;
	/** 联系人 */
	private String contacts;
	/** 联系人联系方式 */
	private String contactsTel;
	/** 合同编号 */
	private String contractNo;
	/** 开户银行 */
	private String bank;
	/** 开户账号 */
	private String bankAccount;
	/** 结算周期 */
	private String settlement;
	/** 创建时间 */
	private Date createTime;
	/** 操作人 */
	private String operId;
	/** 司机状态 1：正常 2;禁用 9：删除 */
	private Integer status;

	
	
	public String getMemId() {
		return memId;
	}

	public void setMerId(String memId) {
		this.memId = memId;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getMemRealName() {
		return memRealName;
	}

	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}

	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getContactsTel() {
		return contactsTel;
	}

	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

}