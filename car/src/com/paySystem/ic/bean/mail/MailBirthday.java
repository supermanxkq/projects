package com.paySystem.ic.bean.mail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 
 * @ClassName:MailBirthday
 * @TODO:会员生日邮件内容表
 * @date: 2014-3-24下午06:33:57
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "M_MailBirthday")
public class MailBirthday implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 邮件主题 **/
	private String subject;
	/** 邮件内容 **/
	private String content;
	/** 所属机构 **/
	private String orgId;
	/** 创建日期 **/
	private Date createTime;
	/** 启用状态0:启用 1:关闭 **/
	private Integer status;

/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MailBirthday")
	@SequenceGenerator(name = "S_MailBirthday", sequenceName = "S_MailBirthday")*/
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
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
    @Column
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
