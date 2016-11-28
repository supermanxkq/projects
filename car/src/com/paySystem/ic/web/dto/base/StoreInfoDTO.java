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
	/** 图片路径 **/
	private File imageFile;
	/** 文件名称 */
	private String imageFileFileName;

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getDomainAddress() {
		return domainAddress;
	}

	public void setDomainAddress(String domainAddress) {
		this.domainAddress = domainAddress;
	}

	public String getStoreSign() {
		return storeSign;
	}

	public void setStoreSign(String storeSign) {
		this.storeSign = storeSign;
	}

	public String getStoreIntroduct() {
		return storeIntroduct;
	}

	public void setStoreIntroduct(String storeIntroduct) {
		this.storeIntroduct = storeIntroduct;
	}

	public char getBusinType() {
		return businType;
	}

	public void setBusinType(char businType) {
		this.businType = businType;
	}

	public String getContPerson() {
		return contPerson;
	}

	public void setContPerson(String contPerson) {
		this.contPerson = contPerson;
	}

	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Integer getSettPeriod() {
		return settPeriod;
	}

	public void setSettPeriod(Integer settPeriod) {
		this.settPeriod = settPeriod;
	}

	public char getSettWay() {
		return settWay;
	}

	public void setSettWay(char settWay) {
		this.settWay = settWay;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public char getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(char mainProduct) {
		this.mainProduct = mainProduct;
	}

	public char getIsStoreOrNot() {
		return isStoreOrNot;
	}

	public void setIsStoreOrNot(char isStoreOrNot) {
		this.isStoreOrNot = isStoreOrNot;
	}

	public char getIsFactOrNot() {
		return isFactOrNot;
	}

	public void setIsFactOrNot(char isFactOrNot) {
		this.isFactOrNot = isFactOrNot;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}