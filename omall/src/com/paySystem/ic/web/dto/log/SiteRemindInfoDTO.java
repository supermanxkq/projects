/**  
* @Title: SiteRemindInfo.java
* @Package: com.paySystem.ic.web.dto.log
* @Description: TODO
* @Author: A18ccms A18ccms_gmail_com  
* @Date: 2014-9-12 下午02:45:28
* @Version: V1.0  
*/
package com.paySystem.ic.web.dto.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall2014911
 * @ClassName:SiteRemindInfo
 * @Description:网站提醒
 * @date: 2014-9-12
 * @author: 孙晓磊 廖晓远（改造）
 * @version: V1.0
 */
public class SiteRemindInfoDTO extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = -1324112849666702193L;
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
	/**
	 * 关系表主键
	 */
	private Integer detailId;
	/**
	 * 网站提醒的子信息
	 */
	private List<SiteRemindInfoDTO> childrens = new ArrayList<SiteRemindInfoDTO>();
	
	public Integer getContId() {
		return contId;
	}
	public void setContId(Integer contId) {
		this.contId = contId;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getRemSign() {
		return remSign;
	}
	public void setRemSign(Integer remSign) {
		this.remSign = remSign;
	}
	public Integer getEmailReceive() {
		return emailReceive;
	}
	public void setEmailReceive(Integer emailReceive) {
		this.emailReceive = emailReceive;
	}
	public Integer getLeterReceive() {
		return leterReceive;
	}
	public void setLeterReceive(Integer leterReceive) {
		this.leterReceive = leterReceive;
	}
	public Integer getPhoReceive() {
		return phoReceive;
	}
	public void setPhoReceive(Integer phoReceive) {
		this.phoReceive = phoReceive;
	}
	public List<SiteRemindInfoDTO> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<SiteRemindInfoDTO> childrens) {
		this.childrens = childrens;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
}
