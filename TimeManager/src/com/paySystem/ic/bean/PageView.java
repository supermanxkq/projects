package com.paySystem.ic.bean;

import java.util.List;

import com.paySystem.ic.utils.Globals;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:PageView
 * @Description:页面显示的封装方法
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;
	/** 页码开始索引和结束索引 **/
	private PageIndex pageindex;
	/** 总页数 **/
	private long totalpage;
	/** 每页显示记录数 **/
	private int maxresult = Globals.PAGE_NUM;
	/** 当前页 **/
	private int currentpage = 1;
	/** 总记录数 **/
	private long totalrecord;

	/** 要获取记录的开始索引 **/
	public int getFirstResult() {
		return (this.currentpage - 1) * this.maxresult;
	}

	public PageView() {

	}
 
	public PageView(int currentpage,long totalrecord) {
		this.currentpage = currentpage;
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord % this.maxresult == 0 ? this.totalrecord / this.maxresult : this.totalrecord / this.maxresult + 1);
	}

	public void setQueryResult(QueryResult<T> qr) {
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public long getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(totalpage, currentpage);
		//this.pageindex = PageIndex.getPageIndex(totalpage, currentpage,totalpage);
	}

	public int getMaxresult() {
		return maxresult;
	}

	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
}
