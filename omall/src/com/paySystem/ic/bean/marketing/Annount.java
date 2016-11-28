/**  
 * @Title: annount.java
 * @Package: com.paySystem.ic.bean.Marketing
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-9-9 上午10:15:26
 * @Version: V1.0  
 */
package com.paySystem.ic.bean.marketing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName:omall20140905
 * @ClassName:annount
 * @Description:全站公告管理实体类
 * @date: 2014-9-9
 * @author: 孙晓磊
 * @version: V1.0
 */
@Entity
@Table(name = "M_ANNOUNT")
public class Annount {

	/** 序列号用于反序列化 **/
	private static final long serialVersionUID = -1781073735536614648L;
	/** 公告编号 **/
	private Integer announId;
	/** 公告标题 **/
	private String announTitle;
	/** 公告内容 **/
	private String announContent;
	/** 公告的创建时间 **/
	private Date createTime;
	/** 公告的发布时间 **/
	private Date reportTime;
	/** 公告的发布人 **/
	private String author;
	/** 状态1:未发布（默认）2:发布 9:删除 **/
	private Integer status;
	/** 是否置顶0：否(默认)1：是 **/
	private Integer isTop;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getAnnounId() {
		return announId;
	}

	public void setAnnounId(Integer announId) {
		this.announId = announId;
	}

	@Column(length = 100)
	public String getAnnounTitle() {
		return announTitle;
	}

	public void setAnnounTitle(String announTitle) {
		this.announTitle = announTitle;
	}

	@Column(length = 200)
	public String getAnnounContent() {
		return announContent;
	}

	public void setAnnounContent(String announContent) {
		this.announContent = announContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(length = 50)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(length = 1)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length = 1)
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

}
