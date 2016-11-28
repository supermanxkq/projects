package com.paySystem.ic.web.dto.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class TurnCashDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***转账编号**/
	private String turnCashId;
	
	/**转账标识**/
	private Integer turnCashSign;
	
	/**转账标识**/
	private String turnCashSignName;
	
	/**转账人编号**/
	private String  memId;
	
	/**转账人编号**/
	private String  memName;
	
	/**转账人电话**/
	private String  telNo;
	
	/**转账金额**/
	private BigDecimal turnCashAmt;
	
	/**转账金额大写**/
	private String amtUpper;
	
	/***操作人**/
	private String operId;

	/**转账时间**/
	private Date createTime;
	
	/**卡号**/
	private String cardsNo;
	
	/**卡标识***/
	private Integer cardSingn;
	
	/***验证码***/
	private String telNoCheck;
	
	/**密码**/
	private String pwd;

	public String getTurnCashId() {
		return turnCashId;
	}

	public void setTurnCashId(String turnCashId) {
		this.turnCashId = turnCashId;
	}

	public Integer getTurnCashSign() {
		return turnCashSign;
	}

	public void setTurnCashSign(Integer turnCashSign) {
		this.turnCashSign = turnCashSign;
	}

	public String getTurnCashSignName() {
		return turnCashSignName;
	}

	public void setTurnCashSignName(String turnCashSignName) {
		this.turnCashSignName = turnCashSignName;
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

	public BigDecimal getTurnCashAmt() {
		return turnCashAmt;
	}

	public void setTurnCashAmt(BigDecimal turnCashAmt) {
		this.turnCashAmt = turnCashAmt;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCardsNo() {
		return cardsNo;
	}

	public void setCardsNo(String cardsNo) {
		this.cardsNo = cardsNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTelNoCheck() {
		return telNoCheck;
	}

	public void setTelNoCheck(String telNoCheck) {
		this.telNoCheck = telNoCheck;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getCardSingn() {
		return cardSingn;
	}

	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}
	
	
}
