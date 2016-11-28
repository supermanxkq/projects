package com.paySystem.ic.bean.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

@Entity
@Table(name = "t_orderDetail")
public class OrderDetail {
	private Integer id;
	private Integer orderId;
	private Integer count;
	private Integer goodsId;
	private String  goodsName;
	private float price;
	
	
	@Column
	public Integer getCount() {
		return count;
	}
	@Column
	public Integer getGoodsId() {
		return goodsId;
	}
	@Column
	public String getGoodsName() {
		return goodsName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column
	public Integer getOrderId() {
		return orderId;
	}
	@Column
	public float getPrice() {
		return price;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
