package com.spring.demo.test.object;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:SWI
 * @ClassName:TUser
 * @Description:用户实体类
 * @date: 2015-7-10下午03:18:09
 * @author: 半仙儿
 * @version: V1.0
 * @date:2015-7-10下午03:18:09
 */
public class TUser implements Serializable {

	/** 序列化 */
	private static final long serialVersionUID = 3407171496879486874L;
	/** 编号 */
	private Integer id;
	/** 用户名 */
	private String uname;
	/** 密码 */
	private String pwd;
	/** 中文姓名 */
	private String cnname;
	/** 创建日期 */
	private Date createdate;
	/** 性别 */
	private String sex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
