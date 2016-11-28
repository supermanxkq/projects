package com.paySystem.ic.bean.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:Orders
 * @Description:订单表实体 (括号内为新增内容)
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "O_Orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 4026724528959127766L;

	/** 订单编号 */
	private String orderId;
	/** 商户Id */
	private String merId;

	/** (买家昵称) */
	private String memName;
	/**
	 * 订单状态 0:代付款 1：待确定 2：订单确认 3：待发货 4：已发货 5：订单撤销 6：订单成功 7：订单失败 (8:退货中 9：退货成功 )
	 */
	private Integer status;
	/** 邮费 */
	private BigDecimal postAmt;
	/** 实际支付金额 */
	private BigDecimal paidAmt;
	/** 买家 */
	private String memId;
	/**
	 * 评价状态 0:未评价 1：已评价
	 */
	private Integer criticalStatus;
	/** 下单时间及交易完成时间 */
	private Date orderTime;

	/** (操作时间时间 每次操作订单的时间 ) */
	private Date operTime;
	/** (延长收货天数) */
	private Integer extendDays;
	/** （是否关闭 0 否 1 是） */
	private Integer isClose;

	/** 平台订单号 */
	private String tadeId;
	/**
	 * 投诉状态 0 未投诉 1 投诉中 2 投诉完成
	 */
	private Integer ordCompType;

	/** 手续费 */
	private BigDecimal sxf;

	@Id
	@Column(length = 16)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 15)
	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition = "DECIMAL(15,4)")
	public BigDecimal getPostAmt() {
		return postAmt;
	}

	public void setPostAmt(BigDecimal postAmt) {
		this.postAmt = postAmt;
	}

	@Column(columnDefinition = "DECIMAL(15,4)")
	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	@Column(length = 10)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getCriticalStatus() {
		return criticalStatus;
	}

	public void setCriticalStatus(Integer criticalStatus) {
		this.criticalStatus = criticalStatus;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getIsClose() {
		return isClose;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(length = 2)
	public Integer getExtendDays() {
		return extendDays;
	}

	public void setExtendDays(Integer extendDays) {
		this.extendDays = extendDays;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getOrdCompType() {
		return ordCompType;
	}

	public void setOrdCompType(Integer ordCompType) {
		this.ordCompType = ordCompType;
	}

	@Column(length = 18)
	public String getTadeId() {
		return tadeId;
	}

	public void setTadeId(String tadeId) {
		this.tadeId = tadeId;
	}

	@Column(columnDefinition = "DECIMAL(13,4)")
	public BigDecimal getSxf() {
		return sxf;
	}

	public void setSxf(BigDecimal sxf) {
		this.sxf = sxf;
	}
}
