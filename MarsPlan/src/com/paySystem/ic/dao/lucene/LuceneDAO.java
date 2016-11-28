package com.paySystem.ic.dao.lucene;

import java.util.List;

/**
 * @ProjectName:MarsPlan
 * @ClassName:LuceneDAO
 * @Description:索引数据库操作接口 查询索引源数据集合
 * @date: Apr 16, 20162:57:31 PM
 * @author: bruce
 * @version: V1.0
 */
public interface LuceneDAO {
	/** 常量 */
	public static final String LUCENEDAO = "LuceneDAO";

	/***
	 * 
	 * @MethodName:queryForList
	 * @Description:查询索引源数据集合
	 * @param statementName要检索的MapperId
	 * @author:bruce
	 * @return List查询结果集合
	 * @date:Apr 16, 20162:58:53 PM
	 */
	public List queryForList(String statementName);
}
