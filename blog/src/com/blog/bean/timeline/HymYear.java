package com.blog.bean.timeline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HymYear entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hym_year")
public class HymYear implements java.io.Serializable {

	// Fields

	/** */
	private static final long serialVersionUID = -2511454960328265345L;
	private Integer id;
	private String year;

	// Constructors

	/** default constructor */
	public HymYear() {
	}

	/** full constructor */
	public HymYear(String year) {
		this.year = year;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	@Column
	public String getYear() {
		return this.year;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setYear(String year) {
		this.year = year;
	}

}