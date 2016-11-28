package com.paySystem.ic.web.dto.mail;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName:MailContentDTO
 * @TODO:邮件内容记录DTO
 * @date: 2014-3-19下午02:09:31
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MailContentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 创建日期 **/
	private Date createTime;
	/** 内容 **/
	private String content;
	/**所属机构**/
	private String orgId;
	/**主题**/
	private String subject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
