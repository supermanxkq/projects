package com.paySystem.ic.bean.order;

import java.io.Serializable;
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
 * @ClassName:OrdersRec
 * @Description:订单记录表实体Bean (括号内未新增字段)
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "O_OrdersRec")
public class OrdersRec implements Serializable {

	

	private static final long serialVersionUID = 930506260908270362L;

	/** 记录Id */
	private Integer recId;
	/** 订单编号 */
	private String orderId;
	/**
	 * 订单状态 0:代付款 1：待确定 2：订单确认 3：待发货 4：已发货 5：订单撤销 6：订单成功 7：订单失败
	 */
	private Integer status;
	/** (支付状态
	 * 1 已支付
	 * 0 未支付) 
	 *  */
	private String payStatus;
	/** 操作人 */
	private String operator;
	/** 操作时间 */
	private Date operTime;
	/** 创建时间 */
	private Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@Column(length = 16)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length = 20)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(length = 1)
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

}
