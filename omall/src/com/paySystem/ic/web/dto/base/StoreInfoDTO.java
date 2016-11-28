package com.paySystem.ic.web.dto.base;

import java.io.File;
import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:StoreInfoDTO
 * @Description:店铺基本设置数据传输对象
 * @date: 2014-9-23下午06:50:42
 * @author:徐凯强
 * @version: V1.0
 */
public class StoreInfoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 开户银行 */
	//private String bank;
	/** 开户账号 */
	//private String bankAccNo;
	/** 开户名称 */
	//private String bankName;
	/** 经营类型0：个人全职 1：个人兼职 2：公司开店 */
	private char businType;
	/** 市Id */
	private String cityId;
	/** 联系人 */
	private String contPerson;
	/** 域名地址 */
	private String domainAddress;
	/** 图片路径 **/
	private File imageFile;
	/** 文件名称 */
	private String imageFileFileName;
	/** 是否有工厂或仓库0：有1：无 */
	private char isFactOrNot;
	/** 是否有实体店0：有1：无 */
	private char isStoreOrNot;
	/** 营业执照 */
	private String license;
	/** 主要货源0：线下批发市场 1：实体店拿货 2：阿里巴巴批发 3：分销/代销 4：自己生产 5：代工生产 6：自由公司渠道 7：货源还未确定 */
	private char mainProduct;
	/** 商户号，外键 */
	private String merId;
	/** 店铺昵称 */
	private String nickName;
	/** 省Id */
	private String provinceId;
	/** 结算周期：以天为单位 */
	//private Integer settPeriod;
	/** 结算方式0：手动结算1：自动结算 */
	//private char settWay;
	/** 店铺地址 */
	private String storeAddress;
	/** 店铺介绍 */
	private String storeDesc;
	/** 编号 */
	private String storeId;
	/** 店铺简介 */
	private String storeIntroduct;
	/** 店铺名称 */
	private String storeName;
	/** 店铺标志 */
	private String storeSign;
	/** 手机号码 */
	private String teleNo;
	/** 是否支持银行卡，1：支持，0：不支持 **/
	private Integer isBankCard;
	
//
//	public String getBank() {
//		return bank;
//	}
//
//	public String getBankAccNo() {
//		return bankAccNo;
//	}
//
//	public String getBankName() {
//		return bankName;
//	}

	public Integer getIsBankCard() {
		return isBankCard;
	}

	public void setIsBankCard(Integer isBankCard) {
		this.isBankCard = isBankCard;
	}


	public char getBusinType() {
		return businType;
	}

	public String getCityId() {
		return cityId;
	}

	public String getContPerson() {
		return contPerson;
	}

	public String getDomainAddress() {
		return domainAddress;
	}

	public File getImageFile() {
		return imageFile;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public char getIsFactOrNot() {
		return isFactOrNot;
	}

	public char getIsStoreOrNot() {
		return isStoreOrNot;
	}

	public String getLicense() {
		return license;
	}

	public char getMainProduct() {
		return mainProduct;
	}

	public String getMerId() {
		return merId;
	}

	public String getNickName() {
		return nickName;
	}

	public String getProvinceId() {
		return provinceId;
	}

//	public Integer getSettPeriod() {
//		return settPeriod;
//	}
//
//	public char getSettWay() {
//		return settWay;
//	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getStoreIntroduct() {
		return storeIntroduct;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreSign() {
		return storeSign;
	}

	public String getTeleNo() {
		return teleNo;
	}

//	public void setBank(String bank) {
//		this.bank = bank;
//	}
//
//	public void setBankAccNo(String bankAccNo) {
//		this.bankAccNo = bankAccNo;
//	}
//
//	public void setBankName(String bankName) {
//		this.bankName = bankName;
//	}

	public void setBusinType(char businType) {
		this.businType = businType;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}

	public void setDomainAddress(String domainAddress) {
		this.domainAddress = domainAddress;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
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

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

//	public void setSettPeriod(Integer settPeriod) {
//		this.settPeriod = settPeriod;
//	}
//
//	public void setSettWay(char settWay) {
//		this.settWay = settWay;
//	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public void setStoreId(String storeId) {
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