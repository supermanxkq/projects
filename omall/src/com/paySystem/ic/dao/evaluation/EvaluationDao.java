package com.paySystem.ic.dao.evaluation;

import java.util.List;

import com.paySystem.ic.dao.common.DAO;


/**  
 * @Title: EvaluationDao.java
 * @Package: com.paySystem.ic.dao.evaluation
 * @Description: 评价管理dao
 * @Author: yanwuyang 
 * @Date: 2014-10-19 下午04:41:38
 * @Version: V1.0  
 */

public interface EvaluationDao extends DAO<Object> {
	
	public final static String EVALUATIONDAO="evaluationDao";
	
	/**
	 * 
	 *@Title:queryGriTotal
	 *@Description:评价统计
	 *@Params:@param merId 会员Id
	 *@Params:@param beginDate 开始时间
	 *@Params:@param endDate 结束时间
	 *@Params:@return
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:25:48
	 */
	public List queryGriTotal(String merId,String beginDate,String endDate); 
	
	/**
	 * 
	 *@Title:queryUserByName
	 *@Description:查询用户信息
	 *@Params:@param userName 用户名称
	 *@Params:@return
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:26:10
	 */
	public List queryUserByName(String userName);
	
	/**
	 * 
	 *@Title:queryOrderByOrderId
	 *@Description:查询订单信息
	 *@Params:@param orderId 订单ID
	 *@Params:@return
	 *@Return:List
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:26:21
	 */
	public List queryOrderByOrderId(String orderId);
	
	/**
	 * 
	 *@Title:updateOrder
	 *@Description:修改订单状态为已评价
	 *@Params:@param orderId
	 *@Return:void
	 *@author:yanwuyang
	 *@Date:2014-10-21下午10:39:21
	 */
	public void updateOrder(String orderId);

}
