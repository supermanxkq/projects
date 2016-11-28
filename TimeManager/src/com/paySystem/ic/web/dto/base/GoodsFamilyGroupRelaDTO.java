package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: GoodsFamilyGroupRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品分类规格分组关联表
 * @Author: yanwuyang 
 * @Date: 2014-8-21 下午11:58:09
 * @Version: V1.0  
 */
public class GoodsFamilyGroupRelaDTO  extends BaseDTO implements Serializable {

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
	

	public Integer getTfgrId() {
		return tfgrId;
	}

	public void setTfgrId(Integer tfgrId) {
		this.tfgrId = tfgrId;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	
}
