package com.paySystem.ic.dao.evaluation.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.evaluation.MerCreditTotal;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.evaluation.MerCreditTotalDao;

/**
 * @ProjectName:omproject
 * @ClassName:MerCreditTotalDaoImpl
 * @Description:TODO
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
@Repository(MerCreditTotalDao.MERCREDITTOTALDAO)
public class MerCreditTotalDaoImpl extends DaoSupport<MerCreditTotal> implements
		MerCreditTotalDao {

	/**
	 *@OverRiddenMethod：@see 
	 *                        com.paySystem.ic.dao.evaluation.MerCreditTotalDao#deleteByMerId
	 *                        (java.lang.String)
	 *@MethodName:deleteByMerId
	 *@Description:根据商户id删除对象
	 *@param merId
	 *@throws Exception
	 *@Author:毛智东
	 *@Date:2014-11-28下午06:57:05
	 */
	@Override
	public void deleteByMerId(String merId) throws Exception {
		String sql = "delete from b_MerCreditTotal  where merId = ?";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, merId);
		query.executeUpdate();
	}

}
