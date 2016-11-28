package com.paySystem.ic.web.dto.goods;

/**  
* @Title: GoodsPhotoDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description: 商品图片DTO对象
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class GoodsPhotoDTO {
	
	/** 图片表主键**/
	private long photoId;
	
	/** 商品编号 **/
	private long goodsId;
	
	/** 商品图片类型  **/
	private int goodsStyle;
	
	/** 查看图片详情的每个商品的小图片路径 **/
	private String goodsLittPho;

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(int goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public String getGoodsLittPho() {
		return goodsLittPho;
	}

	public void setGoodsLittPho(String goodsLittPho) {
		this.goodsLittPho = goodsLittPho;
	}
	
}
