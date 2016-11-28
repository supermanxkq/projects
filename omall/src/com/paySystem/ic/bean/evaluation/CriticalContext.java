package com.paySystem.ic.bean.evaluation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: CriticalContext.java
 * @Package: com.paySystem.ic.bean.evaluation
 * @Description: 评论表
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午5:53:39
 * @Version: V1.0  
 */

@Entity
@Table(name="B_CriticalContext")
public class CriticalContext implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**评论编号 */
	private Integer griId;
	/**评价信息ID */
	private Integer mcrid;
	/**用户编号 */
	private String memId;
	/**用户类型 */
	private Integer memType;
	/**评论内容 */
	private String context;
	/**评论类型 */
	private Integer griType;
	/**回复评论编号  */
	private Integer griReplyId;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getGriId() {
		return griId;
	}
	
	public void setGriId(Integer griId) {
		this.griId = griId;
	}

	@Column(nullable=false)
	public Integer getMcrid() {
		return mcrid;
	}
	
	public void setMcrid(Integer mcrid) {
		this.mcrid = mcrid;
	}
	
	@Column(length=15,nullable=false)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(columnDefinition = "char(1)",nullable=false)
	public Integer getMemType() {
		return memType;
	}

	public void setMemType(Integer memType) {
		this.memType = memType;
	}

	@Column(length=255,nullable=false)
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(columnDefinition = "char(1)",nullable=false)
	public Integer getGriType() {
		return griType;
	}

	public void setGriType(Integer griType) {
		this.griType = griType;
	}

	@Column
	public Integer getGriReplyId() {
		return griReplyId;
	}

	public void setGriReplyId(Integer griReplyId) {
		this.griReplyId = griReplyId;
	}
	
	
	
	

}
