package com.paySystem.ic.web.dto.system;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

public class UserDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;

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
	private Integer wrpass;
	/** 登录状态1 登录0 未登录 */
	private String loginFlag;
	/** 用户级别：0总部1发卡机构2商户(如果是0，商户号和机构号为空，是1，需记录机构号，是2需记录商户号) */
	private Integer userLevel;
	
	private String userLevelName;
	/** 最后登录时间 */
	private String lastLoginTime;
	
	private String merName;
	
	private String organName;
	
	private String roleName;
	
	private String newPassWord;
	/** KEYID */
	private String keyID;
	/** 代理商标志0-非代理商1-代理商 */
	private Integer dlsFlag;
	
	private List<String> roleIds;
	/** 企业ID */
	private String companyId;
	/** 企业名称 */
	private String companyName;
	/** 是否企业用户0-否1-是 */
	private Integer cpyFlag;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getWrpass() {
		return wrpass;
	}
	public void setWrpass(Integer wrpass) {
		this.wrpass = wrpass;
	}
	public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserLevelName() {
		return userLevelName;
	}
	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getNewPassWord() {
		return newPassWord;
	}
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	public String getKeyID() {
		return keyID;
	}
	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}
	public Integer getDlsFlag() {
		return dlsFlag;
	}
	public void setDlsFlag(Integer dlsFlag) {
		this.dlsFlag = dlsFlag;
	}
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getCpyFlag() {
		return cpyFlag;
	}
	public void setCpyFlag(Integer cpyFlag) {
		this.cpyFlag = cpyFlag;
	}
}
