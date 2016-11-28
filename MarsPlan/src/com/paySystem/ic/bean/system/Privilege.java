package com.paySystem.ic.bean.system;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 系统权限
 * 
 * @version 2011-8-22 下午01:50:31
 */
@Entity
@Table(name = "s_privilege")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Privilege implements Serializable {
	private static final long serialVersionUID = -7781914639237589297L;

	private String id;

	/** 权限名称 */
	private String name;
	/** 权限级别0总部1 发卡机构 */
	private Integer privilegeLevel;
	
	/** 权限-模块是多对一的关系，这里设成双向关系 */
	private Module module;

	public Privilege(Module module, String id, String name) {
		this.module = module;
		this.id = id;
		this.name = name;
	}

	public Privilege(Module module) {
		this.module = module;
	}

	public Privilege() {
	}

	@Id
	@Column(length = 40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 40, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column
	public Integer getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(Integer privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "moduleid")
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Privilege(Element element){
		this.id = element.attributeValue("id");
		this.name = element.attributeValue("name");
		this.privilegeLevel = StringUtils.isNotBlank(element.attributeValue("privilegeLevel"))?Integer.valueOf(element.attributeValue("privilegeLevel")):1;
	}

}
