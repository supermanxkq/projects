package com.paySystem.ic.web.dto.order;

import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class OrderDTO extends BaseDTO {
	private Integer id;
	private String customer;
	private String goodsName;
	private Integer count;
	private float price;
	private Integer status;
	private Date createTime;
	private String beginDate;
	private String endDate;
	private String customerPhoneNumber;
	private String orderDesc;

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
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

	public Integer getCount() {
		return count;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCustomer() {
		return customer;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
