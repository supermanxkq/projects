package com.paySystem.ic.web.dto.abnormal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.bean.account.AccKinds;
import com.paySystem.ic.web.dto.BaseDTO;

public class AbnorTradesDTO extends BaseDTO implements Serializable {
	/**
	 * 流水ID号
	 * */
	private String tradeId;
	/**
	 * 批次号
	 */
	private String batchId;

	/**
	 * 商户id
	 */
	private String merId;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 终端ID
	 */
	private String termId;
	/**
	 * 终端名称
	 */
	private String termName;
	/**
	 * 交易类型 1 退货 2 充值 3 充值冲正 4 充值撤销 　 5 充值撤销冲正 　 13 修改密码 　 14 查询卡状态 　 15 消费 　 16
	 * 消费冲正 　 17 消费撤销 　 18 消费撤销冲正 　20 积分消费 　21 积分消费冲正 　22 积分消费撤销 　23 积分消费撤销冲正
	 * */
	private Integer tradeType;
	/**
	 * 交易时间
	 */
	private String placedTime;

	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 更新日期
	 */
	private Date updateTime;
	/**
	 * 实际交易金额/积分
	 */
	private BigDecimal tradeAmt;
	/**
	 * 交易产生积分
	 */
	private BigDecimal points;
	/**
	 * 手续费
	 */
	private BigDecimal sxf;
	/**
	 * 结算金额(交易金额 — 手续费)
	 */
	private BigDecimal proxyAmt;
	/**
	 * 交易账户类型 0现金 1积分
	 * */
	private Integer accTypeId;
	private AccKinds accKinds;

	/**
	 * 交易起期
	 */
	private String beginDate;
	/**
	 * 交易止期
	 */
	private String endDate;
	/**
	 * 累计记录号
	 */
	private Integer id;

	/**
	 * 卡号
	 */
	private String cardNo;

	/**
	 * 处理人
	 */
	private String dealMen;
	/**
	 * 处理时间
	 */
	private Date dealTime;
	/**
	 * 处理描述
	 */
	private String dealDesc;
	/**
	 * 异常类型
	 * 1金额异常
	 * 2卡号异常
	 */
	private String AbnorType;
	/**
	 * 上调整总数
	 */
	private BigDecimal upAmt;
	/**
	 * 下调整总数
	 */
	private BigDecimal downAmt;
	
	/** 原价 */
	private BigDecimal retailAmt;

	

	public BigDecimal getRetailAmt() {
		return retailAmt;
	}

	public void setRetailAmt(BigDecimal retailAmt) {
		this.retailAmt = retailAmt;
	}

	public String getPlacedTime() {
		return placedTime;
	}

	public void setPlacedTime(String placeTime) {
		this.placedTime = placeTime;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public AccKinds getAccKinds() {
		return accKinds;
	}

	public void setAccKinds(AccKinds accKinds) {
		this.accKinds = accKinds;
	}

	public BigDecimal getUpAmt() {
		return upAmt;
	}

	public void setUpAmt(BigDecimal upAmt) {
		this.upAmt = upAmt;
	}

	public BigDecimal getDownAmt() {
		return downAmt;
	}

	public void setDownAmt(BigDecimal downAmt) {
		this.downAmt = downAmt;
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

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDealMen() {
		return dealMen;
	}

	public void setDealMen(String dealMen) {
		this.dealMen = dealMen;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public String getAbnorType() {
		return AbnorType;
	}

	public void setAbnorType(String abnorType) {
		AbnorType = abnorType;
	}

	public Integer getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(Integer accTypeId) {
		this.accTypeId = accTypeId;
	}
	

}
