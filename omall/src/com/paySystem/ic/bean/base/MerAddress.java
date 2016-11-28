package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商户地址实体
 * @ClassName: MerAddress.java
 * @Description:商户地址实体类
 * @date: 2014-10-10 午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_MerAddress")
public class MerAddress implements Serializable {
	private static final long serialVersionUID = -5377651435695686856L;
	
	/** 地址编号**/
	private Integer retAddId;
	
	/** 商户ID**/
	private String merId;
	
	/** 联系人姓名**/
	private String merName;
	
	/** 所在地区**/
	private String area;
	
	/** 街道地址**/
	private String address;
	
	/** 邮政编码**/
	private String zip;
	
	/** 电话号码**/
	private String teleNo;
	
	/** 联系人电话号码**/
	private String conPerTeleNo;
	
	/** 公司名称**/
	private String comName;
	
	/** 是否是默认发货地址**/
	/**
	 * 0:否
	 * 1:是
	 */
	private String isSend;
	
	/** 是否为默认退货地址**/
	/**
	 * 0:否
	 * 1:是
	 */
	private String isReturn;
	
	/** 备注**/
	private String descr;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getRetAddId() {
		return retAddId;
	}

	public void setRetAddId(Integer retAddId) {
		this.retAddId = retAddId;
	}

	@Column(length=15,nullable=false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length=60,nullable=false)
	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	@Column(length=60,nullable=false)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(length=100,nullable=false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length=10,nullable=false)
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(length=13,nullable=true)
	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	@Column(length=11,nullable=false)
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}

	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}

	@Column(length=50,nullable=true)
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	@Column(length=1,nullable=true)
	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	@Column(length=1,nullable=true)
	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	@Column(length=255,nullable=true)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
