package com.paySystem.ic.web.dto.stock;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

//张国法

public class StockAdjustmentDTO extends BaseDTO {

	//库存编号
	private Integer stockId;
	//加油站编号
	private String fillStationId;
	// 预警比例
	private BigDecimal warnProportIon ;
	//商户编号
	private String merId;
	//调整油品类型
	private Integer oilType;
	//调整库存油量
	private Integer stockOilAmoUnt;
	//创建时间 
	private Date createTime;
	//审核时间 
	private Date auditTime;
	//备注
	private String descR;
	//商户联系人
	private String merName;
	/** 商户手机号 */
	private String teleNo;
	private Integer status;
	/**
	 * 操作发生时间段-开始时间
	 */
	private String beginDate;
	/**
	 * 操作发生时间
	 */
	private String endDate;
	
	
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

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getFillStationId() {
		return fillStationId;
	}
	public void setFillStationId(String fillStationId) {
		this.fillStationId = fillStationId;
	}
	public BigDecimal getWarnProportIon() {
		return warnProportIon;
	}
	public void setWarnProportIon(BigDecimal warnProportIon) {
		this.warnProportIon = warnProportIon;
	}

	public Integer getOilType() {
		return oilType;
	}
	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}
	public Integer getStockOilAmoUnt() {
		return stockOilAmoUnt;
	}
	public void setStockOilAmoUnt(Integer stockOilAmoUnt) {
		this.stockOilAmoUnt = stockOilAmoUnt;
	}
	public String getDescR() {
		return descR;
	}
	public void setDescR(String descR) {
		this.descR = descR;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStockId() {
		return stockId;
	}

	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
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
