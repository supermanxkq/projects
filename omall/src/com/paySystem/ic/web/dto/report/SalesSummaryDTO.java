package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class SalesSummaryDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -7412939225750267805L;

	/** 编号id */
	private Integer saleId;
	/** 商户编号 */
	private String merId;
	/** 商户名称 */
	private String merName;
	/** 成交数量 */
	private Integer salesQty;
	/** 销售金额 */
	private BigDecimal salesAmt;
	/** 成交时间 */
	private String lastTime;
	/** 起始时间 */
	private String beginDate;
	/** 终止时间 */
	private String endDate;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
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

	public Integer getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(Integer salesQty) {
		this.salesQty = salesQty;
	}

	public BigDecimal getSalesAmt() {
		return salesAmt;
	}

	public void setSalesAmt(BigDecimal salesAmt) {
		this.salesAmt = salesAmt;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
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

}
