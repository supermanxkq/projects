package com.paySystem.ic.dao.base;

import com.paySystem.ic.bean.base.MerBussParam;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.base.MerchantsDTO;

/**
 * @ClassName:MerBussDao
 * @Description:商户业务参数Dao接口
 * @date: 2014-7-4上午11:04:18
 * @author: 谢洪飞
 * @version: V1.0
 */
public interface MerBussDao extends DAO<MerBussParam> {
	
	public static final String MERBUSSDAO = "merBussDao";
	
	/**
	 * 保存商户业务参数
	 *@Title:saveMerBuss
	 *@Description:  保存商户业务参数
	 *@param:@param merBussParam  商户业务参数实体对象
	 *@Return:void     
	 *@author: 谢洪飞
	 *@Thorws:  
	 */
	public void saveMerBuss(MerchantsDTO merDto);
	
	
	/**
	 *@Title:updateMerBuss
	 *@Description: 更新商户业务参数信息
	 *@param:@param merDto
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public void updateMerBuss(MerchantsDTO merDto);
	
	/**
	 *@Title:findMerBuss
	 *@Description:商户业务参数
	 *@param:@param merId
	 *@Return:void
	 *@author: 谢洪飞
	 *@Thorws:
	 */
	public MerchantsDTO findMerBuss(String merId);
	
	

}
