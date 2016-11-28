package com.lucene.dto;


/**
 * @ProjectName:Demo1
 * @ClassName:UserDTO
 * @Description:用户数据传输对象
 * @date: 2015-6-14下午09:41:17
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午09:41:17
 */
public class UserDTO {
	private Integer userId;
	private String userName;
	private String passWord;


	public String getPassWord() {
		return passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
