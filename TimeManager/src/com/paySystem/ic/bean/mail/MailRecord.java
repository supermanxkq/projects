package com.paySystem.ic.bean.mail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * 
 * @ClassName:MailRecord
 * @TODO:邮件记录表实体
 * @date: 2014-3-19上午10:14:14
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "M_MailRecord")
public class MailRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private Integer id;
	/** 发送邮箱 **/
	private String fromMail;
	/** 目标邮箱 **/
	private String toMail;
	/** 发送时间 **/
	private Date sendDate;
	/**发送类型：0代表个人，1代表群组  2代表节日  3代表生日**/
	private Integer type;
	/**与邮件内容记录表进行关联，一对多，此为多方**/
	private MailContent mailContent;
	/**机构号**/
	private String orgId;
/*	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="S_MailRecord")
	@SequenceGenerator(name="S_MailRecord",sequenceName="S_MailRecord")*/
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

	public Date getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	@Column(columnDefinition="char(1)")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name="m_id",referencedColumnName="id")
	public MailContent getMailContent() {
		return mailContent;
	}

	public void setMailContent(MailContent mailContent) {
		this.mailContent = mailContent;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
