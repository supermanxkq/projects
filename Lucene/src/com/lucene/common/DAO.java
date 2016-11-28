package com.lucene.common;



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
}
