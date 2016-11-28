package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ProjectName: omall
 * @ClassName: Member
 * @Description: 电子商务平台会员信息
 * @date: 2014-10-8 下午04:27:31
 * @author: 郭营
 * @version: V1.0
 */
@Entity
@Table(name = "M_Members")
public class Members implements Serializable {

	private static final long serialVersionUID = 6075428406653446377L;
	/** 会员编号 */
	private Integer memId;
	/** 会员真实姓名 */
	private String realName;
	/** 会员证件类型 1： 身份证 2：军官证 3：护照 */
	private Integer perType;
	/** 会员证件号码 */
	private String personId;
	/** 会员电话 */
	private String teleNo;
	/** 会员性别 1：男 2：女 */
	private Integer sex;
	/** 会员所属区域 */
	private String areaId;
	/** 会员电子邮箱 */
	private String email;
	/** 会员群组 */
	private String groupId;
	/** 会员信息修改时间 */
	private Date updateTime;
	/** 会员信息创建时间 */
	private Date createTime;
	/** 会员状态 1：正常 0;禁用 9：删除 */
	private Integer status;
	/** 用户名 */
	private String userName;

	public Members(Integer memId) {
		this.memId = memId;
	}

	public Members() {
		super();
	}

	@Id
	@Column(length = 10)
	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Column(length = 30)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(length = 20)
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(length = 11)
	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(length = 10)
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 8)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPerType(Integer perType) {
		this.perType = perType;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getPerType() {
		return perType;
	}

	@Column(length = 30)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
