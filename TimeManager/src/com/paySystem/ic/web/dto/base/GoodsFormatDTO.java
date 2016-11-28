package com.paySystem.ic.web.dto.base;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: GoodsFormat.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品规格DTO
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:25:46
 * @Version: V1.0  
 */
public class GoodsFormatDTO  extends BaseDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7817317283291111185L;

	/** 编号 */
	private Integer formatId;
	
	/** 规格名称 */
	private String formatName;
	
	private String isParent;

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	

}
