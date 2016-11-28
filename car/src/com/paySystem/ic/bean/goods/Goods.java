package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ProjectName:car
 * @ClassName:Goods
 * @Description:商品信息实体类
 * @date: 2016年2月7日下午1:04:08
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:04:08
 */
@Entity
@Table(name = "t_goods")
public class Goods   implements Serializable{

	//
	private static final long serialVersionUID = 7339902691115878742L;
	// 编号
	private Integer id;
	// 商品名称
	private String goodsName;
	// 品牌
	private String brand;
	// 型号
	private String model;
	// 分类id
	private Integer goodsTypeId;
	// 分类名称
	private String goodsTypeName;
	// 价格
	private float price;
	// 数量
	private Integer count;
	// 厂家名称
	private String merchant;
	// 商品状态
	private Integer status;
	// 厂家地址
	private String address;
	// 进货日期
	private Date purchaseDate;
	// 描述
	private String goodsDesc;

	@Column
	public String getAddress() {
		return address;
	}

	@Column
	public String getBrand() {
		return brand;
	}

	@Column
	public Integer getCount() {
		return count;
	}

	@Column
	public String getGoodsDesc() {
		return goodsDesc;
	}

	@Column
	public String getGoodsName() {
		return goodsName;
	}
	@Column
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	@Column
	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column
	public String getMerchant() {
		return merchant;
	}

	@Column
	public String getModel() {
		return model;
	}

	@Column
	public float getPrice() {
		return price;
	}

	@Column
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
