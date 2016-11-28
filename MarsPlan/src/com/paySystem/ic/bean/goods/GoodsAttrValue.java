package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品属性值表bean
 * @ClassName: GoodsAttrValue
 * @Description: 商品属性值表类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */

@Entity
@Table(name = "B_GoodsAttrValue")
public class GoodsAttrValue implements Serializable {
	private static final long serialVersionUID = 6946616349820378149L;
	
	/** 编号*/
	private long attrvalId;
	
	/**商品编号 */
	private long goodsId;
	
	/**属性编号1 */
	private Integer attrId1;
	
	/** 属性值1*/
	private String attrValue1;
	
	/** 属性编号2*/
	private Integer attrId2;
	
	/**属性值2 */
	private String attrValue2;
	
	/**属性编号3 */
	private Integer attrId3;
	
	/**属性值3 */
	private String attrValue3;
	
	
	/** 属性编号4*/
	private Integer attrId4;
	
	/** 属性值4*/
	private String attrValue4;
	
	/**属性编号5 */
	private Integer attrId5;
	
	/** 属性值5*/
	private String attrValue5;
	
	/**图片路径 */
	private String picture ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public long getAttrvalId() {
		return attrvalId;
	}

	public void setAttrvalId(long attrvalId) {
		this.attrvalId = attrvalId;
	}

	@Column(length=20,nullable=false)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length=8,nullable=true)
	public Integer getAttrId1() {
		return attrId1;
	}

	public void setAttrId1(Integer attrId1) {
		this.attrId1 = attrId1;
	}

	@Column(length=60,nullable=true)
	public String getAttrValue1() {
		return attrValue1;
	}

	public void setAttrValue1(String attrValue1) {
		this.attrValue1 = attrValue1;
	}
	
	@Column(length=8,nullable=true)
	public Integer getAttrId2() {
		return attrId2;
	}

	public void setAttrId2(Integer attrId2) {
		this.attrId2 = attrId2;
	}

	@Column(length=60,nullable=true)
	public String getAttrValue2() {
		return attrValue2;
	}

	public void setAttrValue2(String attrValue2) {
		this.attrValue2 = attrValue2;
	}

	@Column(length=8,nullable=true)
	public Integer getAttrId3() {
		return attrId3;
	}

	public void setAttrId3(Integer attrId3) {
		this.attrId3 = attrId3;
	}

	@Column(length=60,nullable=true)
	public String getAttrValue3() {
		return attrValue3;
	}

	public void setAttrValue3(String attrValue3) {
		this.attrValue3 = attrValue3;
	}

	@Column(length=8,nullable=true)
	public Integer getAttrId4() {
		return attrId4;
	}

	public void setAttrId4(Integer attrId4) {
		this.attrId4 = attrId4;
	}

	@Column(length=60,nullable=true)
	public String getAttrValue4() {
		return attrValue4;
	}

	public void setAttrValue4(String attrValue4) {
		this.attrValue4 = attrValue4;
	}

	@Column(length=8,nullable=true)
	public Integer getAttrId5() {
		return attrId5;
	}

	public void setAttrId5(Integer attrId5) {
		this.attrId5 = attrId5;
	}

	@Column(length=60,nullable=true)
	public String getAttrValue5() {
		return attrValue5;
	}

	public void setAttrValue5(String attrValue5) {
		this.attrValue5 = attrValue5;
	}

	@Column(length=200,nullable=true)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
