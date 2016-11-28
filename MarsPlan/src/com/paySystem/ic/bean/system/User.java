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
	/**电子邮箱*/
	private String email;
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

	public User() {
		super();
	}

	public User(String userName) {
		this.userName = userName;
	}

	@Column(length = 8)
	public String getCompanyId() {
		return companyId;
	}

	@Column(columnDefinition="INTEGER default 0")
	public Integer getCpyFlag() {
		return cpyFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	@Column(columnDefinition="INTEGER default 0")
	public Integer getDlsFlag() {
		return dlsFlag;
	}

	@Column
	public String getEmail() {
		return email;
	}

	@Column(length=20,unique=true)
	public String getKeyID() {
		return keyID;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	@Column
	public Integer getLoginFlag() {
		return loginFlag;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}

	@Column(length = 50)
	public String getPassWord() {
		return passWord;
	}

	@Column(length = 20)
	public String getRealName() {
		return realName;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "s_user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
	public Set<Role> getRoles() {
		return roles;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	@Column(length = 20)
	public String getTeleNo() {
		return teleNo;
	}

	@Column
	public Integer getUserLevel() {
		return userLevel;
	}

	@Id
	@Column(length = 20)
	public String getUserName() {
		return userName;
	}

	@Column
	public Integer getWrpass() {
		return wrpass;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCpyFlag(Integer cpyFlag) {
		this.cpyFlag = cpyFlag;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDlsFlag(Integer dlsFlag) {
		this.dlsFlag = dlsFlag;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}
	
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setWrpass(Integer wrpass) {
		this.wrpass = wrpass;
	}
}
