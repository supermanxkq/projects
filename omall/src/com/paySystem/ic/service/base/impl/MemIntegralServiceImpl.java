package com.paySystem.ic.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.dao.base.MemIntegralDAO;
import com.paySystem.ic.service.base.MemIntegralService;
import com.paySystem.ic.web.dto.base.MemIntegral;

@Repository(MemIntegralService.MEMINTEGRALSERVICE)
public class MemIntegralServiceImpl implements MemIntegralService{

	@Resource MemIntegralDAO memIntegralDAOImpl;
	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.base.MemIntegralService#queryByMemId(java.lang.String)
	 *@MethodName:queryByMemId
	 *@Description:根据会员编号查询商城积分信息
	 *@param memId
	 *@return
	 *@Author:王楠
	 *@Date:2014-9-18下午02:32:22
	 */
	public MemIntegral queryByMemId(String memId) {
		MemIntegral memIntegral=memIntegralDAOImpl.queryByMemId(memId);
		return memIntegral;
	}

}
