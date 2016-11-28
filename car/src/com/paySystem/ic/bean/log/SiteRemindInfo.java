/**  
* @Title: siteRemindInfo.java
* @Package: com.paySystem.ic.bean.log
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-12 下午02:30:11
* @Version: V1.0  
*/
package com.paySystem.ic.bean.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall2014911
 * @ClassName:siteRemindInfo
 * @Description:TODO
 * @date: 2014-9-12
 * @author: 孙晓磊
 * @version: V1.0
 */
@Entity
@Table(name = "L_SITEREMINDINFO")
public class SiteRemindInfo {
	/**序列号用于反序列化**/
	private static final long serialVersionUID = -1781073735536614648L;
	/**内容编号**/
	private Integer contId;
	/**信息内容**/
	private String infoContent;
	/**上级编号**/
	private String parentId;
	/**标识 0：卖家提醒 1：买家提醒**/
	private Integer remSign;
	/**邮件接收方式0：否 1：是**/
	private Integer emailReceive;
	/**站内信接收方式 0：否 1：是**/
	private Integer leterReceive;
	/**手机接收方式0：否 1：是**/
	private Integer phoReceive;
	
	/**get set 方法**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getContId() {
		return contId;
	}
	public void setContId(Integer contId) {
		this.contId = contId;
	}
	@Column(length = 500)
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	@Column(length = 8)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(length=1)
	public Integer getRemSign() {
		return remSign;
	}
	public void setRemSign(Integer remSign) {
		this.remSign = remSign;
	}
	@Column(length=1)
	public Integer getEmailReceive() {
		return emailReceive;
	}
	public void setEmailReceive(Integer emailReceive) {
		this.emailReceive = emailReceive;
	}
	@Column(length=1)
	public Integer getLeterReceive() {
		return leterReceive;
	}
	public void setLeterReceive(Integer leterReceive) {
		this.leterReceive = leterReceive;
	}
	@Column(length=1)
	public Integer getPhoReceive() {
		return phoReceive;
	}
	public void setPhoReceive(Integer phoReceive) {
		this.phoReceive = phoReceive;
	}
	
	
	
	
	

}
