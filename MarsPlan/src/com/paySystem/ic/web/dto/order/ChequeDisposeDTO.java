package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class ChequeDisposeDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/***到账处理订单编号*/
	private String disposeId;
	/***订单编号**/
	private String orderId;
	/**到账处理状态**/
	private Integer status;
	/**订单支付类型**/
	private Integer ordType;
	/***到账处理人***/
	private String operatord;
	/***创建时间**/
	private Date createTime;
	/***支付时间***/
	private Date paidTime;
	/***付款银行***/
	private String bank;
	/***付款账号***/
	private String bankAcc;
	/**支付银行账号开卡人**/
	private String bankAccHolder;
	/***审核时间**/
	private Date reviewTime;
	/***审核描述**/
	private String reviewDesc;
	/**订单金额*/
	private BigDecimal orderAmt;
	/***实际支付金额*/
	private BigDecimal payAmt;
	/**所属机构**/
	private String organId;
	/***所属机构名称***/
	private String organName;
	/***到账描述***/
	private String descr;
	
	
	public String getDisposeId() {
		return disposeId;
	}
	public void setDisposeId(String disposeId) {
		this.disposeId = disposeId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrdType() {
		return ordType;
	}
	public void setOrdType(Integer ordType) {
		this.ordType = ordType;
	}
	public String getOperatord() {
		return operatord;
	}
	public void setOperatord(String operatord) {
		this.operatord = operatord;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAcc() {
		return bankAcc;
	}
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}
	public String getBankAccHolder() {
		return bankAccHolder;
	}
	public void setBankAccHolder(String bankAccHolder) {
		this.bankAccHolder = bankAccHolder;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getReviewDesc() {
		return reviewDesc;
	}
	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
