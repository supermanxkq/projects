package cn.goodym.ORM;

import java.util.Date;



/**
 * HymArticle entity. @author MyEclipse Persistence Tools
 */

public class HymArticle implements java.io.Serializable {

	// Fields

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
	public HymArticle(String title, String content, Date cdate,
			Integer color,Integer yearid) {
		this.title = title;
		this.content = content;
		this.cdate = cdate;
		this.color = color;
		this.yearid = yearid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Integer getColor() {
		return this.color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getYearid() {
		return yearid;
	}

	public void setYearid(Integer yearid) {
		this.yearid = yearid;
	}

}