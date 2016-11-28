package com.paySystem.ic.web.dto.account;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class AccountsDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 8276358496490702016L;
	/*** 账户编号*/
	private String accId;
	/*** 账户类型 0：现金账户；1：积分账户*/
	private Integer accTypeID;
	/*** 账户类型名称 0：现金账户；1：积分账户*/
	private String accTypeName;
	/*** 所属卡号*/
	private String cardNo;
	/*** 入账总数*/
	private BigDecimal inAmt;
	/*** 出账总数*/
	private BigDecimal outAmt;
	/*** 上调整总数*/
	private BigDecimal upAmt;
	/*** 下调整总数*/
	private BigDecimal downAmt;
	/*** 余额/积分*/
	private BigDecimal balPoint;
	/*** 总余额*/
	private BigDecimal totalBalPoint;
	/*** 账户状态 0：启用；1：禁用；2：删除；3：冻结*/
	private Integer status;
	/** 主账号余额*/
	private BigDecimal mainBal;
	/*** 更新时间*/
	private String updateTime;
	private String ownedOrgId;
	private String ownedMerId;
    /*** 账户类型ID**/
	private Integer accTId;
	/**类型名称*/
	private String kindName; 
	
		
	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

	public Integer getAccTypeID() {
		return accTypeID;
	}

	public void setAccTypeID(Integer accTypeID) {
		this.accTypeID = accTypeID;
	}

	public String getAccTypeName() {
		return accTypeName;
	}

	public void setAccTypeName(String accTypeName) {
		this.accTypeName = accTypeName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getInAmt() {
		return inAmt;
	}

	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getUpAmt() {
		return upAmt;
	}

	public void setUpAmt(BigDecimal upAmt) {
		this.upAmt = upAmt;
	}

	public BigDecimal getDownAmt() {
		return downAmt;
	}

	public void setDownAmt(BigDecimal downAmt) {
		this.downAmt = downAmt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getBalPoint() {
		return balPoint;
	}

	public void setBalPoint(BigDecimal balPoint) {
		this.balPoint = balPoint;
	}

	public BigDecimal getTotalBalPoint() {
		return totalBalPoint;
	}

	public void setTotalBalPoint(BigDecimal totalBalPoint) {
		this.totalBalPoint = totalBalPoint;
	}

	public BigDecimal getMainBal() {
		return mainBal;
	}

	public void setMainBal(BigDecimal mainBal) {
		this.mainBal = mainBal;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}



	public void setOwnedMerId(String ownedMerId) {
		this.ownedMerId = ownedMerId;
	}

	public String getOwnedMerId() {
		return ownedMerId;
	}

	public void setOwnedOrgId(String ownedOrgId) {
		this.ownedOrgId = ownedOrgId;
	}

	public String getOwnedOrgId() {
		return ownedOrgId;
	}

	public Integer getAccTId() {
		return accTId;
	}

	public void setAccTId(Integer accTId) {
		this.accTId = accTId;
	}

}
