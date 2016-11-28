package com.paySystem.ic.dao.buss;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;
import com.paySystem.ic.web.dto.buss.PromotionDTO;


/**
 * @ClassName:PromotionDao
 * @Description:活动管理Dao
 * @date: 2014-8-21上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface PromotionDao extends DAO<Promotion> {
	
	

	 
	/**
	 * 
	*@Title:savePromotion
	*@Description:保存活动
	*@Params:@param promotionDTO
	*@Return:void
	*@author:luckygroup
	*@Date:2014-8-24上午9:17:27
	 */
	
	public Integer savePromotion(PromotionDTO promotionDTO);
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询活动管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  赵瑞群
	 *@Thorws:
	 */
	public QueryResult<List> queryApplyRecordByCond(int firstPage, int pageNum,
			MerApplyRecordDTO merApplyRecordDTO, LinkedHashMap<String, String> orderBy)throws Exception;
	
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询活动管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param promotionDTO PromotionDTO对象
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  赵瑞群
	 *@Thorws:
	 */
	public QueryResult<PromotionDTO> queryPromotionByCond(int firstPage, int pageNum,
			PromotionDTO promotionDTO, LinkedHashMap<String, String> orderBy)throws Exception;
	
	/**
	 *  获取活动管理信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理信息Dto对象
	 *@param:@param proid
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
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO delPromotion(Integer proid) throws Exception;
	
	/**
	 *   开始招募
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO openRecruit(Integer proid) throws Exception;
	
	/**
	 *   停止招募
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO stopRecruit(Integer proid) throws Exception;
	
	/**
	 *   终止活动
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO stopPromotion(Integer proid) throws Exception;
	
	/**
	 *   开始活动
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public PromotionDTO beginPromotion(Integer proid) throws Exception;
	
	
	
	
	/**
	 *  检查活动管理名称是否已存在
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
	 *  查询平台可用的平台活动
	 *@Title: findOnlinePromotionPlatform
	 *@Description:  查询平台可用的平台活动
	 *@param:@return
	 *@Return: List<PromotionDTO> 可报名的平台活动
	 *@author: Jacky
	 *@Thorws:
	 */
	public List<PromotionDTO> findOnlinePromotionPlatform(String merId,String userName) throws Exception ;
	
	/**
	 *  通过活动id查询参与活动的商品数量
	 *@Title: findGoodsCountByProid
	 *@Description:  查询平台可用的平台活动
	 *@param:@return
	 *@Return: List<PromotionDTO>
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	
	public long findGoodsCountByProid(int proid);
	
	public static final String PROMOTIONDAO ="promotionDao";

	/**
	 * 修改结束时间
	 * @param promotionDTO
	 */
	public void updateEndTime(PromotionDTO promotionDTO);
	/**
	*@Title:ajaxObject
	*@Description:验证广告对象
	*@Params:@param id
	*@Params:@return
	*@Return:boolean
	*@author:孟凡岭
	*@Date:2014-12-8上午11:30:46
	*/
	public boolean ajaxObject(String id);
	
}
