package com.paySystem.ic.bean.report;

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

import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.bean.member.Member;

/**
 * @ClassName:Trades
 * @Description:联机交易实体Bean
 * @date: 2013-12-24下午02:57:53
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="T_Trades")
public class Trades implements Serializable {


	
	private static final long serialVersionUID = 9113209437840195489L;
	/**流水ID号*/
	private String tradeId;
	/** 前流水ID号 */
	private String preTradeId;
	/**交易状态 ：
	 * P：处理中
	   C：完成
	   R：冲正
	   r: 冲正中
	 * */
	private String status;
	/** 交易所在商户 */
	private Merchants merchants;

	/** 交易发生卡号 */
	private Cards cards;
   
	/**POS流水号*/
	private String traceNo;
	/** 批次号 */
	private String batchId;
	/** 交易时间 */
	private Date placedtime;
	/** 发生交易卡所属卡BIN */
	private CardBIN cardBin;
	/** 原价 */
	private BigDecimal retailAmt;
	/** 实际交易金额/积分*/
	private BigDecimal tradeAmt;
	/** 交易产生积分*/
	private BigDecimal points;
	/** 手续费*/
	private BigDecimal sxf;
	/** 结算金额(交易金额 — 手续费) */
	private BigDecimal proxyAmt;
	/** 
	 * 
	 * 交易类型 
	 * 1 退货
	   2 充值
	   3 充值冲正
	   4 充值撤销
	　  5 充值撤销冲正
	　  13 修改密码
	　  14 查询卡状态
	　  15 消费
	　  16 消费冲正
	　  17 消费撤销
	　  18 消费撤销冲正
	  　20 积分消费
	  　21 积分消费冲正
	  　22 积分消费撤销
	  　23 积分消费撤销冲正
	 * 
	 * */
	private Integer tradeType;
	/** 交易状态 
	 * 0 正常
	   1 已冲正
	   2 已退货
	   3 已撤销
	 * 
	 * */
	private Integer flag;
	/** 操作员号 */
	private String operId;
    /** 创建日期 */
	private Date createTime;
	private Date updateTime;
	/** 是否允许退货 0 不允许，1允许*/
	
	private Integer allowRetSign;
	/**所属会员*/
	private Member member;
	/** 交易账户类型
	 * 0现金
	   1积分
	 *  */
	private AccKinds accKinds;
	
	@Id
	@Column(length=18)
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	@Column(length=18)
	public String getPreTradeId() {
		return preTradeId;
	}
	public void setPreTradeId(String preTradeId) {
		this.preTradeId = preTradeId;
	}
	@Column(columnDefinition="char(1)")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(cascade = CascadeType.REFRESH ,optional = false)
	@JoinColumn(name="merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}

	@ManyToOne(cascade = CascadeType.REFRESH,optional = false)
	@JoinColumn(name="cardNo")
	public Cards getCards() {
		return cards;
	}
	public void setCards(Cards cards) {
		this.cards = cards;
	}
	@Column(length=6)
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	@Column(length=6)
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getPlacedtime() {
		return placedtime;
	}
	public void setPlacedtime(Date placedtime) {
		this.placedtime = placedtime;
	}
	@ManyToOne(cascade = CascadeType.REFRESH , optional = false)
	@JoinColumn(name="binId")
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getRetailAmt() {
		return retailAmt;
	}
	public void setRetailAmt(BigDecimal retailAmt) {
		this.retailAmt = retailAmt;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getPoints() {
		return points;
	}
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	@Column(columnDefinition = "DECIMAL(13,3)")
	public BigDecimal getSxf() {
		return sxf;
	}
	public void setSxf(BigDecimal sxf) {
		this.sxf = sxf;
	}
	@Column(columnDefinition="DECIMAL(13,3)")
	public BigDecimal getProxyAmt() {
		return proxyAmt;
	}
	public void setProxyAmt(BigDecimal proxyAmt) {
		this.proxyAmt = proxyAmt;
	}
	@Column(columnDefinition="Varchar(3)")
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	@Column(columnDefinition="char(1)")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(length=15)
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(columnDefinition="char(1)")
	public Integer getAllowRetSign() {
		return allowRetSign;
	}
	public void setAllowRetSign(Integer allowRetSign) {
		this.allowRetSign = allowRetSign;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional = true)
	@JoinColumn(name="memId")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@ManyToOne(cascade = CascadeType.REFRESH,optional = false)
	@JoinColumn(name="accTypeId")
	public AccKinds getAccKinds() {
		return accKinds;
	}
	public void setAccKinds(AccKinds accKinds) {
		this.accKinds = accKinds;
	}
	

	
}
