package com.paySystem.ic.web.dto.goods;

import java.math.BigDecimal;

/**
 * @ClassName: StockPriMaDTO
 * @Description: 商品库存价格管理类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class StockPriMaDTO {

	/** id **/
	private int goodsItem;

	/** 商品编号 **/
	private String goodsId;

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

	/** 商品属性值id **/
	private long attrvalId;

	/** 一个库存关联一组属性值 **/
	private GoodsAttrValueDTO goodsAttrValueDTO;

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public GoodsAttrValueDTO getGoodsAttrValueDTO() {
		return goodsAttrValueDTO;
	}

	public void setGoodsAttrValueDTO(GoodsAttrValueDTO goodsAttrValueDTO) {
		this.goodsAttrValueDTO = goodsAttrValueDTO;
	}

	public int getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(int goodsItem) {
		this.goodsItem = goodsItem;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public long getAttrvalId() {
		return attrvalId;
	}

	public void setAttrvalId(long attrvalId) {
		this.attrvalId = attrvalId;
	}

}
