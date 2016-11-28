package com.ssi.stu.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 批量插入回调函数
 */
public class BatchInsertSqlMapCallback implements SqlMapClientCallback {

	private String statementId;// SqlMap配置文件中SQL语句的ID

	@SuppressWarnings("unchecked")
	private List dataList;// 待插入的数据
	private boolean needReturnKeys = false;

	public BatchInsertSqlMapCallback() {
	}

	/**
	 * 设置true则返回的object为List&lt;Object&gt;的主键列表，false与空构造是一样的效果，即默认只返回影响行数
	 * 
	 * @param needReturnKeys
	 */
	public BatchInsertSqlMapCallback(boolean needReturnKeys) {
		// 是否需要返回list封装的主键
		this.needReturnKeys = needReturnKeys;
	}

	public boolean isNeedReturnKeys() {
		return needReturnKeys;
	}

	public void setNeedReturnKeys(boolean needReturnKeys) {
		this.needReturnKeys = needReturnKeys;
	}

	@SuppressWarnings("unchecked")
	public BatchInsertSqlMapCallback(String statementId, List dataList) {
		this.statementId = statementId;
		this.dataList = dataList;
	}

	@SuppressWarnings("unchecked")
	public List getDataList() {
		return dataList;
	}

	@SuppressWarnings("unchecked")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	/**
	 * 返回插入的记录数量
	 */
	public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		if (dataList == null || statementId == null
				|| "".equals(statementId.trim())) {
			throw new SQLException(
					"No data for batch insert or statement id is null.");
		}

		int effectedRows = 0;
		List<Object> primarys = null; // 如果用户初始构造时，指定需要返回主键，则用此对象封装
		executor.startBatch();

		if (this.needReturnKeys) {
			primarys = new ArrayList<Object>();
			try {
				for (int count = 0; count < dataList.size(); count++) {
					primarys.add(executor.insert(statementId,
							dataList.get(count)));
				}
			} catch (Exception e) {
				throw new SQLException("批量插入时出现错误，请确认数据库正常，及您执行的语句为insert语句");
			}

		} else {
			for (int count = 0; count < dataList.size(); count++) {
				executor.insert(statementId, dataList.get(count));
			}
		}

		effectedRows = executor.executeBatch();

		if (needReturnKeys) {
			return primarys; // 如果是insert语句，则返回
		}
		return new Integer(effectedRows);
	}

}
