package com.paySystem.ic.bean.system;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "s_area")
public class Area implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	
	private String id;
	/** 显示名称 */
	private String displayName;
	/** 全称 */
	private String fullName;
	/** 状态 0禁用 1启用 */
	private Integer status;
	/** 父级ID */
	private String parentId;
	/** 更新时间 */
	private Date updateTime;
	/** 级别 */
	private Integer areaLevel;
	/** 地区编码 */
	private String areaCode;
	
	@Id
	@Column(length = 10)
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(length = 40)
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Column(length = 255)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(length = 10)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	@Column(length = 10)
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
}