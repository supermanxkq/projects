package com.paySystem.ic.service.order;

import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.Order;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.OrderDTO;

public interface OrderService extends DAO<Order>{

	/** 常量 */
	public static final String ORDERSERVICE = "OrderService";
	public Order addSave(Order order);
	public QueryResult<OrderDTO> queryAll(int firstindex, int pageNum,		OrderDTO orderDTO, LinkedHashMap<String, String> orderBy);
}