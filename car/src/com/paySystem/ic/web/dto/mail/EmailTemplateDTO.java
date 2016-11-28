/**  
* @Title: EmailTemplate.java
* @Package: com.paySystem.ic.web.dto.mail
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-23 上午11:05:05
* @Version: V1.0  
*/
package com.paySystem.ic.web.dto.mail;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:EmailTemplate
 * @Description:TODO
 * @date: 2014-9-23
 * @author: 孙晓磊
 * @version: V1.0
 */
public class EmailTemplateDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**模板id**/
	private Integer emailId;
	private Integer everyId;
	/**模板名称**/
	private String emailName;
	/**邮件主题**/
	private String emailTile;
	/**邮件类型**/
	private Integer emailType;
	/**模板内容**/
	private String etContent;
	/**更新时间**/
	private String update;
	/**操作人**/
	private String operator;
	
	
	
	public Integer getEveryId() {
		return everyId;
	}
	public void setEveryId(Integer everyId) {
		this.everyId = everyId;
	}
	
	public Integer getEmailId() {
		return emailId;
	}
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}
	public String getEmailName() {
		return emailName;
	}
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	public String getEmailTile() {
		return emailTile;
	}
	public void setEmailTile(String emailTile) {
		this.emailTile = emailTile;
	}
	public Integer getEmailType() {
		return emailType;
	}
	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}
	public String getEtContent() {
		return etContent;
	}
	public void setEtContent(String etContent) {
		this.etContent = etContent;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	

	
}
