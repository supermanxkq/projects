package com.paySystem.ic.dao.article;

import java.math.BigInteger;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ReadTimesDAO
 * @Description:阅读数量数据库操作类
 * @date: Apr 21, 20162:49:22 PM
 * @author: bruce
 * @version: V1.0
 */
public interface ReadTimesDAO {
	/** 常量 */
	public static final String READTIMESDAO = "ReadTimesDAO";

	/**
	 * @MethodName:queryCountByArticleId
	 * @Description:根据文章的编号查询阅读次数
	 * @param articleId文章编号
	 * @author:bruce
	 * @return Integer
	 * @date:Apr 21, 20162:50:43 PM
	 */
	public BigInteger queryCountByArticleId(Integer articleId);
}
