package com.blog.dao.common;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.blog.bean.QueryResult;


/**
 * 实体操作辅助类
 * 
 * @param <T>
 *            实体类
 */ 
public interface DAO<T> {
	// ----------------------------------基本方法-----------------------------------------//
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(Object entity);
	
	/**
	 * 保存多实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void saves(List<T> entitys);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void update(Object entity);

	/**
	 * 删除实体 ...任意个
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(Serializable... entityids);

	/**
	 * 获取实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public T find(Serializable entityId);

	/**
	 * 根据jpl查询
	 */
	public List<T> findByJpl(String jpl);
	
	/**
	 * 清除一级缓存的数据
	 */
	public void clear();

	// ---------------------------------分页方法-------------------------------------//

	/**
	 * 获取记录总数
	 * 
	 * @param entityClass
	 *            实体类
	 * @return
	 */
	public long getCount();

	public long getCount(Object[] queryParams, String wherejpql);

	public QueryResult<T> getScrollData(int firstindex, int maxresult) throws Exception;

	public QueryResult<T> getScrollData() throws Exception;

	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby) throws Exception;

	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams) throws Exception;

	/**
	 *@Title:getScrollData
	 *@Description:查询公共调用方法
	 *@param:@param firstindex 要查询起始条数记录号
	 *@param:@param maxresult 要查询最大条数记录号
	 *@param:@param wherejpql 条件sql字符串
	 *@param:@param queryParams 查询条件数组
	 *@param:@param orderby     排序方式Map
	 *@param:@return
	 *@param:@throws Exception
	 *@return:QueryResult<T>
	 *@thorws:
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) throws Exception;

	
	
	public Date getSysTime();
}
