package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:StoreInfo
 * @Description:店铺设置实体类
 * @date: 2014-9-24下午06:56:59
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "B_StoreInfo")
public class StoreInfo implements Serializable {

	private static final long serialVersionUID = -7660685794009797768L;
	/** 编号 */
	private Integer storeId;
	/** 店铺名称 */
	private String storeName;
	/** 域名地址 */
	private String domainAddress;
	/** 店铺标志 */
	private String storeSign;
	/** 店铺简介 */
	private String storeIntroduct;
	/** 经营类型0：个人全职 1：个人兼职 2：公司开店 */
	private char businType;
	/** 联系人 */
	private String contPerson;
	/** 手机号码 */
	private String teleNo;
	/** 店铺地址 */
	private String storeAddress;
	/** 结算周期：以天为单位 */
	private Integer settPeriod;
	/** 结算方式0：手动结算1：自动结算 */
	private char settWay;
	/** 开户银行 */
	private String bank;
	/** 开户账号 */
	private String bankAccNo;
	/** 开户名称 */
	private String bankName;
	/** 营业执照 */
	private String license;
	/** 店铺介绍 */
	private String storeDesc;
	/** 主要货源0：线下批发市场 1：实体店拿货 2：阿里巴巴批发 3：分销/代销 4：自己生产 5：代工生产 6：自由公司渠道 7：货源还未确定 */
	private char mainProduct;
	/** 是否有实体店0：有1：无 */
	private char isStoreOrNot;
	/** 是否有工厂或仓库0：有1：无 */
	private char isFactOrNot;

	@Column(length = 40)
	public String getBank() {
		return bank;
	}

	@Column(length = 40)
	public String getBankAccNo() {
		return bankAccNo;
	}

	@Column(length = 40)
	public String getBankName() {
		return bankName;
	}

	@Column(length = 1)
	public char getBusinType() {
		return businType;
	}

	@Column(length = 30)
	public String getContPerson() {
		return contPerson;
	}

	@Column(length = 60, nullable = false)
	public String getDomainAddress() {
		return domainAddress;
	}

	@Column(length = 1)
	public char getIsFactOrNot() {
		return isFactOrNot;
	}

	@Column(length = 1)
	public char getIsStoreOrNot() {
		return isStoreOrNot;
	}

	@Column(length = 30)
	public String getLicense() {
		return license;
	}

	@Column(length = 2)
	public char getMainProduct() {
		return mainProduct;
	}

	@Column(length = 4)
	public Integer getSettPeriod() {
		return settPeriod;
	}

	@Column(length = 1)
	public char getSettWay() {
		return settWay;
	}

	@Column(length = 100)
	public String getStoreAddress() {
		return storeAddress;
	}

	@Column(length = 255)
	public String getStoreDesc() {
		return storeDesc;
	}

	@Id
	@Column(length = 8)
	public Integer getStoreId() {
		return storeId;
	}

	@Lob
	public String getStoreIntroduct() {
		return storeIntroduct;
	}

	@Column(length = 30)
	public String getStoreName() {
		return storeName;
	}

	@Column(length = 255, nullable = false)
	public String getStoreSign() {
		return storeSign;
	}

	@Column(length = 11, nullable = false)
	public String getTeleNo() {
		return teleNo;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBusinType(char businType) {
		this.businType = businType;
	}

	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}

	public void setDomainAddress(String domainAddress) {
		this.domainAddress = domainAddress;
	}

	public void setIsFactOrNot(char isFactOrNot) {
		this.isFactOrNot = isFactOrNot;
	}

	public void setIsStoreOrNot(char isStoreOrNot) {
		this.isStoreOrNot = isStoreOrNot;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setMainProduct(char mainProduct) {
		this.mainProduct = mainProduct;
	}

	public void setSettPeriod(Integer settPeriod) {
		this.settPeriod = settPeriod;
	}

	public void setSettWay(char settWay) {
		this.settWay = settWay;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public void setStoreIntroduct(String storeIntroduct) {
		this.storeIntroduct = storeIntroduct;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setStoreSign(String storeSign) {
		this.storeSign = storeSign;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
}
