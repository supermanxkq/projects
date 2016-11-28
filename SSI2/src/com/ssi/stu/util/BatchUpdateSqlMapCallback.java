package com.ssi.stu.util;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 批量更新SqlMap回调类
 */
@SuppressWarnings("unchecked")
public class BatchUpdateSqlMapCallback implements SqlMapClientCallback{

	
	private String statementId;//SqlMap配置文件中SQL语句的ID
	private List dataList;//待更新的数据
	
	
	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public BatchUpdateSqlMapCallback()
	{
		
	}
	
	public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		
		if (dataList == null || statementId == null || "".equals(statementId.trim()))
		{
			throw new SQLException("No data for batch update or statement id is null.");
		}
		int effectedRows = 0;
		executor.startBatch();
		
		for(int count = 0; count < dataList.size(); count++)
		{
			executor.update(statementId, dataList.get(count));
		}
		
		effectedRows = executor.executeBatch();
		
		return new Integer(effectedRows);
	}

}
