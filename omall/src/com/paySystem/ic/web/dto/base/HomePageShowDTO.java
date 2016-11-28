package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class HomePageShowDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 650897235844709999L;
    
	/** 机构ID */
	private String organId;
	/** 机构名称 */
	private String name;
	/** 联系人姓名 */
	private String conPerName;
	/** 联系人电话号码 */
	private String conPerTeleNo;
	/** 地址 */
	private String address;
	/** 编号 */
	private String storeId;
	/**商户编号*/
	private String merId;
	/** 店铺简介 */
	private String storeIntroduct;
	/** 手机号码 */
	private String teleNo;
	/** 店铺地址 */
	private String storeAddress;
	/** 联系人 */
	private String contPerson;
	/** 店铺名称 */
	private String storeName;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getConPerTeleNo() {
		return conPerTeleNo;
	}
	public void setConPerTeleNo(String conPerTeleNo) {
		this.conPerTeleNo = conPerTeleNo;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConPerName() {
		return conPerName;
	}
	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreIntroduct() {
		return storeIntroduct;
	}
	public void setStoreIntroduct(String storeIntroduct) {
		this.storeIntroduct = storeIntroduct;
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getContPerson() {
		return contPerson;
	}
	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}
	

}
