package com.paySystem.ic.web.dto.shopcar;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class ShopCarDTO extends BaseDTO implements Serializable {

	//
	private static final long serialVersionUID = 7339902691115878742L;
	private Integer id;
	private Integer goodsId;
	private Integer count;
	private String goodsName;
	private float price;
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public Integer getId() {
		return id;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
