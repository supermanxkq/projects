package com.blog.bean.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
@Entity
@Table(name = "t_log")
public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;

	private String sessionId;

	private String ip;

	private String page;

	private Date accessTime;

	private int stayTime;

	@Column(name = "access_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAccessTime() {
		return accessTime;
	}

	@Id
	public String getId() {
		return id;
	}

	@Index(name = "log_index")
	@Column
	public String getIp() {
		return ip;
	}

	@Column
	public String getPage() {
		return page;
	}

	@Index(name = "log_index")
	@Column
	public String getSessionId() {
		return sessionId;
	}

	@Column
	public int getStayTime() {
		return stayTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
	}

}
