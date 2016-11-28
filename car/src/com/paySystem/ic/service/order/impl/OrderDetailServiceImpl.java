package com.paySystem.ic.service.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderDetail;
import com.paySystem.ic.dao.order.OrderDetailDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.order.OrderDetailService;

@Service(OrderDetailService.ORDERDETAILSERVICE)
public class OrderDetailServiceImpl extends DaoSupport<OrderDetail> implements OrderDetailService {

	@Resource
	private OrderDetailDao orderDetailDao;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addSave(OrderDetail orderDetail) {
		this.save(orderDetail);
	}
	public QueryResult<OrderDetail> queryOrderDetailByOrderId(Integer orderId){
		return orderDetailDao.queryOrderDetailByOrderId(orderId);
	}
}
