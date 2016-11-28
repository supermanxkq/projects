package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
 * 会员辅助表数据传送对象
 * 
 * @author 井建国
 *
 */
public class AmemberDTO extends BaseDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 会员辅助表流水账号 */
	private String amemId;
	/**会员Id */
	private String memId;
	/**会员昵称 */
	private String memName;
	/**会员生日 */
	private String birthday;
	/**会员职业 */
	private String career;
	/**会员学历 */
	private String cultDegree;
	/** 会员居住地址*/
	private String address;
	/**会员邮编 */
	private String residZip;
	/**会员密码*/
	private String pwd;
	/**会员证件有效期*/
	private Date certExTime;
	/**会员信息创建时间  */
	private Date createTime;
	
	public String getAmemId() {
		return amemId;
	}
	public void setAmemId(String amemId) {
		this.amemId = amemId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getCultDegree() {
		return cultDegree;
	}
	public void setCultDegree(String cultDegree) {
		this.cultDegree = cultDegree;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResidZip() {
		return residZip;
	}
	public void setResidZip(String residZip) {
		this.residZip = residZip;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCertExTime() {
		return certExTime;
	}
	public void setCertExTime(Date certExTime) {
		this.certExTime = certExTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
