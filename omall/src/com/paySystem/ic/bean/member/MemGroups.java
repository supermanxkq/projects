package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.base.Organs;

/***
 * 会员群组管理
 * 
 * @author 解文侠
 * 
 */
@Entity
@Table(name = "M_MemGroups")
public class MemGroups implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 会员群组编号 */
	private Integer groupId;
	/** 会员群组名称 */
	private String groupName;
	/** 创建人 */
	private String userName;
	/** 群组所属机构 */
	private Organs organs;
	/** 所属商户 */
	private String merId;
	/** 群组启用状态 : 9 :删除 1：启用 0：禁用 */
	private Integer status;
	/** 群组创建时间 */
	private Date createTime;
	/** 群组修改时间 */
	private Date updateTime;
	/** 群组的描述信息 */
	private String groupDesc;

/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MemGroups")
	@SequenceGenerator(name = "S_MemGroups", sequenceName = "S_MemGroups")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(length = 16)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
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

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length = 300)
	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

}
