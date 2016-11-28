package com.blog.service.order.impl;

import org.springframework.stereotype.Service;

import com.blog.bean.QueryResult;
import com.blog.bean.help.Order;
import com.blog.dao.common.DaoSupport;
import com.blog.service.order.OrderService;

@Service(OrderService.ORDERSERVICE)
public class OrderServiceImpl extends DaoSupport<Order> implements OrderService {

	@Override
	public QueryResult<Order> queryAll() {
		try {
			return this.getScrollData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
