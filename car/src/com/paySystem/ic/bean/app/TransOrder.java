package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:TransOrder
 * @Description:交易订单实体
 * @date: 2014-5-26下午04:29:13
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_transorder")
public class TransOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 订单信息编号 */
	private Integer orderId;
	
	/** 订单金额 */
	private BigDecimal orderAmt;
	
	/** 状态 */
	private Integer status;
	
	/** 会员编号 */
	private String memId;
	
	/** 消费卡号 */
	private String cardId;
	
	/**  订单类型 */
	private Integer orderType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(columnDefinition="DECIMAL(15,4)")
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length=8)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(length=19)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(columnDefinition="char(1)")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	
	
}
