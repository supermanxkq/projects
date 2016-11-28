package com.paySystem.ic.web.dto.base;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:GoodsFamilyDTO
 * @Description:商品分类DTO
 * @date: 2014-6-27下午07:11:44
 * @author: 张亚运
 * @version: V1.0
 */
public class GoodsFamilyDTO extends BaseDTO implements Serializable {

	/**
	 *序列号
	 */
	private static final long serialVersionUID = -8522185316993875460L;

	/** 分类信息编号 */
	private Integer familyId;

	/** 上级分类信息名称 */
	private String pFamilyName;

	/** 上级分类Id */
	private Integer parentId;

	/** 分类信息名称 */
	private String familyName;

	/** 节点等级 */
	private Integer nodeLevel;

	/** 商品分类图片路径 */
	private String picPath;

	/** 关键字 */
	private String keyWords;

	/** 使用状态 */
	private Integer status;

	/** 是否默认展开 */
	private Integer defaultShow;

	/**
	 * 分类类型 0：手工分类 1：自动分类
	 * */
	private Integer familyWay;

	/** 创建时间 */
	private Date createTime;

	/** 创建人 */
	private String operator;

	/** 是否是私有属性 */
	private Integer preFlag;

	/**
	 * 是否生成楼层广告标志 0:否( 不 生 成 ) 1:是( 生 成 )
	 */
	private Integer createFloorSign;

	/**
	 * 是否展示广告信息 0:否 (不展示) 1:是(展示)
	 */
	private Integer showAdvertSign;

	/** 关联Id */
	private Integer relId;

	/**
	 * 广告主体标志 0:商品广告 1:店铺广告 2:品牌广告 3:活动广告
	 * */
	private Integer advertContentSign;

	/** 广告图片 */
	private File advertPic;

	/** 图片路径 */
	private String advertPicFileName;

	/** 广告图片路径 */
	private String advertPicPath;

	/** 楼层展示顺序 */
	private Integer orderSort;

	/**
	 * 内容Id
	 */
	private Integer objectId;
	/**
	 * 支付类型 快捷支付，网银，融芯宝，支付宝，第三方支付
	 * **/
	private Integer chargeRateType;
	/**
	 * 手续费率 快捷支付费率，网银费率，融芯宝费率
	 * **/
	private BigDecimal chargeRate;
	/** 融芯宝，默认标识号为1 **/
	private Integer rswinPay = 1;
	/** 融芯宝费率 **/
	private BigDecimal rswinPayValue;
	/** 快捷支付，默认标识号为2 **/
	private Integer fastPay = 2;
	/** 快捷支付费率 **/
	private BigDecimal fastPayValue;
	/** 网银支付，默认标识号为3 **/
	private Integer wyPay = 3;
	/** 网银支付费率 **/
	private BigDecimal wyPayValue;
	/** 支付宝支付，默认标识号为4 **/
	private Integer aliPay = 4;
	/** 支付宝支付费率 **/
	private BigDecimal aliPayValue;
	/** 第三方支付，默认标识号为5 **/
	private Integer otherPay = 5;
	/** 第三方支付费率 **/
	private BigDecimal otherPayValue;
	/** 父节点等级 **/
	private Integer parentLevel;

	public Integer getPreFlag() {
		return preFlag;
	}

	public void setPreFlag(Integer preFlag) {
		this.preFlag = preFlag;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public String getPFamilyName() {
		return pFamilyName;
	}

	public void setPFamilyName(String pFamilyName) {
		this.pFamilyName = pFamilyName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Integer getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getDefaultShow() {
		return defaultShow;
	}

	public void setDefaultShow(Integer defaultShow) {
		this.defaultShow = defaultShow;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFamilyWay() {
		return familyWay;
	}

	public void setFamilyWay(Integer familyWay) {
		this.familyWay = familyWay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getpFamilyName() {
		return pFamilyName;
	}

	public void setpFamilyName(String pFamilyName) {
		this.pFamilyName = pFamilyName;
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public Integer getAdvertContentSign() {
		return advertContentSign;
	}

	public void setAdvertContentSign(Integer advertContentSign) {
		this.advertContentSign = advertContentSign;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getCreateFloorSign() {
		return createFloorSign;
	}

	public void setCreateFloorSign(Integer createFloorSign) {
		this.createFloorSign = createFloorSign;
	}

	public Integer getShowAdvertSign() {
		return showAdvertSign;
	}

	public void setShowAdvertSign(Integer showAdvertSign) {
		this.showAdvertSign = showAdvertSign;
	}

	public String getAdvertPicPath() {
		return advertPicPath;
	}

	public void setAdvertPicPath(String advertPicPath) {
		this.advertPicPath = advertPicPath;
	}

	public Integer getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
	}

	public File getAdvertPic() {
		return advertPic;
	}

	public void setAdvertPic(File advertPic) {
		this.advertPic = advertPic;
	}

	public String getAdvertPicFileName() {
		return advertPicFileName;
	}

	public void setAdvertPicFileName(String advertPicFileName) {
		this.advertPicFileName = advertPicFileName;
	}

	public Integer getChargeRateType() {
		return chargeRateType;
	}

	public void setChargeRateType(Integer chargeRateType) {
		this.chargeRateType = chargeRateType;
	}

	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}

	public Integer getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(Integer parentLevel) {
		this.parentLevel = parentLevel;
	}

	public Integer getRswinPay() {
		return rswinPay;
	}

	public void setRswinPay(Integer rswinPay) {
		this.rswinPay = rswinPay;
	}

	public BigDecimal getRswinPayValue() {
		return rswinPayValue;
	}

	public void setRswinPayValue(BigDecimal rswinPayValue) {
		this.rswinPayValue = rswinPayValue;
	}

	public Integer getWyPay() {
		return wyPay;
	}

	public void setWyPay(Integer wyPay) {
		this.wyPay = wyPay;
	}

	public BigDecimal getWyPayValue() {
		return wyPayValue;
	}

	public void setWyPayValue(BigDecimal wyPayValue) {
		this.wyPayValue = wyPayValue;
	}

	public Integer getFastPay() {
		return fastPay;
	}

	public void setFastPay(Integer fastPay) {
		this.fastPay = fastPay;
	}

	public BigDecimal getFastPayValue() {
		return fastPayValue;
	}

	public void setFastPayValue(BigDecimal fastPayValue) {
		this.fastPayValue = fastPayValue;
	}

	public Integer getAliPay() {
		return aliPay;
	}

	public void setAliPay(Integer aliPay) {
		this.aliPay = aliPay;
	}

	public BigDecimal getAliPayValue() {
		return aliPayValue;
	}

	public void setAliPayValue(BigDecimal aliPayValue) {
		this.aliPayValue = aliPayValue;
	}

	public Integer getOtherPay() {
		return otherPay;
	}

	public void setOtherPay(Integer otherPay) {
		this.otherPay = otherPay;
	}

	public BigDecimal getOtherPayValue() {
		return otherPayValue;
	}

	public void setOtherPayValue(BigDecimal otherPayValue) {
		this.otherPayValue = otherPayValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
