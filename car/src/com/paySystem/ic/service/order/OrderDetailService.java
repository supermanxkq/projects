package com.paySystem.ic.service.order;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderDetail;
import com.paySystem.ic.dao.common.DAO;

public interface OrderDetailService extends DAO<OrderDetail>{

	/** 常量 */
	public static final String ORDERDETAILSERVICE = "OrderDetailService";
	public void addSave(OrderDetail orderDetail);
	public QueryResult<OrderDetail> queryOrderDetailByOrderId(Integer orderId);
}