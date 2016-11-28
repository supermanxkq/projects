package com.paySystem.ic.bean.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 系统角色
 * 
 * @version 2011-8-22 下午01:54:24
 */
@Entity
@Table(name = "s_role")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {
	private static final long serialVersionUID = -6559461654897632453L;

	private Integer id;
	/** 角色名称 */
	private String name;
	/** 角色编号 */
	private String code;
	/** 状态(1 启用0禁用) */
	private Integer status;
	/** 备注 */
	private String memo;
	/** 所属机构ID */
	private String organId;
	/** 是否通用 0否 1是 */
	private Integer isCommon;
	/** 角色-权限是多对多的关系，这里设成单向关系 */
	private Set<Privilege> privileges = new HashSet<Privilege>();

	public Role() {
	}

	public Role(Integer id) {
		this.id = id;
	}

/*	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="role_seq")
    @SequenceGenerator(name="role_seq",sequenceName="s_role_seq",allocationSize=1)*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 4,unique=true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(columnDefinition = "CHAR(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length = 255)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	@Column(columnDefinition="INTEGER default 0")
	public Integer getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "s_role_privilege", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "privilegeid", referencedColumnName = "id"))
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
}