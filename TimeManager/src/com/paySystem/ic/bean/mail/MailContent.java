package com.paySystem.ic.bean.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @ClassName:MailContend
 * @TODO:邮件内容记录实体
 * @date: 2014-3-19下午12:00:20
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "M_MailContent")
public class MailContent implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 * @author 孟凡岭
	 * @date 2014-3-19 下午12:01:06
	 */
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
	/**与邮件内容记录表进行关联，一对多，此为一方**/
	private Set<MailRecord> recordSet;
/*	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="S_MailContent")
	@SequenceGenerator(name="S_MailContent",sequenceName="S_MailContent")*/
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	@OneToMany(mappedBy="mailContent",fetch=FetchType.LAZY)
	public Set<MailRecord> getRecordSet() {
		return recordSet;
	}

	public void setRecordSet(Set<MailRecord> recordSet) {
		this.recordSet = recordSet;
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
