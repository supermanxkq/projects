package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:OrderGroupRelaDTO
 * @Description:订单商品关联表的ＤＴＯ
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
public class OrderGoodsRelDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 2853496053601399594L;

	/** 编号Id */
	private Integer relaId;
	/** 订单编号 */
	private String orderId;
	/** 商品编号 */
	private String goodsId;
	/** 商品名称 */
	private String goodsName;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 商品折扣 */
	private BigDecimal discount;
	/** 数量 */
	private Integer qty;
	/** 总价 */
	private BigDecimal price;
	/**起始时间*/
	private String beginDate;
	/**截止时间*/
	private String endDate;
	/**商户ID*/
	private String merId;

	public Integer getRelaId() {
		return relaId;
	}
	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}

}
