package com.paySystem.ic.dao.evaluation;

import com.paySystem.ic.bean.evaluation.MerCreditTotal;
import com.paySystem.ic.dao.common.DAO;

/**
 * @ProjectName:omproject
 * @ClassName:MerCreditTotalDao
 * @Description:商户评价累计Dao接口
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
public interface MerCreditTotalDao extends DAO<MerCreditTotal> {

	public static final String MERCREDITTOTALDAO = "merCreditTotalDao";

	/**
	 * 
	 *@Title:deleteByMerId
	 *@Description:根据商户id删除对象
	 *@Params:@param merId
	 *@Params:@throws Exception
	 *@Return:void
	 *@author:毛智东
	 *@Date:2014-11-28下午06:52:20
	 */
	public void deleteByMerId(String merId) throws Exception;
}
