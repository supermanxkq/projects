package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品图片实体bean
 * @ClassName:GoodBrands
 * @Description:商品图片实体类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_GoodsPhoto")
public class GoodsPhoto implements Serializable {
	private static final long serialVersionUID = -7617691706957407225L;
	
	/** 图片表主键**/
	private long photoId;
	
	/** 商品编号 **/
	private long goodsId;
	
	/** 商品图片类型  **/
	private int goodsStyle;
	
	/** 查看图片详情的每个商品的小图片路径 **/
	private String goodsLittPho;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	@Column(length=20,nullable=false,unique=false)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(length=30,nullable=false)
	public int getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(int goodsStyle) {
		this.goodsStyle = goodsStyle;
	}
	
	@Column(length=512)
	public String getGoodsLittPho() {
		return goodsLittPho;
	}

	public void setGoodsLittPho(String goodsLittPho) {
		this.goodsLittPho = goodsLittPho;
	}
	
}
