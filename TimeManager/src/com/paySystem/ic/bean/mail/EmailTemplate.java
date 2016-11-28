/**  
* @Title: EmailTemplate.java
* @Package: com.paySystem.ic.bean.mail
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-22 下午04:48:40
* @Version: V1.0  
*/
package com.paySystem.ic.bean.mail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplate
 * @Description:TODO
 * @date: 2014-9-22
 * @author: 孙晓磊
 * @version: V1.0
 */
@Entity
@Table(name="M_EmailTemplate")
public class EmailTemplate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**模板ID**/
	private Integer emailId;
	/**模板名称**/
	private String etmailName;
	/**邮件主题**/
	private String emailTitle;
	/**邮件类型**/
	private String emailType;
	/**邮件内容**/
	private String etContent;
	/**更新时间**/
	private Date updateTime;
	/**操作内容**/
	private String Operator;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getEmailId() {
		return emailId;
	}
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}
	@Column(length=50)
    public String getEtmailName() {
		return etmailName;
	}
	public void setEtmailName(String etmailName) {
		this.etmailName = etmailName;
	}
	@Column(length=200)
	public String getEmailTitle() {
		return emailTitle;
	}

	
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	@Column(columnDefinition="char(1)")
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	@Column(length=1000)
	public String getEtContent() {
		return etContent;
	}
	public void setEtContent(String etContent) {
		this.etContent = etContent;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length=20)
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	
	


}
