package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: GoodsFamilyAttrRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品属性分类关联DTO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:34:58
 * @Version: V1.0  
 */

public class GoodsFamilyAttrRelaDTO  extends BaseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 570298953748467665L;

	/**编号 */
	private Integer gaId;
	
	/**商品分类编号（商品分类Id */
	private Integer familyId;
	
	/**属性编号 */
	private Integer attrId;

	public Integer getGaId() {
		return gaId;
	}

	public void setGaId(Integer gaId) {
		this.gaId = gaId;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

}
