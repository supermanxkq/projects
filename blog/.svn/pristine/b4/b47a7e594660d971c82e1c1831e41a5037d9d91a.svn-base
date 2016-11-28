package com.blog.bean.timeline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="admin")
public class Admin implements java.io.Serializable {

	// Fields

	/** */
	private static final long serialVersionUID = 6994876574879966385L;
	private Integer id;
	private String loginname;
	private String loginpwd;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String loginname, String loginpwd) {
		this.loginname = loginname;
		this.loginpwd = loginpwd;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	@Column
	public String getLoginname() {
		return this.loginname;
	}
	@Column
	public String getLoginpwd() {
		return this.loginpwd;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

}