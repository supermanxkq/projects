package com.paySystem.ic.dao.base;

import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;

/**  
 * @Title: DeliveryOrdersDAO.java
 * @Package: com.paySystem.ic.dao.base
 * @Description: 发货管理DAO
 * @Author: Jacky 
 * @Date: 2014-10-10 下午11:12:41
 */
public interface DeliveryOrdersDAO {
	public static final String DELIVERYORDERSDAO = "deliveryOrdersDAO";
	
	/**
	 *@Title: queryDeliveryOrdersList
	 *@Description: 根据订单来查询
	 *@Params:@param orderIds 商品订单列表
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-8-20下午10:10:02
	 */
	List<DeliveryOrdersDTO> queryDeliveryOrdersList(List<String> orderIds) throws Exception ;
	
	/**
	 *@Title: queryDeliveryOrders
	 *@Description: 根据条件来查询
	 *@Params:@param deliveryDTO 发货查询信息
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-8-20下午10:10:02
	 */
	QueryResult<DeliveryOrdersDTO> queryDeliveryOrders(DeliveryOrdersDTO deliveryDTO) throws Exception ;
}
