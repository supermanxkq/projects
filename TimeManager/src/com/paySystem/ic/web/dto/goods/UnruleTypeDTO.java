package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;


/**  
 * @Title: UnruleType.java
 * @Package: com.paySystem.ic.bean.goods
 * @Description: 违规类型
 * @Author: Jacky
 * @Date: 2014-8-27 下午4:33:31
 * @Version: V1.0  
 */
public class UnruleTypeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**违规类型编号*/
	private Integer unruleTypeId;
	
	/**违规类型名称 */
	private String unruleTypeName;

	public Integer getUnruleTypeId() {
		return unruleTypeId;
	}

	public void setUnruleTypeId(Integer unruleTypeId) {
		this.unruleTypeId = unruleTypeId;
	}
	public String getUnruleTypeName() {
		return unruleTypeName;
	}

	public void setUnruleTypeName(String unruleTypeName) {
		this.unruleTypeName = unruleTypeName;
	}
	
	
	

}
