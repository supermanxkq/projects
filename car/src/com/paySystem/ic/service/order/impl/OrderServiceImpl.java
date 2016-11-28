package com.paySystem.ic.service.order.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.Order;
import com.paySystem.ic.dao.order.OrderDao;
import com.paySystem.ic.service.common.DaoSupport;
import com.paySystem.ic.service.order.OrderService;
import com.paySystem.ic.web.dto.order.OrderDTO;

@Service(OrderService.ORDERSERVICE)
public class OrderServiceImpl extends DaoSupport<Order> implements OrderService {

	@Resource
	private OrderDao orderDao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Order addSave(Order order) {
		this.save(order);
		return order;
	}

	public QueryResult<OrderDTO> queryAll(int firstindex, int pageNum, OrderDTO orderDTO,
			LinkedHashMap<String, String> orderBy) {
		return orderDao.queryAll(firstindex, pageNum, orderDTO, orderBy);
	}
}
