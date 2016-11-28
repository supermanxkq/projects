package com.ssi.stu.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	/***/
	private static final long serialVersionUID = 5601222642763823559L;
	public String exceptionInfo;

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

}
