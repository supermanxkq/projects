package com.paySystem.ic.bean.front;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:Annount
 * @Description:全站公告管理实体
 * @date: 2014-8-5
 * @author: 赵巧鹤
 * @version: V1.0
 */

public class Annount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**公告管理*/
	private Integer  annountId;
	
	/**公告标题*/
	private String annountTitle;
	
	/**公告内容*/
	private String annountCoutent;
	
	/**公告发布人*/
	private Date createTime;
	
	/**公告发布人*/
	private String author;
	
	
	
	public Integer getAnnountId() {
		return annountId;
	}
	public void setAnnountId(Integer annountId) {
		this.annountId = annountId;
	}
	public String getAnnountTitle() {
		return annountTitle;
	}
	public void setAnnountTitle(String annountTitle) {
		this.annountTitle = annountTitle;
	}
	public String getAnnountCoutent() {
		return annountCoutent;
	}
	public void setAnnountCoutent(String annountCoutent) {
		this.annountCoutent = annountCoutent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}
