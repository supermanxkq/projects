package com.paySystem.ic.service.base.impl;

import org.springframework.stereotype.Service;

import com.paySystem.ic.service.base.MerSinRelationService;

/**
 * @ClassName:MerSinRelationServiceImpl
 * @Description:商户卡BIN关联关系Service实现
 * @date: 2014-1-5下午02:47:39
 * @author: 谢洪飞
 * @version: V1.0
 */
@Service(MerSinRelationService.MERSINRELSERVICE)
public class MerSinRelationServiceImpl implements MerSinRelationService {

/*	@Resource MerSinRelationDao merSinRelationDao;
	*//**
	 *@Title:queryMerBinByMerId
	 *@Description:根据商户号获取卡允许消费的卡BIN
	 *@param:@return
	 *@return:List<MerSinRelation>  商户卡BIN关联关系集合
	 *@author: 谢
	 *@thorws:
	 *//*
	@Override
	public List<MerSinRelation> queryMerBinByMerId(String merId,String binId) {
		
		return merSinRelationDao.queryMerBinByMerId(merId,binId);
		
	}
*/
}
