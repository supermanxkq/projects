package com.paySystem.ic.web.dto.mail;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:MailHolidayDTO
 * @TODO:节日邮件DTO
 * @date: 2014-3-21上午11:06:53
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MailHolidayDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 节日名 **/
	private String holidayName;
	/** 节日日期 **/
	private String holidayDate;
	/**邮件主题**/
	private String subject;
	/**邮件内容**/
	private String content;
	/** 所属机构 **/
	private String orgId;
	/** 开启状态 **/
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
