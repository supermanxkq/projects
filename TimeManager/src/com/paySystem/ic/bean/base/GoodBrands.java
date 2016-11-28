package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商品品牌管理实体bean
 * @ClassName:GoodBrands
 * @Description:商品品牌管理实体类
 * @date: 2014-7-21 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_GoodsBrand")
public class GoodBrands implements Serializable {
	private static final long serialVersionUID = -4266486519335402511L;

	/** 品牌信息编号 **/
	private int brandId = 0;
	
	/** 品牌名称 **/
	private String brandName;
	
	/** 品牌网址  **/
	private String brandUrl;
	
	/** 品牌Logo地址 **/
	private String brandLogo;
	
	/** 品牌描述  **/
	private String brandDesc;
	
	/** 是否显示 0是 1否 **/
	private short isShow;
	
	/** 创建时间  **/
	private Date createTime;
	
	/** 操作人 **/
	private String operator;
	
	/**
	 * 1表示删除
	 * 0表示正常
	 */
	private short isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	@Column(length=60,nullable=false)
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(length=100)
	public String getBrandUrl() {
		return brandUrl;
	}

	public void setBrandUrl(String brandUrl) {
		this.brandUrl = brandUrl;
	}

	@Column(length=200)
	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	@Column(length=255)
	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	@Column(columnDefinition="char(1)",nullable=false)
	public short getIsShow() {
		return isShow;
	}

	public void setIsShow(short isShow) {
		this.isShow = isShow;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length=20,nullable=false)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(length=1,nullable=false)
	public short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(short isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
