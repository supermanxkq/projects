package com.paySystem.ic.service.order;


import java.util.ArrayList;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.order.OrdersDTO;

/**
 * @ClassName: OrderService.java
 * @Description:订单服务类
 * @date: 2014-10-10下午03:03:53
 * @author: luckygroup
 * @version: V1.0
 */
public interface OrdersService {
	public static final String ORDERSERVICE = "ordersService";
	
	/**
	 *@Title: getDelieryOrderPage
	 *@Description: 根据各种条件查询订单信息
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: List<OrdersDTO> 
	 *@author: luckygroup
	 *@Date:2014-10-10下午10:10:02
	 */
	public QueryResult<OrdersDTO> getOrderPage(OrdersDTO ordersDTO) throws Exception ;
	
	/**
	 * 
	*@Title:queryGoodsByOrderId
	*@Description:通过订单号获取相应商品
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ArrayList
	*@author:luckygroup
	*@Date:2014-11-21上午12:57:07
	 */
	public ArrayList queryGoodsByOrderId(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:
	*@Description:关闭订单
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-18下午11:08:47
	 */
	public OrdersDTO CloseOrder(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:freeFreight
	*@Description:免运费
	*@Params:@param orderIds
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19上午12:02:42
	 */
	public void freeFreight(String orderIds) throws Exception;
	
	/**
	 * 
	*@Title:queryUpdatePrice
	*@Description:获取修改价格页面信息
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-19下午4:11:04
	 */
	public OrdersDTO queryUpdatePrice(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:updatePrice
	*@Description:修改订单价格
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午4:47:20
	 */
	public void updatePrice(OrdersDTO ordersDTO) throws Exception;
	/**
	 * 
	*@Title:queryDeliveryOrder
	*@Description:获取发货日期
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-19下午9:28:17
	 */
	public OrdersDTO queryOrderSendTime(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:extendTakeOverTime
	*@Description:延长收货时间
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午9:34:27
	 */
	public void extendTakeOverTime(OrdersDTO ordersDTO) throws Exception;
	
	/**
	 * 
	*@Title:queryOrderDetails
	*@Description:查询订单详情页
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-20上午12:56:46
	 */
	public OrdersDTO queryOrderDetails(String orderId) throws Exception;
	
}
