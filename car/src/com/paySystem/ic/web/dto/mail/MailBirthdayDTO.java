package com.paySystem.ic.web.dto.mail;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/***
 * 
 * @ClassName:MailBirthday
 * @TODO:会员生日邮件内容DTO
 * @date: 2014-3-24下午06:37:20
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MailBirthdayDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 邮件主题 **/
	private String subject;
	/** 邮件内容 **/
	private String content;
	/** 所属机构 **/
	private String orgId;
	/**创建日期**/
	private String createTime;
	/**启用状态0:启用  1:关闭**/
	private Integer status;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
