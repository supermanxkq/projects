package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 商户充值限额DTObean 
 * @author xie
 *
 */
public class MerRechargeLimitDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 2645274462616083256L;
	/** 业务流水号 */
	private String rechLimId;
	
	/**	商户编号  */
	private Merchants merchants;
	/** 商户充值限额 */
	private BigDecimal rechargeLimit = new BigDecimal(0.00);
	/** 日充值限额 */
	private BigDecimal dayRecLimit = new BigDecimal(0.00); 
    /** 月充值限额 */
	private BigDecimal monthReclLimit = new BigDecimal(0.00);
	/** 单笔充值限额 */
	private BigDecimal oddRecLimit = new BigDecimal(0.00);
	/** 更新时间 */
	private Date updateTime;
	/** 限定方式 0：固定，1：灵活*/
	private Integer limitType = 0;
	/** 商户是否限额  0:否 ，1：是*/
	private Integer isLimitSign;
	
	/** 查询条件 - 商户编号*/
	private String merId;
	/**商户名称*/
	private String merName;
	
	
	public String getRechLimId() {
		return rechLimId;
	}
	public void setRechLimId(String rechLimId) {
		this.rechLimId = rechLimId;
	}
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	public BigDecimal getRechargeLimit() {
		return rechargeLimit;
	}
	public void setRechargeLimit(BigDecimal rechargeLimit) {
		this.rechargeLimit = rechargeLimit;
	}
	public BigDecimal getDayRecLimit() {
		return dayRecLimit;
	}
	public void setDayRecLimit(BigDecimal dayRecLimit) {
		this.dayRecLimit = dayRecLimit;
	}
	public BigDecimal getMonthReclLimit() {
		return monthReclLimit;
	}
	public void setMonthReclLimit(BigDecimal monthReclLimit) {
		this.monthReclLimit = monthReclLimit;
	}
	public BigDecimal getOddRecLimit() {
		return oddRecLimit;
	}
	public void setOddRecLimit(BigDecimal oddRecLimit) {
		this.oddRecLimit = oddRecLimit;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getLimitType() {
		return limitType;
	}
	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}
	public Integer getIsLimitSign() {
		return isLimitSign;
	}
	public void setIsLimitSign(Integer isLimitSign) {
		this.isLimitSign = isLimitSign;
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
	
	

}
