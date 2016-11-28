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
	
	/** id **/
	private int goodsItem;
	
	/** 商品编号 **/
	private String goodsId;
	
	/** 商品库存 **/
	private String stockNo;
	
	/** 商品店铺价格 **/
	private BigDecimal goodsPrice;
	
	/** 商品活动价格**/
	private BigDecimal proGoodsPrice;
	
	/** 商品市场价格**/
	private BigDecimal marketGoodsPrice;
	
	/** 商品属性值id **/
	private long attrvalId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(int goodsItem) {
		this.goodsItem = goodsItem;
	}
	
	@Column(length=15,nullable=false)
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length=50,nullable=false)
	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(length=10,nullable=false,scale=4)
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(length=10,nullable=false,scale=4)
	public BigDecimal getProGoodsPrice() {
		return proGoodsPrice;
	}

	public void setProGoodsPrice(BigDecimal proGoodsPrice) {
		this.proGoodsPrice = proGoodsPrice;
	}

	@Column(length=10,nullable=false,scale=4)
	public BigDecimal getMarketGoodsPrice() {
		return marketGoodsPrice;
	}

	public void setMarketGoodsPrice(BigDecimal marketGoodsPrice) {
		this.marketGoodsPrice = marketGoodsPrice;
	}
	
	@Column(length=20,nullable=false)
	public long getAttrvalId() {
		return attrvalId;
	}

	public void setAttrvalId(long attrvalId) {
		this.attrvalId = attrvalId;
	}

}
