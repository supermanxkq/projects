package com.paySystem.ic.web.dto.mail;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:MailRecordDTO
 * @TODO:邮件记录表DTO
 * @date: 2014-3-19上午10:38:10
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MailRecordDTO extends BaseDTO implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 * @author 孟凡岭
	 * @date 2014-3-19 上午10:39:02
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 发送邮箱 **/
	private String fromMail;
	/** 目标邮箱 **/
	private String toMail;
	/** 发送时间 **/
	private String sendDate;
	/**发送类型：0代表个人，1代表群组  2代表节日  3代表生日**/
	private Integer type;
	/**机构号**/
	private String orgId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
