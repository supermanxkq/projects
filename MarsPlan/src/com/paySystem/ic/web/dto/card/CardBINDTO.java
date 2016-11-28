package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.BaseDTO;

public class CardBINDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = -1324112849666702193L;

	/**卡BIN号*/
	private String binId;
	/** 卡名称*/
	private String binName;
	/** 卡标志
	 * 0、CPU卡
	 * 1、磁条卡
	 * */
	private Integer binSign;

	/**卡类别
	 * 0、专卡
	 * 1、通卡
	 * */
	private Integer binType;
	/**面值*/
	private BigDecimal initAmt;
	/**卡内额度上限*/
	private BigDecimal limitAmt;
	/**单笔消费金额上限*/
	private BigDecimal singleConsAmt;
	/** 单笔充值金额上限*/
	private BigDecimal singleRechAmt;
	/**日累计交易次数上限*/
	private Integer dayConsTimes;
	/**日累计交易金额上限*/
	private BigDecimal dayConsAmtLimt;

	/** 描述*/
	private String descr;
	/**状态
	 1、启用  0、禁用 9、删除*/
	private Integer status;
  
	
    /**修改时间*/
    private Date updateTime;
    
    /**创建时间*/
    private Date createTime;
    /**
     * 单笔消费金额预警
     * */
	private BigDecimal singleConsWorn;
	/**
	 * 单笔充值金额预警
	 * */
	private BigDecimal singleRechWorn;
   
    /**查询条件：机构名称*/
	@Transient
	private String orgName;
	
	private Organs Organs;
	
	
	private String organId;
	
	/**
	 * 该卡BIN下属卡片显示卡号长度
	 * */
	private Integer dispNoLen;
	
	public Organs getOrgans() {
		return Organs;
	}
	public void setOrgans(Organs organs) {
		Organs = organs;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public Integer getBinSign() {
		return binSign;
	}
	public void setBinSign(Integer binSign) {
		this.binSign = binSign;
	}
	public Integer getBinType() {
		return binType;
	}
	public void setBinType(Integer binType) {
		this.binType = binType;
	}
	public BigDecimal getInitAmt() {
		return initAmt;
	}
	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}
	public BigDecimal getLimitAmt() {
		return limitAmt;
	}
	public void setLimitAmt(BigDecimal limitAmt) {
		this.limitAmt = limitAmt;
	}
	public BigDecimal getSingleConsAmt() {
		return singleConsAmt;
	}
	public void setSingleConsAmt(BigDecimal singleConsAmt) {
		this.singleConsAmt = singleConsAmt;
	}
	public BigDecimal getSingleRechAmt() {
		return singleRechAmt;
	}
	public void setSingleRechAmt(BigDecimal singleRechAmt) {
		this.singleRechAmt = singleRechAmt;
	}
	public Integer getDayConsTimes() {
		return dayConsTimes;
	}
	public void setDayConsTimes(Integer dayConsTimes) {
		this.dayConsTimes = dayConsTimes;
	}
	public BigDecimal getDayConsAmtLimt() {
		return dayConsAmtLimt;
	}
	public void setDayConsAmtLimt(BigDecimal dayConsAmtLimt) {
		this.dayConsAmtLimt = dayConsAmtLimt;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public BigDecimal getSingleConsWorn() {
		return singleConsWorn;
	}
	public void setSingleConsWorn(BigDecimal singleConsWorn) {
		this.singleConsWorn = singleConsWorn;
	}
	public BigDecimal getSingleRechWorn() {
		return singleRechWorn;
	}
	public void setSingleRechWorn(BigDecimal singleRechWorn) {
		this.singleRechWorn = singleRechWorn;
	}
	public Integer getDispNoLen() {
		return dispNoLen;
	}
	public void setDispNoLen(Integer dispNoLen) {
		this.dispNoLen = dispNoLen;
	}
	
}
