package com.paySystem.ic.web.dto.evaluation;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;


/**  
 * @Title: EvaluationDTO.java
 * @Package: com.paySystem.ic.web.dto.evaluation
 * @Description: 评论内容
 * @Author: yanwuyang 
 * @Date: 2014-10-9 下午11:53:19
 * @Version: V1.0  
 */

public class CriticalContextDTO extends BaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2056915830405721261L;
	
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
	
	
	public Integer getGriId() {
		return griId;
	}
	public void setGriId(Integer griId) {
		this.griId = griId;
	}
	public Integer getMcrid() {
		return mcrid;
	}
	public void setMcrid(Integer mcrid) {
		this.mcrid = mcrid;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getMemType() {
		return memType;
	}
	public void setMemType(Integer memType) {
		this.memType = memType;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getGriType() {
		return griType;
	}
	public void setGriType(Integer griType) {
		this.griType = griType;
	}
	public Integer getGriReplyId() {
		return griReplyId;
	}
	public void setGriReplyId(Integer griReplyId) {
		this.griReplyId = griReplyId;
	}
	
	

}
