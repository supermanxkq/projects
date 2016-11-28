package com.paySystem.ic.web.dto.system;

import java.io.Serializable;
import java.util.HashMap;

import com.paySystem.ic.bean.system.Module;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String passWord;
	private String realName;
	//0是平台 ，1是机构，2是商户
	private Integer userLevel;
	private String organId;
	private String merId;
	private String organName;
	private String merName;
	/** 上级 */
	private String preOrganId;
	/** 是否代理商0-否1-是 */
	private Integer dlsFlag;
	/** 是否企业用户0-否1-是 */
	private Integer cpyFlag;
	/** 企业ID */
	private String companyId;
	/**会员编号*/
	

	HashMap<String, Module> modulesMap = new HashMap<String, Module>();

	public UserSession() {
	}

	public HashMap<String, Module> getModulesMap() {
		return modulesMap;
	}

	public void setModulesMap(HashMap<String, Module> modulesMap) {
		this.modulesMap = modulesMap;
	}

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

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getPreOrganId() {
		return preOrganId;
	}

	public void setPreOrganId(String preOrganId) {
		this.preOrganId = preOrganId;
	}

	public Integer getDlsFlag() {
		return dlsFlag;
	}

	public void setDlsFlag(Integer dlsFlag) {
		this.dlsFlag = dlsFlag;
	}
	public Integer getCpyFlag() {
		return cpyFlag;
	}

	public void setCpyFlag(Integer cpyFlag) {
		this.cpyFlag = cpyFlag;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	
	
}
