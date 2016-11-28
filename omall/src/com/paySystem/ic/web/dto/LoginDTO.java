package com.paySystem.ic.web.dto;

import java.io.Serializable;

public class LoginDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = -8537154766011006748L;

	private String userName;
	private String passWord;
	private String authCode;
	private String rnd;
	private String return_EncData;
	private String keyID;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getRnd() {
		return rnd;
	}
	public void setRnd(String rnd) {
		this.rnd = rnd;
	}
	public String getReturn_EncData() {
		return return_EncData;
	}
	public void setReturn_EncData(String returnEncData) {
		return_EncData = returnEncData;
	}
	public String getKeyID() {
		return keyID;
	}
	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}
}
