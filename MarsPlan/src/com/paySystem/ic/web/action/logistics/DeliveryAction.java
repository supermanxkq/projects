package com.paySystem.ic.web.action.logistics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.base.DeliveryOrderService;
import com.paySystem.ic.service.base.LogisticsService;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.base.AreaBeanDTO;
import com.paySystem.ic.web.dto.base.CityDTO;
import com.paySystem.ic.web.dto.base.DeliveryOrdersDTO;
import com.paySystem.ic.web.dto.base.LogisticsDTO;
import com.paySystem.ic.web.dto.base.ProvinceDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**  
* @Title: LogisticsAction.java
* @Package: com.paySystem.ic.web.action.logistics
* @Description: 物流管理类
* @Author: Jacky
* @Date: 2014-10-08
* @Version: V1.0  
*/
@Controller("/base/delivery")
public class DeliveryAction extends BaseAction {
	private static final long serialVersionUID = 151416337290759697L;
	
	@Resource
	private LogisticsService logisticsService;
	
	@Resource
	private DeliveryOrderService deliveryOrderService;
	
	private DeliveryOrdersDTO deliveryDTO = new DeliveryOrdersDTO();
	
	public void setDeliveryDTO(DeliveryOrdersDTO deliveryDTO) {
		this.deliveryDTO = deliveryDTO;
	}
	
	public DeliveryOrdersDTO getDeliveryDTO() {
		return deliveryDTO;
	}

	/**
	 * 物流列表查询
	 *@Title:list
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String list() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		/** 设置排序规则 **/
		LinkedHashMap<String, String> orderby
        	= new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
		    && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else { 
			orderby.put("createTime", "desc"); 
		}
		deliveryDTO.setOrderBy(orderby);
		deliveryDTO.setPageNum(pageNum);
		/** 默认查询 未发货状态订单 **/
		if(null == deliveryDTO.getStatus()) {
			deliveryDTO.setStatus(0);
		}
		
		/** 设置active的tab**/
		this.getRequest().setAttribute("activeTab", deliveryDTO.getActiveTab());
		
		QueryResult<DeliveryOrdersDTO> queryResult = deliveryOrderService.getDelieryOrderPage(deliveryDTO);
		deliveryDTO.setTotalPage(queryResult.getTotalrecord()%deliveryDTO.getPageNum() > 0?queryResult.getTotalrecord()/deliveryDTO.getPageNum()+1:queryResult.getTotalrecord()/deliveryDTO.getPageNum());
		if(deliveryDTO.getStatus() == 0) {
			this.getRequest().setAttribute("deliveryList", queryResult);
		} else if(deliveryDTO.getStatus() == 1) {
			this.getRequest().setAttribute("deliveryingList", queryResult);
		} else if(deliveryDTO.getStatus() == 2) {
			this.getRequest().setAttribute("deliveryedList", queryResult);
		}
		return "list";
	}
	
	public String deliverOrderDetail() throws Exception {
		
		return "ddetail";
	}
	
	/**
	 * 发货详情页面
	 *@Title: deliverPage
	 *@Description: 发货详情页
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String deliverPage() throws Exception {
		
		/** 基本校验 **/
		if(StringUtils.isBlank(deliveryDTO.getOrderIdStr())) {
			this.getRequest().setAttribute("result", "参数不合法！");
			this.getRequest().setAttribute("url", "base/delivery!list");
			return ERROR;
		}
		String orders[] = deliveryDTO.getOrderIdStr().split(",");
		if(orders.length < 1) {
			this.getRequest().setAttribute("result", "参数不合法！");
			this.getRequest().setAttribute("url", "base/delivery!list");
			return ERROR;
		}
		/** 根据订单号查询出发货信息 和收货信息**/
		
		
		
		/** 省**/
		List<ProvinceDTO>  provList = deliveryOrderService.getProvince();
		this.getRequest().setAttribute("pList", provList);
		
		/** 物流公司列表 **/
		List<LogisticsDTO> logisticList = logisticsService.getLogisticsList();
		this.getRequest().setAttribute("logiList", logisticList);
		
		/** 根据订单查询发货信息**/
//		List<DeliveryOrdersDTO> orderList = deliveryOrderService.getDeliveryOrderList(deliveryDTO.getOrderIds());
//		this.getRequest().setAttribute("orderList", orderList);
		
		return "delieverp";
	}
	
	/**
	 * 异步联动市区
	 *@Title: ajaxLoadData
	 *@Description: 异步联动市区
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxLoadData() throws Exception {
		if(StringUtils.isNotBlank(deliveryDTO.getParentCode()) && deliveryDTO.getType() > 0) {
			if(deliveryDTO.getType() == 1) {
				List<CityDTO> cityList = deliveryOrderService.getCityList(deliveryDTO.getParentCode());
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("dataMap", cityList);
				Utils.printInfoWithoutType(resultMap);
			} else if(deliveryDTO.getType() == 2) {
				List<AreaBeanDTO> areaList = deliveryOrderService.getAreaList(deliveryDTO.getParentCode());
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("dataMap", areaList);
				Utils.printInfoWithoutType(resultMap);
			}
			
		}
		return null;
	}
}
