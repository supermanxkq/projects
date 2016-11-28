package com.alipay.bean;

import java.util.List;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:QueryResult
 * @Description:查询方法所返回的类型
 * @date: 2014-7-17
 * @author: 王楠
 * @version: V1.0
 */
public class QueryResult<T> {
	/**查询后的结果集，T代表实体*/
	private List<T> resultlist;
	/**记录数据的总条数*/
	private long totalrecord;
	
 	public List<T> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
