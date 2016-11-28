package com.supermanxkq.model;

import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * 订单实体
 * 
 * @author bolt
 *
 */
public class Order {
	private int id;
	// 顾客
	private String customer;
	// 商品名称
	private String goodsName;
	// 个数
	private Integer count;
	// 单价
	private float price;
	// 状态0打白条1成功2取消
	private Integer status;
	// 订单生成时间
	private Date createTime;
	//查询开始时间
	private String startDate;
	//查询结束时间
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
