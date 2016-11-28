package com.paySystem.ic.bean.shopcar;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_shopcar")
public class ShopCar implements Serializable {

	//
	private static final long serialVersionUID = 7339902691115878742L;
	private Integer id;
	private Integer goodsId;
	private Integer count;
	private String goodsName;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public Integer getId() {
		return id;
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

	public void setPrice(float price) {
		this.price = price;
	}

}
