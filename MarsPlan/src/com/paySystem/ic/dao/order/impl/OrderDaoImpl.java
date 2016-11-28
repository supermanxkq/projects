package com.paySystem.ic.dao.order.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.OrderDao;
import com.paySystem.ic.web.dto.order.OrderAndGoodsQueryDTO;

/**
 * 活动管理Dao实现类
 * 
 * @ClassName:PromotionDaoImpl
 * @Description:活动管理Dao实现类
 * @date: 2014-8-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(OrderDao.ORDERDAO)
public class OrderDaoImpl extends DaoSupport<Promotion> implements OrderDao {

	
	
	


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.PromotionDao#queryApplyRecordByCond(int, int, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO, java.util.LinkedHashMap)
	 *@MethodName:queryApplyRecordByCond
	 *@Description:获取商户申请列表
	 *@param firstPage
	 *@param pageNum
	 *@param merApplyRecordDTO
	 *@param orderBy
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-25下午9:42:30
	 */
	
	public QueryResult<List> queryOrderList(int firstPage, int pageNum,
			OrderAndGoodsQueryDTO orderAndGoodsQueryDTO,
			LinkedHashMap<String, String> orderBy) throws Exception {
		String goodsName = orderAndGoodsQueryDTO.getGoodsName();
		String mem = orderAndGoodsQueryDTO.getMem();
		String beginDate = orderAndGoodsQueryDTO.getBeginDate();
		String endDate = orderAndGoodsQueryDTO.getEndDate();
		String criticalStatus = orderAndGoodsQueryDTO.getCriticalStatus();
		String orderStatus = orderAndGoodsQueryDTO.getOrderStauts();
		String isClose = orderAndGoodsQueryDTO.getIsClose();
		String merId = orderAndGoodsQueryDTO.getMerId();
		String orderId = orderAndGoodsQueryDTO.getOrderId();
		QueryResult<List> qr = new QueryResult<List>();
		String sql = "select o.orderId orderid,o.orderTime orderTime,g.goodsId goodesName,g.price price,g.qty qty,o.memId mem,o.`status` orderStatus,o.paidAmt paidAmt from o_orders o,o_ordergoodsrel g where o.orderId=g.orderId";
		
		if(goodsName!=null && !goodsName.equals("")){
			sql += " and g.goodsName like :goodsName";
		}
		if(mem!=null && !mem.equals("")){
			sql += " and o.memId like :mem";
		}
		if(criticalStatus!=null && !criticalStatus.equals("")){
			sql += " and o.criticalStatus = :criticalStatus";
		}
		if(orderId!=null && !orderId.equals("")){
			sql += " and o.orderId like :orderId";
		}
		if(orderStatus!=null && !orderStatus.equals("")){
			sql += " and g.status = :orderStatus";
		}
		
		sql += " order by o.orderTime desc ";
		Query query = em.createNativeQuery(sql);
		if(goodsName!=null && !goodsName.equals("")){
			query.setParameter("goodsName", "%"+goodsName+"%");
		}
		if(mem!=null && !mem.equals("")){
			query.setParameter("mem", "%"+mem+"%");
		}
		if(criticalStatus!=null && !criticalStatus.equals("")){
			query.setParameter("criticalStatus", criticalStatus);
		}
		if(orderId!=null && !orderId.equals("")){
			query.setParameter("orderId", "%"+orderId+"%");
		}
		if(orderStatus!=null && !orderStatus.equals("")){
			query.setParameter("orderStatus", orderStatus);
		}
		int countNum = query.getResultList().size();
		if (firstPage != -1 && pageNum != -1) {
			query.setFirstResult(firstPage).setMaxResults(pageNum);
		}
		qr.setResultlist(query.getResultList());
		
		qr.setTotalrecord(countNum);

		return qr;
	}

	


	

}
