package com.paySystem.ic.dao.base.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.base.DeliveryOrders;
import com.paySystem.ic.dao.base.DeliveryOrdersDAO;
import com.paySystem.ic.dao.common.DaoSupport;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;

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
	public List<DeliveryOrdersDTO> queryDeliveryOrdersList(List<String> orderIds)  throws Exception {
		List<DeliveryOrders> resultList = em.createQuery("from DeliveryOrders o where o.orderId in ("+getBatchInSQL(orderIds)+")").getResultList();
		return convert2DTO(resultList);
	}

	/**
	 *@Title: queryDeliveryOrders
	 *@Description: 根据条件来查询
	 *@Params:@param deliveryDTO 发货查询信息
	 *@Return:void
	 *@author: Jacky
	 *@Date:2014-8-20下午10:10:02
	 */
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
		
		QueryResult<DeliveryOrders> result =
			this.getScrollData((deliveryDTO.getPage()-1)*deliveryDTO.getPageNum(), 
					deliveryDTO.getPageNum().intValue(), sb.toString(), params.toArray(),deliveryDTO.getOrderBy());
		return convertDeliveryOrder(result);
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
				sb.append(orderId).append(",");
			}
			return sb.substring(0, sb.lastIndexOf(","));
		}
		return null;
	}
	
}
