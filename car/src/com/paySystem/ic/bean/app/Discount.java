package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:Discount
 * @Description:优惠信息实体
 * @date: 2014-5-26下午04:15:43
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_discount")
public class Discount implements Serializable {


	private static final long serialVersionUID = 1L;
	
	/** 优惠信息记录编号 */
	private Integer discountId;
	
	/** 优惠标题 */
	private String disCountName;
	
	/** 优惠信息图片链接 */
	private String disCountUrl;
	
	/** 信息创建时间  */
	private Date createTime;

	/** 地址 */
	private String address;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	@Column(length=30)
	public String getDisCountName() {
		return disCountName;
	}

	public void setDisCountName(String disCountName) {
		this.disCountName = disCountName;
	}

	@Column(length=100)
	public String getDisCountUrl() {
		return disCountUrl;
	}

	public void setDisCountUrl(String disCountUrl) {
		this.disCountUrl = disCountUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length=100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
