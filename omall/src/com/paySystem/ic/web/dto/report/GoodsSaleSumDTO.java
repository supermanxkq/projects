package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class GoodsSaleSumDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -3527042843920710277L;

	/** 商品名称 */
	private String goodsName;
	/**商品货号*/
	private String goodsItem;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 数量 */
	private Integer qty;
	/** 总价 */
	private BigDecimal price;
    /**下单时间*/
	private String orderTime;
	/**起始时间*/
	private String beginDate;
	/**截止时间*/
	private String endDate;
	/**均价*/
	private BigDecimal avgPrice;
	
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsItem() {
		return goodsItem;
	}
	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
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
	
	
}
