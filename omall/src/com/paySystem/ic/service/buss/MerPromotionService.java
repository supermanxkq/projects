package com.paySystem.ic.service.buss;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;

/**  
* @Title: MerPromotionService.java
* @Package: com.paySystem.ic.service.buss
* @Description: 商户活动关联接口
* @Author: 赵瑞群
* @Date: 2014-08-21
* @Version: V1.0  
*/
public interface MerPromotionService {

	public static final String MERPROMOTIONSERV = "merPromotionService";
	
	
	
	/**
	 *  获取商户活动关联信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理Dto对象
	 *@param:@param logistId
	 *@param:@return
	 *@return:PromotionDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public MerPromotionDTO findByProIdAndMerId(Integer proid,String merId);


	/**
	 *  获取商户活动关联信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理Dto对象
	 *@param:@param logistId
	 *@param:@return
	 *@return:PromotionDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */

	public MerPromotionDTO findByProId(Integer proid);
	
	
	
	

	

}
