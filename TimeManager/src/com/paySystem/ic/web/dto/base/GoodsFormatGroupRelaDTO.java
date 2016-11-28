package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: GoodsFormatGroupRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 规格分组关联DTO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:32:01
 * @Version: V1.0  
 */

public class GoodsFormatGroupRelaDTO  extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9063546235526049705L;

	/**编号 */
	private Integer fgrId;
	
	/** 规格编号（规格表Id*/
	private Integer formatId;
	
	/**规格分组id（规格分组表Id */
	private Integer fgroupId;
	
	/**规格分组名称*/
	private String fgroupName;
	
	public String getFgroupName() {
		return fgroupName;
	}

	public void setFgroupName(String fgroupName) {
		this.fgroupName = fgroupName;
	}

	public Integer getFgrId() {
		return fgrId;
	}

	public void setFgrId(Integer fgrId) {
		this.fgrId = fgrId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public Integer getFgroupId() {
		return fgroupId;
	}

	public void setFgroupId(Integer fgroupId) {
		this.fgroupId = fgroupId;
	}
	
	
	
	
	

}
