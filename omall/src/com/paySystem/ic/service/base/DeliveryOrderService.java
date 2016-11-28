package com.paySystem.ic.service.base;

import java.util.List;
import java.util.Map;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.MerAddressDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;

/**
 * @ClassName: DeliveryOrderService.java
 * @Description:发货服务类
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
public interface DeliveryOrderService {
	public static final String DELIVERYORDERSERVICE = "deliveryOrderService";
	
	/**
	 *@Title: getDeliveryOrderList
	 *@Description: 根据订单号来查询发货订单
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: List<DeliveryOrdersDTO> 发货相关信息表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<DeliveryOrdersDTO> getDeliveryOrderList(String merId,List<String> orderIds) throws Exception ;
	
	/**
	 *@Title: getDelieryOrderPage
	 *@Description: 根据各种条件查询发货订单信息
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: List<DeliveryOrdersDTO> 发货相关信息表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public QueryResult<DeliveryOrdersDTO> getDelieryOrderPage(DeliveryOrdersDTO deliveryDTO) throws Exception ;
	
	/**
	 *@Title: getProvince
	 *@Description: 查询所有省
	 *@Return: List<ProvinceDTO> 省dto列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<ProvinceDTO> getProvince() throws Exception;
	
	/**
	 *@Title: getCityList
	 *@Description: 根据省编码查询下属市
	 *@param parentCode编码
	 *@Return: List<CityDTO> 市列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<CityDTO> getCityList(String parentCode) throws Exception;
	
	/**
	 *@Title: getAreaList
	 *@Description: 根据编码查询区域列表
	 *@param parentCode编码
	 *@Return: List<AreaBeanDTO> 市列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<AreaBeanDTO> getAreaList(String parentCode) throws Exception;
	
	/**
	 *@Title: closeOrder
	 *@Description: 关闭订单
	 *@param orderId 订单号
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean closeOrder(String orderId,String merId) throws Exception;
	
	/**
	 *@Title: confirmSendGoods
	 *@Description: 确认发货
	 *@param orderId 订单号
	 *@param merId 商户编码
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean confirmSendGoods(String orderId,String merId,String wayBillNo) throws Exception;
	
	/**
	 *@Title: sendSingleGoods
	 *@Description: 单个发送
	 *@param deliveryDTO 发货dto
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean sendSingleGoods(DeliveryOrdersDTO deliveryDTO) throws Exception;
	
	/**
	 *@Title: batchSendGoods
	 *@Description: 批量发货
	 *@param deliveryDTOList 发货dto
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean batchSendGoods(List<DeliveryOrdersDTO> deliveryDTOList) throws Exception;
	
	/**
	 *@Title: batchConfirmSendGoods
	 *@Description: 批量确认发货
	 *@param orderBillMap 订单、物流号map
	 *@param orderIds 订单列表
	 *@Return: true表示 成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean batchConfirmSendGoods(Map<String,String> orderBillMap,List<String> orderIds,String merId) throws Exception;
	
	
	/**
	 *@Title: getDeliveryDetailInfo
	 *@Description: 根据orderId查询物流详情
	 *@param orderId 订单号
	 *@param merId 商户编码
	 *@Return: DeliveryOrdersDTO 物流详细信息
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public DeliveryOrdersDTO getDeliveryDetailInfo(String orderId,String merId) throws Exception;
	
	/**
	 *@Title: updateWayBillNo
	 *@Description: 更新物流单号
	 *@param orderId 订单号
	 *@param merId 商户编码
	 *@param wayBillNo 物流单号
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean updateWayBillNo(String orderId,String merId,String wayBillNo) throws Exception;
	
	/**
	 *@Title: updateSendAddress
	 *@Description: 更新发货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean updateSendAddress(List<String> orderIdList,String address) throws Exception;
	
	/**
	 *@Title: updateReturnAddress
	 *@Description: 更新退货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean updateReturnAddress(List<String> orderIdList,String address) throws Exception;
	
	/**
	 *@Title: updateReceiveAddress
	 *@Description: 更新收货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public boolean updateReceiveAddress(String orderId,String address,String memName,String memTele) throws Exception;
	
	/**
	 *@Title: queryMerAddressDTOMap
	 *@Description: 根据商户查询其默认的收发货地址
	 *@param merId 商户编码
	 *@Return: Map<String,MerAddressDTO> send:key 表示默认发货地址 rece:key表示默认收货地址
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public Map<String,MerAddressDTO> queryMerAddressDTOMap(String merId) throws Exception;
	
	/**
	 *@Title: queryOrderAddresses
	 *@Description: 查询该订单的收发货地址
	 *@param orderId 订单号
	 *@Return: String[] 查询订单收发货地址0表示退货地址、1表示发货地址
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public String[] queryOrderAddresses(String orderId) throws Exception;
}
