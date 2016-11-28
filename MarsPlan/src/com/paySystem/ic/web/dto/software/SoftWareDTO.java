package com.paySystem.ic.web.dto.software;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class SoftWareDTO extends BaseDTO implements Serializable {

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
	private Integer id;
	private String name;
	private Integer typeId;
	private String thumbnailPath;
	private String description;
	private String author;
	private Date createDate;
	private String summary;
	private String panHref;
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

	public String getPanHref() {
		return panHref;
	}

	public void setPanHref(String panHref) {
		this.panHref = panHref;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
