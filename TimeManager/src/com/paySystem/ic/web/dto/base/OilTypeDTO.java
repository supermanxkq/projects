package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:OilTypeDTO
 * @Description:油品管理DTO
 * @date: 2014-5-20下午08:29:53
 * @author: 井建国
 * @version: V1.0
 */
public class OilTypeDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**油品编号***/
	private Integer oilTypeId;
	
	/***油品名称***/
	private String oilTypeName;
	
	/**启用状态   1：启用    2：禁用    9：删除***/
	private Integer status;
	
	/***操作人编号（名称）**/
	private String operId;
	
	/***更新时间**/
	private Date updateTime;
	
	/**油品描述**/
	private String descr;
	
	

	public Integer getOilTypeId() {
		return oilTypeId;
	}

	public void setOilTypeId(Integer oilTypeId) {
		this.oilTypeId = oilTypeId;
	}

	public String getOilTypeName() {
		return oilTypeName;
	}

	public void setOilTypeName(String oilTypeName) {
		this.oilTypeName = oilTypeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
