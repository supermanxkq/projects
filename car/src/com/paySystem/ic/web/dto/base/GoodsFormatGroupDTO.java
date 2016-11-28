package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: Goods.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品规格分组DTO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:28:49
 * @Version: V1.0  
 */

public class GoodsFormatGroupDTO  extends BaseDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1666513345447667397L;

	/** 分组编号 */
	private Integer fgroupId;
	
	/** 规格分组名称 */
	private String fgroupName;

	public Integer getFgroupId() {
		return fgroupId;
	}

	public void setFgroupId(Integer fgroupId) {
		this.fgroupId = fgroupId;
	}

	public String getFgroupName() {
		return fgroupName;
	}

	public void setFgroupName(String fgroupName) {
		this.fgroupName = fgroupName;
	}
	
	

}
