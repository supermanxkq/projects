package com.paySystem.ic.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.dao.base.DeliveryOrdersDAO;
import com.paySystem.ic.dao.base.ProvinceDAO;
import com.paySystem.ic.service.base.DeliveryOrderService;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;

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
	
	/**
	 *@Title: getDeliveryOrderList
	 *@Description: 根据订单号来查询发货订单
	 *@Params:@param orderIds 商品订单列表id
	 *@Return: List<DeliveryOrdersDTO> 发货相关信息表
	 *@author: Jacky
	 *@Date:2014-10-10下午10:10:02
	 */
	public List<DeliveryOrdersDTO> getDeliveryOrderList(List<String> orderIds)  throws Exception {
		return deliveryOrdersDAO.queryDeliveryOrdersList(orderIds);
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
		return deliveryOrdersDAO.queryDeliveryOrders(deliveryDTO);
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
