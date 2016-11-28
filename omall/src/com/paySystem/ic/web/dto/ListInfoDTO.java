package com.paySystem.ic.web.dto;

import java.util.List;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:ListInfoDTO
 * @Description:打印整个页面数据的一个传输对象
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
public class ListInfoDTO {

	/**存放查询结果的集合*/
	private List<List<String>> list;
	/**获取分页信息的字符串*/
	private String pagehtml = "";
	/**页面其他信息的字符串*/
	private String otherhtml = "";

	public ListInfoDTO() {
		super();
	}
	
	public ListInfoDTO(List<List<String>> list, String pagehtml) {
		super();
		this.list = list;
		this.pagehtml = pagehtml;
	}
	
	public ListInfoDTO(List<List<String>> list, String pagehtml,String otherhtml) {
		super();
		this.list = list;
		this.pagehtml = pagehtml;
		this.otherhtml = otherhtml;
	}

	public List<List<String>> getList() {
		return list;
	}

	public void setList(List<List<String>> list) {
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
