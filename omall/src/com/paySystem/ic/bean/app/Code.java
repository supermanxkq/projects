package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:Code
 * @Description:验证码实体信息
 * @date: 2014-5-26下午04:08:26
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_code")
public class Code implements Serializable {


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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	

	@Column(length=30)
	public String getSpaceTime() {
		return spaceTime;
	}

	public void setSpaceTime(String spaceTime) {
		this.spaceTime = spaceTime;
	}
	@Column(length=50)
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	@Column(length=15)
	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	@Column(length=6)
	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
