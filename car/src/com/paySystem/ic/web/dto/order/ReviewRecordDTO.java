package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:ReviewRecordDTO
 * @Description:审核订单数据传输对象
 * @date: 2014-2-26下午04:16:46
 * @author: 井建国
 * @version: V1.0
 */
public class ReviewRecordDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**审核订单编号**/ 
	private String revRecId;
	/**订单编号**/ 
	private String orderId;
	/**起始卡号**/ 
	private String beginCardNo;
	/**卡片数量**/ 
	private Integer cardQty;
	/**订单金额**/ 
	private BigDecimal orderAmt;
	/**实际支付金额**/ 
	private BigDecimal piadAmt;
	/**支付方式**/ 
	private Integer paidType;
	/**审核员**/ 
	private String operator;
	/**创建时间**/ 
	private Date createTime;
	/**审核时间**/ 
	private Date revrecTime;
	/**订单类型**/ 
	private Integer ordType;
	/**备注**/ 
	private String descr;
	/**所属机构Id**/ 
	private String orgId;
	/** 所属商户id**/
	private String merId;
	/** 审核状态**/
	private Integer status;

	public String getRevRecId() {
		return revRecId;
	}

	public void setRevRecId(String revRecId) {
		this.revRecId = revRecId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBeginCardNo() {
		return beginCardNo;
	}

	public void setBeginCardNo(String beginCardNo) {
		this.beginCardNo = beginCardNo;
	}

	public Integer getCardQty() {
		return cardQty;
	}

	public void setCardQty(Integer cardQty) {
		this.cardQty = cardQty;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public BigDecimal getPiadAmt() {
		return piadAmt;
	}

	public void setPiadAmt(BigDecimal piadAmt) {
		this.piadAmt = piadAmt;
	}

	public Integer getPaidType() {
		return paidType;
	}

	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getRevrecTime() {
		return revrecTime;
	}

	public void setRevrecTime(Date revrecTime) {
		this.revrecTime = revrecTime;
	}

	public Integer getOrdType() {
		return ordType;
	}

	public void setOrdType(Integer ordType) {
		this.ordType = ordType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
