package com.paySystem.ic.bean.base;

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
 * @ClassName:DeliveryOrders.java
 * @Description:发货信息实体
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "O_DeliveryOrders")
public class DeliveryOrders implements Serializable {
	private static final long serialVersionUID = 3970336097852824875L;
	
	/**
	 * 自增id
	 */
	private long doId;
	
	/**
	 * 发货状态
	 *  0：未发货；
		1：发货中； 
		2：已发货
	 */
	private Integer status;
	
	/**
	 * 商户merId
	 */
	private String merId;
	
	/**
	 * 买家
	 */
	private String memId;
	
	/**
	 * 收货人姓名
	 */
	private String memName;
	
	/**
	 * 收货人电话
	 */
	private String memTele;
	
	/**
	 * 收货地址
	 */
	private String address;
	
	/**
	 * 发货地址
	 */
	private String merAddress;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	
	/**
	 * 商品数量
	 */
	private Integer qty;
	
	/**
	 * 下单时间
	 */
	private Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public long getDoId() {
		return doId;
	}

	public void setDoId(long doId) {
		this.doId = doId;
	}

	@Column(length=1,nullable=false)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length=15,nullable=false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length=10,nullable=false)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	@Column(length=15,nullable=false)
	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Column(length=11,nullable=false)
	public String getMemTele() {
		return memTele;
	}

	public void setMemTele(String memTele) {
		this.memTele = memTele;
	}

	@Column(length=255,nullable=false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(length=255,nullable=false)
	public String getMerAddress() {
		return merAddress;
	}

	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
	}

	@Column(length=16,nullable=false)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(length=60,nullable=false)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(nullable=false,scale=4,precision=13)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(length=5,nullable=false)
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Column(nullable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
