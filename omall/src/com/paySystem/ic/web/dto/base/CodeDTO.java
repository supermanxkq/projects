package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class CodeDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 验证码记录编号 */
	private Integer codeId;
	
	/** 验证码 */
	private String codeNum;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 间隔时间 */
	private String spaceTime;
	
	/** 邮箱 */
	private String Email;
	
	/** 电话号码 */
	private String telNum;
	
	/** 返回发送信息*/
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSpaceTime() {
		return spaceTime;
	}

	public void setSpaceTime(String spaceTime) {
		this.spaceTime = spaceTime;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
}
