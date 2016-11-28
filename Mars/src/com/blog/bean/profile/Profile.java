package com.blog.bean.profile;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 个人简介详情
 * 
 */
@Entity
@Table(name = "t_profile")
public class Profile implements Serializable{
	/** */
	private static final long serialVersionUID = 686564512944050852L;
	/** 编号 */
	private Integer profileId;
	/** 姓名 */
	private String userName;
	/** 国籍 */
	private String country;
	/** 生日 */
	private Date birthday;
	/** 职业 */
	private String occupation;
	/** 电子邮箱 */
	private String email;
	/** QQ */
	private String QQ;
	/** 头像地址 */
	private String photoHref;
	/** 目前状态 */
	private String currentStatus;
	/** 职位 */
	private String job;
	/** 微博 */
	private String microBlog;
	/** 微信账号的形式 */
	private String weChat;
	/** 个人博客 */
	private String blogAddress;

	@Column
	public Date getBirthday() {
		return birthday;
	}

	@Column
	public String getBlogAddress() {
		return blogAddress;
	}

	@Column
	public String getCountry() {
		return country;
	}

	@Column
	public String getCurrentStatus() {
		return currentStatus;
	}

	@Column
	public String getEmail() {
		return email;
	}

	@Column
	public String getJob() {
		return job;
	}

	@Column
	public String getMicroBlog() {
		return microBlog;
	}

	@Column
	public String getOccupation() {
		return occupation;
	}

	@Column
	public String getPhotoHref() {
		return photoHref;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getProfileId() {
		return profileId;
	}

	@Column
	public String getQQ() {
		return QQ;
	}

	@Column
	public String getUserName() {
		return userName;
	}
	@Column
	public String getWeChat() {
		return weChat;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBlogAddress(String blogAddress) {
		this.blogAddress = blogAddress;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setMicroBlog(String microBlog) {
		this.microBlog = microBlog;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setPhotoHref(String photoHref) {
		this.photoHref = photoHref;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

}
