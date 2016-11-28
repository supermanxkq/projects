package com.paySystem.ic.service.article;

import java.math.BigInteger;

import com.paySystem.ic.bean.article.ReadTimes;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ReadTimesService
 * @Description:阅读次数服务类
 * @date: Apr 21, 20162:26:23 PM
 * @author: bruce
 * @version: V1.0
 */
public interface ReadTimesService extends DAO<ReadTimes> {
	/** 常量 */
	public static final String READTIMESSERVICE = "ReadTimesService";
	/**
	 *@MethodName:queryCountByArticleId 
	 *@Description:根据文章编号查找文章的阅读次数
	 *@param articleId文章编号
	 *@author:bruce
	 *@return long
	 *@date:Apr 21, 20162:47:18 PM
	 */
	public BigInteger queryCountByArticleId(Integer articleId);
}
