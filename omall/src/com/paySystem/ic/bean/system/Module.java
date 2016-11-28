package com.paySystem.ic.bean.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

/**
 * 系统模块
 * 
 * @version 2011-8-22 下午01:50:31
 */

@Entity
@Table(name = "s_module")
public class Module implements Serializable {
	private static final long serialVersionUID = -7084393963287332082L;

	private String id;

	/** 模块名称 */
	private String name;

	/** 模块排序号 */
	private Integer sortOrder;

	/** 父模块 */
	private Module parent;

	/** URL地址 */
	private String linkAddr;
	/** 是否常用 0否 1是 */
	private Integer isOften = 0;

	/** 模块-权限是一对多的关系，这里设成双向关系 */
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	/** 子模块 */
	private List<Module> childModules = new ArrayList<Module>();

	public Module() {
	}

	public Module(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(length = 40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(length = 40)
	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}
	
	@Column
	public Integer getIsOften() {
		return isOften;
	}

	public void setIsOften(Integer isOften) {
		this.isOften = isOften;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "parentid")
	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "module", cascade = { CascadeType.PERSIST })
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST }, mappedBy = "parent", fetch = FetchType.EAGER)
	@OrderBy(value="sortOrder ASC")
	public List<Module> getChildModules() {
		return childModules;
	}

	public void setChildModules(List<Module> childModules) {
		this.childModules = childModules;
	}

	public Module(Element element){
		this.id = element.attributeValue("id");
		this.name = element.attributeValue("name");
		this.sortOrder = StringUtils.isNotBlank(element.attributeValue("sortorder"))?Integer.valueOf(element.attributeValue("sortorder")):null;
		this.linkAddr = element.attributeValue("linkaddr");
		this.isOften = StringUtils.isNotBlank(element.attributeValue("isOften"))?Integer.valueOf(element.attributeValue("isOften")):null;
	}
}