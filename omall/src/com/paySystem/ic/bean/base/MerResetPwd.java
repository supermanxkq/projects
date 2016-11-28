package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwd
 * @Description:商户入驻密码重置
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "b_MerResetPwd")
public class MerResetPwd implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 商户号 **/
	private String merId;
	/** 会员号 **/
	private Integer memId;
	/** 手机号 **/
	private String teleNo;
	/** MD5（商户号+会员号+手机号） **/
	private String md5;
	/** 审核通过时间 **/
	private Date AuditingTime;
	/** 是否已经重置过密码，0、未重置；1、已重置 **/
	private Integer isReset;
	/** 操作人 **/
	private String operateId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 10)
	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Column(length = 11)
	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	@Column
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Column(columnDefinition = "timestamp")
	public Date getAuditingTime() {
		return AuditingTime;
	}

	public void setAuditingTime(Date auditingTime) {
		AuditingTime = auditingTime;
	}

	@Column(columnDefinition = "char(1) default 0")
	public Integer getIsReset() {
		return isReset;
	}

	public void setIsReset(Integer isReset) {
		this.isReset = isReset;
	}

	@Column(length = 20)
	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

}
