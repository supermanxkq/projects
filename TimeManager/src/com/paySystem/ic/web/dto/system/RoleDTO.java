package com.paySystem.ic.web.dto.system;

import com.paySystem.ic.web.dto.BaseDTO;

public class RoleDTO extends BaseDTO {
	private static final long serialVersionUID = 3821284030659657658L;
	private Integer id;
	private String name;
	private String code;
	private String areaIds;
	/** 角色级别(0总部1发卡机构2商户) */
	private Integer flag;
	/** 状态(1 启用0禁用) */
	private Integer status;
	/** 所属机构ID */
	private String organId;
	
	private String organName;
	/** 备注 */
	private String memo;
	/** 是否通用 0否 1是 */
	private Integer isCommon = 0;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}
}
