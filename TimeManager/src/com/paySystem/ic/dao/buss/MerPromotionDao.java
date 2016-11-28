package com.paySystem.ic.dao.buss;

import java.util.List;

import com.paySystem.ic.bean.buss.MerPromotion;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.buss.MerPromotionDTO;


/**
 * MerPromotionDao
 * @Description:活动商户管理Dao
 * @date: 2014-8-21上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface MerPromotionDao extends DAO<MerPromotion> {
   
	/**
	 * 
	*@Title:saveMerPromotion
	*@Description:保存商户活动关联表
	*@Params:@param merpromotionDTO
	*@Return:void
	*@author:luckygroup
	*@Date:2014-8-24上午9:22:30
	 */
	public void saveMerPromotion(MerPromotionDTO merPromotionDTO);
	
	/**
	 * 
	*@Title:findByProIdAndMerId
	*@Description:根据活动编码和商户编码查找商户活动关联信息
	*@Params:@param proid
	*@Params:@param merId
	*@Params:@return
	*@Return:MerPromotionDTO
	*@author:luckygroup
	*@Date:2014-8-24上午9:23:04
	 */
	
	public MerPromotionDTO findByProIdAndMerId(Integer proid,String merId);

	/**
	 * 
	*@Title:findCountMerByProId
	*@Description:通过活动id查询参与该活动的商户数
	*@Params:@param proid
	*@Params:@return
	*@Return:long
	*@author:luckygroup
	*@Date:2014-8-23下午5:31:23
	 */
	public long findCountMerByProId(int proid);
	

	
	
	
	/**
	 *  查询平台可用的平台活动
	 *@Title: findOnlinePromotionPlatformById
	 *@Description:  查询平台可用的平台活动
	 *@param:@return
	 *@Return: List<PromotionDTO> 可报名的平台活动
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<MerPromotionDTO> findOnlinePromotionPlatformById(Integer proId) throws Exception ;

	/**
	 * 
	*@Title: findByProId
	*@Description:通过活动id查询参与该活动的商户数
	*@Params:@param proid
	*@Params:@return
	*@Return:long
	*@author:luckygroup
	*@Date:2014-8-23下午5:31:23
	 */
	
	public MerPromotionDTO findByProId(Integer proid);
	
	
	public static final String MERPROMOTIONDAO ="merPromotionDao";
}
