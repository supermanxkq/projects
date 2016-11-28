package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
 * @ProjectName:car
 * @ClassName:Goods
 * @Description:商品信息DTO类
 * @date: 2016年2月7日下午1:04:08
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:04:08
 */
public class GoodsDTO extends BaseDTO implements Serializable {

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
	// 起始日期
	private String beginDate;
	// 结束日期
	private String endDate;
	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAddress() {
		return address;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getCount() {
		return count;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public String getMerchant() {
		return merchant;
	}

	public String getModel() {
		return model;
	}

	public float getPrice() {
		return price;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

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

	public Integer getId() {
		return id;
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
