package com.paySystem.ic.web.dto;

public class ListInfoDTOF {

	private String list;
	/** 获取分页信息的字符串 */
	private String pagehtml = "";
	/** 页面其他信息的字符串 */
	private String otherhtml = "";

	public ListInfoDTOF() {
		super();
	}

	public ListInfoDTOF(String list, String pagehtml) {
		super();
		this.list = list;
		this.pagehtml = pagehtml;
	}

	public ListInfoDTOF(String list, String pagehtml, String otherhtml) {
		super();
		this.list = list;
		this.pagehtml = pagehtml;
		this.otherhtml = otherhtml;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getPagehtml() {
		return pagehtml;
	}

	public void setPagehtml(String pagehtml) {
		this.pagehtml = pagehtml;
	}

	public String getOtherhtml() {
		return otherhtml;
	}

	public void setOtherhtml(String otherhtml) {
		this.otherhtml = otherhtml;
	}
}
