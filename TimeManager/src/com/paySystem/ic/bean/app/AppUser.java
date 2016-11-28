package com.paySystem.ic.bean.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:AppUser
 * @Description:App端需要 app用户信息
 * @date: 2014-5-26下午03:53:23
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_appuser")
public class AppUser implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/** app用户Id号  */
	private Integer userId;
	
	/** app用户名称 */
	private String userName;
	
	/** 用户密码 */
	private String password;
	/** 身份验证 */
	private String token;

	/** 会员编号 */
	private String memId;
	
	/** 创建时间 */
	private String createTime;

	private String lat;
	
	private String lng;
	
	private String headerUrl;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length=15)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length=8)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	@Column(length=16)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@Column(length=200)
	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}
	
	
	
}
