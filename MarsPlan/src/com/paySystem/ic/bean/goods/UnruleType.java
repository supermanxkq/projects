package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: UnruleType.java
 * @Package: com.paySystem.ic.bean.goods
 * @Description: 违规类型
 * @Author: Jacky
 * @Date: 2014-8-27 下午4:33:31
 * @Version: V1.0  
 */
@Entity
@Table(name = "B_UnruleType")
public class UnruleType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**违规类型编号*/
	private Integer unruleTypeId;
	
	/**违规类型名称 */
	private String unruleTypeName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getUnruleTypeId() {
		return unruleTypeId;
	}

	public void setUnruleTypeId(Integer unruleTypeId) {
		this.unruleTypeId = unruleTypeId;
	}

	@Column(length=60,nullable=false)
	public String getUnruleTypeName() {
		return unruleTypeName;
	}

	public void setUnruleTypeName(String unruleTypeName) {
		this.unruleTypeName = unruleTypeName;
	}
	
	
	

}
