package com.paySystem.ic.bean.order;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:OrderGroupRela
 * @Description:订单商品关联表的实体Bean
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "O_OrderGoodsRel")
public class OrderGoodsRel implements Serializable {

	private static final long serialVersionUID = -7699232378789178453L;

	/** 编号Id */
	private Integer ogrId;
	/** 订单编号 */
	private String orderId;
	/** 商品编号 */
	private String goodsId;
	/** 商品名称 */
	private String goodsName;
	/**商品货号*/
	private String goodsItem;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 商品折扣 */
	private BigDecimal discount;
	/** 数量 */
	private Integer qty;
	/** 总价 */
	private BigDecimal price;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getOgrId() {
		return ogrId;
	}

	public void setOgrId(Integer ogrId) {
		this.ogrId = ogrId;
	}
	
	@Column(length = 16)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(length = 15)
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length = 15)
	public String getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}

	@Column(length = 50)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(columnDefinition = "DECIMAL(13,4)")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(columnDefinition = "DECIMAL(3,2)")
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column
	//@Column(length=13)
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Column(columnDefinition = "DECIMAL(15,4)")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
