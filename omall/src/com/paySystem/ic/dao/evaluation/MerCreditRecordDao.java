package com.paySystem.ic.dao.evaluation;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.evaluation.MerCreditRecord;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;


/**  
 * @Title: MerCreditRecordDao.java
 * @Package: com.paySystem.ic.dao.evaluation.impl
 * @Description: 评论记录dao
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午6:09:19
 * @Version: V1.0  
 */

public interface MerCreditRecordDao extends DAO<MerCreditRecord> {
	
	public final static String MERCREDITRECORDDAO = "merCreditRecordDao";
	
	/**
	 * 
	 *@Title:save
	 *@Description:保存
	 *@Params:@param merCreditRecordDTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-20下午09:02:55
	 */
	public Integer save(MerCreditRecordDTO merCreditRecordDTO);
	
	/**
	 * 
	 *@Title:queryMerCreditRecord
	 *@Description:查询评论记录
	 *@Params:@param merId 会员ID
	 *@Params:@param type 查询类型
	 *@Params:@param appType 评价类型
	 *@Params:@param griType 评论类型
	 *@Params:@return
	 *@Return:List<MerCreditRecordDTO>
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:25:07
	 */
	public QueryResult<MerCreditRecordDTO> queryMerCreditRecord(String merId,int type,String appType,String griType,int firstPage, int pageNum);

	
	
	List<MerCreditRecordDTO> queryAll(int firstindex, int maxresult,
			MerCreditRecordDTO merCreditRecordDTO,
			LinkedHashMap<String, String> orderBy) throws Exception;
	
	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张国法
	 */
	public List<MerCreditRecordDTO> queryMessConsTotalReport(
			MerCreditRecordDTO messConsTotalDTO,
			LinkedHashMap<String, String> orderBy);
}
