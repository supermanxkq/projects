package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paySystem.ic.bean.base.GoodsFamily;
import com.paySystem.ic.bean.base.StoreInfo;

/**
 * 商品信息实体bean
 * 
 * @ClassName:GoodBrands
 * @Description:商品信息实体类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_GoodsInfo")
public class GoodInfo implements Serializable {
	private static final long serialVersionUID = -650549006069913310L;

	/** 商品编号ID **/
	private long goodsId;

	/** 商品名称 **/
	private String goodsName;

	/** 商品货号 **/
	private String goodsItem;

	/** 商户号 **/
	private String merId;

	/**
	 * 商户（店铺）
	 */
	private StoreInfo storeInfo;

	/** 商户名称 **/
	private String userName;

	/** 商品分类ID **/
	private int typeId;

	/** 排序id **/
	private String sortId;

	/** 商品编号ID **/
	/**
	 * 商品状态: 0：正常 1：禁用 9：删除
	 */
	private int goodsSta;

	/**
	 * 商品上下架状态： 0：上架 1：下架
	 */
	private int goodSaleSta;

	/** 是否为活动商品 **/
	/**
	 * 0：否 1：是 默认不是活动商品
	 */
	private int proId;

	/** 上架更新时间 **/
	private Date saleDate;

	/** 下架更新时间 **/
	private Date offSaleDate;

	/** 更新时间 **/
	private Date updateDate;

	/** 商品描述 **/
	private String goodDescr;

	/** 违规处理状态 **/
	/**
	 * 0:未违规状态 1:处理 2：未处理
	 */
	private Integer unRuleMaSta;

	/** 是否免邮 0：否 1：是 **/
	private Integer isFreeTran;

	/** 折扣率 **/
	private Double rate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length = 1, nullable = true)
	public Integer getIsFreeTran() {
		return isFreeTran;
	}

	public void setIsFreeTran(Integer isFreeTran) {
		this.isFreeTran = isFreeTran;
	}

	@Column(length = 50, nullable = false)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(length = 15, nullable = false)
	public String getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}

	@Column(length = 15, nullable = false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 8, nullable = false)
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(length = 8, nullable = false)
	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	@Column(length = 2, nullable = false)
	public int getGoodsSta() {
		return goodsSta;
	}

	public void setGoodsSta(int goodsSta) {
		this.goodsSta = goodsSta;
	}

	@Column(length = 2, nullable = false, updatable = true)
	public int getGoodSaleSta() {
		return goodSaleSta;
	}

	public void setGoodSaleSta(int goodSaleSta) {
		this.goodSaleSta = goodSaleSta;
	}

	@Column(length = 2, nullable = false)
	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	@Column(nullable = true)
	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	@Column(nullable = true)
	public Date getOffSaleDate() {
		return offSaleDate;
	}

	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	@Column(nullable = false)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(length = 1000)
	public String getGoodDescr() {
		return goodDescr;
	}

	public void setGoodDescr(String goodDescr) {
		this.goodDescr = goodDescr;
	}

	@Column(nullable = false)
	public Integer getUnRuleMaSta() {
		return unRuleMaSta;
	}

	public void setUnRuleMaSta(Integer unRuleMaSta) {
		this.unRuleMaSta = unRuleMaSta;
	}

	@Column(length = 6, scale = 2, nullable = true)
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Column(length = 20, nullable = true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * @Column(length = 15) public String getStoreId() { return storeId; }
	 * 
	 * public void setStoreId(String storeId) { this.storeId = storeId; }
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "storeId")
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}
	
	
}
