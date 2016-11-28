package com.paySystem.ic.dao.base;

import java.util.List;
import java.util.Map;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.MerAddressDTO;
import com.paySystem.ic.web.dto.goods.GoodsPhotoDTO;
import com.paySystem.ic.web.dto.order.OrdersDTO;

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
	 *@Date:2014-10-15下午10:10:02
	 */
	List<DeliveryOrdersDTO> queryDeliveryOrdersList(String merId,List<String> orderIds) throws Exception ;
	
	/**
	 *@Title: queryDeliveryOrders
	 *@Description: 根据条件来查询
	 *@Params:@param deliveryDTO 发货查询信息
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	QueryResult<DeliveryOrdersDTO> queryDeliveryOrders(DeliveryOrdersDTO deliveryDTO) throws Exception ;
	
	/**
	 *@Title: queryWayBillNoByOrderIds
	 *@Description: 根据订单号查询物流单号
	 *@Params:@param orderIds 订单id列表
	 *@Return: Map<String,String> 查询结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Map<String,String> queryWayBillNoByOrderIds(List<String> orderIds) throws Exception;
	
	/**
	 *@Title: queryOrdersById
	 *@Description: 根据商户id和订单号
	 *@Params:@param merId 商户id
	 *@Params:@param orderIds 订单id列表
	 *@Return: List<OrdersDTO> 订单列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	List<OrdersDTO> queryOrdersById(String merId,List<String> orderIds) throws Exception;
	
	/**
	 *@Title: updasteOrderStatus
	 *@Description: 更新订单状态
	 *@Params: orderId 订单id
	 *@param status 订单状态
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Integer updasteOrderStatus (String orderId,Integer status) throws Exception;
	
	/**
	 *@Title: updateWaybillNo
	 *@Description: 更新订单运单号
	 *@Params: orderId 订单id
	 *@param wayBillNo 运单单号
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Integer updateWaybillNo(String orderId,String wayBillNo) throws Exception;
	

	/**
	 *@Title: updateOrderDetail
	 *@Description: 更新订单明细表
	 *@Params: delieryDTO 发货dto
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Integer updateOrderDetail(DeliveryOrdersDTO delieryDTO) throws Exception;
	
	/**
	 *@Title: updateOrderDetailAddress
	 *@Description: 更新发货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	Integer updateOrderDetailAddress(List<String> orderList,String address) throws Exception ;
	
	/**
	 *@Title: updateOrderReturnAddress
	 *@Description: 更新退货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	Integer updateOrderReturnAddress(List<String> orderList,String address) throws Exception;
	
	/**
	 *@Title: updateOrderReceiveAddress
	 *@Description: 更新收货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	Integer updateOrderReceiveAddress(String orderId,String address,String memName,String memTele) throws Exception;
	
	/**
	 *@Title: queryOrderDetail
	 *@Description: 查询订单明细
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	OrderDetails queryOrderDetail(String orderId) throws Exception;
	
	/**
	 *@Title: updateDeliveryStatus
	 *@Description: 更新发货信息的状态
	 *@Params: orderId 订单id
	 *@Params: status 发货信息状态
	 *@Return: Integer 更新记录条数
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Integer updateDeliveryStatus(String orderId,Integer status) throws Exception;
	
	/**
	 *@Title: updateDeliveryInfo
	 *@Description: 更新发货信息
	 *@Params: deliverDTO 发货信息
	 *@Return: Integer 更新记录条数
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	Integer updateDeliveryInfo(DeliveryOrdersDTO deliverDTO) throws Exception;
	
	/**
	 *@Title: queryMerAddressDTO
	 *@Description: 查询商户地址DTO
	 *@Params: merId 商户id
	 *@Return: MerAddressDTO 查询商户地址
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	List<MerAddressDTO> queryMerAddressDTO(String merId) throws Exception;
	
	/**
	 *@Title: queryOrderDetailsList
	 *@Description: 查询订单详情
	 *@Params: merId 商户id
	 *@param: List<String> orderIds 订单id
	 *@Return: List<OrderDetails>  订单明细列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	List<OrderDetails> queryOrderDetailsList(String merId,List<String> orderIds) throws Exception;
	
	/**
	 *@Title: queryOrderGoodPic
	 *@Description: 根据订单号查询商品图片
	 *@Params: List<String> orderIds 订单ID
	 *@Return: List<GoodsPhotoDTO>  商品图片列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	List<GoodsPhotoDTO> queryOrderGoodPic(List<String> orderIds) throws Exception;
	
}
