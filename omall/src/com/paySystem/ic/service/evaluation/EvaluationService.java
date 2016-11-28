package com.paySystem.ic.service.evaluation;

import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;


/**  
 * @Title: EvaluationService.java
 * @Package: com.paySystem.ic.service.evaluation
 * @Description: 评价管理Service
 * @Author: yanwuyang 
 * @Date: 2014-10-9 下午11:52:21
 * @Version: V1.0  
 */

public interface EvaluationService {
	
	public static final String EVALUATIONSERVICE = "evaluationService";
	
	/**
	 * 
	 *@Title:queryGriTotal
	 *@Description:查询统计记录
	 *@Params:@param merId 商户ID
	 *@Params:@param beginDate 开始时间
	 *@Params:@param endDate 结束时间
	 *@Params:@return 统计记录
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:31:41
	 */
	public List queryGriTotal(String merId,String beginDate,String endDate); 
	
	/**
	 * 
	 *@Title:getUserByName
	 *@Description:查询用户信息
	 *@Params:@param userName 用户名称
	 *@Params:@return
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:31:32
	 */
	public List getUserByName(String userName);
	
	/**
	 * 
	 *@Title:queryMerCreditRecord
	 *@Description:查询评价记录
	 *@Params:@param merId 商户ID
	 *@Params:@param type 商户类型
	 *@Params:@param appType 评价类型
	 *@Params:@param griType 回复评论编号
	 *@Params:@return
	 *@Return:List<MerCreditRecordDTO>
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:31:20
	 */
	public QueryResult<MerCreditRecordDTO> queryMerCreditRecord(String merId,int type,String appType,String griType,int firstPage, int pageNum);
	
	/**
	 * 
	 *@Title:saveCriticalContext
	 *@Description:保存评价内容
	 *@Params:@param contextDTO 评价内容DTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-20下午09:07:24
	 */
	public void saveCriticalContext(CriticalContextDTO contextDTO);
	
	/**
	 * 
	 *@Title:saveMerCreditRecord
	 *@Description:保存评价记录
	 *@Params:@param merCreditRecordDTO 评价记录DTO
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-20下午09:07:36
	 */
	public Integer saveMerCreditRecord(MerCreditRecordDTO merCreditRecordDTO);
	
	
	/**
	 * 
	 *@Title:queryOrderByOrderId
	 *@Description:查询订单信息
	 *@Params:@param orderId 订单号
	 *@Params:@return
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:31:08
	 */
	public List queryOrderByOrderId(String orderId);
	

	/**
	 * 
	 *@Title:updateOrder
	 *@Description:修改订单状态为已评价
	 *@Params:@param orderId 订单号
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:39:21
	 */
	public void updateOrder(String orderId);
	
	
    public List<MerCreditRecordDTO> queryAll(int page, int pageNum, MerCreditRecordDTO messConsTotalDTO,
            LinkedHashMap<String, String> orderBy) throws Exception;

	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出Excel调用
	 *@param:@param merConsTotal
	 *@param:@param orderBy
	 *@param:@return
	 *@return:List<TermConsTotalDTO>
	 *@author:张国法
	 *@thorws:
	 */
	public List<MerCreditRecordDTO> queryMessConsTotalReport(MerCreditRecordDTO merConsTotal,
			LinkedHashMap<String, String>orderBy);
	

}
