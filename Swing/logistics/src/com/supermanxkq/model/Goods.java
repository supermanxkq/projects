package com.supermanxkq.model;


import java.util.Date;
/**
 * 商品实体类
 * @author xukaiqiang
 *
 */
public class Goods {

	// 编号
	private int id;
	// 商品名称
	private String goodsName;
	// 价格
	private float price;
	// 数量
	private Integer count;
	// 型号
	private String model;
	// 商家
	private String merchant;
	// 状态
	private Integer status;
	// 进货日期
	private Date purchaseDate;
	// 描述
	private String goodsDesc;

	@Override
	public String toString() {
		return goodsName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

}
