package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.web.dto.BaseDTO;

public class MerSettTotalDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 4186468798084863929L;

	
	/** 结算信息记录号 */
	private Integer id;
	/** 记录所属商户 */	
	private Merchants merchants;
	/** 结算标志
	 *         0:未结算
	           1:已结算 */
	private Integer flag;
	/** 本周期起始时间  */
	private Date beginTime;
	/**本周期结束时间*/
	private Date endTime;
	/** 消费总金额  */
	private BigDecimal consAmt;
	/**消费总手续费*/
	private BigDecimal consCommis;
	/**退货总金额*/
	private BigDecimal returnAmt;
	/** 退货商品 - 原交易手续费 */
	private BigDecimal returnCommis;
	/** 充值总金额 */
	private BigDecimal rechAmt;
	/** 上次结余 */
	private BigDecimal lastBalance;
	/** 本次结算金额 */
	private BigDecimal supportSettAmt;
	/** 实际交付金额 */
	private BigDecimal actualSettAmt;
	/** 本次结余 */
	private BigDecimal thisTimeBalance;
	/** 结算时间 */
	private Date settTime;
	/** 操作人 Id*/
	private String operator;
	/** 更新时间 */
	private Date updateTime;
	
	//-------------------
	private String merId;//商户编号
	//商户名称
	private String merName;
	/** 本次需结算金额 ，经过计算得出*/
	private String needSettAmt;
	
	/**交易次数*/
	private BigDecimal tradeCount;
	
	/**查询条件 -- 开始时间*/
	private String selectBeginDate;
	
	/**查询条件 -- 结束时间*/
	private String selectEndDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Merchants getMerchants() {
		return merchants;
	}

	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getConsAmt() {
		return consAmt;
	}

	public void setConsAmt(BigDecimal consAmt) {
		this.consAmt = consAmt;
	}

	public BigDecimal getConsCommis() {
		return consCommis;
	}

	public void setConsCommis(BigDecimal consCommis) {
		this.consCommis = consCommis;
	}

	public BigDecimal getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}

	public BigDecimal getReturnCommis() {
		return returnCommis;
	}

	public void setReturnCommis(BigDecimal returnCommis) {
		this.returnCommis = returnCommis;
	}

	public BigDecimal getRechAmt() {
		return rechAmt;
	}

	public void setRechAmt(BigDecimal rechAmt) {
		this.rechAmt = rechAmt;
	}

	public BigDecimal getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(BigDecimal lastBalance) {
		this.lastBalance = lastBalance;
	}

	public BigDecimal getSupportSettAmt() {
		return supportSettAmt;
	}

	public void setSupportSettAmt(BigDecimal supportSettAmt) {
		this.supportSettAmt = supportSettAmt;
	}

	public BigDecimal getActualSettAmt() {
		return actualSettAmt;
	}

	public void setActualSettAmt(BigDecimal actualSettAmt) {
		this.actualSettAmt = actualSettAmt;
	}

	public BigDecimal getThisTimeBalance() {
		return thisTimeBalance;
	}

	public void setThisTimeBalance(BigDecimal thisTimeBalance) {
		this.thisTimeBalance = thisTimeBalance;
	}

	public Date getSettTime() {
		return settTime;
	}

	public void setSettTime(Date settTime) {
		this.settTime = settTime;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getNeedSettAmt() {
		return needSettAmt;
	}

	public void setNeedSettAmt(String needSettAmt) {
		this.needSettAmt = needSettAmt;
	}

	/**
	 * @return the tradeCount
	 */
	public BigDecimal getTradeCount() {
		return tradeCount;
	}

	/**
	 * @param tradeCount the tradeCount to set
	 */
	public void setTradeCount(BigDecimal tradeCount) {
		this.tradeCount = tradeCount;
	}

	public String getSelectBeginDate() {
		return selectBeginDate;
	}

	public void setSelectBeginDate(String selectBeginDate) {
		this.selectBeginDate = selectBeginDate;
	}

	public String getSelectEndDate() {
		return selectEndDate;
	}

	public void setSelectEndDate(String selectEndDate) {
		this.selectEndDate = selectEndDate;
	}


}
