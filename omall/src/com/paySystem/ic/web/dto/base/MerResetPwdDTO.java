/**  
 * @Title: MerResetPwd.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-11 下午02:06:04
 * @Version: V1.0  
 */
package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:MerResetPwd
 * @Description:商户入驻密码重置
 * @date: 2014-12-11
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MerResetPwdDTO extends BaseDTO implements Serializable {
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
	/** 是否已经重置过密码 **/
	private Integer isReset;
	/** 操作人 **/
	private String operateId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getTeleNo() {
		return teleNo;
	}

	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Date getAuditingTime() {
		return AuditingTime;
	}

	public void setAuditingTime(Date auditingTime) {
		AuditingTime = auditingTime;
	}

	public Integer getIsReset() {
		return isReset;
	}

	public void setIsReset(Integer isReset) {
		this.isReset = isReset;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

}
