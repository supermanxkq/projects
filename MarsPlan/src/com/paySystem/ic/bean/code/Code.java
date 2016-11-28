package com.paySystem.ic.bean.code;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_code")
public class Code implements Serializable {

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
	private Integer id;
	private String name;
	private String summary;
	private Integer typeId;
	private String thumbnailPath;
	private String description;
	private String author;
	private Date createDate;
	private String panHref;

	@Column
	public String getPanHref() {
		return panHref;
	}

	public void setPanHref(String panHref) {
		this.panHref = panHref;
	}

	@Column
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column
	public String getAuthor() {
		return author;
	}

	@Column
	public Date getCreateDate() {
		return createDate;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column
	public String getName() {
		return name;
	}

	@Column
	public String getThumbnailPath() {
		return thumbnailPath;
	}

	@Column
	public Integer getTypeId() {
		return typeId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
