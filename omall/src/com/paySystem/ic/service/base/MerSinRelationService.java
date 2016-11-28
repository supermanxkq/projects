package com.paySystem.ic.service.base;

import java.util.List;





/**
 * @ClassName:MerSinRelationService
 * @Description:商户卡BIN管理关系Servcice接口
 * @date: 2014-1-5下午02:44:58
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerSinRelationService {

	/**
	 *@Title:queryMerBinByMerId
	 *@Description:根据商户号获取卡允许消费的卡BIN
	 *@param:@return
	 *@return:List<MerSinRelation>  商户卡BIN关联关系集合
	 *@author: 谢
	 *@thorws:
	 */
	/*List<MerSinRelation> queryMerBinByMerId(String merId,String binId);*/
	
	public static final String MERSINRELSERVICE = "merSinRelationService";	
}
