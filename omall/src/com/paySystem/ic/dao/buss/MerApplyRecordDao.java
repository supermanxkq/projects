package com.paySystem.ic.dao.buss;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.MerApplyRecord;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.buss.MerApplyRecordDTO;


/**
 * @ClassName:MerApplyRecordDao
 * @Description:活动申请记录Dao
 * @date: 2014-8-21上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface MerApplyRecordDao extends DAO<MerApplyRecord> {
	
	

	 
	/**
	 * 
	*@Title:saveMerApplyRecord
	*@Description:保存商户申请记录
	*@Params:@param merApplyRecordDTO
	*@Return:void
	*@author:luckygroup
	*@Date:2014-8-24上午9:17:27
	 */
	
	public Integer saveMerApplyRecord(MerApplyRecordDTO merApplyRecordDTO);
	
	/**
	 *  获取活动管理信息
	 *@Title:findById
	 *@Description:根据Id获取活动管理信息Dto对象
	 *@param:@param proid
	 *@param:@return
	 *@return:MerApplyRecordDTO
	 *@author:赵瑞群
	 *@Thorws:
	 */
	public MerApplyRecordDTO findById(Integer proid);
	
	/**
	 * 通过活动编码和商户编码获取最新的审核记录
	 * @param proid
	 * @param merId
	  *@param:@return
	 *@return:MerApplyRecordDTO
	 *@author:赵瑞群
	 */
	public MerApplyRecordDTO findByProIdAndMerIdNew(Integer proid, String merId);
	
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
	 *   全部审核通过
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	public void passAllAudit(int proid) throws Exception;
	
	
	
	/**
	 *   拒绝/清退
	 *@Title:delPromotion
	 *@Description:
	 *@param:@param proid
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	
	public void refuseAudit(String recordIds,MerApplyRecordDTO merApplyRecordDTO) throws Exception;
	
	
	
	public static final String MERAPPLYRECORDDAO ="merApplyRecordDao";
	
}
