package com.paySystem.ic.dao.base.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.dao.base.MemIntegralDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.MemIntegral;

@Repository(MemIntegralDAO.MEMINTEGRALDAO)
public class MemIntegralDAOImpl extends DaoSupport<MemIntegral>
                                             implements MemIntegralDAO{

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.base.MemIntegralDAO#queryByMemId(java.lang.String)
	 *@MethodName:queryByMemId
	 *@Description:根据会员编号查询积分信息
	 *@param memId 会员编号
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-18上午11:54:33
	 */
	@SuppressWarnings("unchecked")
	public MemIntegral queryByMemId(String memId) {
        String sql="select o from MemIntegral o where o.memId =?1";
        Query query =em.createQuery(sql);
        query.setParameter(1, memId);
        List<MemIntegral> list=query.getResultList();
        MemIntegral mem=new MemIntegral();
        if(list.size()>0){
        	mem=list.get(0);
        	if(null==mem.getAvailableAmt()||("").equals(mem.getAvailableAmt())){
        		mem.setAvailableAmt(new BigDecimal(0.00));
        	}
        }else{
        	mem=null;
        }
		return mem;
	}

}
