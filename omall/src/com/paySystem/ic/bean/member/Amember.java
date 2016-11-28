package com.paySystem.ic.bean.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 会员辅助表
 * 
 * @author 井建国
 *
 */
@Entity
@Table(name = "MA_Member")
public class Amember {
	/** 会员辅助表流水账号 */
	private String amemId;
	/**会员昵称 */
	private String memName;
	/**会员生日 */
	private Date birthday;
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
	
	@Id
	@Column(length = 10)
	public String getAmemId() {
		return amemId;
	}
	public void setAmemId(String amemId) {
		this.amemId = amemId;
	}
//	@OneToOne(mappedBy="amember",fetch = FetchType.LAZY)
//	@JoinColumn(name="memId")
//	public Member getMember() {
//		return member;
//	}
//	public void setMember(Member member) {
//		this.member = member;
//	}
	@Column(length = 20)
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(length = 20)
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	@Column(length = 30)
	public String getCultDegree() {
		return cultDegree;
	}
	public void setCultDegree(String cultDegree) {
		this.cultDegree = cultDegree;
	}
	@Column(length = 60)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length = 10)
	public String getResidZip() {
		return residZip;
	}
	public void setResidZip(String residZip) {
		this.residZip = residZip;
	}
	@Column(length = 20)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column
	public Date getCertExTime() {
		return certExTime;
	}
	public void setCertExTime(Date certExTime) {
		this.certExTime = certExTime;
	}
}
