package com.blog.service.order;

import com.blog.bean.QueryResult;
import com.blog.bean.help.Order;
import com.blog.dao.common.DAO;

public interface OrderService extends DAO<Order> {
	/** 常量 */
	public static final String ORDERSERVICE = "OrderService";

	public QueryResult<Order> queryAll();
}
