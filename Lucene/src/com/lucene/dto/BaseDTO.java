package com.lucene.dto;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:BaseDTO
 * @Description:公用参数传输对象
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
public class BaseDTO {

	/** 获取当前页码 **/
	private int page = 1;

	/** 设置是否进行查找 **/
	private String query;

	private Integer pageNum = 10;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page==0){
			page = 1;
		}
		this.page = page;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
