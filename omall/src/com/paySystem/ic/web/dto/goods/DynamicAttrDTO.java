package com.paySystem.ic.web.dto.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.paySystem.ic.web.dto.base.KeyValue;

/**
 * 动态属性DTO
 * 
 * @ClassName: DynamicAttrDTO
 * @Description: 动态属性DTO
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class DynamicAttrDTO {

	/** 用于保存前台传过来的动态属性key,value **/
	List<KeyValue> attrNameValueList = new ArrayList<KeyValue>();

	/** 商品库存 **/
	private String stockNo;

	/** 商品店铺价格 **/
	private BigDecimal goodsPrice;

	/** 商品活动价格 **/
	private BigDecimal proGoodsPrice;

	/** 商品市场价格 **/
	private BigDecimal marketGoodsPrice;
	/** 商品进价 */
	private BigDecimal purchasePrice;

	/** 库存对应图片url地址 **/
	private String imgUrl;

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public List<KeyValue> getAttrNameValueList() {
		return attrNameValueList;
	}

	public void setAttrNameValueList(List<KeyValue> attrNameValueList) {
		this.attrNameValueList = attrNameValueList;
	}

	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getProGoodsPrice() {
		return proGoodsPrice;
	}

	public void setProGoodsPrice(BigDecimal proGoodsPrice) {
		this.proGoodsPrice = proGoodsPrice;
	}

	public BigDecimal getMarketGoodsPrice() {
		return marketGoodsPrice;
	}

	public void setMarketGoodsPrice(BigDecimal marketGoodsPrice) {
		this.marketGoodsPrice = marketGoodsPrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
