package com.paySystem.ic.web.dto.goods;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;

/**  
* @Title: GoodsDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description: 商品DTO对象
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class GoodsDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 5099201521268504553L;
	
	/** 商品名称  **/
	private String goodsName;
	
	/** 商品编号ID **/
	private Long goodsId;
	
	/** 商品货号 **/
	private String goodsItem;
	
	/** 商户编号 **/
	private String merId;
	
	/** 商户名称**/
	private String userName;
	
	/** 所有分类 **/
	private Integer typeId;
	
	/** 商品分类名称 **/
	private String typeName;
	
	/** 上下架状态 **/
	/**
	 * 0：上架
     * 1：下架
	 */
	private Integer goodSaleSta;
	
	/** 处理状态 **/
	/**
	 * 商品状态:
	 *	0：正常
	 *	1：禁用
	 *	9：删除
	 */
	private Integer goodsSta;
	
	/** 用于接收前端数据**/
	private Integer[] goodsSaleStas;
	
	/** 排序id **/
	private String sortId;
	
	/** 是否为活动商品 **/
	/**
	 * 0：否
     * 1：是
     * 默认不是活动商品
	 */
	private int proId;
	
	/** 上架更新时间 **/
	private Date saleDate;
	
	/** 下架更新时间 **/
	private Date offSaleDate;
	
	/** 更新时间 **/
	private Date updateDate;
	
	/** 商品描述**/
	private String goodDescr;
	
	/** 违规类型id**/
	private Integer illegalId;
	
	/** 触犯案例**/
	public Integer illCaseId;
	
	/** 处罚分数 **/
	private Integer punishScore;
	
	/** 商品处理方式**/
	private Integer goodPunishType;
	
	/** 处理原因**/
	private String dealReason;
	
	/** 处理说明**/
	private String dealDesc;
	
	/** 当前登陆者 **/
	private String currentMerId;
	
	/** 上传的文件 **/
	private File uploadFile;
	
	/** 上传文件类型**/
	private String uploadFileContentType;
	
	/** 上传文件名**/
	private String uploadFileFileName;
	
	/** 批量下架，复选商品id**/
	private List<String> goodsCheckId;
	
	/** 处理状态**/
	private Integer phtype;
	
	/** 商品主界面展示图**/
	private String[] mainResultHidden;
	
	/** 商品图片**/
	private String[] itemResultHidden;
	
	/** 商品详情图片**/
	private String[] descResultHidden;
	
	/** 是否免邮 0：否 1：是 **/
	private Integer isFreeTran;
	
	/** 折扣率**/
	private Double rate = 1.0d;
	
	/** 最大行数,用于前端动态copy属性的时候使用 **/
	private int maxRow = 1;
	
	/** 动态属性个数**/
	private int attCount = 0;
	
	/** 促销活动信息 **/
	private MerPromotionDTO promotion;
	
	/** 动态属性 list**/
	private List<DynamicAttrDTO> dynamicAttr = new ArrayList<DynamicAttrDTO>();
	
	/** 动态规格 list 这都是编辑，查看时候才使用**/
	private List<FormatInfoDTO> formatDynamic = new ArrayList<FormatInfoDTO>();
	
	/** 规格组 **/
	private List<GoodsFormatNameDTO> formatGroupList = new ArrayList<GoodsFormatNameDTO>(0);
	
	/** 库存list**/
	private List<StockPriMaDTO> stockPriList = new ArrayList<StockPriMaDTO>(0);
	
	/** 违规处理状态  **/
	/**
	 * 0:未违规状态
	 * 1:处理
	 * 2：未处理
	 */
	private Integer unRuleMaSta;
	
	/** 总库存数 **/
	private Long totalInventory;
	

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Long totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getUnRuleMaSta() {
		return unRuleMaSta;
	}

	public void setUnRuleMaSta(Integer unRuleMaSta) {
		this.unRuleMaSta = unRuleMaSta;
	}

	public int getAttCount() {
		return attCount;
	}

	public void setAttCount(int attCount) {
		this.attCount = attCount;
	}

	public MerPromotionDTO getPromotion() {
		return promotion;
	}

	public void setPromotion(MerPromotionDTO promotion) {
		this.promotion = promotion;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public List<StockPriMaDTO> getStockPriList() {
		return stockPriList;
	}

	public void setStockPriList(List<StockPriMaDTO> stockPriList) {
		this.stockPriList = stockPriList;
	}

	public List<GoodsFormatNameDTO> getFormatGroupList() {
		return formatGroupList;
	}

	public void setFormatGroupList(List<GoodsFormatNameDTO> formatGroupList) {
		this.formatGroupList = formatGroupList;
	}

	public List<FormatInfoDTO> getFormatDynamic() {
		return formatDynamic;
	}

	public void setFormatDynamic(List<FormatInfoDTO> formatDynamic) {
		this.formatDynamic = formatDynamic;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<DynamicAttrDTO> getDynamicAttr() {
		return dynamicAttr;
	}

	public void setDynamicAttr(List<DynamicAttrDTO> dynamicAttr) {
		this.dynamicAttr = dynamicAttr;
	}

	public Integer getIsFreeTran() {
		return isFreeTran;
	}

	public void setIsFreeTran(Integer isFreeTran) {
		this.isFreeTran = isFreeTran;
	}

	public Integer[] getGoodsSaleStas() {
		return goodsSaleStas;
	}

	public void setGoodsSaleStas(Integer[] goodsSaleStas) {
		this.goodsSaleStas = goodsSaleStas;
	}

	public String[] getItemResultHidden() {
		return itemResultHidden;
	}

	public void setItemResultHidden(String[] itemResultHidden) {
		this.itemResultHidden = itemResultHidden;
	}

	public String[] getDescResultHidden() {
		return descResultHidden;
	}

	public void setDescResultHidden(String[] descResultHidden) {
		this.descResultHidden = descResultHidden;
	}

	public String[] getMainResultHidden() {
		return mainResultHidden;
	}

	public void setMainResultHidden(String[] mainResultHidden) {
		this.mainResultHidden = mainResultHidden;
	}

	public Integer getPhtype() {
		return phtype;
	}

	public void setPhtype(Integer phtype) {
		this.phtype = phtype;
	}

	public List<String> getGoodsCheckId() {
		return goodsCheckId;
	}

	public void setGoodsCheckId(List<String> goodsCheckId) {
		this.goodsCheckId = goodsCheckId;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getCurrentMerId() {
		return currentMerId;
	}

	public void setCurrentMerId(String currentMerId) {
		this.currentMerId = currentMerId;
	}

	public String getDealReason() {
		return dealReason;
	}

	public void setDealReason(String dealReason) {
		this.dealReason = dealReason;
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public Integer getIllCaseId() {
		return illCaseId;
	}

	public void setIllCaseId(Integer illCaseId) {
		this.illCaseId = illCaseId;
	}

	public Integer getGoodPunishType() {
		return goodPunishType;
	}

	public void setGoodPunishType(Integer goodPunishType) {
		this.goodPunishType = goodPunishType;
	}

	public Integer getPunishScore() {
		return punishScore;
	}

	public void setPunishScore(Integer punishScore) {
		this.punishScore = punishScore;
	}

	public Integer getIllegalId() {
		return illegalId;
	}

	public void setIllegalId(Integer illegalId) {
		this.illegalId = illegalId;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getOffSaleDate() {
		return offSaleDate;
	}

	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getGoodDescr() {
		return goodDescr;
	}

	public void setGoodDescr(String goodDescr) {
		this.goodDescr = goodDescr;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getGoodSaleSta() {
		return goodSaleSta;
	}

	public void setGoodSaleSta(Integer goodSaleSta) {
		this.goodSaleSta = goodSaleSta;
	}

	public Integer getGoodsSta() {
		return goodsSta;
	}

	public void setGoodsSta(Integer goodsSta) {
		this.goodsSta = goodsSta;
	}
	
}
