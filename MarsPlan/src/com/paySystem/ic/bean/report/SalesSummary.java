package com.paySystem.ic.bean.report;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @ProjectName:MCIU_DS
 * @ClassName:SalesSummary
 * @Description:销售额汇总实体
 * @date: 2014-7-16
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "R_SalesSummary")
public class SalesSummary implements Serializable {

	private static final long serialVersionUID = -1493976990387521909L;

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
	private Date lastTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 50)
	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	@Column(columnDefinition = "DECIMAL(15,2)")
	public Integer getSalesQty() {
		return salesQty;
	}

	public void setSalesQty(Integer salesQty) {
		this.salesQty = salesQty;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getSalesAmt() {
		return salesAmt;
	}

	public void setSalesAmt(BigDecimal salesAmt) {
		this.salesAmt = salesAmt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}
