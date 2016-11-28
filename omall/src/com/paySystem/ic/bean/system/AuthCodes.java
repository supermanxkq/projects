package com.paySystem.ic.bean.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 验证码表
 * @author DELL
 *
 */
@Entity
@Table(name = "s_authcodes")
public class AuthCodes implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	private Integer id;
	/** 验证码标志 MRT手机端注册验证 MFP找回密码验证 PNR网银充值号码验证PBR 银行卡转账充值号码验证 */
	private String flag;
	/** 用户类型1个人版2商户版 3机构 4商户*/
	private String type;
	/** 手机号码 */
	private String teleNo;
	/** 验证码 */
	private String authCode;
	/** 验证码过期时间 */
	private Date exTime;
	/** 创建时间 */
	private Date createTime;
	
/*	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="s_authcode_seq")
    @SequenceGenerator(name="s_authcode_seq",sequenceName="s_authcode_seq",allocationSize=1)*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(length=20)
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	@Column(length=6)
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getExTime() {
		return exTime;
	}
	public void setExTime(Date exTime) {
		this.exTime = exTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

