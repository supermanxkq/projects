package com.blog.dto;

public class CodeListInfoDTO {

	private String codeList;
	/** 获取分页信息的字符串 */
	private String pagehtml = "";
	/** 页面其他信息的字符串 */
	private String otherhtml = "";

	public CodeListInfoDTO() {
		super();
	}

	public CodeListInfoDTO(String codeList, String pagehtml) {
		super();
		this.codeList = codeList;
		this.pagehtml = pagehtml;
	}

	public CodeListInfoDTO(String codeList, String pagehtml, String otherhtml) {
		super();
		this.codeList = codeList;
		this.pagehtml = pagehtml;
		this.otherhtml = otherhtml;
	}

	public String getCodeList() {
		return codeList;
	}

	public void setCodeList(String codeList) {
		this.codeList = codeList;
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
