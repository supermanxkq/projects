package com.paySystem.ic.bean.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: omall_back
 * @ClassName: SiteRemind
 * @Description: 网站提醒详细
 * @date: 2014-11-7
 * @author: 廖晓远 
 * @version: V1.0
 */
@Entity
@Table(name = "L_SITEREMINDINFODETAIL")
public class SiteRemind {
	/**序列号用于反序列化**/
	private static final long serialVersionUID = 1L;
	/**内容编号**/
	private Integer detailId;
	/**网站提醒对象**/
	private Integer contId;
	/**标识 0：卖家提醒 1：买家提醒**/
	private Integer remSign;
	/**邮件接收方式0：否 1：是**/
	private Integer emailReceive;
	/**站内信接收方式 0：否 1：是**/
	private Integer leterReceive;
	/**手机接收方式0：否 1：是**/
	private Integer phoReceive;
	/**商户ID**/
	private String merId;
	/**get set 方法**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	@Column(length=1 ,nullable=false)
	public Integer getRemSign() {
		return remSign;
	}
	public void setRemSign(Integer remSign) {
		this.remSign = remSign;
	}
	@Column(length=1 ,nullable=false)
	public Integer getEmailReceive() {
		return emailReceive;
	}
	public void setEmailReceive(Integer emailReceive) {
		this.emailReceive = emailReceive;
	}
	@Column(length=1 ,nullable=false)
	public Integer getLeterReceive() {
		return leterReceive;
	}
	public void setLeterReceive(Integer leterReceive) {
		this.leterReceive = leterReceive;
	}
	@Column(length=1 ,nullable=false)
	public Integer getPhoReceive() {
		return phoReceive;
	}
	public void setPhoReceive(Integer phoReceive) {
		this.phoReceive = phoReceive;
	}
	@Column(length=15 ,nullable=false)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	@Column(length=8 ,nullable=false)
	public Integer getContId() {
		return contId;
	}
	public void setContId(Integer contId) {
		this.contId = contId;
	}
	
}
