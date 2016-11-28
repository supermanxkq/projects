package com.paySystem.ic.bean.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	
	/** 电话号码 */
	private String  telephone;
	
	/** 验证码编号 */
	private String codeNum;
	
	/** 创建时间 */
	private String createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	@Column(length=15)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length=6)
	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	@Column(length=20)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
