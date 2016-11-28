package com.paySystem.ic.dao.order;


import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.Order;
import com.paySystem.ic.bean.order.OrderDetail;

public interface OrderDetailDao {

	public static final String ORDERDETAILDAO = "OrderDetailDao";

	public QueryResult<OrderDetail> queryOrderDetailByOrderId(Integer orderDetailId);
}
