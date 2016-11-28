package com.paySystem.ic.bean.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoney
 * @Description: 退款信息记录表
 * @date: 2014-11-12 下午03:22:02
 * @author: 王少辉
 * @version: V1.0
 */
@Entity
@Table(name = "B_ReMoney")
public class ReMoney implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -4765119631392743405L;

	/**
	 * 退款详情Id
	 */
	private Integer reMoneyId;

	/**
	 * 订单编号
	 */
	private String orderId;

	/**
	 * 退款申请Id
	 */
	private Integer reMoReId;

	/**
	 * 退款状态
	 * 0：退款申请等待卖家确认中
	 * 1：卖家不同意协议，等待买家修改
	 * 2：退款申请达成，等待买家发货
	 * 3：买家已退货，等待卖家确认收货
	 * 4：退款关闭
	 * 5：退款成功
	 */
	private Integer reMoSta;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 实际退款金额
	 */
	private BigDecimal actuReturnMoney;

	/**
	 * 申请退款金额
	 */
	private BigDecimal returnMoney;

	/**
	 * @return Integer 退款详情Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getReMoneyId() {
		return reMoneyId;
	}

	/**
	 * @param reMoneyId
	 *            退款详情Id
	 */
	public void setReMoneyId(Integer reMoneyId) {
		this.reMoneyId = reMoneyId;
	}

	/**
	 * @return String 订单编号
	 */
	@Column(length = 16)
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            订单编号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return Integer 退款申请Id
	 */
	@Column(length = 10)
	public Integer getReMoReId() {
		return reMoReId;
	}

	/**
	 * @param reMoReId
	 *            退款申请Id
	 */
	public void setReMoReId(Integer reMoReId) {
		this.reMoReId = reMoReId;
	}

	/**
	 * @return Integer 退款状态 
	 * 0：退款申请等待卖家确认中 
	 * 1：卖家不同意协议，等待买家修改 
	 * 2：退款申请达成，等待买家发货
	 * 3：买家已退货，等待卖家确认收货 
	 * 4：退款关闭 
	 * 5：退款成功
	 */
	@Column(columnDefinition = "char(1)")
	public Integer getReMoSta() {
		return reMoSta;
	}

	/**
	 * @param reMoSta
	 *            退款状态 
	 *            0：退款申请等待卖家确认中 
	 *            1：卖家不同意协议，等待买家修改 
	 *            2：退款申请达成，等待买家发货
	 *            3：买家已退货，等待卖家确认收货 
	 *            4：退款关闭 
	 *            5：退款成功
	 */
	public void setReMoSta(Integer reMoSta) {
		this.reMoSta = reMoSta;
	}

	/**
	 * @return Date 更新时间
	 */
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return BigDecimal 实际退款金额
	 */
	@Column(columnDefinition = "DECIMAL(9,4)")
	public BigDecimal getActuReturnMoney() {
		return actuReturnMoney;
	}

	/**
	 * @param actuReturnMoney
	 *            实际退款金额
	 */
	public void setActuReturnMoney(BigDecimal actuReturnMoney) {
		this.actuReturnMoney = actuReturnMoney;
	}

	/**
	 * @return BigDecimal 申请退款金额
	 */
	@Column(columnDefinition = "DECIMAL(9,4)")
	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	/**
	 * @param returnMoney
	 *            申请退款金额
	 */
	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}
	
}
