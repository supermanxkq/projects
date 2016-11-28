package com.paySystem.ic.utils;

import java.io.Serializable;
import java.util.List;

import com.paySystem.ic.bean.search.Search;

/***
 * 
 * @ProjectName:MarsPlan 
 * @ClassName:ReturnTool  
 * @Description:接口返回数据工具类返回查询数据
 * @date: Apr 16, 20163:22:09 PM
 * @author: bruce
 * @version: V1.0
 */
public class ReturnTool implements Serializable{

	
	private static final long serialVersionUID = 3022417690071170922L;
	
	
    /**成功标识*/
	private boolean success = false;
	/**提示信息*/
	private String msg = "";
	/**查询结果*/
	private List<Search> searches = null;
	/**页数*/
	private int pages;
	/**总条数*/
	private int counts=0;

	
	
	
	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Search> getSearches() {
		return searches;
	}

	public void setSearches(List<Search> searches) {
		this.searches = searches;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
	
	
	
}