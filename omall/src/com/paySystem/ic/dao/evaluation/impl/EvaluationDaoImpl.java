package com.paySystem.ic.dao.evaluation.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.evaluation.EvaluationDao;


/**  
 * @Title: EvaluationDaoImpl.java
 * @Package: com.paySystem.ic.dao.evaluation.impl
 * @Description: 评价管理实现类
 * @Author: yanwuyang 
 * @Date: 2014-10-19 下午04:43:40
 * @Version: V1.0  
 */

@Repository(EvaluationDao.EVALUATIONDAO)
public class EvaluationDaoImpl extends DaoSupport<Object> implements EvaluationDao {

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.evaluation.EvaluationDao#queryGriTotal(java.lang.String, java.lang.String, java.lang.String)
	 *@MethodName:queryGriTotal
	 *@Description:根据实际查询评价统计
	 *@param merId 商户ID
	 *@param beginDate 起始时间
	 *@param endDate 结束时间
	 *@return
	 *@Author:yanwuyang
	 *@Date:2014-10-19下午05:59:54
	 */
	public List queryGriTotal(String merId,String beginDate,String endDate) {
		String sql="select sum(goodgrinum) as goodgrinum ,sum(MiddleGriNum) as MiddleGriNum ,sum(BaDGirNum) as BaDGirNum,avg(ServiceAvg) as ServiceAvg ,avg(RealAvg) as RealAvg,avg(PostAvg) as PostAvg  from GriTotal where MerId=? ";
		if(beginDate!=null && endDate!=null){
			sql+="and createtime BETWEEN ? and ?";
		}else if(beginDate!=null && endDate==null){
			sql+="and createtime < ?";
		}
		Query query =this.em.createNativeQuery(sql);
		if(beginDate!=null && endDate!=null){
			query.setParameter(1, merId);
			query.setParameter(2, beginDate);
			query.setParameter(3, endDate);
		}else if(beginDate!=null && endDate==null){
			query.setParameter(1, merId);
			query.setParameter(2, beginDate);
		}
		return query.getResultList();
	}

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
	public List queryUserByName(String userName) {
		Query query =this.em.createNativeQuery("select qq,NetInFo, Address from s_user where userName=?");
		query.setParameter(1, userName);
		return query.getResultList();
	}

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
	public List queryOrderByOrderId(String orderId) {
		Query query = this.em.createNativeQuery("select o.GoodsId,o.GoodsName,g.goodsLittPho from o_ordergoodsrel o left join b_goodsphoto g on o.goodsId=g.goodsId where o.orderId=?");
		query.setParameter(1, orderId);
		return query.getResultList();
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.evaluation.EvaluationDao#updateOrder(java.lang.String)
	 *@MethodName:updateOrder
	 *@Description:修改订单状态
	 *@param orderId
	 *@Author:yanwuyang
	 *@Date:2014-10-21下午10:37:47
	 */
	public void updateOrder(String orderId) {
		Query query = em.createNativeQuery("update O_Orders set criticalStatus=1 where orderId=?");
		query.setParameter(1, orderId);
		query.executeUpdate();
	}

}
