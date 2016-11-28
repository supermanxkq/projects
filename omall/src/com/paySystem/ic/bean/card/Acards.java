package com.paySystem.ic.bean.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.member.Member;

/**
 * 卡辅助表
 * 
 * @author lily
 * 
 */
@Entity
@Table(name = "CA_Cards")
public class Acards implements Serializable {

	private static final long serialVersionUID = -7418244899544745964L;

	/**
	 * 卡号 卡bin（6位）+ 标志(1位)+预留（2位 88）+9位流水+1位校验位
	 * */
	private String cardNo;

	/**
	 * 售卡机构/商户号
	 * */
	private Organs organs;
	
	private Merchants merchants;

	/**
	 * 售卡时初始面值
	 * */
	private BigDecimal amount;
	/**
	 * 购卡人客户号
	 * */
	private Member member;

	/**
	 * 制卡时间
	 * */
	private Date makeTime;
	/**
	 * 卡启用时间
	 * */
	private Date startTime;

	@Id
	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "merId")
	public Merchants getMerchants() {
		return merchants;
	}

	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "buyMemId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	

}