package com.paySystem.ic.web.dto.card;
import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class CardsDTO extends BaseDTO implements Serializable{
	
	/**
	* @Fields serialVersionUID : 序列化常量
	*/
	private static final long serialVersionUID = -5907903116994778546L;
	private String cardNo;
	private String binId;
	private String binName;
	private String cardViewNo;
	private Integer binFlag;
	//private Double cardLimitValue;
	private String holdmemId;
	private String holdName;
	private String bank;
	private String bankAcc;
	private String levelId;
	private String levelName;
	private String validTime;
	private Integer memsign;
	private Integer pwdsign;
	private Integer pwdErrorNum;
	private Integer status;
	//private String deptType;
	private String organId;
	private String organName;
	private String merId;
	private String merName;
	private Integer bcSign;
	private String telNo;
	private String pinBlock;
	private String track2;
	private String createTime;
	/**	会员证件号 */
	private String memIdNum;
	/**账户类型*/
	private Integer typeId;
	/**账户号*/
	private String accId;
	/**账户余额*/
	private BigDecimal balance;
	/**
	 * 企业Id
	 * */
	private Integer companyId;
	/**
	 * 卡标志
	 * */
	private Integer cardSingn;
	/**
	 * 卡标志
	 * */
	private String cardSingnName;
	
	/**密码***/
	private String pwd;
	
	public String getCardNo() {
		return cardNo;
	}
	public String getMemIdNum() {
		return memIdNum;
	}
	public void setMemIdNum(String memIdNum) {
		this.memIdNum = memIdNum;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public Integer getBinFlag() {
		return binFlag;
	}
	public void setBinFlag(Integer binFlag) {
		this.binFlag = binFlag;
	}
//	public Double getCardLimitValue() {
//		return cardLimitValue;
//	}
//	public void setCardLimitValue(Double cardLimitValue) {
//		this.cardLimitValue = cardLimitValue;
//	}
	public String getHoldmemId() {
		return holdmemId;
	}
	public void setHoldmemId(String holdmemId) {
		this.holdmemId = holdmemId;
	}
	public String getHoldName() {
		return holdName;
	}
	public void setHoldName(String holdName) {
		this.holdName = holdName;
	}	
	
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public Integer getMemsign() {
		return memsign;
	}
	public void setMemsign(Integer memsign) {
		this.memsign = memsign;
	}
	public Integer getPwdsign() {
		return pwdsign;
	}
	public void setPwdsign(Integer pwdsign) {
		this.pwdsign = pwdsign;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
//	public String getDeptType() {
//		return deptType;
//	}
//	public void setDeptType(String deptType) {
//		this.deptType = deptType;
//	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public Integer getBcSign() {
		return bcSign;
	}
	
	public Integer getPwdErrorNum() {
		return pwdErrorNum;
	}
	public void setPwdErrorNum(Integer pwdErrorNum) {
		this.pwdErrorNum = pwdErrorNum;
	}
	public void setBcSign(Integer bcSign) {
		this.bcSign = bcSign;
	}
	public String getPinBlock() {
		return pinBlock;
	}
	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
	}
	public String getTrack2() {
		return track2;
	}
	public void setTrack2(String track2) {
		this.track2 = track2;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getCardViewNo() {
		return cardViewNo;
	}
	public void setCardViewNo(String cardViewNo) {
		this.cardViewNo = cardViewNo;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getCardSingn() {
		return cardSingn;
	}
	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
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
	public String getCardSingnName() {
		return cardSingnName;
	}
	public void setCardSingnName(String string) {
		this.cardSingnName = string;
	}
	
}
