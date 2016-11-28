package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="b_news")
public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer newsId;//消息id
	private String newsTitle;//消息标题
	private String content;//消息内容
	private Integer newsType;//消息类型
	private Integer ststus;//状态
	private String newsUrl;//消息图片
	private Date createTime;//创建时间
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	
	@Column(length=50)
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	@Column(length=200)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(columnDefinition = "char(1)")
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	
	@Column(columnDefinition = "char(1)")
	public Integer getStstus() {
		return ststus;
	}
	public void setStstus(Integer ststus) {
		this.ststus = ststus;
	}
	
	@Column(length=200)
	public String getNewsUrl() {
		return newsUrl;
	}
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}