package com.paySystem.ic.web.dto.base;

/**
 * 商户地址实体DTO
 * @ClassName: MerAddressDTO.java
 * @Description:商户地址实体类DTO
 * @date: 2014-10-10 午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class MerAddressDTO {
	
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

	public Integer getRetAddId() {
		return retAddId;
	}

	public void setRetAddId(Integer retAddId) {
		this.retAddId = retAddId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	public String getConPerTeleNo() {
		return conPerTeleNo;
	}

	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
