package com.blog.web.action.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.bean.QueryResult;
import com.blog.bean.help.Order;
import com.blog.service.order.OrderService;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/order/order")
@Scope("prototype")
public class OrderAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private OrderService orderService;
	private List<Order> orderList;

	public String queryAll() {
		QueryResult<Order> orderQueryResult = orderService.queryAll();
		orderList = orderQueryResult.getResultlist();
		return "queryorders";
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
