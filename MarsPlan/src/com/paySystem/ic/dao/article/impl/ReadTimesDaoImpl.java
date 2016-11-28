package com.paySystem.ic.dao.article.impl;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.article.ReadTimes;
import com.paySystem.ic.dao.article.ReadTimesDAO;
import com.paySystem.ic.dao.common.DaoSupport;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ReadTimesDaoImpl
 * @Description:阅读数量数据库操作类
 * @date: Apr 21, 20162:51:53 PM
 * @author: bruce
 * @version: V1.0
 */
@Repository(ReadTimesDAO.READTIMESDAO)
public class ReadTimesDaoImpl extends DaoSupport<ReadTimes> implements ReadTimesDAO {

	/**
	 * @MethodName:queryCountByArticleId
	 * @Description:根据文章编号查找阅读次数
	 * @param articleId
	 * @author:徐半仙儿
	 * @date:Apr 21, 20162:52:55 PM
	 */
	@Override
	public BigInteger queryCountByArticleId(Integer articleId) {
		String sql2 = "select count(o.ip) "
				+ "from (select distinct(t.ip) from "
				+ "(select * from t_readtimes o where o.articleId=:id) t) o";
		//String sql = "select count(o.id) from ReadTimes o where o.articleId=:id";
		Query query = this.em.createNativeQuery(sql2);
		query.setParameter("id", articleId);
		return (BigInteger) query.getSingleResult();
	}

}
