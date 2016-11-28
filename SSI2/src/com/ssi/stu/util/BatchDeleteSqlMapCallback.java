package com.ssi.stu.util;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 批量删除
 * @author rongmijia
 * @createdate 2009-8-21
 */
public class BatchDeleteSqlMapCallback implements SqlMapClientCallback {
	
	public BatchDeleteSqlMapCallback() {}
	
	public BatchDeleteSqlMapCallback(String statementId, List<Object> list) {
		this.list = list;
		this.statementId = statementId;
	}
	
	private List list;  //待删除的数据
	
	private String statementId;  //SqlMap配置文件中SQL语句的ID

	public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		if (list == null || list.isEmpty() || statementId == null || statementId.trim().equals("")) {
			throw new SQLException("不能执行批量删除操作，有值为空");
		}
		
		executor.startBatch();
		for (Object object : list) {
			executor.delete(statementId, object);
		}
//		for(Integer count = 0; count < list.size(); count++)
//		{
//			executor.delete(statementId, list.get(count));
//			
//			if(count!=0 && count%10000==0){
//				executor.executeBatch();
//				//每次提交后会自动关闭所以要再次开启
//				executor.startBatch();
//			}
//		}
		Integer effectedRows = executor.executeBatch();
		return effectedRows;
	}



	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
