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
 * @ProjectName: omall
 * @ClassName: GoodsInfo
 * @Description: 商品实体Bean
 * @date: 2014-10-27 下午02:44:20
 * @author: 王少辉
 * @version: V1.0
 */
@Entity
@Table(name = "B_GoodsInfo")
public class GoodsInfo implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号ID
	 */
	private long goodsId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品货号
	 */
	private String goodsItem;

	/**
	 * 商户（店铺）
	 */
	private StoreInfo storeInfo;

	/**
	 * 商品分类
	 */
	private GoodsFamily goodsFamily;

	/**
	 * 排序id
	 */
	private String sortId;

	/**
	 * 商品状态：0，正常；1，禁用；9，删除；
	 */
	private int goodsSta;

	/**
	 * 商品上下架状态： 0：上架 1：下架；
	 */
	private int goodSaleSta;

	/**
	 * 是否为活动商品：0，否；1，是；默认不是活动商品；
	 */
	private int proId;

	/**
	 * 上架更新时间
	 */
	private Date saleDate;

	/**
	 * 下架更新时间
	 */
	private Date offSaleDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	/**
	 * 商品描述
	 */
	private String goodDescr;

	/**
	 * 违规处理状态：0，未违规状态；1，处理；2，未处理；
	 */
	private Integer unRuleMaSta;

	/**
	 * 是否免邮 0：否 1：是
	 */
	private Integer isFreeTran;

	/** 商户号 **/
	private String merId;

	/** 店铺号 */
	private String storeId;

	/** 商户名称 **/
	private String userName;

	/** 商品分类ID **/
	private int typeId;

	/** 折扣率 **/
	private Double rate;

	/**
	 * @return long 商品编号ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            商品编号ID
	 */
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return String 商品名称
	 */
	@Column(length = 50, nullable = false)
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return String 商品货号
	 */
	@Column(length = 15, nullable = false)
	public String getGoodsItem() {
		return goodsItem;
	}

	/**
	 * @param goodsItem
	 *            商品货号
	 */
	public void setGoodsItem(String goodsItem) {
		this.goodsItem = goodsItem;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "storeId")
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "typeId")
	public GoodsFamily getGoodsFamily() {
		return goodsFamily;
	}

	public void setGoodsFamily(GoodsFamily goodsFamily) {
		this.goodsFamily = goodsFamily;
	}

	/**
	 * @return String 排序id
	 */
	@Column(length = 8, nullable = false)
	public String getSortId() {
		return sortId;
	}

	/**
	 * @param sortId
	 *            排序id
	 */
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	/**
	 * @return int 商品状态：0，正常；1，禁用；9，删除；
	 */
	@Column(length = 2, nullable = false)
	public int getGoodsSta() {
		return goodsSta;
	}

	/**
	 * @param goodsSta
	 *            商品状态：0，正常；1，禁用；9，删除；
	 */
	public void setGoodsSta(int goodsSta) {
		this.goodsSta = goodsSta;
	}

	/**
	 * @return int 商品上下架状态： 0：上架 1：下架；
	 */
	@Column(length = 2, nullable = false)
	public int getGoodSaleSta() {
		return goodSaleSta;
	}

	/**
	 * @param goodSaleSta
	 *            商品上下架状态： 0：上架 1：下架；
	 */
	public void setGoodSaleSta(int goodSaleSta) {
		this.goodSaleSta = goodSaleSta;
	}

	/**
	 * @return int 是否为活动商品：0，否；1，是；默认不是活动商品；
	 */
	@Column(length = 2, nullable = false)
	public int getProId() {
		return proId;
	}

	/**
	 * @param proId
	 *            是否为活动商品：0，否；1，是；默认不是活动商品；
	 */
	public void setProId(int proId) {
		this.proId = proId;
	}

	/**
	 * @return Date 上架更新时间
	 */
	@Column(nullable = true)
	public Date getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate
	 *            上架更新时间
	 */
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * @return Date 下架更新时间
	 */
	@Column(nullable = true)
	public Date getOffSaleDate() {
		return offSaleDate;
	}

	/**
	 * @param offSaleDate
	 *            下架更新时间
	 */
	public void setOffSaleDate(Date offSaleDate) {
		this.offSaleDate = offSaleDate;
	}

	/**
	 * @return Date 更新时间
	 */
	@Column(nullable = true)
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return String 商品描述
	 */
	@Column(length = 500)
	public String getGoodDescr() {
		return goodDescr;
	}

	/**
	 * @param goodDescr
	 *            商品描述
	 */
	public void setGoodDescr(String goodDescr) {
		this.goodDescr = goodDescr;
	}

	/**
	 * @return Integer 违规处理状态：0，未违规状态；1，处理；2，未处理；
	 */
	@Column(nullable = false)
	public Integer getUnRuleMaSta() {
		return unRuleMaSta;
	}

	/**
	 * @param unRuleMaSta
	 *            违规处理状态：0，未违规状态；1，处理；2，未处理；
	 */
	public void setUnRuleMaSta(Integer unRuleMaSta) {
		this.unRuleMaSta = unRuleMaSta;
	}

	/**
	 * @return Integer 是否免邮 0：否 1：是
	 */
	@Column(length = 1, nullable = true)
	public Integer getIsFreeTran() {
		return isFreeTran;
	}

	/**
	 * @param isFreeTran
	 *            是否免邮 0：否 1：是
	 */
	public void setIsFreeTran(Integer isFreeTran) {
		this.isFreeTran = isFreeTran;
	}

	@Column(length=15,nullable=false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	/*
	@Column(length=15)
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	*/
	@Column(length=20,nullable=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
/*
	@Column(length=8,nullable=false)
	public int getTypeId() {
		return typeId;
	}
*/
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	
	@Column(length=6,scale=2,nullable=true)
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
