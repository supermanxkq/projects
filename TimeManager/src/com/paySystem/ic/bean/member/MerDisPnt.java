package com.paySystem.ic.bean.member;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paySystem.ic.bean.base.Merchants;
/**
 * 商户折扣积分信息表
 * 
 * 
 * @author 井建国
 *
 */
@Entity
@Table(name="C_MerDisPnt")
public class MerDisPnt {
	/**商户折扣积分 */
	private String merDisPntId;
	/**卡等级关联 */
	private CardLevel cardLevel;
	/**商户关联 */
	private Merchants mercharts;
	/**账户类型关联   暂时用Id，不关联     0：现金账户   1：积分账户*/
	private String accTypeId;
	/**消费折扣 */
	private BigDecimal payDiscount;
	/**消费积分比率 */
	private BigDecimal ppointRate;
	/**充值积分比率 */
	private BigDecimal rpointRate;
	@Id
	@Column(length = 10)
	public String getMerDisPntId() {
		return merDisPntId;
	}
	public void setMerDisPntId(String merDisPntId) {
		this.merDisPntId = merDisPntId;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "levelId")
	public CardLevel getCardLevel() {
		return cardLevel;
	}
	public void setCardLevel(CardLevel cardLevel) {
		this.cardLevel = cardLevel;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "merId")
	public Merchants getMercharts() {
		return mercharts;
	}
	public void setMercharts(Merchants mercharts) {
		this.mercharts = mercharts;
	}
	@Column(columnDefinition="char(2)")
	public String getAccTypeId() {
		return accTypeId;
	}
	public void setAccTypeId(String accTypeId) {
		this.accTypeId = accTypeId;
	}
	public BigDecimal getPayDiscount() {
		return payDiscount;
	}
	public void setPayDiscount(BigDecimal payDiscount) {
		this.payDiscount = payDiscount;
	}
	public BigDecimal getPpointRate() {
		return ppointRate;
	}
	public void setPpointRate(BigDecimal ppointRate) {
		this.ppointRate = ppointRate;
	}
	public BigDecimal getRpointRate() {
		return rpointRate;
	}
	public void setRpointRate(BigDecimal rpointRate) {
		this.rpointRate = rpointRate;
	}
	
}