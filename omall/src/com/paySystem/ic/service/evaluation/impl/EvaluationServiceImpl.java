package com.paySystem.ic.service.evaluation.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.evaluation.CriticalContextDao;
import com.paySystem.ic.dao.evaluation.EvaluationDao;
import com.paySystem.ic.dao.evaluation.MerCreditRecordDao;
import com.paySystem.ic.service.evaluation.EvaluationService;
import com.paySystem.ic.web.dto.evaluation.CriticalContextDTO;
import com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO;
import com.paySystem.ic.web.dto.report.MessConsTotalDTO;


/**  
 * @Title: EvaluationServiceImpl.java
 * @Package: com.paySystem.ic.service.evaluation.impl
 * @Description: 评价管理Service
 * @Author: yanwuyang 
 * @Date: 2014-10-9 下午11:52:43
 * @Version: V1.0  
 */
@Service(EvaluationService.EVALUATIONSERVICE)
public class EvaluationServiceImpl implements EvaluationService {

	/** *  评价dao*/
	@Resource
	private EvaluationDao evaluationDao;
	
	/** *  评价记录dao*/
	@Resource
	private MerCreditRecordDao creditRecordDao;
	
	/** *  评价内容dao*/
	@Resource
	private CriticalContextDao contextDao;
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#queryGriTotal(java.lang.String, java.lang.String, java.lang.String)
	 *@MethodName:queryGriTotal
	 *@Description:查询评价统计
	 *@param merId 商户ID
	 *@param beginDate 开始时间
	 *@param endDate 结束时间
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:28:48
	 */
	public List queryGriTotal(String merId, String beginDate, String endDate) {
		return evaluationDao.queryGriTotal(merId, beginDate, endDate);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#getUserByName(java.lang.String)
	 *@MethodName:getUserByName
	 *@Description:查询用户
	 *@param userName 用户名称
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:29:02
	 */
	public List getUserByName(String userName) {
		return evaluationDao.queryUserByName(userName);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#queryMerCreditRecord(java.lang.String, int, java.lang.String, java.lang.String)
	 *@MethodName:queryMerCreditRecord
	 *@Description:查询评价记录
	 *@param merId 会员ID
	 *@param type 查询类型
	 *@param appType 评论类型
	 *@param griType 评价类型
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:29:16
	 */
	public QueryResult<MerCreditRecordDTO> queryMerCreditRecord(String merId, int type,String appType,String griType,int firstPage, int pageNum) {
		return creditRecordDao.queryMerCreditRecord(merId,type,appType,griType,firstPage,pageNum);
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#saveCriticalContext(com.paySystem.ic.web.dto.evaluation.CriticalContextDTO)
	 *@MethodName:saveCriticalContext
	 *@Description:保存评论内容
	 *@param contextDTO
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:29:28
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void saveCriticalContext(CriticalContextDTO contextDTO) {
		contextDao.save(contextDTO);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#saveMerCreditRecord(com.paySystem.ic.web.dto.evaluation.MerCreditRecordDTO)
	 *@MethodName:saveMerCreditRecord
	 *@Description:评价记录
	 *@param merCreditRecordDTO
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:29:42
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Integer saveMerCreditRecord(MerCreditRecordDTO merCreditRecordDTO) {
		return creditRecordDao.save(merCreditRecordDTO);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.evaluation.EvaluationService#queryOrderByOrderId(java.lang.String)
	 *@MethodName:queryOrderByOrderId
	 *@Description:查询订单信息
	 *@param orderId 订单ID 
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:29:54
	 */
	public List queryOrderByOrderId(String orderId) {
		return evaluationDao.queryOrderByOrderId(orderId);
	}


	/**
	 * 
	 *@Title:updateOrder
	 *@Description:修改订单状态为已评价
	 *@Params:@param orderId
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:39:21
	 */

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void updateOrder(String orderId) {
		evaluationDao.updateOrder(orderId);
	}

	
    public List<MerCreditRecordDTO> queryAll(int page, int pageNum, MerCreditRecordDTO messConsTotalDTO,
            LinkedHashMap<String, String> orderBy) throws Exception {

        return creditRecordDao.queryAll(page, pageNum, messConsTotalDTO, orderBy);
    }

	/**
	 *@Title:queryMerConsTotalReport
	 *@Description:导出报表Service
	 *@param:@return
	 *@return:ReturnDTO
	 *@author:张国法
	 */
	public List<MerCreditRecordDTO> queryMessConsTotalReport(
			MerCreditRecordDTO merCreditRecordDTO,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return creditRecordDao.queryMessConsTotalReport(merCreditRecordDTO, orderBy);
	}
}
