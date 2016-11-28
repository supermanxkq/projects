package com.paySystem.ic.bean.system;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "s_user")
public class User implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	/** 主键 */
	private String userName;
	/** 密码 */
	private String passWord;
	/** 姓名  */
	private String realName;
	/** 所属机构ID */
	private String organId;
	/** 所属商户ID */
	private String merId;
	/** 电话 */
	private String teleNo;
	/** 状态(0禁用1启用2锁定) */
	private Integer status;
	/** 密码错误次数锁定 默认值：0 */
	private Integer wrpass = 0;
	/** 登录状态1 登录0 未登录 */
	private Integer loginFlag = 0;
	/** 用户级别：0总部1发卡机构2商户(如果是0，商户号和机构号为空，是1，需记录机构号，是2需记录商户号) */
	private Integer userLevel;
	/** 创建时间 */
	private Date createTime;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** KEYID */
	private String keyID;
	/** 是否代理商0-否1-是 */
	private Integer dlsFlag;
	/** 是否企业用户0-否1-是 */
	private Integer cpyFlag = 0;
	/** 企业ID */
	private String companyId;
	/** 角色 */
	private Set<Role> roles = new HashSet<Role>();

	public User(String userName) {
		this.userName = userName;
	}

	public User() {
		super();
	}

	@Id
	@Column(length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length = 50)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(length = 20)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 20)
	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column
	public Integer getWrpass() {
		return wrpass;
	}

	public void setWrpass(Integer wrpass) {
		this.wrpass = wrpass;
	}

	@Column
	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}

	@Column
	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
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
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Column(columnDefinition="INTEGER default 0")
	public Integer getDlsFlag() {
		return dlsFlag;
	}

	public void setDlsFlag(Integer dlsFlag) {
		this.dlsFlag = dlsFlag;
	}
	
	@Column(columnDefinition="INTEGER default 0")
	public Integer getCpyFlag() {
		return cpyFlag;
	}

	public void setCpyFlag(Integer cpyFlag) {
		this.cpyFlag = cpyFlag;
	}

	@Column(length = 8)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(length=20,unique=true)
	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "s_user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
