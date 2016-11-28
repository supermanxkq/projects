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
@Table(name = "s_SystemConfig")
public class SystemConfig implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;

	private String paramKey;
	/** 参数名 */
	private String paramValue;
	/** 更新时间 */
	private String description;
	
	/** 更新时间 */
	private Date updateTime;
	
	@Id
	@Column(length = 40)
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	@Column(length = 40)
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	@Column(length = 255)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
