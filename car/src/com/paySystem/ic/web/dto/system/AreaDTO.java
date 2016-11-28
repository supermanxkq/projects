package com.paySystem.ic.web.dto.system;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;


public class AreaDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;

	private String id;
	/** 显示名称 */
	private String displayName;
	/** 全称 */
	private String fullName;
	/** 状态 0禁用 1启用 */
	private Integer status;
	/** 父级ID */
	private String parentId;
	/** 地区编码 */
	private String areaCode;
	/** 级别 */
	private Integer areaLevel;
	
	private String parentName;
	
	/** 父级ID */
	private String _parentId;
	
	private String operate;
	
	private List<AreaDTO> child;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public List<AreaDTO> getChild() {
		return child;
	}
	public void setChild(List<AreaDTO> child) {
		this.child = child;
	}
	
}
