package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class CardsDetailDTO extends BaseDTO implements Serializable{
	/**
	* @Fields serialVersionUID : 序列化
	*/
	private static final long serialVersionUID = -8323116746764968202L;
	private String cardNo;
	private String saleOrgId;
	private String saleOrgName;
	private String saleMerId;
	private String saleMerName;
	private BigDecimal amount;
	private String buyMemId;
	private String buyMemName;
	private String makeTime;
	private String startTime;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSaleOrgId() {
		return saleOrgId;
	}
	public void setSaleOrgId(String saleOrgId) {
		this.saleOrgId = saleOrgId;
	}
	public String getSaleOrgName() {
		return saleOrgName;
	}
	public void setSaleOrgName(String saleOrgName) {
		this.saleOrgName = saleOrgName;
	}
	public String getSaleMerId() {
		return saleMerId;
	}
	public void setSaleMerId(String saleMerId) {
		this.saleMerId = saleMerId;
	}
	public String getSaleMerName() {
		return saleMerName;
	}
	public void setSaleMerName(String saleMerName) {
		this.saleMerName = saleMerName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBuyMemId() {
		return buyMemId;
	}
	public void setBuyMemId(String buyMemId) {
		this.buyMemId = buyMemId;
	}
	public String getBuyMemName() {
		return buyMemName;
	}
	public void setBuyMemName(String buyMemName) {
		this.buyMemName = buyMemName;
	}
	public String getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
