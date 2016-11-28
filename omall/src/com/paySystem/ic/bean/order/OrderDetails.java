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
 * @ProjectName:omall
 * @ClassName:OrderDetails
 * @Description:订单详情表实体
 * @date: 2014-10-12
 * @author: luckygroup
 * @version: V1.0
 */
@Entity
@Table(name = "O_OrderDetails")
public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 4026724528959127766L;

	/** 订单编号 */
	private String orderId;
	/** 买家 */
	private String memId;
	/** 收货地址 */
	private String address;
	/** 买家电话 */
	private String memTele;
	/** 物流id */
	private Integer logistId;
	/**
	 * 发货方式 0 无需物流 1 自己联系
	 * */
	private String deliveryWay;
	/** 发货地址 */
	private String sendAddress;
	/** 退货地址 */
	private String returnAddress;
	/** 运单号 */
	private String waybillNo;
	/** 支付方式 支付接口表id */
	private String payWay;
	/**
	 * 支付状态 0:未支付 1：已支付
	 */
	private String payStatus;

	/** 订单金额 */
	private BigDecimal orderAmt;
	/** 积分 */
	private BigDecimal integral;

	/** 邮费 */
	private BigDecimal postAmt;
	/** 实际支付金额 */
	private BigDecimal paidAmt;

	/** 下单时间及交易完成时间 */
	private Date orderTime;
	/** (买家真实姓名) */
	private String memRealName;
	/** (买家邮件) */
	private String mail;
	/** (交易号) */
	private String payTradeId;
	/** (支付账号) */
	private String payAccount;
	/***/
	private String leaveWord;

	@Id
	@Column(length = 16)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(columnDefinition = "DECIMAL(15,4)")
	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	@Column(columnDefinition = "DECIMAL(9,4)")
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Column(length = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length = 32)
	public String getMemTele() {
		return memTele;
	}

	public void setMemTele(String memTele) {
		this.memTele = memTele;
	}

	@Column(length = 10)
	public Integer getLogistId() {
		return logistId;
	}

	public void setLogistId(Integer logistId) {
		this.logistId = logistId;
	}

	@Column(length = 1)
	public String getDeliveryWay() {
		return deliveryWay;
	}

	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}

	@Column(length = 255)
	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	@Column(length = 255)
	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

	@Column(length = 15)
	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	@Column(length = 10)
	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	@Column(length = 1)
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@Column(columnDefinition = "DECIMAL(15,4)")
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	@Column(length = 15)
	public String getMemRealName() {
		return memRealName;
	}

	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}

	@Column(length = 15)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(length = 16)
	public String getPayTradeId() {
		return payTradeId;
	}

	public void setPayTradeId(String payTradeId) {
		this.payTradeId = payTradeId;
	}

	@Column(length = 16)
	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	@Column(length = 255)
	public String getLeaveWord() {
		return leaveWord;
	}

	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}

}
