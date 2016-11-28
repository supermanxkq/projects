package com.spring.demo.test.object;

import java.io.Serializable;


/**
 * @ProjectName:SWI2 
 * @ClassName:TInfo  
 * @Description:人员信息实体
 * @date: 2015-7-12下午08:56:04
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-7-12下午08:56:04
 */
public class TInfo implements Serializable {
	/** */
	private static final long serialVersionUID = -5388747793836320734L;
	/**编号*/
	private Integer id;
	/**外键（用户编号）*/
	private String fid;
	/**年龄*/
	private String ages;
	/**手机*/
	private String mobile;
	/**地址*/
	private String addr;
	/**电子邮箱*/
	private String email;

	// Constructors
	/** default constructor */
	public TInfo() {
	}

	/** full constructor */
	public TInfo(String fid, String ages, String mobile, String addr,
			String email) {
		this.fid = fid;
		this.ages = ages;
		this.mobile = mobile;
		this.addr = addr;
		this.email = email;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getAges() {
		return this.ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}