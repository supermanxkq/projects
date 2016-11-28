package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="b_banner")
public class BannerImg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer bannerId;
	
	private String bannerUrl;
	
	private Integer status;
	
	private String merId;
	
	private Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	@Column(length=200)
	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public String getMerId() {
		return merId;
	}
	@Column(length=15)
	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
