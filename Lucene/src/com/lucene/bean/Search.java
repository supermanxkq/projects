package com.lucene.bean;

import java.io.Serializable;

/**
 *   查询索引返回结果实体类
 * 
 * @ClassName:Searche
 * @Description: 查询实体类
 * @date: 2014-10-23上午11:22:12
 * @author: 谢洪飞
 * @version: V1.0
 */
public class Search implements Serializable{

	private static final long serialVersionUID = -8649990015548740985L;
	
	/** 检索数据ID */
	private String id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 跳转url地址 */
	private String url;
	/** 类型 */
	private String type;
	/** 图片路径 */
	private String picPath;
	/** 价格  */
	private String price;
	/** 市场价格 */
	private String marketPrice;
	/** 店铺名称 */
	private String storeName;
	/**店铺编号*/
	private String storeId;
    /**店铺老板*/
	private String storeOwnner;
	/**商品总量*/
	private String goodsTcount;
	/** 省份名称 */
	private String proName;
	/**城市名称*/
	private String cityName;
	/** 商品分类ID */
	private String typeId;
	/** 商品分类名称*/
	private String typeName;
	/** 销量 */
	private String saleCount;

	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreOwnner() {
		return storeOwnner;
	}
	public void setStoreOwnner(String storeOwnner) {
		this.storeOwnner = storeOwnner;
	}
	public String getGoodsTcount() {
		return goodsTcount;
	}
	public void setGoodsTcount(String goodsTcount) {
		this.goodsTcount = goodsTcount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(String saleCount) {
		this.saleCount = saleCount;
	}
	
	
}
