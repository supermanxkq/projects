package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.web.dto.BaseDTO;

public class LockDTO extends BaseDTO implements Serializable{
	
private static final long serialVersionUID = -1324112849666702193L;

	/**
	 * 挂失表ID
	 * */
	private Integer lockId;
	
	
	/**
	 * 丢失的时间
	 * */
	private String lostTime;
	
	/**
	 * 丢失的地点
	 * */
	private String lostPlace;
	
	/**
	 * 操作人
	 * */
	private String operId;
	
	/**
	 * 操作时间
	 * */
	private String createTime;
	
	/**
	 * 身份证号码
	 * */
	private String personId;
	
	/**
	 * 挂失描述
	 * */
	private String descr;
	
	/**
	 * 机构
	 * */
	private Organs organs;
	
	/**
	 * 
	 * 卡表
	 * 
	 * */
	private Cards cards;
	
	/**
	 * 商户
	 * */
	
	private Merchants merchants;
	/**
	 * 用户级别
	 *
	 * 0、发卡机构 1、商户
	 * */
	private String perLevel;
	/**
	 * 卡号
	 * */
	private String cardNo;
	
	/**
	 * 卡的状态
	 * */
	private String status;
	
	/**
	 * 机构ID
	 * */
	private String organId;
	/**
	 * 显示卡号
	 * */
	private String cardViewNo;
	/**
	 * 会员姓名
	 * */
	private String merName;
	/**
	 * 会员证件号
	 * */
	private String memIdNum;
	
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getCardViewNo() {
		return cardViewNo;
	}
	public void setCardViewNo(String cardViewNo) {
		this.cardViewNo = cardViewNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getLockId() {
		return lockId; 
	}
	public void setLockId(Integer lockId) {
		this.lockId = lockId;
	}
	public String getLostTime() {
		return lostTime;
	}
	public void setLostTime(String lostTime) {
		this.lostTime = lostTime;
	}
	public String getLostPlace() {
		return lostPlace;
	}
	public void setLostPlace(String lostPlace) {
		this.lostPlace = lostPlace;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	public String getPerLevel() {
		return perLevel;
	}
	public void setPerLevel(String perLevel) {
		this.perLevel = perLevel;
	}
	
	public Cards getCards() {
		return cards;
	}
	public void setCards(Cards cards) {
		this.cards = cards;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMemIdNum() {
		return memIdNum;
	}
	public void setMemIdNum(String memIdNum) {
		this.memIdNum = memIdNum;
	}
	
	
}
