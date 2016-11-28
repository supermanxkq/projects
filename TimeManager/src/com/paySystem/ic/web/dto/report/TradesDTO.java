package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.bean.system.User;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @author:谢鸿飞
 * @projectName: MCIU20140326
 * @className :TradesDTO
 * @description :TODO 联机交易DTO
 * @date: 2014-4-10下午06:11:27
 * @param :
 * 
 */
public class TradesDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 8823399592397227636L;

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
	/** 交易发生终端 */
	//private Terminals Terminals ;
	/** 交易发生卡号 */
	private CardNo cardNo;
   
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
	private User operId;
    /** 创建日期 */
	private Date createTime;
	private Date updateTime;
	/** 机构结算状态  0：未结算 ，1：已结算*/
	private Integer organSettleFlag;
	/** 商户结算状态  0：未结算 ， 1：已结算 */
	private Integer merSettleFlag;
	/** 其它机构结算状态  0 ： 未结算 ，1：已结算*/
	private Integer otherSettleFlag;
	/** 是否允许退货 0 不允许，1允许*/
	private Integer allowRetSign;
	/**所属会员*/
	private Member member;
	/** 交易账户类型
	 * 0现金
	   1积分
	 *  */
	private AccKinds accKinds;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getPreTradeId() {
		return preTradeId;
	}
	public void setPreTradeId(String preTradeId) {
		this.preTradeId = preTradeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	/*public Terminals getTerminals() {
		return Terminals;
	}
	public void setTerminals(Terminals terminals) {
		Terminals = terminals;
	}*/
	public CardNo getCardNo() {
		return cardNo;
	}
	public void setCardNo(CardNo cardNo) {
		this.cardNo = cardNo;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Date getPlacedtime() {
		return placedtime;
	}
	public void setPlacedtime(Date placedtime) {
		this.placedtime = placedtime;
	}
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	public BigDecimal getRetailAmt() {
		return retailAmt;
	}
	public void setRetailAmt(BigDecimal retailAmt) {
		this.retailAmt = retailAmt;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public BigDecimal getPoints() {
		return points;
	}
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	public BigDecimal getSxf() {
		return sxf;
	}
	public void setSxf(BigDecimal sxf) {
		this.sxf = sxf;
	}
	public BigDecimal getProxyAmt() {
		return proxyAmt;
	}
	public void setProxyAmt(BigDecimal proxyAmt) {
		this.proxyAmt = proxyAmt;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public User getOperId() {
		return operId;
	}
	public void setOperId(User operId) {
		this.operId = operId;
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
	public Integer getOrganSettleFlag() {
		return organSettleFlag;
	}
	public void setOrganSettleFlag(Integer organSettleFlag) {
		this.organSettleFlag = organSettleFlag;
	}
	public Integer getMerSettleFlag() {
		return merSettleFlag;
	}
	public void setMerSettleFlag(Integer merSettleFlag) {
		this.merSettleFlag = merSettleFlag;
	}
	public Integer getOtherSettleFlag() {
		return otherSettleFlag;
	}
	public void setOtherSettleFlag(Integer otherSettleFlag) {
		this.otherSettleFlag = otherSettleFlag;
	}
	public Integer getAllowRetSign() {
		return allowRetSign;
	}
	public void setAllowRetSign(Integer allowRetSign) {
		this.allowRetSign = allowRetSign;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public AccKinds getAccKinds() {
		return accKinds;
	}
	public void setAccKinds(AccKinds accKinds) {
		this.accKinds = accKinds;
	}
	
	
}
