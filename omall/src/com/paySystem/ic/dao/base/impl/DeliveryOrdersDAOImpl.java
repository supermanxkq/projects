package com.paySystem.ic.dao.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.DeliveryOrders;
import com.paySystem.ic.bean.base.MerAddress;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.bean.order.Orders;
import com.paySystem.ic.dao.base.DeliveryOrdersDAO;
import com.paySystem.ic.dao.common.DaoSupport;
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
@Repository(DeliveryOrdersDAO.DELIVERYORDERSDAO)
public class DeliveryOrdersDAOImpl extends DaoSupport<DeliveryOrders>  implements DeliveryOrdersDAO {

	/**
	 *@Title: queryDeliveryOrdersList
	 *@Description: 根据订单来查询
	 *@Params:@param orderIds 商品订单列表
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public List<DeliveryOrdersDTO> queryDeliveryOrdersList(String merId,List<String> orderIds)  throws Exception {
		List<DeliveryOrders> resultList = em.createQuery("from DeliveryOrders o where o.orderId in ("+getBatchInSQL(orderIds)+") and o.merId=?").setParameter(1, merId).getResultList();
		return convert2DTO(resultList);
	}

	/**
	 *@Title: queryDeliveryOrdersList
	 *@Description: 根据订单来查询
	 *@Params:@param orderIds 商品订单列表
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> queryWayBillNoByOrderIds(List<String> orderIds)
			throws Exception {
		String sql = getBatchInSQL(orderIds);
		String str = "select t1.OrderId,t1.waybillNo from O_OrderDetails t1 where t1.OrderId in ("+sql+")";
		List<Object[]> result = em.createNativeQuery(str).getResultList();
		if(CollectionUtils.isNotEmpty(result)) {
			Map<String,String> resultMap = new HashMap<String,String>();
			for(Object[] objs : result) {
				resultMap.put(objs[0].toString(), objs[1]==null?null:objs[1].toString());
			}
			return resultMap;
		}
		return null;
	}
	
	/**
	 *@Title: queryOrdersById
	 *@Description: 根据商户id和订单号
	 *@Params:@param merId 商户id
	 *@Params:@param orderIds 订单id列表
	 *@Return: List<OrdersDTO> 订单列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public List<OrdersDTO> queryOrdersById(String merId, List<String> orderIds)
			throws Exception {
		String sql = this.getBatchInSQL(orderIds);
		String sb = "select * from O_Orders o where o.merId='"+merId+"' and o.orderId in ("+sql+")";
		List<Orders> orderList = em.createNativeQuery(sb,Orders.class).getResultList();
		if(CollectionUtils.isNotEmpty(orderList)) {
			List<OrdersDTO> orderDTOList = new ArrayList<OrdersDTO>();
			for(Orders o : orderList) {
				OrdersDTO od = new OrdersDTO();
				BeanUtils.copyProperties(o, od);
				orderDTOList.add(od);
			}
			return orderDTOList;
		}
		return null;
	}

	/**
	 *@Title: queryMerAddressDTO
	 *@Description: 查询商户地址DTO
	 *@Params: merId 商户id
	 *@Return: List<MerAddressDTO> 查询商户地址
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public List<MerAddressDTO> queryMerAddressDTO(String merId) throws Exception {
		List<MerAddress> resultList = em.createQuery("from MerAddress o where o.merId=? and (o.isSend=1 or o.isReturn=1 )").setParameter(1, merId).getResultList();
		List<MerAddressDTO> addreList = new ArrayList<MerAddressDTO>();
		if(CollectionUtils.isNotEmpty(resultList)) {
			for(MerAddress ma : resultList) {
				MerAddressDTO md = new MerAddressDTO();
				BeanUtils.copyProperties(ma, md);
				addreList.add(md);
			}
		}
		return addreList;
	}

	/**
	 *@Title: updasteOrderStatus
	 *@Description: 更新订单状态
	 *@Params:@param orderId 订单id
	 *@param status 订单状态
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	public Integer updasteOrderStatus( String orderId,Integer status)
			throws Exception {
		return em.createQuery("update Orders o set o.status=? where o.orderId=?").setParameter(1, status).setParameter(2, orderId).executeUpdate();
	}
	

	/**
	 *@Title: updateDeliveryInfo
	 *@Description: 更新发货信息
	 *@Params: deliverDTO 发货信息
	 *@Return: Integer 更新记录条数
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	public Integer updateDeliveryInfo(DeliveryOrdersDTO deliverDTO)
			throws Exception {
		return em.createQuery("update DeliveryOrders o set o.status=?,o.memName=?,o.memTele=?,o.address=?,o.merAddress=?,o.remarks=? where o.merId=? and o.orderId=?")
		.setParameter(1, deliverDTO.getStatus()).setParameter(2, deliverDTO.getMemName()).setParameter(3, deliverDTO.getMemTele())
		.setParameter(4, deliverDTO.getAddress()).setParameter(5, deliverDTO.getSendAddress()).setParameter(6, deliverDTO.getRemarks())
		.setParameter(7, deliverDTO.getMerId()).setParameter(8, deliverDTO.getOrderId()).executeUpdate();
	}

	/**
	 *@Title: updateWaybillNo
	 *@Description: 更新订单运单号
	 *@Params: orderId 订单id
	 *@param wayBillNo 运单单号
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	public Integer updateWaybillNo(String orderId, String wayBillNo)
			throws Exception {
		return em.createNativeQuery("update O_OrderDetails o set o.waybillNo=? where o.orderId=?").setParameter(1, wayBillNo).setParameter(2, orderId).executeUpdate();
	}

	/**
	 *@Title: updateOrderDetailAddress
	 *@Description: 更新发货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Override
	public Integer updateOrderDetailAddress(List<String> orderList,
			String address) throws Exception {
		if(CollectionUtils.isEmpty(orderList)) return 0;
		int count = em.createQuery("update OrderDetails o set o.sendAddress = ? where o.orderId in (:orderList)").setParameter(1, address)
			.setParameter("orderList", orderList).executeUpdate();
		if(count > 0) {
			return em.createQuery("update DeliveryOrders o set o.merAddress = ? where o.orderId in (:orderList)")
				.setParameter(1, address).setParameter("orderList", orderList).executeUpdate();
		} else {
			return 0;
		}
	}
	
	/**
	 *@Title: updateOrderReturnAddress
	 *@Description: 更新退货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Override
	public Integer updateOrderReturnAddress(List<String> orderList,
			String address) throws Exception {
		if(CollectionUtils.isEmpty(orderList)) return 0;
		return em.createQuery("update OrderDetails o set o.returnAddress = ? where o.orderId in (:orderList)").setParameter(1, address)
		.setParameter("orderList", orderList).executeUpdate();
	}
	
	/**
	 *@Title: updateOrderReceiveAddress
	 *@Description: 更新收货地址
	 *@param orderIdList 订单号列表
	 *@param address 发货地址
	 *@Return: boolean true表示成功 false表示失败
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	@Override
	public Integer updateOrderReceiveAddress(String orderId, String address,
			String memName, String memTele) throws Exception {
		int count = em.createQuery("update DeliveryOrders o set o.memName = ? ,o.memTele=? ,o.address=? where o.orderId=?").setParameter(1, memName)
		.setParameter(2, memTele).setParameter(3, address).setParameter(4, orderId).executeUpdate();
		if(count > 0) {
			return em.createQuery("update OrderDetails o set o.address = ? where o.orderId = ?")
				.setParameter(1, address).setParameter(2, orderId).executeUpdate();
		} else {
			return 0;
		}
	}

	/**
	 *@Title: updateOrderDetail
	 *@Description: 更新订单明细表
	 *@Params: delieryDTO 发货dto
	 *@Return: Integer 更新结果
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	public Integer updateOrderDetail(DeliveryOrdersDTO delieryDTO)
			throws Exception {
		return em.createQuery("update OrderDetails o set o.logistId=?,o.waybillNo=?,o.deliveryWay=?,o.address=?,o.memTele=?,o.sendAddress=?,o.returnAddress=? where o.orderId=? ")
		.setParameter(1, delieryDTO.getLogiId()).setParameter(2, delieryDTO.getWaybillNo()).setParameter(3, delieryDTO.getDeliveryWay())
		.setParameter(4, delieryDTO.getAddress()).setParameter(5, delieryDTO.getMemTele()).setParameter(6, delieryDTO.getSendAddress())
		.setParameter(7, delieryDTO.getReturnAddress()).setParameter(8, delieryDTO.getOrderId()).executeUpdate();
	}

	/**
	 *@Title: updateDeliveryStatus
	 *@Description: 更新发货信息的状态
	 *@Params: orderId 订单id
	 *@Params: status 发货信息状态
	 *@Return: Integer 更新记录条数
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	public Integer updateDeliveryStatus(String orderId, Integer status)
			throws Exception {
		return em.createNativeQuery("update O_DeliveryOrders o set o.status=? where o.orderId=?").setParameter(1, status).setParameter(2, orderId).executeUpdate();
	}

	/**
	 *@Title: queryOrderDetail
	 *@Description: 查询订单明细
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public OrderDetails queryOrderDetail(String orderId) throws Exception {
		List<OrderDetails> od = em.createQuery("from OrderDetails o where o.orderId=?").setParameter(1, orderId).getResultList();
		if(CollectionUtils.isNotEmpty(od)) {
			return od.get(0);
		}
		return null;
	}

	/**
	 *@Title: queryOrderDetailsList
	 *@Description: 查询订单详情
	 *@Params: merId 商户id
	 *@param: List<String> orderIds 订单id
	 *@Return: List<OrderDetails>  订单明细列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public List<OrderDetails> queryOrderDetailsList(String merId,
			List<String> orderIds) throws Exception {
		return em.createQuery("from OrderDetails o where o.orderId in (:orderList)").setParameter("orderList", orderIds).getResultList();
	}

	/**
	 *@Title: queryDeliveryOrders
	 *@Description: 根据条件来查询
	 *@Params:@param deliveryDTO 发货查询信息
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-8-20下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<DeliveryOrdersDTO> queryDeliveryOrders (
			DeliveryOrdersDTO deliveryDTO)  throws Exception {
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		if(StringUtils.isNotBlank(deliveryDTO.getMemName())) {
			sb.append(" and o.memName like ?").append(params.size()+1);
			params.add("%"+deliveryDTO.getMemName().trim()+"%");
		}
		if(StringUtils.isNotBlank(deliveryDTO.getMemId())) {
			sb.append(" and o.memId like ?").append(params.size()+1);
			params.add("%"+deliveryDTO.getMemId().trim()+"%");
		}
		if(StringUtils.isNotBlank(deliveryDTO.getOrderId())) {
			sb.append(" and o.orderId like ?").append(params.size()+1);
			params.add("%"+deliveryDTO.getOrderId().trim()+"%");
		}
		if(null != deliveryDTO.getStartTime()) {
			sb.append(" and o.createTime >= ?").append(params.size()+1);
			params.add(deliveryDTO.getStartTime());
		}
		if(null != deliveryDTO.getEndTime()) {
			sb.append(" and o.createTime <= ?").append(params.size()+1);
			params.add(deliveryDTO.getEndTime());
		}
		if(null != deliveryDTO.getStatus()) {
			sb.append(" and o.status= ?").append(params.size()+1);
			params.add(deliveryDTO.getStatus());
		}
		if(null != deliveryDTO.getMerId()) {
			sb.append(" and o.merId= ?").append(params.size()+1);
			params.add(deliveryDTO.getMerId());
		}
		
		QueryResult<DeliveryOrders> qr = new QueryResult<DeliveryOrders>();

		Query query = em.createNativeQuery("select o.* from O_DeliveryOrders o , O_Orders o2 where o2.orderId=o.orderId and (o2.isClose=0 or o2.isClose is null) and o2.status !=7 "
				+sb.toString() + buildOrderby(deliveryDTO.getOrderBy()),DeliveryOrders.class);
		setQueryParams(query, params.toArray());
		/** 获取所有的记录 **/
		if ((deliveryDTO.getPage()-1)*deliveryDTO.getPageNum() != -1 && deliveryDTO.getPageNum().intValue() != -1) {
			query.setFirstResult((deliveryDTO.getPage()-1)*deliveryDTO.getPageNum()).setMaxResults(deliveryDTO.getPageNum().intValue());
		}
		qr.setResultlist(query.getResultList());

		query = em.createNativeQuery("select count(1) from O_DeliveryOrders o , O_Orders o2 where o2.orderId=o.orderId and (o2.isClose=0 or o2.isClose is null) and o2.status !=7 "
				+sb.toString() + buildOrderby(deliveryDTO.getOrderBy()));
		setQueryParams(query, params.toArray());
		qr.setTotalrecord(Long.valueOf(query.getSingleResult().toString()));
		
		return convertDeliveryOrder(qr);
	}

	/**
	 *@Title: convertDeliveryOrder
	 *@Description: 由bean转dto
	 *@Params:@param QueryResult<DeliveryOrders> result 查询出来的结果
	 *@Return: QueryResult<DeliveryOrdersDTO> 结果集
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	private QueryResult<DeliveryOrdersDTO> convertDeliveryOrder(QueryResult<DeliveryOrders> result) {
		QueryResult<DeliveryOrdersDTO> newResult = new QueryResult<DeliveryOrdersDTO>();
		if(null != result && result.getTotalrecord() > 0) {
			newResult.setTotalrecord(result.getTotalrecord());
			List<DeliveryOrders> deliveryList = result.getResultlist();
			if(CollectionUtils.isNotEmpty(deliveryList)) {
				newResult.setResultlist(this.convert2DTO(result.getResultlist()));
			}
		}
		return newResult;
	}

	/**
	 *@Title: convert2DTO
	 *@Description: 获得DTO 的转换结果
	 *@Params:@param resultList 发货list
	 *@Return: List<DeliveryOrdersDTO> 结果集
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<DeliveryOrdersDTO> convert2DTO(List<DeliveryOrders> resultList) {
		if(CollectionUtils.isNotEmpty(resultList)) {
			List<DeliveryOrdersDTO> deliveryList = new ArrayList<DeliveryOrdersDTO>();
			for(DeliveryOrders deliveryO : resultList) {
				DeliveryOrdersDTO dto = new DeliveryOrdersDTO();
				BeanUtils.copyProperties(deliveryO, dto);
				deliveryList.add(dto);
			}
			return deliveryList;
		}
		return null;
	}
	
	/**
	 *@Title: getBatchInSQL
	 *@Description: 获得用逗号分开的字符串，sql查询使用
	 *@Params:@param orderIds 商品订单列表
	 *@Return: String 字符串
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	private String getBatchInSQL(List<String> orderIds) {
		if(CollectionUtils.isNotEmpty(orderIds)) {
			StringBuilder sb = new StringBuilder();
			for(String orderId : orderIds) {
				sb.append("'"+orderId+"'").append(",");
			}
			return sb.substring(0, sb.lastIndexOf(","));
		}
		return null;
	}

	/**
	 *@Title: queryOrderGoodPic
	 *@Description: 根据订单号查询商品图片
	 *@Params: List<String> orderIds 订单ID
	 *@Return: List<GoodsPhotoDTO>  商品图片列表
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsPhotoDTO> queryOrderGoodPic(List<String> orderIds)
			throws Exception {
		List<Object[]> objList = em.createNativeQuery("select o.orderId,b.goodsId,b.goodsLittPho from " +
				"O_OrderGoodsRel o,B_GoodsPhoto b where o.orderId in (:orderIds) and b.goodsStyle=1 and o.goodsId=b.goodsId order by b.photoId asc").setParameter("orderIds", orderIds).getResultList();
		List<GoodsPhotoDTO> gpList = new ArrayList<GoodsPhotoDTO>();
		if(CollectionUtils.isNotEmpty(objList)) {
			for(Object[] obj : objList) {
				GoodsPhotoDTO gp = new GoodsPhotoDTO();
				gp.setOrderId(obj[0].toString());
				gp.setGoodsId(Long.valueOf(obj[1].toString()));
				gp.setGoodsLittPho(null == obj[2]?"":obj[2].toString());
				gpList.add(gp);
			}
		}
		return gpList;
	}
	
}
