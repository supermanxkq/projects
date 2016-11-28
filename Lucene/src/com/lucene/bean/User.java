package com.lucene.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:Demo1
 * @ClassName:User  
 * @Description:用户类
 * @date: 2015-6-14下午09:14:55
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午09:14:55
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{
	/** */
	private static final long serialVersionUID = 1259759131007751657L;
	private Integer userId;
	private String userName;
	private String passWord;


	@Column
	public String getPassWord() {
		return passWord;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getUserId() {
		return userId;
	}

	@Column
	public String getUserName() {
		return userName;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
