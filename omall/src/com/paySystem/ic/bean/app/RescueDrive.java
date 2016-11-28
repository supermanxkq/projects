package com.paySystem.ic.bean.app;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: 张国法
 * @version: V1.0
 */
@Entity
@Table(name="b_rescue_drive")
public class RescueDrive implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/** app用户Id号  */
	private Integer id;
	
	/** app用户名称 */
	private String memId;
	
	/** 用户密码 */
	private String status;
	/** 身份验证 */
	private String telephone;
	private String lat;
	private String lng;
	
	/** 创建时间 */
	private String createTime;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=15)
	public String getMemId() {
		return memId;
	}


	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(length=20)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(length=15)
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	@Column(length=15)
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	@Column(length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(length=15)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	
	
}
