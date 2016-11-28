package com.paySystem.ic.bean.buss;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 	商户活动信息关联bean
 * @ClassName:MerPromotion
 * @Description:商户活动信息关联
 * @date: 2014-8-18 上午08:29:36
 * @author: luckygoup
 * @version: V1.0
 */
@Entity
@Table(name = "b_merpromotion")
public class MerPromotion implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
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


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getMpid() {
        return this.mpid;
    }
    
    public void setMpid(Integer mpid) {
        this.mpid = mpid;
    }
    
    @Column(name="proid", nullable=false)

    public Integer getProid() {
        return this.proid;
    }
    
    public void setProid(Integer proid) {
        this.proid = proid;
    }
    
    @Column(name="merid", nullable=false, length=15)

    public String getMerid() {
        return this.merid;
    }
    
    public void setMerid(String merid) {
        this.merid = merid;
    }
    
    @Column(name="rateSign", nullable=false, length=1)

    public String getRateSign() {
        return this.rateSign;
    }
    
    public void setRateSign(String rateSign) {
        this.rateSign = rateSign;
    }
    
    @Column(name="suggRate", precision=6, scale=4)

    public Double getSuggRate() {
        return this.suggRate;
    }
    
    public void setSuggRate(Double suggRate) {
        this.suggRate = suggRate;
    }
    
    @Column(name="descr")

    public String getDescr() {
        return this.descr;
    }
    
    public void setDescr(String descr) {
        this.descr = descr;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="createTime", nullable=false, length=10)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="updateTime", nullable=false, length=10)

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="operatorId", nullable=false, length=30)

    public String getOperatorId() {
        return this.operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Column(name="applyGoodsQuantity")
	public Integer getApplyGoodsQuantity() {
		return this.applyGoodsQuantity;
	}

	public void setApplyGoodsQuantity(Integer applyGoodsQuantity) {
		this.applyGoodsQuantity = applyGoodsQuantity;
	}
	
}
