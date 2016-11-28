package com.paySystem.ic.web.dto.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName: omall_back
 * @ClassName: AccountsDTO
 * @Description: 线上积分账户DTO
 * @date: 2014-11-11
 * @author: 廖晓远 
 * @version: V1.0
 */
public class AccountsDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 8276358496490702016L;
	/**账户编号*/
	private String onAccId;
	/**账户类型*/
	private Integer onAccTypeId;
	/**会员编号*/
	private String memId;
	/**入账总数*/
	private BigDecimal onInAmt;
	/**出账总数*/
	private BigDecimal onOutAmt;
	/**当前积分总额*/
	private BigDecimal pointsNum;
	/**当前现金总额*/
	private BigDecimal cardMoney;
	
	private String oganpws; 
	/**更新时间*/
	private Date updateTime;
	
	private String memPhoneNum;
	private String phoneCheckNum;
	private  String newOganpws;
	
	public String getOnAccId() {
		return onAccId;
	}
	public void setOnAccId(String onAccId) {
		this.onAccId = onAccId;
	}
	public Integer getOnAccTypeId() {
		return onAccTypeId;
	}
	public void setOnAccTypeId(Integer onAccTypeId) {
		this.onAccTypeId = onAccTypeId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public BigDecimal getOnInAmt() {
		return onInAmt;
	}
	public void setOnInAmt(BigDecimal onInAmt) {
		this.onInAmt = onInAmt;
	}
	public BigDecimal getOnOutAmt() {
		return onOutAmt;
	}
	public void setOnOutAmt(BigDecimal onOutAmt) {
		this.onOutAmt = onOutAmt;
	}
	public BigDecimal getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(BigDecimal pointsNum) {
		this.pointsNum = pointsNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public BigDecimal getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}
	public String getOganpws() {
		return oganpws;
	}
	public void setOganpws(String oganpws) {
		this.oganpws = oganpws;
	}
	public String getMemPhoneNum() {
		return memPhoneNum;
	}
	public void setMemPhoneNum(String memPhoneNum) {
		this.memPhoneNum = memPhoneNum;
	}
	public String getPhoneCheckNum() {
		return phoneCheckNum;
	}
	public void setPhoneCheckNum(String phoneCheckNum) {
		this.phoneCheckNum = phoneCheckNum;
	}
	public String getNewOganpws() {
		return newOganpws;
	}
	public void setNewOganpws(String newOganpws) {
		this.newOganpws = newOganpws;
	}
	
		
}
