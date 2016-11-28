package com.paySystem.ic.service.buss;


import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;

/**  
* @Title: MerPromotionService.java
* @Package: com.paySystem.ic.service.buss
* @Description: 活动审核记录接口
* @Author: 赵瑞群
* @Date: 2014-08-21
* @Version: V1.0  
*/
public interface MerApplyRecordService {

	public static final String MERAPPLYRECORDSERV = "merApplyRecordService";
	
	
	
	/**
	 *  获取活动审核记录
	 *@Title:findByProIdAndMerIdNew
	 *@Description:根据Id获取活动审核记录Dto对象
	 *@param:@param proid merId
	 *@param:@return
	 *@return: MerApplyRecordDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public MerApplyRecordDTO findByProIdAndMerIdNew(Integer proid,String merId);
	
	
	/**
	 *   审核通过
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public void passAudit(String recordIds) throws Exception;
	
	/**
	 *   活动拒绝/清退  status 1 拒绝 2 清退
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public void refuseAudit(String recordIds,MerApplyRecordDTO merApplyRecordDTO) throws Exception;
	
	/**
	 *   全部审核通过
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public void passAllAudit(int proid) throws Exception;
	
	
	

}
