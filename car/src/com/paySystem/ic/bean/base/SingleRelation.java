package com.paySystem.ic.bean.base;

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


/**
 * 收单关系实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="B_SinRel")
public class SingleRelation implements Serializable {

	private static final long serialVersionUID = -7660685794009797768L;
	
	private String sinRelId;
	/**关系名称*/
	private String sinRelName;
	/**关系对应商户*/
	private Merchants merchants;
	/**是否主机构 0:是 1：非*/
	private Integer mainOrganSign;
	/**关系对应机构：发卡机构*/
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
	/**消费是否赠送积分 0:否 , 1 是*/
	//private Integer givePointSign;
	/**手续费率（按金额）*/
	private BigDecimal rateFee;
	/**单笔手续费（按笔数）*/
	private BigDecimal asinTranRateFee;
	/**手续费上限*/
	private BigDecimal feeTopLimit;
	/**服务平台分成比率*/
	private BigDecimal servPlatformRatio;
	/**收单机构分成比率*/
	private BigDecimal acquirerRate;
	/**发卡机构分成比率*/
	private BigDecimal organRate;
	/**计算结算方式*/
	private Integer countSettType;
	/**允许消费的卡BIN*/
	//private CardBIN cardBin;
    /**更新时间*/
	private Date updateTime;
    /**创建时间*/
	private Date createTime;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	
	@Id
	@Column(length=15)
	public String getSinRelId() {
		return sinRelId;
	}
	public void setSinRelId(String sinRelId) {
		this.sinRelId = sinRelId;
	}
	@Column( name = "NAME",length=60)
	public String getSinRelName() {
		return sinRelName;
	}
	public void setSinRelName(String sinRelName) {
		this.sinRelName = sinRelName;
	}
	//单向一对一注解
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column
	public Integer getMainOrganSign() {
		return mainOrganSign;
	}
	public void setMainOrganSign(Integer mainOrganSign) {
		this.mainOrganSign = mainOrganSign;
	}
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="organId")
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="sinOrganId")
	public Organs getSinOrgans() {
		return sinOrgans;
	}
	public void setSinOrgans(Organs sinOrgans) {
		this.sinOrgans = sinOrgans;
	}
	@Column
	public Integer getMehodOfSett() {
		return mehodOfSett;
	}
	public void setMehodOfSett(Integer mehodOfSett) {
		this.mehodOfSett = mehodOfSett;
	}
	@Column(columnDefinition="DECIMAL(5,4)")
	public BigDecimal getRateFee() {
		return rateFee;
	}
	public void setRateFee(BigDecimal rateFee) {
		this.rateFee = rateFee;
	}
	@Column(name="ATRANRATE",columnDefinition="DECIMAL(13,4)")
	public BigDecimal getAsinTranRateFee() {
		return asinTranRateFee;
	}
	public void setAsinTranRateFee(BigDecimal asinTranRateFee) {
		this.asinTranRateFee = asinTranRateFee;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getFeeTopLimit() {
		return feeTopLimit;
	}
	public void setFeeTopLimit(BigDecimal feeTopLimit) {
		this.feeTopLimit = feeTopLimit;
	}
	@Column(name="SERVPLATRATE",columnDefinition="DECIMAL(5,2)")
	public BigDecimal getServPlatformRatio() {
		return servPlatformRatio;
	}
	public void setServPlatformRatio(BigDecimal servPlatformRatio) {
		this.servPlatformRatio = servPlatformRatio;
	}
	@Column(name="ACQRATE",columnDefinition="DECIMAL(5,2)")
	public BigDecimal getAcquirerRate() {
		return acquirerRate;
	}
	public void setAcquirerRate(BigDecimal acquirerRate) {
		this.acquirerRate = acquirerRate;
	}
	@Column(name="ORGRATE",columnDefinition="DECIMAL(5,2)")
	public BigDecimal getOrganRate() {
		return organRate;
	}
	public void setOrganRate(BigDecimal organRate) {
		this.organRate = organRate;
	}
	@Column(length=1)
	public Integer getCountSettType() {
		return countSettType;
	}
	public void setCountSettType(Integer countSettType) {
		this.countSettType = countSettType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	/*@JoinTable (//关联表
            name =  "student_teacher" , //关联表名
            inverseJoinColumns =  @JoinColumn (name =  "teacher_id" ),//被维护端外键
            joinColumns =  @JoinColumn (name =  "student_id" ))//维护端外键被维护端注解
    */
/*	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="binId")
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	@Column(length=1)
	public Integer getStatus() {
		return status;
	}*/
	@Column
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
/*	@Column(columnDefinition="char(1)")
	public Integer getGivePointSign() {
		return givePointSign;
	}
	public void setGivePointSign(Integer givePointSign) {
		this.givePointSign = givePointSign;
	}
	*/
	
	/*public Set<CardBIN> getBins() {
		return bins;
	}
	public void setBins(Set<CardBIN> bins) {
		this.bins = bins;
	}*/
    
    
}
