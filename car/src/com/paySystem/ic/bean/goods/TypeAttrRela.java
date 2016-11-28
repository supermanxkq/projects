package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品分类和属性映射表
 * @ClassName: StockPriMa
 * @Description: 商品分类和属性映射表
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
//@Entity
//@Table(name = "B_TypeAttrRela")
public class TypeAttrRela implements Serializable {
	private static final long serialVersionUID = 8759404912747859400L;
	
	/** 编号**/
	private int id;
	
	/** 商品类型编号**/
	private int typeId;
	
	/** 属性编号**/
	private int attrId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=8,nullable=false)
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(length=8,nullable=false)
	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}
	
}
