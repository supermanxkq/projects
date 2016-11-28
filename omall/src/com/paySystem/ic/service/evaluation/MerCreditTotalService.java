package com.paySystem.ic.service.evaluation;

/**
 * @ProjectName:omproject
 * @ClassName:MerCreditTotalService
 * @Description:商户评价累计Service接口
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
public interface MerCreditTotalService {

	public static final String MERCREDITTOTALSERVICE = "merCreditTotalService";

	/**
	 * 
	 *@Title:initMerCreditTotal
	 *@Description:根据商户编号初始化对象
	 *@Params:@param merId
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:毛智东
	 *@Date:2014-11-28下午07:04:02
	 */
	public void initMerCreditTotal(String merId) throws Exception;
}
