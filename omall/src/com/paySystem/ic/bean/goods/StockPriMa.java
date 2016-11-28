package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品库存价格实体bean
 * 
 * @ClassName: StockPriMa
 * @Description: 商品库存价格管理类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_StockPriMa")
public class StockPriMa implements Serializable {
	private static final long serialVersionUID = 8529148131612845930L;

	/** 商品属性值id **/
	private long attrvalId;
	/** 商品编号 **/
	private String goodsId;
	/** id **/
	private int goodsItem;
	/** 商品店铺价格 **/
	private BigDecimal goodsPrice;
	/** 商品市场价格 **/
	private BigDecimal marketGoodsPrice;
	/** 商品活动价格 **/
	private BigDecimal proGoodsPrice;
	/** 商品进价 */
	private BigDecimal purchasePrice;
	/** 商品库存 **/
	private String stockNo;

	@Column(length = 20, nullable = false)
	public long getAttrvalId() {
		return attrvalId;
	}

	@Column(length = 15, nullable = false)
	public String getGoodsId() {
		return goodsId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGoodsItem() {
		return goodsItem;
	}

	@Column(length = 10, nullable = false, scale = 4)
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	@Column(length = 10, nullable = false, scale = 4)
	public BigDecimal getMarketGoodsPrice() {
		return marketGoodsPrice;
	}

	@Column(length = 10, nullable = false, scale = 4)
	public BigDecimal getProGoodsPrice() {
		return proGoodsPrice;
	}

	@Column(length = 10, nullable = false, scale = 4)
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	@Column(length = 50, nullable = false)
	public String getStockNo() {
		return stockNo;
	}

	public void setAttrvalId(long attrvalId) {
		this.attrvalId = attrvalId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public void setGoodsItem(int goodsItem) {
		this.goodsItem = goodsItem;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public void setMarketGoodsPrice(BigDecimal marketGoodsPrice) {
		this.marketGoodsPrice = marketGoodsPrice;
	}

	public void setProGoodsPrice(BigDecimal proGoodsPrice) {
		this.proGoodsPrice = proGoodsPrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

}
