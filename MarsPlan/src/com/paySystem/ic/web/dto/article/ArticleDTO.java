package com.paySystem.ic.web.dto.article;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class ArticleDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3077939892741272807L;
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
	/** 状态 */
	private Integer status;
	/** 阅读次数 */
	private BigInteger readTimes;
	private Integer blogTypeId;
	/** 文章类型名称 */
	private String blogTypeName;
	/** 文章类型下文章数目 */
	private Integer blogTypeCount;
	/** 缩略图 */
	private String thumbnailPath;
	private String contentDateString;
	private String count;
	private String summary;
	private File imageFile;
	private String imageFileFileName;

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getContentDate() {
		return contentDate;
	}

	public void setContentDate(Date contentDate) {
		this.contentDate = contentDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public BigInteger getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(BigInteger readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getBlogTypeId() {
		return blogTypeId;
	}

	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}

	public String getBlogTypeName() {
		return blogTypeName;
	}

	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}

	public Integer getBlogTypeCount() {
		return blogTypeCount;
	}

	public void setBlogTypeCount(Integer blogTypeCount) {
		this.blogTypeCount = blogTypeCount;
	}

	public String getContentDateString() {
		return contentDateString;
	}

	public void setContentDateString(String contentDateString) {
		this.contentDateString = contentDateString;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
