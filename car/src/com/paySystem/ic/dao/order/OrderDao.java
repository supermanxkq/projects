package com.paySystem.ic.dao.order;


import java.util.LinkedHashMap;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.order.OrderDTO;

public interface OrderDao {

	public static final String ORDERDAO = "OrderDao";

	public QueryResult<OrderDTO> queryAll(int firstindex, int pageNum, OrderDTO orderDTO,
			LinkedHashMap<String, String> orderBy);
}
