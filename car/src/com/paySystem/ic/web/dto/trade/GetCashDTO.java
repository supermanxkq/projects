package com.paySystem.ic.web.dto.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class GetCashDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/***提现编号**/
	private String getCashId;
	
	/**提现标识名称**/
	private Integer getCashSign;
	
	/**提现标识**/
	private String getCashSignName;
	
	/**提现人编号**/
	private String  memId;
	
	/**提现人电话**/
	private String  telNo;

	/**提现人名称**/
	private String  memName;
	
	/**提现金额**/
	private BigDecimal getCashAmt;
	
	/**提现金额大写**/
	private String amtUpper;
	
	/***操作人**/
	private String operId;
	
	/**手机验证码***/
	private String telNoCheck;
	
	/**提现类型***/
	private Integer getCashType;
	
	/**提现类型名称***/
	private String getCashTypeName;
	
	/**提现银行***/
	private String bank;
	
	/***提现银行账户***/
	private String bankAcc;
	
	/***提现密码**/
	private String pwd;
	
	/***提现完成状态**/
	private Integer status;
	
	/***提现完成状态**/
	private String statusName;

	/**提现时间**/
	private Date createTime;
	
	/**更新时间***/
	private Date updateTime;
	
	/**卡号***/
	private String cardNo;
	
	/**卡标识***/
	private Integer cardSingn;
	
	public String getGetCashId() {
		return getCashId;
	}

	public void setGetCashId(String getCashId) {
		this.getCashId = getCashId;
	}

	public Integer getGetCashSign() {
		return getCashSign;
	}

	public void setGetCashSign(Integer getCashSign) {
		this.getCashSign = getCashSign;
	}

	public String getGetCashSignName() {
		return getCashSignName;
	}

	public void setGetCashSignName(String getCashSignName) {
		this.getCashSignName = getCashSignName;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public BigDecimal getGetCashAmt() {
		return getCashAmt;
	}

	public void setGetCashAmt(BigDecimal getCashAmt) {
		this.getCashAmt = getCashAmt;
	}

	public String getAmtUpper() {
		return amtUpper;
	}

	public void setAmtUpper(String amtUpper) {
		this.amtUpper = amtUpper;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getTelNoCheck() {
		return telNoCheck;
	}

	public void setTelNoCheck(String telNoCheck) {
		this.telNoCheck = telNoCheck;
	}

	public Integer getGetCashType() {
		return getCashType;
	}

	public void setGetCashType(Integer getCashType) {
		this.getCashType = getCashType;
	}

	public String getGetCashTypeName() {
		return getCashTypeName;
	}

	public void setGetCashTypeName(String getCashTypeName) {
		this.getCashTypeName = getCashTypeName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Integer getCardSingn() {
		return cardSingn;
	}

	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}
	
}
