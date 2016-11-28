package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 *  收单关系管理DTO Bean
 * @author xie
 * @version 1.0
 */

public class SingleRelationDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 4723311199309459872L;
	
	private String sinRelId;
	/**关系名称*/
	private String sinRelName;
	/**关系对应商户*/
	//private Terminals terminals;
	/**是否主机构*/
	private Integer mainOrganSign;
	/**关系对应机构:发卡机构*/
	private Organs organs;
	/**关系对应机构：收单机构*/
	private Organs sinOrgans;
	/**
	 * 结算方式
	 * 0, "按消费金额"
	 * 1, "按交易笔数"
	 * 2, "无需结算"
	 */
	private Integer mehodOfSett;
	/**手续费率（按金额）*/
	private BigDecimal rateFee = new BigDecimal(0.0);
	/**单笔手续费率（按笔数）*/
	private BigDecimal asinTranRateFee = new BigDecimal(0.0);
	/**手续费上限*/
	private BigDecimal feeTopLimit = new BigDecimal(0.0);
	/**服务平台分成比率*/
	private BigDecimal servPlatformRatio = new BigDecimal(0);
	/**收单机构分成比率*/
	private BigDecimal acquirerRate = new BigDecimal(0);
	/**发卡机构分成比率*/
	private BigDecimal organRate = new BigDecimal(10);
	/**计算结算方式*/
	private Integer countSettType;
	/**允许消费的卡BIN*/
	
	/*private Set<CardBIN> bins;*/
	private String bins;
    /**更新时间*/
	private Date updateTime;
    /**创建时间*/
	private Date createTime;
	/**商户名称 ——查询条件*/
	private String merName;
	/**机构名称*/
	private String organName;
	/**商户编号*/
	private String merId;
	/**机构编号:发卡机构*/
	private String organId;
	
	/**机构编号：收单机构*/
	private String sinOrganId;
	
	private String sinOrganName;
	
	private Integer status;
	/**消费是否赠送积分下拉框字符串*/
	private String isSalePointStr;
	/**消费是否赠送积分*/
	private Integer isSalePoint;
	/**消费是否赠送积分 获取的集合*/
	private List<Integer> isSalePoints;
	private List<String> cardBins;
	private List<String> cardBinStatuss;
	private String cardBin;	
	private String cardName;
	private String cardBinStatus;
	
	
		
	
	public String getCardBinStatus() {
		return cardBinStatus;
	}
	public void setCardBinStatus(String cardBinStatus) {
		this.cardBinStatus = cardBinStatus;
	}
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getIsSalePointStr() {
		return isSalePointStr;
	}
	public void setIsSalePointStr(String isSalePointStr) {
		this.isSalePointStr = isSalePointStr;
	}
	public Integer getIsSalePoint() {
		return isSalePoint;
	}
	public void setIsSalePoint(Integer isSalePoint) {
		this.isSalePoint = isSalePoint;
	}
	public List<Integer> getIsSalePoints() {
		return isSalePoints;
	}
	public void setIsSalePoints(List<Integer> isSalePoints) {
		this.isSalePoints = isSalePoints;
	}
	public List<String> getCardBins() {
		return cardBins;
	}
	public void setCardBins(List<String> cardBins) {
		this.cardBins = cardBins;
	}
	public List<String> getCardBinStatuss() {
		return cardBinStatuss;
	}
	public void setCardBinStatuss(List<String> cardBinStatuss) {
		this.cardBinStatuss = cardBinStatuss;
	}
	public String getSinRelId() {
		return sinRelId;
	}
	public void setSinRelId(String sinRelId) {
		this.sinRelId = sinRelId;
	}
	public String getSinRelName() {
		return sinRelName;
	}
	public void setSinRelName(String sinRelName) {
		this.sinRelName = sinRelName;
	}
	/*public Terminals getTerminals() {
		return terminals;
	}
	public void setTerminals(Terminals terminals) {
		this.terminals = terminals;
	}*/
	public Integer getMainOrganSign() {
		return mainOrganSign;
	}
	public void setMainOrganSign(Integer mainOrganSign) {
		this.mainOrganSign = mainOrganSign;
	}
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
	
	public Organs getSinOrgans() {
		return sinOrgans;
	}
	public void setSinOrgans(Organs sinOrgans) {
		this.sinOrgans = sinOrgans;
	}
	public Integer getMehodOfSett() {
		return mehodOfSett;
	}
	public void setMehodOfSett(Integer mehodOfSett) {
		this.mehodOfSett = mehodOfSett;
	}
	public BigDecimal getRateFee() {
		return rateFee;
	}
	public void setRateFee(BigDecimal rateFee) {
		this.rateFee = rateFee;
	}
	public BigDecimal getAsinTranRateFee() {
		return asinTranRateFee;
	}
	public void setAsinTranRateFee(BigDecimal asinTranRateFee) {
		this.asinTranRateFee = asinTranRateFee;
	}
	public BigDecimal getFeeTopLimit() {
		return feeTopLimit;
	}
	public void setFeeTopLimit(BigDecimal feeTopLimit) {
		this.feeTopLimit = feeTopLimit;
	}
	public BigDecimal getServPlatformRatio() {
		return servPlatformRatio;
	}
	public void setServPlatformRatio(BigDecimal servPlatformRatio) {
		this.servPlatformRatio = servPlatformRatio;
	}
	public BigDecimal getAcquirerRate() {
		return acquirerRate;
	}
	public void setAcquirerRate(BigDecimal acquirerRate) {
		this.acquirerRate = acquirerRate;
	}
	public BigDecimal getOrganRate() {
		return organRate;
	}
	public void setOrganRate(BigDecimal organRate) {
		this.organRate = organRate;
	}
	public Integer getCountSettType() {
		return countSettType;
	}
	public void setCountSettType(Integer countSettType) {
		this.countSettType = countSettType;
	}
	public String getBins() {
		return bins;
	}
	public void setBins(String bins) {
		this.bins = bins;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getSinOrganId() {
		return sinOrganId;
	}
	public void setSinOrganId(String sinOrganId) {
		this.sinOrganId = sinOrganId;
	}
	public String getSinOrganName() {
		return sinOrganName;
	}
	public void setSinOrganName(String sinOrganName) {
		this.sinOrganName = sinOrganName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
