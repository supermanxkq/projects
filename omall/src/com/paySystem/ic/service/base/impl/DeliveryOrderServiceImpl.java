package com.paySystem.ic.service.base.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.dao.base.DeliveryOrdersDAO;
import com.paySystem.ic.dao.base.LogisticsDao;
import com.paySystem.ic.dao.base.ProvinceDAO;
import com.paySystem.ic.service.base.DeliveryOrderService;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
import com.paySystem.ic.web.dto.base.MerAddressDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;
import com.paySystem.ic.web.dto.goods.GoodsPhotoDTO;
import com.paySystem.ic.web.dto.order.OrdersDTO;

/**
 * @ClassName: DeliveryOrderService.java
 * @Description:发货服务类
 * @date: 2014-10-10下午03:03:53
 * @author: Jacky
 * @version: V1.0
 */
@Service(DeliveryOrderService.DELIVERYORDERSERVICE)
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
	
	@Resource
	private DeliveryOrdersDAO deliveryOrdersDAO;
	
	@Resource
	private ProvinceDAO provinceDAO;
	
	@Resource
	private LogisticsDao logisticsDao;
	
	/**
	 *@Title: getDeliveryOrderList
	 *@Description: 根据订单号来查询发货订单
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: List<DeliveryOrdersDTO> 发货相关信息表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<DeliveryOrdersDTO> getDeliveryOrderList(String merId,List<String> orderIds)  throws Exception {
		List<DeliveryOrdersDTO> deliveryList = deliveryOrdersDAO.queryDeliveryOrdersList(merId,orderIds);
		if(CollectionUtils.isNotEmpty(deliveryList)) {
			List<GoodsPhotoDTO> goodPicList = deliveryOrdersDAO.queryOrderGoodPic(orderIds);
			Map<String,GoodsPhotoDTO> orderUrl = new HashMap<String,GoodsPhotoDTO>();
			if(CollectionUtils.isNotEmpty(goodPicList)) {
				for(GoodsPhotoDTO gp : goodPicList) {
					if(orderUrl.get(gp.getOrderId())==null) {
						orderUrl.put(gp.getOrderId(), gp);
					}
				}
			}
			Iterator<DeliveryOrdersDTO> iter = deliveryList.iterator();
			while(iter.hasNext()) {
				DeliveryOrdersDTO dod = iter.next();
				if(dod.getStatus() != 0 && dod.getStatus() != 3) {
					iter.remove();
				} else {
					GoodsPhotoDTO gpd = orderUrl.get(dod.getOrderId());
					if(null != gpd) {
						dod.setGoodsId(String.valueOf(gpd.getGoodsId()));
						dod.setGoodsUrl(gpd.getGoodsLittPho());
					}
				}
				
			}
			
		}
		return deliveryList;
	}

	/**
	 *@Title: closeOrder
	 *@Description: 关闭订单
	 *@param orderId 订单号
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean closeOrder(String orderId,String merId) throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(merId, Arrays.asList(orderId));
		if(CollectionUtils.isNotEmpty(orderList)) {
			OrdersDTO orderDTO = orderList.get(0);
			if(orderDTO.getStatus()!=null && orderDTO.getStatus() != 7 && orderDTO.getStatus() != 5) {
				Integer count = deliveryOrdersDAO.updasteOrderStatus(orderId, 5);
				if(count > 0) {
					/** 更新发货状态，为已取消**/
					Integer c = deliveryOrdersDAO.updateDeliveryStatus(orderId,3);
					if(c < 1) {
						throw new RuntimeException("关闭订单失败！回滚...");
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 *@Title: batchConfirmSendGoods
	 *@Description: 批量确认发货
	 *@param orderBillMap 订单、物流号map
	 *@param orderIds 订单列表
	 *@Return: true表示 成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean batchConfirmSendGoods(Map<String, String> orderBillMap,List<String> orderIds,String merId)
			throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(merId, orderIds);
		if(CollectionUtils.isNotEmpty(orderList)) {
			for(OrdersDTO ordersDTO : orderList) {
				OrderDetails orderDetail = deliveryOrdersDAO.queryOrderDetail(ordersDTO.getOrderId());
				/** 为了安全，判断状态是已发货并且物流单号为空的情况才能批量确认发货**/
				if(ordersDTO.getStatus() == 4 && StringUtils.isBlank(orderDetail.getWaybillNo())) {
					String wayBill = orderBillMap.get(orderDetail.getOrderId());
					if(StringUtils.isNotBlank(wayBill)) {
						Integer count = deliveryOrdersDAO.updateWaybillNo(orderDetail.getOrderId(), wayBill);
						if(count > 0) {
							Integer updated = deliveryOrdersDAO.updateDeliveryStatus(orderDetail.getOrderId(), 2);
							if(updated < 1) {
								throw new IllegalArgumentException("更新订单物流单号失败！回滚...");
							}
						}
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 *@Title: queryMerAddressDTOMap
	 *@Description: 根据商户查询其默认的收发货地址
	 *@param merId 商户编码
	 *@Return: Map<String,MerAddressDTO> send:key 表示默认发货地址 return:key表示默认收货地址
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public Map<String, MerAddressDTO> queryMerAddressDTOMap(String merId)
			throws Exception {
		List<MerAddressDTO> merAddressList = deliveryOrdersDAO.queryMerAddressDTO(merId);
		Map<String, MerAddressDTO> maps = new HashMap<String, MerAddressDTO>();
		for(MerAddressDTO mad: merAddressList) {
			if(mad.getIsReturn().equals("1")) {
				maps.put("return", mad);
			}
			if(mad.getIsSend().equals("1")) {
				maps.put("send", mad);
			}
		}
		return maps;
	}

	/**
	 *@Title: sendSingleGoods
	 *@Description: 单个发送
	 *@param delieryDTO 发货dto
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean sendSingleGoods(DeliveryOrdersDTO delieryDTO) throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(delieryDTO.getMerId(), Arrays.asList(delieryDTO.getOrderId()));
		if(CollectionUtils.isNotEmpty(orderList)) {
			OrdersDTO ordersDTO = orderList.get(0);
			OrderDetails orderDetail = deliveryOrdersDAO.queryOrderDetail(ordersDTO.getOrderId());
			/** 状态 为2 或者 4 的时候才允许发货**/
			if(ordersDTO.getStatus()!=null && (ordersDTO.getStatus() == 3 || ordersDTO.getStatus() == 5) && StringUtils.isBlank(orderDetail.getWaybillNo())) {
				/** 无需物流情况，把几个参数置空**/
				if(delieryDTO.getDeliveryWay().equals("0")) {
					delieryDTO.setLogiId(null);
					delieryDTO.setWaybillNo(null);
					delieryDTO.setAddress(null);
					delieryDTO.setSendAddress(null);
					delieryDTO.setReturnAddress(null);
					delieryDTO.setMemTele(null);
					delieryDTO.setMemName(null);
					/** 无需物流直接变成发货完成**/
					delieryDTO.setStatus(3);
				} 
				/** 更新明细表中的物流单号**/
				Integer count = deliveryOrdersDAO.updateOrderDetail(delieryDTO);
				if(count > 0) {
					if(delieryDTO.getDeliveryWay().equals("1")) {
						/** 如果没有填写运单号，那么就是发货中**/
						if(StringUtils.isBlank(delieryDTO.getWaybillNo())&& delieryDTO.getDeliveryWay().equals("1")) {
							delieryDTO.setStatus(1);
						} else {
							/** 已发货的**/
							delieryDTO.setStatus(2);
						}
					}
					
					/** 更新发货状态，已发货**/
					Integer updated = deliveryOrdersDAO.updateDeliveryInfo(delieryDTO);
					/** 更新订单状态，已发货**/
					Integer updatedOrder = deliveryOrdersDAO.updasteOrderStatus(delieryDTO.getOrderId(), 4);
					if(updated < 1 || updatedOrder < 1) {
						throw new IllegalArgumentException("更新订单物流单号失败！回滚...");
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *@Title: batchSendGoods
	 *@Description: 批量发货
	 *@param deliveryDTO 发货dto
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean batchSendGoods(List<DeliveryOrdersDTO> deliveryDTOList)
			throws Exception {
		if(CollectionUtils.isNotEmpty(deliveryDTOList)) {
			for(DeliveryOrdersDTO delieryDTO : deliveryDTOList) {
				List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(delieryDTO.getMerId(), Arrays.asList(delieryDTO.getOrderId()));
				if(CollectionUtils.isNotEmpty(orderList)) {
					OrdersDTO ordersDTO = orderList.get(0);
					OrderDetails orderDetail = deliveryOrdersDAO.queryOrderDetail(ordersDTO.getOrderId());
					/** 状态至少要为2待发货或者取消的**/
					if(ordersDTO.getStatus()!=null && (ordersDTO.getStatus() == 3 || ordersDTO.getStatus() == 5) && StringUtils.isBlank(orderDetail.getWaybillNo())) {
						/** 更新明细表中的物流单号**/
						Integer count = deliveryOrdersDAO.updateOrderDetail(delieryDTO);
						if(count > 0) {
							/** 如果没有填写运单号，并且是需要物流 那么就是发货中**/
							if(StringUtils.isBlank(delieryDTO.getWaybillNo()) && delieryDTO.getDeliveryWay().equals("1")) {
								delieryDTO.setStatus(1);
							} else {
								/** 已发货的**/
								delieryDTO.setStatus(2);
							}
							
							/** 更新发货状态，已发货**/
							Integer updated = deliveryOrdersDAO.updateDeliveryInfo(delieryDTO);
							/** 更新订单状态，已发货**/
							Integer updatedOrder = deliveryOrdersDAO.updasteOrderStatus(delieryDTO.getOrderId(), 4);
							if(updated < 1 || updatedOrder < 1) {
								throw new IllegalArgumentException("更新订单物流单号失败！回滚...");
							}
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 *@Title: confirmSendGoods
	 *@Description: 确认发货
	 *@param orderId 订单号
	 *@param merId 商户编码
	 *@Return: true表示关闭成功、false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean confirmSendGoods(String orderId, String merId,String wayBillNo)
			throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(merId, Arrays.asList(orderId));
		if(CollectionUtils.isNotEmpty(orderList)) {
			OrdersDTO ordersDTO = orderList.get(0);
			OrderDetails orderDetail = deliveryOrdersDAO.queryOrderDetail(ordersDTO.getOrderId());
			/** 状态至少要为3**/
			if(ordersDTO.getStatus()!=null && ordersDTO.getStatus() == 4 && StringUtils.isBlank(orderDetail.getWaybillNo())) {
				/** 更新明细表中的物流单号**/
				Integer count = deliveryOrdersDAO.updateWaybillNo(orderId, wayBillNo);
				if(count > 0) {
					/** 更新发货状态，已发货**/
					Integer updated = deliveryOrdersDAO.updateDeliveryStatus(orderId, 2);
					/** 更新订单状态，已发货**/
					Integer updatedOrder = deliveryOrdersDAO.updasteOrderStatus(orderId, 4);
					if(updated < 1 || updatedOrder < 1) {
						throw new IllegalArgumentException("更新订单物流单号失败！回滚...");
					}
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 *@Title: getDeliveryDetailInfo
	 *@Description: 根据orderId查询物流详情
	 *@param orderId 订单号
	 *@param merId 商户编码
	 *@Return: DeliveryOrdersDTO 物流详细信息
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public DeliveryOrdersDTO getDeliveryDetailInfo(String orderId, String merId)
			throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(merId, Arrays.asList(orderId));
		if(CollectionUtils.isNotEmpty(orderList)) {
			List<DeliveryOrdersDTO> deliveryDTO = deliveryOrdersDAO.queryDeliveryOrdersList(merId,Arrays.asList(orderId));
			if(CollectionUtils.isNotEmpty(deliveryDTO)) {
				/** 商品信息 **/
				DeliveryOrdersDTO dod = new DeliveryOrdersDTO();
				dod.setGoodsName(deliveryDTO.get(0).getGoodsName());
				dod.setPrice(deliveryDTO.get(0).getPrice());
				dod.setQty(deliveryDTO.get(0).getQty());
				dod.setOrderId(orderId);
				dod.setStatus(deliveryDTO.get(0).getStatus());
				
				/** 收货信息**/
				OrderDetails orderDetail = deliveryOrdersDAO.queryOrderDetail(orderId);
				if(null != orderDetail) {
					dod.setWaybillNo(orderDetail.getWaybillNo());
					dod.setAddress(orderDetail.getAddress());
					dod.setSendAddress(orderDetail.getSendAddress());
					dod.setReturnAddress(orderDetail.getReturnAddress());
					
					/** 退货信息**/
					LogisticsDTO logiDTO = logisticsDao.findById(orderDetail.getLogistId());
					dod.setDeliveryWay(orderDetail.getDeliveryWay());
					if(null != logiDTO) {
						dod.setDeliveryComp(logiDTO.getLogistName());
						dod.setCompUrl(logiDTO.getUrl());
					}
				}
				return dod;
			}
		}
		return null;
	}
	
	
	/**
	 *@Title: updateSendAddress
	 *@Description: 更新发货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateSendAddress(List<String> orderIdList, String address)
			throws Exception {
		return deliveryOrdersDAO.updateOrderDetailAddress(orderIdList, address) > 0;
	}

	/**
	 *@Title: updateReceiveAddress
	 *@Description: 更新退货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateReturnAddress(List<String> orderIdList, String address)
			throws Exception {
		return deliveryOrdersDAO.updateOrderReturnAddress(orderIdList, address) > 0;
	}

	/**
	 *@Title: updateReceiveAddress
	 *@Description: 更新收货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateReceiveAddress(String orderId, String address,
			String memName, String memTele) throws Exception {
		return deliveryOrdersDAO.updateOrderReceiveAddress(orderId, address, memName, memTele) > 0;
	}
	
	/**
	 *@Title: queryOrderAddresses
	 *@Description: 查询该订单的收发货地址
	 *@param orderId 订单号
	 *@Return: String[] 查询订单收发货地址0表示退货地址、1表示发货地址
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Override
	public String[] queryOrderAddresses(String orderId) throws Exception {
		OrderDetails orderDetails = deliveryOrdersDAO.queryOrderDetail(orderId);
		if(null != orderDetails) {
			String [] addresses = new String[]{orderDetails.getReturnAddress()==null?"":orderDetails.getReturnAddress(),orderDetails.getSendAddress()==null?"":orderDetails.getSendAddress()};
			return addresses;
		}
		return null;
	}

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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateWayBillNo(String orderId, String merId,
			String wayBillNo) throws Exception {
		List<OrdersDTO> orderList = deliveryOrdersDAO.queryOrdersById(merId, Arrays.asList(orderId));
		if(CollectionUtils.isNotEmpty(orderList)) {
			Integer count = deliveryOrdersDAO.updateWaybillNo(orderId, wayBillNo);
			if(null != count && count > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 *@Title: getDelieryOrderPage
	 *@Description: 根据各种条件查询发货订单信息
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: QueryResult<DeliveryOrdersDTO> 发货相关信息表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public QueryResult<DeliveryOrdersDTO> getDelieryOrderPage(
			DeliveryOrdersDTO deliveryDTO)  throws Exception {
		QueryResult<DeliveryOrdersDTO> queryResult = deliveryOrdersDAO.queryDeliveryOrders(deliveryDTO);
		List<String> orderIds = new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(queryResult.getResultlist())) {
			for(DeliveryOrdersDTO dod : queryResult.getResultlist()) {
				orderIds.add(dod.getOrderId());
			}
		}
		if(CollectionUtils.isNotEmpty(orderIds)) {
			/** 批量查询订单详情**/
			Set<Integer> logiSet = new HashSet<Integer>();
			List<OrderDetails> orderDetail = deliveryOrdersDAO.queryOrderDetailsList(deliveryDTO.getMerId(), orderIds);
			Map<String,OrderDetails> delieMap = new HashMap<String,OrderDetails>();
			if(CollectionUtils.isNotEmpty(orderDetail)) {
				for(OrderDetails od : orderDetail) {
					logiSet.add(od.getLogistId());
					delieMap.put(od.getOrderId(), od);
				}
			}
			
			//Map<String,String> delieMap = deliveryOrdersDAO.queryWayBillNoByOrderIds(orderIds);
			Map<Integer,LogisticsDTO> logiMap = new HashMap<Integer,LogisticsDTO>();
			if(logiSet.size() > 0) {
				List<LogisticsDTO> logiList = logisticsDao.batchQueryLogisticsList(logiSet);
				for(LogisticsDTO logi : logiList) {
					logiMap.put(logi.getLogistId().intValue(), logi);
				}
			}
			
			
			if(CollectionUtils.isNotEmpty(queryResult.getResultlist())) {
				for(DeliveryOrdersDTO dod : queryResult.getResultlist()) {
					if(null != delieMap && delieMap.size() > 0) {
						OrderDetails wayBill = delieMap.get(dod.getOrderId());
						if(null != wayBill ) {
							if(StringUtils.isNotBlank(wayBill.getWaybillNo())) {
								dod.setWaybillNo(wayBill.getWaybillNo());
							}
							dod.setDeliveryWay(wayBill.getDeliveryWay());
						}
					}
					if(logiMap.size() > 0) {
						OrderDetails id = delieMap.get(dod.getOrderId());
						if(null != id && id.getLogistId()!=null) {
							LogisticsDTO logiDTO = logiMap.get(id.getLogistId().intValue());
							if(null != logiDTO) {
								dod.setDeliveryComp(logiDTO.getLogistName());
							}
						}
					}
				}
			}
		}
		return queryResult;
	}

	/**
	 *@Title: getProvince
	 *@Description: 查询所有省
	 *@Return: List<ProvinceDTO> 省dto列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<ProvinceDTO> getProvince() throws Exception {
		return provinceDAO.queryAllProvince();
	}

	/**
	 *@Title: getCityList
	 *@Description: 根据省编码查询下属市
	 *@param parentCode 编码
	 *@Return: List<CityDTO> 市列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<CityDTO> getCityList(String parentCode) throws Exception {
		return provinceDAO.queryAllCityByCode(parentCode);
	}

	/**
	 *@Title: getAreaList
	 *@Description: 根据编码查询区域列表
	 *@param parentCode编码
	 *@Return: List<AreaBeanDTO> 市列表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<AreaBeanDTO> getAreaList(String parentCode) throws Exception {
		return provinceDAO.queryAllAreaByCode(parentCode);
	}
	
}
