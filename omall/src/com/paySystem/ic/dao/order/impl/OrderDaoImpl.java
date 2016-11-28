package com.paySystem.ic.dao.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.DeliveryOrders;
import com.paySystem.ic.bean.order.OrderDetails;
import com.paySystem.ic.bean.order.OrderGoodsRel;
import com.paySystem.ic.bean.order.Orders;
import com.paySystem.ic.bean.order.OrdersRec;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.dao.order.OrdersDAO;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.order.OrdersDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**
 * 活动管理Dao实现类
 * 
 * @ClassName:PromotionDaoImpl
 * @Description:活动管理Dao实现类
 * @date: 2014-8-21上午10:49:02
 * @author: 赵瑞群
 * @version: V1.0
 */
@Repository(OrdersDAO.ORDERSDAO)
public class OrderDaoImpl extends DaoSupport<Orders> implements OrdersDAO {

	
	
	


	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.buss.PromotionDao#queryApplyRecordByCond(int, int, com.paySystem.ic.web.dto.buss.MerApplyRecordDTO, java.util.LinkedHashMap)
	 *@MethodName:queryApplyRecordByCond
	 *@Description:获取订单列表
	 *@param firstPage
	 *@param pageNum
	 *@param merApplyRecordDTO
	 *@param orderBy
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-8-25下午9:42:30
	 */
	
	@Override
	public QueryResult<OrdersDTO> queryOrderList(OrdersDTO ordersDTO) throws Exception {
		String goodsName = ordersDTO.getGoodsName();
		String memName = ordersDTO.getMemName();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3);
		Date threeMonthAgo = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		String me = ordersDTO.getStartTime();
		if(StringUtils.isNotBlank(ordersDTO.getStartTime())){
			startTime = sdf.parse(ordersDTO.getStartTime());
		}
		
		if(StringUtils.isNotBlank(ordersDTO.getEndTime())){
			
			endTime = sdf.parse(ordersDTO.getEndTime());
			}
		Integer actionTab = ordersDTO.getActiveTab();
		
		Integer criticalStatus = ordersDTO.getCriticalStatus();
		Integer orderStatus = ordersDTO.getStatus();
		Integer isClose = ordersDTO.getIsClose();
		String merId = ordersDTO.getMerId();
		String orderId = ordersDTO.getOrderId();
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>(); 
		if(StringUtils.isNotBlank(goodsName)) {
			sb.append(" and o.goodsName like ?").append(params.size()+1);
			params.add("%"+goodsName.trim()+"%");
		}
		if(StringUtils.isNotBlank(merId)) {
			sb.append(" and o.merId = ?").append(params.size()+1);
			params.add(merId);
		}
		if(StringUtils.isNotBlank(memName)) {
			sb.append(" and o.memName like ?").append(params.size()+1);
			params.add("%"+memName.trim()+"%");
		}
		if(StringUtils.isNotBlank(orderId)) {
			sb.append(" and o.orderId like ?").append(params.size()+1);
			params.add("%"+orderId.trim()+"%");
		}
		if(null != startTime) {
			sb.append(" and o.orderTime >= ?").append(params.size()+1);
			params.add(startTime);
		}
		if(null != endTime) {
			sb.append(" and o.orderTime <= ?").append(params.size()+1);
			params.add(endTime);
		}
		if(null != orderStatus&&orderStatus!=-1&&actionTab!=6) {
			sb.append(" and o.status= ?").append(params.size()+1);
			params.add(orderStatus);
		}
		
		if(null != actionTab&&actionTab==4) {
			sb.append(" and o.criticalStatus= 0");
		}
		if(null != actionTab&&actionTab==6) {
			sb.append(" and o.isClose= 1");
		}
		if(null != criticalStatus&&criticalStatus!=-1) {
			sb.append(" and o.criticalStatus= ?").append(params.size()+1);
			params.add(criticalStatus);
		}
		if(null != isClose&&actionTab!=6) {
			sb.append(" and o.isClose= ?").append(params.size()+1);
			params.add(isClose);
		}
		
		if(null != actionTab&&actionTab!=7) {
			sb.append(" and o.orderTime >= ?").append(params.size()+1);
			params.add(threeMonthAgo);
		}else if(null != actionTab&&actionTab==7){
			sb.append(" and o.orderTime <= ?").append(params.size()+1);
			params.add(threeMonthAgo);
		}
		if(ordersDTO.getExport()!=null&&ordersDTO.getExport()==1){
			QueryResult<Orders> result =
					this.getScrollData(-1,-1, sb.toString(), params.toArray(),ordersDTO.getOrderBy());
			return convertOrder(result);
		}else{
			QueryResult<Orders> result =
				this.getScrollData((ordersDTO.getPage()-1)*ordersDTO.getPageNum(), 
						ordersDTO.getPageNum().intValue(), sb.toString(), params.toArray(),ordersDTO.getOrderBy());
			return convertOrder(result);
		}
		
	}

	/**
	 *@Title: convertDeliveryOrder
	 *@Description: 由bean转dto
	 *@Params:@param QueryResult<DeliveryOrders> result 查询出来的结果
	 *@Return: QueryResult<DeliveryOrdersDTO> 结果集
	 *@Author:luckygroup
	 *@Date:2014-10-10下午10:10:02
	 */
	private QueryResult<OrdersDTO> convertOrder(QueryResult<Orders> result) throws Exception {
		QueryResult<OrdersDTO> newResult = new QueryResult<OrdersDTO>();
		if(null != result && result.getTotalrecord() > 0) {
			newResult.setTotalrecord(result.getTotalrecord());
			List<Orders> orderList = result.getResultlist();
			if(CollectionUtils.isNotEmpty(orderList)) {
				newResult.setResultlist(this.convert2DTO(result.getResultlist()));
			}
		}
		return newResult;
	}

	/**
	 *@Title: convert2DTO
	 *@Description: 获得DTO 的转换结果
	 *@Params:@param resultList 发货list
	 *@Return: List<OrdersDTO> 结果集
	 *@Author:luckygroup
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<OrdersDTO> convert2DTO(List<Orders> resultList) throws Exception {
		if(CollectionUtils.isNotEmpty(resultList)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<OrdersDTO> orderList = new ArrayList<OrdersDTO>();
			for(Orders order : resultList) {
				OrdersDTO dto = new OrdersDTO();
				OrderDetails orderDetails= this.queryOrderDetail(order.getOrderId());
				BeanUtils.copyProperties(order, dto);
				dto.setDeliveryWay(null != orderDetails ? orderDetails.getDeliveryWay():"1");
				orderList.add(dto);
			}
			return orderList;
		}
		return null;
	}

	/**
	 * 
	*@Title:openRecruit
	*@Description:关闭订单
	*@Params:@param orderId
	*@Params:@return
	*@Params:@throws Exception
	*@Return:OrdersDTO
	*@author:luckygroup
	*@Date:2014-10-18下午11:04:16
	 */
	@Override
	public OrdersDTO CloseOrder(String orderId) throws Exception {
		/**根据Id获取订单实体对象*/
		Orders order = this.find(orderId);
		
		order.setIsClose(1);
		
		/**更新修改内容*/
		this.update(order);
		OrdersDTO ordersDTO = new OrdersDTO();
		BeanUtils.copyProperties(order,ordersDTO);
		
		return ordersDTO;
	}
	
	/**
	 *   免运费
	 *@Title:passAudit
	 *@Description:
	 *@param:@param recordIds 订单号字符串
	 *@Return:void
	 *@author: 赵瑞群
	 *@Thorws:
	 */
	@Override
	public void freeFreight(String orderIds) throws Exception {
		String[] orderId = orderIds.split(",");
		for(int i=0;i<orderId.length;i++){
			Orders order = this.find(orderId[i]);
			BigDecimal paidAmt = order.getPaidAmt();
			Double paidAmtDou = paidAmt.doubleValue() - order.getPostAmt().doubleValue();
			order.setPaidAmt(BigDecimal.valueOf(paidAmtDou));
			order.setPostAmt(BigDecimal.valueOf(0));
			
			this.update(order);
		}
		
	}
	
	/**
	 *@Title: queryOrderDetail
	 *@Description: 查询订单明细
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: luckygroup
	 *@Date:2014-10-15下午10:10:02
	 */
	@Override
	@SuppressWarnings("unchecked")
	public OrderDetails queryOrderDetail(String orderId) throws Exception {
		List<OrderDetails> od = em.createQuery("from OrderDetails o where o.orderId=?").setParameter(1, orderId).getResultList();
		if(CollectionUtils.isNotEmpty(od)) {
			return od.get(0);
			
		}
		return null;
	}
	
	/**
	 *@Title: queryOrderDetail
	 *@Description: 查询订单
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: luckygroup
	 *@Date:2014-10-15下午10:10:02
	 */
	@Override
	@SuppressWarnings("unchecked")
	public OrdersDTO queryOrder(String orderId) throws Exception {
		List<Orders> od = em.createQuery("from Orders o where o.orderId=?").setParameter(1, orderId).getResultList();
		if(CollectionUtils.isNotEmpty(od)) {
			OrdersDTO ordersDTO = new OrdersDTO();
			Orders order = od.get(0);
			BeanUtils.copyProperties(order,ordersDTO);
			return ordersDTO;
		}
		return null;
	}
	
	/**
	 *@Title: queryOrderDetail
	 *@Description: 修改订单价格
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: luckygroup
	 *@Date:2014-10-15下午10:10:02
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void updatePrice(OrdersDTO ordersDTO) throws Exception {
		Orders order = this.find(ordersDTO.getOrderId());
		OrderDetails orderDetails = this.queryOrderDetail(ordersDTO.getOrderId());
		UserSession us = Utils.getUserSession();
		if(ordersDTO.getPostAmt()!=null) {
			order.setPostAmt(ordersDTO.getPostAmt());
			orderDetails.setPostAmt(ordersDTO.getPostAmt());
		}
		if(ordersDTO.getPaidAmt()!=null) {
			order.setPaidAmt(ordersDTO.getPaidAmt());
			orderDetails.setPaidAmt(ordersDTO.getPaidAmt());
		}
		if(ordersDTO.getStatus()==5) {
			order.setStatus(5);
			OrdersRec orderRec =new OrdersRec();
			orderRec.setCreateTime(order.getOrderTime());
			orderRec.setOperator(us.getMerId());
			orderRec.setOperTime(new Date());
			orderRec.setOrderId(order.getOrderId());
			orderRec.setPayStatus(orderDetails.getPayStatus());
			orderRec.setStatus(5);
			this.save(orderRec);
		}
		this.update(order);
		this.update(orderDetails);
	}
	
	/**
	 *@Title: queryOrderDetail
	 *@Description: 查询订单明细
	 *@Params: orderId 订单id
	 *@Return: OrderDetails 订单明细
	 *@author: Jacky
	 *@Date:2014-10-15下午10:10:02
	 */
	@Override
	@SuppressWarnings("unchecked")
	public DeliveryOrders queryDeliveryOrder(String orderId) throws Exception {
		List<DeliveryOrders> od = em.createQuery("from DeliveryOrders o where o.orderId=?").setParameter(1, orderId).getResultList();
		if(CollectionUtils.isNotEmpty(od)) {
			return od.get(0);
		}
		return null;
	}
	
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
	
	@Override
	@SuppressWarnings("unchecked")
	public void extendTakeOverTime(OrdersDTO ordersDTO) throws Exception {
		Orders order = this.find(ordersDTO.getOrderId());
		if(ordersDTO.getExtendDays()>=0) {
			order.setExtendDays(ordersDTO.getExtendDays());
		}
		
		this.update(order);
	}

	/**
	 *@OverRiddenMethod：@see com.paySystem.ic.dao.order.OrdersDAO#queryGoodsByOrderId(java.lang.String)
	 *@MethodName:queryGoodsByOrderId
	 *@Description:通过订单号获取相应商品
	 *@param orderId
	 *@return
	 *@throws Exception
	 *@Author:luckygroup
	 *@Date:2014-11-21上午12:50:54
	 */
	
	@Override
	public ArrayList queryGoodsByOrderId(String orderId) throws Exception {
		List<OrderGoodsRel> orderGoodList = em.createQuery("from OrderGoodsRel o where o.orderId=?").setParameter(1, orderId).getResultList();
		return (ArrayList)orderGoodList;
	}

}
