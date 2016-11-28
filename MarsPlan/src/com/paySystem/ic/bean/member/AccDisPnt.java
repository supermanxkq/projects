package com.paySystem.ic.bean.member;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * 账户消费折扣积分表
 * 
 * @author 井建国
 *
 */
@Entity
@Table(name="C_AccDisPnt")
public class AccDisPnt {
	/**账户折扣积分Id */
	private String accDisPntId;
	/**卡等级关联 */
	private CardLevel cardLevel;
	/**现金/积分账户*/
	private Integer caccTypeId;
	/**现金/积分消费折扣      0表示不赠送 */
	private BigDecimal cpayDiscount;
	/**现金/积分消费送积分比率     0表示不赠送*/
	private BigDecimal cppointRate;
	/**现金/积分充值送积分比率    0表示不赠送 */
	private BigDecimal crpointRate;
	
	
	@Id
	@Column(length = 10)
	public String getAccDisPntId() {
		return accDisPntId;
	}
	public void setAccDisPntId(String accDisPntId) {
		this.accDisPntId = accDisPntId;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "levelId")
	public CardLevel getCardLevel() {
		return cardLevel;
	}
	public void setCardLevel(CardLevel cardLevel) {
		this.cardLevel = cardLevel;
	}
	@Column(columnDefinition="char(2)")
	public Integer getCaccTypeId() {
		return caccTypeId;
	}
	public void setCaccTypeId(Integer caccTypeId) {
		this.caccTypeId = caccTypeId;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getCpayDiscount() {
		return cpayDiscount;
	}
	public void setCpayDiscount(BigDecimal cpayDiscount) {
		this.cpayDiscount = cpayDiscount;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getCppointRate() {
		return cppointRate;
	}
	public void setCppointRate(BigDecimal cppointRate) {
		this.cppointRate = cppointRate;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getCrpointRate() {
		return crpointRate;
	}
	public void setCrpointRate(BigDecimal crpointRate) {
		this.crpointRate = crpointRate;
	}
}
