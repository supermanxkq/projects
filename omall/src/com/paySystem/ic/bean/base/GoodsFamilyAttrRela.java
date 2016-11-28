package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: GoodsFamilyAttrRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品分类属性管理表
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:34:58
 * @Version: V1.0  
 */

@Entity
@Table(name="b_familyattrrela")
public class GoodsFamilyAttrRela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5127292620346009231L;
	
	/**编号 */
	private Integer gaId;
	
	/**商品分类编号（商品分类Id */
	private Integer familyId;
	
	/**属性编号 */
	private Integer attrId;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getGaId() {
		return gaId;
	}

	public void setGaId(Integer gaId) {
		this.gaId = gaId;
	}

	@Column(nullable=false)
	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	@Column(nullable=false)
	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	
	

}
