package com.paySystem.ic.dao.goods.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.goods.VioRegul;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.goods.VioRegulDao;


/**  
 * @Title: VioRegulDaoImpl.java
 * @Package: com.paySystem.ic.dao.goods.impl
 * @Description: 违规案例实现
 * @Author: yanwuyang 
 * @Date: 2014-8-27 下午5:08:44
 * @Version: V1.0  
 */

@Repository(VioRegulDao.VIOREGUL)
public class VioRegulDaoImpl extends DaoSupport<VioRegul> implements VioRegulDao {

	/**
	 * 
	 *@Title:getVioRegulByTypeId
	 *@Description:根据违规类型id获取违规案例
	 *@Params:@param typeId
	 *@Params:@return
	 *@Return:List<VioRegul>
	 *@author:yanwuyang
	 *@Date:2014-8-27下午5:07:58
	 */
	@SuppressWarnings("unchecked")
	public List<VioRegul> getVioRegulByTypeId(Integer typeId) {
		Query query = em.createQuery("from VioRegul o where o.unruleTypeId=?");
		query.setParameter(1, typeId);
		return query.getResultList();
	}

	/**
	 *@MethodName:findAll
	 *@Description:TODO
	 *@return
	 *@Author:孟凡岭
	 *@Date:2014-11-21下午04:21:06
	 */
	public List<VioRegul> findAll() {
		// TODO Auto-generated method stub
		return this.findByJpl("from VioRegul");
	}

}
