package com.paySystem.ic.bean.article;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_article")
public class Article implements Serializable {

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
	/** 文章id */
	private Integer id;
	/** 文章title */
	private String title;
	/** 文章内容 */
	private String content;
	/** 发表时间 */
	private Date contentDate;
	/** 编辑者 */
	private String author;
	/** 博客类型 */
	private Integer typeId;
	/** 赞 */
	private Integer good;
	/** 阅读次数 */
	private BigInteger readTimes;
	private Integer status;
	private String thumbnailPath;
	private String summary;

	@Column
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column
	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	@Column
	public String getAuthor() {
		return author;
	}

	@Lob
	public String getContent() {
		return content;
	}

	@Column
	public Date getContentDate() {
		return contentDate;
	}

	@Column
	public Integer getGood() {
		return good;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	@Column
	public String getTitle() {
		return title;
	}

	@Column
	public Integer getTypeId() {
		return typeId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContentDate(Date contentDate) {
		this.contentDate = contentDate;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public BigInteger getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(BigInteger count) {
		this.readTimes = count;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
