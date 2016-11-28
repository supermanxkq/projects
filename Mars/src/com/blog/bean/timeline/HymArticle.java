package com.blog.bean.timeline;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HymArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="hym_article")
public class HymArticle implements java.io.Serializable {

	/** */
	private static final long serialVersionUID = -713310242277041793L;
	private Integer id;
	private String title;
	private String content;
	private Date cdate;
	private Integer color;
	private Integer yearid;

	// Constructors

	/** default constructor */
	public HymArticle() {
	}

	/** full constructor */
	public HymArticle(String title, String content, Date cdate, Integer color,
			Integer yearid) {
		this.title = title;
		this.content = content;
		this.cdate = cdate;
		this.color = color;
		this.yearid = yearid;
	}

	// Property accessors
	@Column
	public Date getCdate() {
		return cdate;
	}

	@Column
	public Integer getColor() {
		return this.color;
	}
	@Column
	public String getContent() {
		return this.content;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}
	@Column
	public String getTitle() {
		return this.title;
	}
	@Column
	public Integer getYearid() {
		return yearid;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYearid(Integer yearid) {
		this.yearid = yearid;
	}

}