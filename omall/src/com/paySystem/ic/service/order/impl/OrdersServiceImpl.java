package com.paySystem.ic.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.DeliveryOrders;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.dao.base.DeliveryOrdersDAO;
import com.paySystem.ic.dao.base.ProvinceDAO;
import com.paySystem.ic.dao.order.OrdersDAO;
import com.paySystem.ic.service.base.DeliveryOrderService;
import com.paySystem.ic.service.order.OrdersService;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;
import com.paySystem.ic.web.dto.order.OrdersDTO;

/**
 * @ClassName: OrdersService.java
 * @Description:订单服务类
 * @date: 2014-10-10下午03:03:53
 * @author: luckygroup
 * @version: V1.0
 */
@Service(OrdersService.ORDERSERVICE)
public class OrdersServiceImpl implements OrdersService {
	
	@Resource
	private OrdersDAO ordersDAO;
	
	
	
	

	/**
	 *@Title: getOrderPage
	 *@Description: 根据各种条件查询订单信息
	 *@Params:@param ordersDTO 
	 *@Return: QueryResult<OrdersDTO> 订单相关信息表
	 *@author: luckygroup
	 *@Date:2014-10-10下午10:10:02
	 */
	public QueryResult<OrdersDTO> getOrderPage(OrdersDTO ordersDTO)  throws Exception {
		return ordersDAO.queryOrderList(ordersDTO);
	}

	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.OrdersService#CloseOrder(java.lang.String)
	 *@MethodName:CloseOrder
	 *@Description:关闭订单
	 *@param orderId
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-10-18下午11:09:37
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public OrdersDTO CloseOrder(String orderId) throws Exception{
		return ordersDAO.CloseOrder(orderId);
	}
	
	/**
	 * 
	*@Title:freeFreight
	*@Description:免运费
	*@Params:@param orderIds
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19上午12:02:14
	 */
	public void freeFreight(String orderIds) throws Exception{
		ordersDAO.freeFreight(orderIds);
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.OrdersService#queryUpdatePrice(java.lang.String)
	 *@MethodName:queryUpdatePr
	 *@Description:获取修改价格页面信息
	 *@param orderId
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-10-19下午4:12:01
	 */
	public OrdersDTO queryUpdatePrice(String orderId) throws Exception{
		OrderDetails orderDetails= ordersDAO.queryOrderDetail(orderId);
		OrdersDTO ordersDTO = ordersDAO.queryOrder(orderId);
		ordersDTO.setAddress(orderDetails.getAddress());
		ordersDTO.setOrderAmt(orderDetails.getOrderAmt());
		return ordersDTO;
	}
	
	/**
	 * 
	*@Title:updatePrice
	*@Description:修改订单价格
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午4:41:55
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void updatePrice(OrdersDTO ordersDTO) throws Exception{
		ordersDAO.updatePrice(ordersDTO);
	}
	
	/**
	 * 
	*@Title:queryDeliveryOrder
	*@Description:通过发货实体获取发货日期
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:DeliveryOrders
	*@author:luckygroup
	*@Date:2014-10-19下午9:22:53
	 */
	public OrdersDTO queryOrderSendTime(String orderId) throws Exception{
		OrdersDTO ordersDTO = ordersDAO.queryOrder(orderId);
		ordersDTO.setSendTime(ordersDAO.queryDeliveryOrder(orderId).getCreateTime());	
		return ordersDTO;
	}
	
	/**
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.OrdersService#extendTakeOverTime(com.paySystem.ic.web.dto.order.OrdersDTO)
	 *@MethodName:extendTakeOverTime
	 *@Description:延长收货时间
	 *@param ordersDTO
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-10-19下午9:34:49
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void extendTakeOverTime(OrdersDTO ordersDTO) throws Exception{
		ordersDAO.extendTakeOverTime(ordersDTO);
	}
	/***
	 * 
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.OrdersService#queryOrderDetails(java.lang.String)
	 *@MethodName:queryOrderDetails
	 *@Description:查询订单详情
	 *@param orderId
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-10-20上午12:57:24
	 */
	public OrdersDTO queryOrderDetails(String orderId) throws Exception{
		OrderDetails orderDetails= ordersDAO.queryOrderDetail(orderId);
		OrdersDTO ordersDTO = ordersDAO.queryOrder(orderId);
		ordersDTO.setAddress(orderDetails.getAddress());
		ordersDTO.setLeaveWord(orderDetails.getLeaveWord());
		ordersDTO.setMemRealName(orderDetails.getMemRealName());
		ordersDTO.setMemTele(orderDetails.getMemTele());
		ordersDTO.setMail(orderDetails.getMail());
		ordersDTO.setPayAccount(orderDetails.getPayAccount());
		ordersDTO.setPayTradeId(orderDetails.getPayTradeId());
		ordersDTO.setOrderAmt(orderDetails.getOrderAmt());
		ordersDTO.setDeliveryWay(orderDetails.getDeliveryWay());
		return ordersDTO;
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.service.order.OrdersService#queryGoodsByOrderId(java.lang.String)
	 *@MethodName:queryGoodsByOrderId
	 *@Description:通过订单号获取相应商品
	 *@param orderId
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-11-21上午12:57:35
	 */
	
	public ArrayList queryGoodsByOrderId(String orderId) throws Exception {
		return ordersDAO.queryGoodsByOrderId(orderId);
	}
}
