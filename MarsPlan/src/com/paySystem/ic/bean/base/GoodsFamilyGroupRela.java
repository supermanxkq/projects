package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: GoodsFamilyGroupRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品分类规格分组关联表
 * @Author: yanwuyang 
 * @Date: 2014-8-21 下午11:58:09
 * @Version: V1.0  
 */
@Entity
@Table(name="B_FamilyGroupRela")
public class GoodsFamilyGroupRela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4768344398050448393L;
	
	/** 编号*/
	private Integer tfgrId;
	
	/** 商品分类Id（商品分类表Id）*/
	private Integer familyId;
	
	/** 规格Id（规格表Id）*/
	private Integer formatId;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getTfgrId() {
		return tfgrId;
	}

	public void setTfgrId(Integer tfgrId) {
		this.tfgrId = tfgrId;
	}

	@Column(nullable=false)
	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	@Column(nullable=false)
	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	
}
