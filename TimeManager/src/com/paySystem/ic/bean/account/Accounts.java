package com.paySystem.ic.bean.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/** 账户实体类 */

@Entity
@Table(name = "C_Accounts")
public class Accounts implements Serializable {

	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = -8150076666514632125L;
	/**
	 * 账户编号
	 */
	private String accId;
	/**
	 * 账户类型 0：现金账户；1：积分账户
	 */
	//private AccountType accountType;
	private Integer accountTypeId;
	/**
	 * 账户名称
	 * */
	private String accTName;
	/**
	 * 所属卡号
	 */
	private String cardNo;
	
	/**
	 * 入账总数
	 */
	private BigDecimal inAmt;
	/**
	 * 出账总数
	 */
	private BigDecimal outAmt;
	/**
	 * 上调整总数
	 */
	private BigDecimal upAmt;
	/**
	 * 下调整总数
	 */
	private BigDecimal downAmt;
	/**
	 * 账户状态 0：启用；1：禁用；2：删除；3：冻结
	 */
	private Integer status;
	/**
	 * 主账号余额
	 */
	private BigDecimal mainBal;
	/**
	 * 更新时间
	 */
	private Date updateTime;
 
	
	private List<AccKinds> listKinds = new ArrayList<AccKinds>();
	@Id
	@Column(length = 16)
	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

//	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
//	@JoinColumn(name = "accTypeId")
//	public AccountType getAccountType() {
//		return accountType;
//	}
//
//	public void setAccountType(AccountType accountType) {
//		this.accountType = accountType;
//	}
	@Column
	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	
	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getInAmt() {
		return inAmt;
	}

	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getUpAmt() {
		return upAmt;
	}

	public void setUpAmt(BigDecimal upAmt) {
		this.upAmt = upAmt;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getDownAmt() {
		return downAmt;
	}

	public void setDownAmt(BigDecimal downAmt) {
		this.downAmt = downAmt;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition = "DECIMAL(18,2)")
	public BigDecimal getMainBal() {
		return mainBal;
	}

	public void setMainBal(BigDecimal mainBal) {
		this.mainBal = mainBal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length = 20)
	public String getAccTName() {
		return accTName;
	}

	public void setAccTName(String accTName) {
		this.accTName = accTName;
	}

	@Transient
	public List<AccKinds> getListKinds() {
		return listKinds;
	}

	public void setListKinds(List<AccKinds> listKinds) {
		this.listKinds = listKinds;
	}
   
}
