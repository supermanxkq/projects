package com.paySystem.ic.web.dto.buss;

import java.util.Date;

public class MerPromotionDTO {
	
	/**活动编号*/
    private Integer mpid;
    /**活动信息ID*/
    private Integer proid;
    /**商户编号*/
    private String merid;
    /**是否统一折扣标志 
     * 统一折扣  0
     * 非统一折扣 1
     * */
    private String rateSign;
    /**统一折扣率*/
    private Double suggRate;
    /**商户活动规则说明*/
    private String descr;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    /**操作人Id*/
    private String operatorId;
    /**计划参与商品的数量*/
    private Integer applyGoodsQuantity; 
    
	public Integer getMpid() {
		return mpid;
	}
	public void setMpid(Integer mpid) {
		this.mpid = mpid;
	}
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public String getMerid() {
		return merid;
	}
	public void setMerid(String merid) {
		this.merid = merid;
	}
	public String getRateSign() {
		return rateSign;
	}
	public void setRateSign(String rateSign) {
		this.rateSign = rateSign;
	}
	public Double getSuggRate() {
		return suggRate;
	}
	public void setSuggRate(Double suggRate) {
		this.suggRate = suggRate;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
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
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	public Integer getApplyGoodsQuantity() {
		return applyGoodsQuantity;
	}
	
	public void setApplyGoodsQuantity(Integer applyGoodsQuantity) {
		this.applyGoodsQuantity = applyGoodsQuantity;
	}
    
}
