package com.paySystem.ic.dao.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderDetail;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.OrderDetailDao;

@Repository(OrderDetailDao.ORDERDETAILDAO)
public class OrderDetailDaoImpl extends DaoSupport<OrderDetail> implements OrderDetailDao {
	@SuppressWarnings("unchecked")
	public QueryResult<OrderDetail> queryOrderDetailByOrderId(Integer orderDetailId) {
		List<Object []> orderDetail=new ArrayList<>();
		QueryResult<OrderDetail> queryResult = new QueryResult<>();
		List<OrderDetail> orderDetailList=new ArrayList<>();
		try {
			String sql="select * from t_orderDetail where orderId="+orderDetailId;
			Query query =this.em.createNativeQuery(sql);
			orderDetail=query.getResultList();
			for(Object [] obj:orderDetail){
				OrderDetail orderDetail2=new OrderDetail();
				orderDetail2.setCount(Integer.parseInt(obj[1].toString()));
				orderDetail2.setGoodsId(Integer.parseInt(obj[2].toString()));
				orderDetail2.setId(Integer.parseInt(obj[0].toString()));
				orderDetail2.setOrderId(Integer.parseInt(obj[4].toString()));
				orderDetail2.setGoodsName(obj[3].toString());
				orderDetail2.setPrice(Float.parseFloat(obj[5].toString()));
				orderDetailList.add(orderDetail2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		queryResult.setResultlist(orderDetailList);
		queryResult.setTotalrecord(queryResult.getTotalrecord());
		return queryResult;

	}

}
