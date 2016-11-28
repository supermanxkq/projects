package com.paySystem.ic.web.dto.system;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.bean.system.Module;
import com.paySystem.ic.web.dto.BaseDTO;

public class ModuleDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;

	private String id;

	/** 模块名称 */
	private String name;

	/** 模块排序号 */
	private String sortOrder;

	/** URL地址 */
	private String linkAddr;
	
	/** 父级ID */
	private String parentId;
	
	private String parentName;
	/** 是否常用 0否 1是 */
	private Integer isOften;
	private List<Module> parent;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Module> getParent() {
		return parent;
	}

	public void setParent(List<Module> parent) {
		this.parent = parent;
	}

	public Integer getIsOften() {
		return isOften;
	}

	public void setIsOften(Integer isOften) {
		this.isOften = isOften;
	}
}
