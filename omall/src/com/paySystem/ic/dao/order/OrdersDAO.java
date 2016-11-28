package com.paySystem.ic.dao.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.DeliveryOrders;
import com.paySystem.ic.bean.buss.Promotion;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.bean.order.Orders;
import com.paySystem.ic.dao.common.DAO;
import com.paySystem.ic.web.dto.order.OrdersDTO;


/**
 * @ClassName:OrderDao
 * @Description:订单管理Dao
 * @date: 2014-10-10上午10:46:46
 * @author: 赵瑞群
 * @version: V1.0
 */
public interface OrdersDAO extends DAO<Orders> {
	 
	
	/**
	 *@Title:queryPromotionByCond
	 *@Description:根据条件查询订单管理信息列表
	 *@param:@param firstPage 开始条数
	 *@param:@param pageNum   每页显示调试
	 *@param:@param orderBy   排序方式
	 *@param:@return
	 *@return:List<PaySerParamDTO> 返回DTO集合
	 *@author:  赵瑞群
	 *@Thorws:
	 */
	public QueryResult<OrdersDTO> queryOrderList(OrdersDTO ORDERDTO)throws Exception;
	
	/**
	 * 
	*@Title:queryGoodsByOrderId
	*@Description:通过订单号获取相应商品
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:ArrayList
	*@author:luckygroup
	*@Date:2014-11-21上午12:55:47
	 */
	public ArrayList queryGoodsByOrderId(String orderId)throws Exception;
	
	/**
	 * 
	*@Title:openRecruit
	*@Description:关闭订单
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-18下午11:07:36
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
	*@Date:2014-10-19上午12:01:06
	 */
	public void freeFreight(String orderIds) throws Exception;
	
	/**
	 * 
	*@Title:queryOrderDetail
	*@Description:查询订单详细
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrderDetails
	*@author:luckygroup
	*@Date:2014-10-19下午4:00:10
	 */
	public OrderDetails queryOrderDetail(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:queryOrder
	*@Description:查询订单
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:Orders
	*@author:luckygroup
	*@Date:2014-10-19下午4:00:46
	 */
	public OrdersDTO queryOrder(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:updatePrice
	*@Description:修改订单价格
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午4:38:03
	 */
	public void updatePrice(OrdersDTO ordersDTO) throws Exception;
	
	/**
	 * 
	*@Title:queryDeliveryOrder
	*@Description:通过查询发货实体获取发货日期
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:DeliveryOrders
	*@author:luckygroup
	*@Date:2014-10-19下午9:21:47
	 */
	public DeliveryOrders queryDeliveryOrder(String orderId) throws Exception;
	
	/**
	 * 
	*@Title:extendTakeOverTime
	*@Description:延长收货时间
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午9:33:28
	 */
	public void extendTakeOverTime(OrdersDTO ordersDTO) throws Exception;
	
	public static final String ORDERSDAO ="ordersDAO";

	
}
