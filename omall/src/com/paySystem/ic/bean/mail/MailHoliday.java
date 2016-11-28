package com.paySystem.ic.bean.mail;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 
 * @ClassName:MailHoliday
 * @TODO:节日邮件表
 * @date: 2014-3-21上午10:41:28
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "M_MailHoliday")
public class MailHoliday implements Serializable {
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
	/** 开启状态 0:未过  1：已过**/
	private Integer status;

/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MailHoliday")
	@SequenceGenerator(name = "S_MailHoliday", sequenceName = "S_MailHoliday")*/
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

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	/*@Column(columnDefinition="clob")
	 * 解决Oracle与MySql移植问题
	 * */
	@Column(columnDefinition="blob")
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
