package com.lucene.dao;

import java.util.List;

public interface LuceneDAO {
	public static final String LUCENEDAO = "LuceneDAO";

	/**
	 * 查询索引源数据集合
	 * 
	 * @Title:queryForList
	 * @Description: 查询索引源数据集合
	 * @param:@param statementName
	 *                   要检索的Mapper Id
	 * @param:@return
	 * @return:List 查询结果集合
	 * @author: 谢洪飞
	 * @Thorws:
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String statementName);
}
