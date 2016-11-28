package com.paySystem.ic.dao.order.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.Order;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.OrderDao;
import com.paySystem.ic.utils.EntityDtoConverter;
import com.paySystem.ic.web.dto.order.OrderDTO;

@Repository(OrderDao.ORDERDAO)
public class OrderDaoImpl extends DaoSupport<Order> implements OrderDao {
	public QueryResult<OrderDTO> queryAll(int firstindex, int pageNum, OrderDTO orderDTO,
			LinkedHashMap<String, String> orderBy) {
		StringBuffer wherejpql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		QueryResult<Order> queryResult = null;
		QueryResult<OrderDTO> queryResult2 = new QueryResult<OrderDTO>();
		List<OrderDTO> list = new ArrayList<OrderDTO>();

		if (orderDTO.getStatus() != null && orderDTO.getStatus()!=-1) {
			wherejpql.append(" and o.status="+orderDTO.getStatus());
		}
//		if(orderDTO.getBeginDate()!=null&&!orderDTO.getBeginDate().equals("")){
//			wherejpql.append(" and o.createTime>='"+orderDTO.getBeginDate()+" 00:00:00'");
//		}
//		if(orderDTO.getEndDate()!=null&&!orderDTO.getEndDate().equals("")){
//			wherejpql.append(" and o.createTime<='"+orderDTO.getEndDate()+" 23:59:59'");
//		}
		if(orderDTO.getCustomer()!=null&&!orderDTO.getCustomer().equals("")){
			wherejpql.append(" and o.customer like ?");
			queryParams.add("%" + orderDTO.getCustomer() + "%");
		}
		try {
			queryResult = this.getScrollData(firstindex, pageNum, wherejpql.toString(), queryParams.toArray(), orderBy);
			for (Order order : queryResult.getResultlist()) {
				OrderDTO orderDTO2 = new OrderDTO();
				orderDTO2 = (OrderDTO) EntityDtoConverter.bean2Dto(order, orderDTO2);
				list.add(orderDTO2);
			}
			queryResult2.setResultlist(list);
			queryResult2.setTotalrecord(queryResult.getTotalrecord());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult2;

	}

}
