package com.paySystem.ic.bean.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order {
	private Integer id;
	private String customer;
	private Integer status;
	private Date createTime;
	private String customerPhoneNumber;
	private String orderDesc;

	@Column
	public Date getCreateTime() {
		return createTime;
	}

	@Column
	public String getCustomer() {
		return customer;
	}

	@Column
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column
	public String getOrderDesc() {
		return orderDesc;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
