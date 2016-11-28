package com.paySystem.ic.web.dto.log;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class OperatorsDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
