package com.paySystem.ic.service.buss;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;

/**  
* @Title: PromotionService.java
* @Package: com.paySystem.ic.service.buss
* @Description: 活动管理服务接口
* @Author: 赵瑞群
* @Date: 2014-08-21
* @Version: V1.0  
*/
public interface PromotionService {

	public static final String PROMOTIONSERV = "promotionService";
	
	/**
	 *@Title:savePromotion
	 *@Description: 保存活动管理信息
	 *@param:@param PromotionDTO 活动管理DTO对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	public void savePromotion (PromotionDTO promotionDTO,MerPromotionDTO merPromotionDTO) throws Exception;
	
	/**
	 *@Title:savePromotion
	 *@Description: 保存参与活动的商户信息
	 *@param:@param PromotionDTO 活动管理DTO对象
	 *@Return:void  
	 *@author:      赵瑞群
	 *@Thorws:
	 */
	public void saveApplyMer (MerPromotionDTO merPromotionDTO, MerApplyRecordDTO merApplyRecordDTO) throws Exception;


	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询活动管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PromotionDTO> 返回DTO集合
	 *@author:  赵瑞群
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<PromotionDTO> queryPromotionByCond(int firstPage, int pageNum,
			PromotionDTO promotionDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询活动申请记录列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PromotionDTO> 返回DTO集合
	 *@author:  赵瑞群
	 * @throws Exception 
	 *@Thorws:
	 */
	public QueryResult<List> queryApplyRecordByCond(int firstPage, int pageNum,
			MerApplyRecordDTO merApplyRecordDTO, LinkedHashMap<String, String> orderBy) throws Exception;
	
	
	/**
	 *  获取活动管理信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理Dto对象
	 *@param:@param logistId
	 *@param:@return
	 *@return:PromotionDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO findById(Integer proid);
	
	
	/**
	 *   删除活动管理信息（逻辑删除）
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param logistId
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO delPromotion(Integer proid) throws Exception;
	
	/**
	 * 
	*@Title:openRecruit
	*@Description:开启招募
	*@Params:@param proid
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PromotionDTO
	*@author:luckygroup
	*@Date:2014-8-24下午5:22:26
	 */
	public PromotionDTO openRecruit(Integer proid) throws Exception;
	
	/**
	 * 
	*@Title:openRecruit
	*@Description:停止招募
	*@Params:@param proid
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PromotionDTO
	*@author:luckygroup
	*@Date:2014-8-24下午5:22:26
	 */
	public PromotionDTO stopRecruit(Integer proid) throws Exception;
	
	/**
	 * 
	*@Title:openRecruit
	*@Description:终止
	*@Params:@param proid
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PromotionDTO
	*@author:luckygroup
	*@Date:2014-8-24下午5:22:26
	 */
	public PromotionDTO stopPromotion(Integer proid) throws Exception;
	
	/**
	 * 
	*@Title:openRecruit
	*@Description:开始活动
	*@Params:@param proid
	*@Params:@return
	*@Params:@throws Exception
	*@Return:PromotionDTO
	*@author:luckygroup
	*@Date:2014-8-24下午5:22:26
	 */
	public PromotionDTO beginPromotion(Integer proid) throws Exception;
	

	
	/**
	 *  检查名称是否已存在
	 *@Title:checkSameName
	 *@Description:  检查活动管理名称是否已存在，如存在返回 false
	 *@param:@param logistName 活动管理名称
	 *@param:@return
	 *@Return:boolean
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public boolean checkSameName(String proname);
	
	/**
	 * 
	*@Title:findCountMerByProId
	*@Description:根据活动编码查询参加活动商户的数量
	*@Params:@param proid
	*@Params:@return
	*@Return:int
	*@author:luckygroup
	*@Date:2014-8-23下午6:20:05
	 */
	public long findCountMerByProId(int proid);
	
	/**
	 * 
	*@Title:findCountGoodsByProId
	*@Description:根据活动编码查询参加活动商品的数量
	*@Params:@param proid
	*@Params:@return
	*@Return:int
	*@author:luckygroup
	*@Date:2014-8-23下午6:20:05
	 */
	public long findCountGoodsByProId(int proid);
	/**
	 * 
	*@Title:findCountGoodsByProId
	*@Description:修改结束时间
	*@Params:@param proid
	*@Params:@return
	*@Return:int
	*@author:luckygroup
	*@Date:2014-8-23下午6:20:05
	 */
	
	
	public void updateEndTime(PromotionDTO promotionDTO);
	
	

}
