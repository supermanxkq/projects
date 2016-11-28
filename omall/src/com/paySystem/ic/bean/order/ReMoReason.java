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
 * @ClassName: ReMoReason
 * @Description: 退款申请表
 * @date: 2014-11-12 下午03:30:50
 * @author: 王少辉
 * @version: V1.0
 */
@Entity
@Table(name = "B_ReMoReason")
public class ReMoReason implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2569627171397704063L;

	/**
	 * 退款申请Id
	 */
	private Integer reMoReId;

	/**
	 * 订单编号
	 */
	private String orderId;

	/**
	 * 会员编号
	 */
	private String memId;

	/**
	 * 是否收到商品 1：已收到货 0：未收到货
	 */
	private Integer isTakeGoods;

	/**
	 * 退款理由 
	 * 0：不想买了 
	 * 1：未收到货 
	 * 2：衣服有瑕疵 
	 * 3：商品错发、漏发
	 * 4：收到的商品与描述不符
	 */
	private Integer reMoReason;

	/**
	 * 退款理由说明
	 */
	private String reMoDesc;

	/**
	 * 退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	private Integer reMoSta;

	/**
	 * 退款申请时间
	 */
	private Date reMoTime;

	/**
	 * 申请退款金额
	 */
	private BigDecimal returnMoney;

	/**
	 * 实际退款金额
	 */
	private BigDecimal actuReturnMoney;
	
	/**
	 * 店铺编号
	 */
	private String storeId;
	
	/**
	 * 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	private Integer status;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * @return Integer 退款申请Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
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
	 * @return String 会员编号
	 */
	@Column(length = 10)
	public String getMemId() {
		return memId;
	}

	/**
	 * @param memId
	 *            会员编号
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}

	/**
	 * @return Integer 是否收到商品 1：已收到货 0：未收到货
	 */
	@Column(columnDefinition = "char(1)")
	public Integer getIsTakeGoods() {
		return isTakeGoods;
	}

	/**
	 * @param isTakeGoods
	 *            是否收到商品 1：已收到货 0：未收到货
	 */
	public void setIsTakeGoods(Integer isTakeGoods) {
		this.isTakeGoods = isTakeGoods;
	}

	/**
	 * @return Integer 退款理由 
	 * 0：不想买了 
	 * 1：未收到货 
	 * 2：衣服有瑕疵 
	 * 3：商品错发、漏发 
	 * 4：收到的商品与描述不符
	 */
	@Column(columnDefinition = "char(1)")
	public Integer getReMoReason() {
		return reMoReason;
	}

	/**
	 * @param reMoReason
	 *            退款理由 
	 *            0：不想买了 
	 *            1：未收到货 
	 *            2：衣服有瑕疵 
	 *            3：商品错发、漏发 
	 *            4：收到的商品与描述不符
	 */
	public void setReMoReason(Integer reMoReason) {
		this.reMoReason = reMoReason;
	}

	/**
	 * @return String 退款理由说明
	 */
	@Column(length = 255)
	public String getReMoDesc() {
		return reMoDesc;
	}

	/**
	 * @param reMoDesc
	 *            退款理由说明
	 */
	public void setReMoDesc(String reMoDesc) {
		this.reMoDesc = reMoDesc;
	}

	/**
	 * @return Integer 退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	@Column(columnDefinition = "char(1)")
	public Integer getReMoSta() {
		return reMoSta;
	}

	/**
	 * @param reMoSta
	 *            退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	public void setReMoSta(Integer reMoSta) {
		this.reMoSta = reMoSta;
	}

	/**
	 * @return Date 退款申请时间
	 */
	@Column
	public Date getReMoTime() {
		return reMoTime;
	}

	/**
	 * @param reMoTime
	 *            退款申请时间
	 */
	public void setReMoTime(Date reMoTime) {
		this.reMoTime = reMoTime;
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
	 * @return String 店铺编号
	 */
	@Column(length=15)
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId 店铺编号
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return Integer 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

}
