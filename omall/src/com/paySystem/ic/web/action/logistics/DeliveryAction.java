package com.paySystem.ic.web.action.logistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
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
import com.paySystem.ic.web.dto.base.MerAddressDTO;
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
@Scope("prototype")
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
		deliveryDTO.setMerId(us.getMerId());
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
		if(deliveryDTO.getStatus() == 0 || deliveryDTO.getStatus() == 3) {
			this.getRequest().setAttribute("deliveryList", queryResult);
		} else if(deliveryDTO.getStatus() == 1) {
			this.getRequest().setAttribute("deliveryingList", queryResult);
		} else if(deliveryDTO.getStatus() == 2) {
			this.getRequest().setAttribute("deliveryedList", queryResult);
		}
		return "list";
	}
	
	/**
	 * 发货页面详情
	 *@Title: deliverOrderDetail
	 *@Description: 发货页面详情
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String deliverOrderDetail() throws Exception {
		String orderId = deliveryDTO.getOrderId();
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		if(StringUtils.isBlank(orderId) || null == us) {
			return ERROR;
		}
		DeliveryOrdersDTO deliveryOrdersDTO = deliveryOrderService.getDeliveryDetailInfo(orderId, us.getMerId());
		this.getRequest().setAttribute("deliveryOrdersDTO", deliveryOrdersDTO);
		if(this.getRequest().getParameter("close")!=null){
			this.getRequest().setAttribute("close",this.getRequest().getParameter("close"));
		}
		
		
		return "ddetail";
	}
	
	/**
	 * 取消订单
	 *@Title: closeOrderAjax
	 *@Description: 取消订单
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String closeOrderAjax() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String orderId = deliveryDTO.getOrderId();
		if(StringUtils.isBlank(orderId) || null == us) {
			return ERROR;
		}
		
		boolean result = false;
		try {
			result = deliveryOrderService.closeOrder(orderId, us.getMerId());
		} catch(Exception e) {
			result = false;
		}
		Map<String,Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("success", result);
		Utils.printInfoWithoutType(resultMap);
		return null;
	}
	
	/**
	 * 修改运单号
	 *@Title: ajaxModifyWayBillNo
	 *@Description: 修改运单号
	 *@param:@throws Exception
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxModifyWayBillNo() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String orderId = deliveryDTO.getOrderId();
		String wayBillNo = deliveryDTO.getWaybillNo();
		if(StringUtils.isBlank(orderId) || null == us || StringUtils.isBlank(wayBillNo)) {
			return ERROR;
		}
		Map<String,Boolean> resultMap = new HashMap<String, Boolean>();
		try {
			boolean result = deliveryOrderService.updateWayBillNo(orderId, us.getMerId(), wayBillNo);
			resultMap.put("success", result);
		} catch(Exception e) {
			resultMap.put("success", false);
		}
		Utils.printInfoWithoutType(resultMap);
		return null;
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
		
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		
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
		String sendAd = null;
		String returnAd = null;
		if(orders.length == 1) {
			/** 根据订单查询收发货地址先**/
			String [] addes = deliveryOrderService.queryOrderAddresses(orders[0]);
			if(null != addes) {
				returnAd = addes[0];
				sendAd = addes[1];
			}
		}
		this.getRequest().setAttribute("sendAddressOrgin", sendAd);
		this.getRequest().setAttribute("returnAddressOrgin", returnAd);
		Map<String, MerAddressDTO> merMap = null;
		if(StringUtils.isBlank(sendAd) || StringUtils.isBlank(returnAd)) {
			/** 根据订单号查询出发货信息 和收货信息**/
			merMap = deliveryOrderService.queryMerAddressDTOMap(us.getMerId());
			this.getRequest().setAttribute("sendAddress", merMap.get("send"));
			this.getRequest().setAttribute("returnAddress", merMap.get("return"));
		}
		
		/** 物流公司列表 **/
		List<LogisticsDTO> logisticList = logisticsService.getLogisticsList();
		this.getRequest().setAttribute("logiList", logisticList);
		
		this.getRequest().setAttribute("orderStr", deliveryDTO.getOrderIdStr());
		
		/** 根据订单查询发货信息**/
		List<DeliveryOrdersDTO> orderList = deliveryOrderService.getDeliveryOrderList(us.getMerId(),Arrays.asList(orders));
		this.getRequest().setAttribute("orderList", orderList);
		if(StringUtils.isNotBlank(this.getRequest().getParameter("returnUrl"))) {
			this.getRequest().setAttribute("returnUrl", this.getRequest().getParameter("returnUrl"));
		} else {
			this.getRequest().setAttribute("returnUrl","base/delivery!list");
		}
		
		if(this.getRequest().getParameter("close")!=null){
			this.getRequest().setAttribute("close",this.getRequest().getParameter("close"));
		}
		
		
		return "delieverp";
	}
	
	/**
	 * 异步拉取省
	 *@Title: getProvinceAjax
	 *@Description: 异步拉取省
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String getProvinceAjax() throws Exception {
		/** 省**/
		List<ProvinceDTO>  provList = deliveryOrderService.getProvince();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("pList", provList);
		Utils.printInfoWithoutType(resultMap);
		return null;
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
	
	/**
	 * 异步修改地址
	 *@Description: 异步修改地址
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxModifyAddress() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String address = deliveryDTO.getAddress();
		
		Map<String,Boolean> resultMap = new HashMap<String, Boolean>();
		if( deliveryDTO.getType() == 1) {
			/** 基本校验 **/
			if(StringUtils.isBlank(deliveryDTO.getOrderIdStr())) {
				return ERROR;
			}
			String orders[] = deliveryDTO.getOrderIdStr().split(",");
			if(orders.length < 1) {
				return ERROR;
			}
			boolean result = deliveryOrderService.updateSendAddress(Arrays.asList(orders), address);
			resultMap.put("success", result);
			Utils.printInfoWithoutType(resultMap);
		} else if(deliveryDTO.getType() == 2) {
			/** 基本校验 **/
			if(StringUtils.isBlank(deliveryDTO.getOrderIdStr())) {
				return ERROR;
			}
			String orders[] = deliveryDTO.getOrderIdStr().split(",");
			if(orders.length < 1) {
				return ERROR;
			}
			boolean result = deliveryOrderService.updateReturnAddress(Arrays.asList(orders), address);
			resultMap.put("success", result);
			Utils.printInfoWithoutType(resultMap);
		} else if(deliveryDTO.getType() == 3) {
			String memName = deliveryDTO.getMemName();
			String memTele = deliveryDTO.getMemTele();
			String orderId = deliveryDTO.getOrderId();
			if(StringUtils.isBlank(memName) || StringUtils.isBlank(memTele) || StringUtils.isBlank(orderId)) {
				resultMap.put("success", false);
				Utils.printInfoWithoutType(resultMap);
				return null;
			}
			boolean result = deliveryOrderService.updateReceiveAddress(orderId, address, memName, memTele);
			resultMap.put("success", result);
			Utils.printInfoWithoutType(resultMap);
		}
		
		return null;
	}
	
	/**
	 * 异步确认发货
	 *@Title: ajaxSendGoods
	 *@Description: 确认发货
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxSendGoods() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String orderId = deliveryDTO.getOrderId();
		String wayBillNo = deliveryDTO.getWaybillNo();
		Map<String,Boolean> resultMap = new HashMap<String, Boolean>();
		try {
			boolean result = deliveryOrderService.confirmSendGoods(orderId, us.getMerId(), wayBillNo);
			resultMap.put("success", result);
		} catch(Exception e) {
			resultMap.put("success", false);
		}
		Utils.printInfoWithoutType(resultMap);
		return null;
	}
	
	/**
	 * 批量确认发货
	 *@Title: batchConfirmSend
	 *@Description: 批量确认发货
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String batchConfirmSend() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String orderId[] = this.getRequest().getParameterValues("orderId");
		String address[] = this.getRequest().getParameterValues("deliveryList.address");
		String sendAddress  = this.getRequest().getParameter("deliveryList.sendAddress");
		String returnAddress  = this.getRequest().getParameter("deliveryList.returnAddress");
		String remarks[] = this.getRequest().getParameterValues("deliveryList.remarks");
		String memTele[] = this.getRequest().getParameterValues("deliveryList.memTele");
		String memName[] = this.getRequest().getParameterValues("deliveryList.memName");
		String wayBillNo[] = this.getRequest().getParameterValues("deliveryList.waybillNo");
		String deliveryWay = deliveryDTO.getDeliveryWay();
		Integer logiId = deliveryDTO.getLogiId();
		if(orderId.length == address.length && address.length ==remarks.length && remarks.length == memTele.length &&
				memTele.length == memName.length && wayBillNo.length ==memName.length &&  StringUtils.isNotBlank(deliveryWay) 
				&& StringUtils.isNotBlank(sendAddress) && StringUtils.isNotBlank(returnAddress)) {
			List<DeliveryOrdersDTO> dodList = assembleDeliveryOrdersDTOList(orderId,address ,  sendAddress,  returnAddress,
					  remarks ,  memTele ,  memName ,  deliveryWay,  logiId,us.getMerId(),wayBillNo);
			
			boolean result = deliveryOrderService.batchSendGoods(dodList);
			
			if(result) {
				this.getRequest().setAttribute("result", dodList.size()>1?"批量发货成功!":"发货成功！");
				/** 目的是为了防止表单自动拉取数据 **/
				deliveryDTO = new DeliveryOrdersDTO();
				this.getRequest().setAttribute("url", StringUtils.isBlank(this.getRequest().getParameter("returnUrl"))?"base/delivery!list":this.getRequest().getParameter("returnUrl").trim());
			
				if(this.getRequest().getParameter("close")!=null&&this.getRequest().getParameter("close").equals("1")){
					return "successclose";
				}else{
					return SUCCESS;
				}
				
			} else {
				this.getRequest().setAttribute("result", dodList.size()>1?"批量发货失败!":"发货失败！");
				/** 目的是为了防止表单自动拉取数据 **/
				deliveryDTO = new DeliveryOrdersDTO();
				this.getRequest().setAttribute("url", StringUtils.isBlank(this.getRequest().getParameter("returnUrl"))?"base/delivery!list":this.getRequest().getParameter("returnUrl").trim());
				return ERROR;
			}
		}
		this.getRequest().setAttribute("result", "批量发货失败!");
		/** 目的是为了防止表单自动拉取数据 **/
		deliveryDTO = new DeliveryOrdersDTO();
		this.getRequest().setAttribute("url", StringUtils.isBlank(this.getRequest().getParameter("returnUrl"))?"base/delivery!list":this.getRequest().getParameter("returnUrl").trim());
		return ERROR;
		
	}
	
	/**
	 * 组装list对象
	 *@Title: assembleDeliveryOrdersDTOList
	 *@Description: 组装list对象
	 *@param: orderId[]是 订单列表
	 *@param: address是收货地址
	 *@param: sendaddress是发货地址 
	 *@param: returnaddress是退货地址
	 *@param: remarks是备注
	 *@param: memTele是收货人电话
	 *@param: memName收货人姓名
	 *@param: deliveryway是发货方式
	 *@param: logiId是物流id
	 *@param: wayBillNo是物流单号
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	private List<DeliveryOrdersDTO> assembleDeliveryOrdersDTOList(String orderId[],String address[],String sendAddress,String returnAddress,
			String remarks[],String memTele[],String memName[],String deliveryWay,Integer logiId,String merId,String wayBillNo[]) {
		List<DeliveryOrdersDTO> list = new ArrayList<DeliveryOrdersDTO>();
		for(int i=0; i < orderId.length; i++) {
			DeliveryOrdersDTO dod = new DeliveryOrdersDTO();
			dod.setOrderId(orderId[i]);
			if(deliveryWay.equals("1")) {
				dod.setAddress(address[i]);
				dod.setSendAddress(sendAddress);
				dod.setReturnAddress(returnAddress);
				dod.setWaybillNo(wayBillNo[i]);
				dod.setMemTele(memTele[i]);
				dod.setMemName(memName[i]);
				dod.setLogiId(logiId);
			} else if(deliveryWay.equals("0")) {
				dod.setAddress("");
				dod.setSendAddress("");
				dod.setReturnAddress("");
				dod.setWaybillNo(null);
				dod.setMemTele("");
				dod.setMemName("");
				dod.setLogiId(null);
			} else {
				throw new RuntimeException("参数错误！");
			}
			dod.setRemarks(remarks[i]);
			dod.setMerId(merId);
			dod.setDeliveryWay(deliveryWay);
			list.add(dod);
		}
		return list;
	}
	
	/**
	 * 单独发货
	 *@Title: ajaxSendSingle
	 *@Description: 确认发货
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxSendSingle() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		deliveryDTO.setMerId(us.getMerId());
		String orderId = deliveryDTO.getOrderId();
		String address = deliveryDTO.getAddress();
		String sendAddress = deliveryDTO.getSendAddress();
		String returnAddress = deliveryDTO.getReturnAddress();
		String deliveryWay = deliveryDTO.getDeliveryWay();
		String memTele = deliveryDTO.getMemTele();
		String memName = deliveryDTO.getMemName();
		if(StringUtils.isBlank(orderId) || StringUtils.isBlank(address)
				|| StringUtils.isBlank(sendAddress) || StringUtils.isBlank(returnAddress) 
				|| StringUtils.isBlank(memTele) || StringUtils.isBlank(deliveryWay) || StringUtils.isBlank(memName)
				) {
			return ERROR;
		}
		Map<String,Boolean> resultMap = new HashMap<String, Boolean>();
		try {
			boolean result = deliveryOrderService.sendSingleGoods(deliveryDTO);
			resultMap.put("success", result);
		} catch(Exception e) {
			resultMap.put("success", false);
		}
		Utils.printInfoWithoutType(resultMap);
		return null;
	}
	
	/**
	 * 批量确认发货
	 *@Title: batchConfirm
	 *@Description: 确认发货
	 *@return:String
	 *@author: Jacky
	 *@Thorws:
	 */
	public String ajaxBatchConfirm() throws Exception {
		UserSession us = Utils.getUserSession();
		switch (us.getUserLevel()) {
			case 2:
				break;
			default:
				return "intercepthtml";
		}
		String str = deliveryDTO.getOrderIdWayBillNoStr();
		if(StringUtils.isBlank(str)) {
			return ERROR;
		}
		String[] orderBill = str.split(",");
		if(orderBill.length == 0) {
			return ERROR;
		}
		Map<String,String> orderMap = new HashMap<String,String>();
		List<String> orderIds = new ArrayList<String>();
		for(String orderB : orderBill) {
			String[] keyValue = orderB.split("=");
			if(keyValue.length==2 && StringUtils.isNotBlank(keyValue[0]) && StringUtils.isNotBlank(keyValue[1])) {
				orderMap.put(keyValue[0], keyValue[1]);
				orderIds.add(keyValue[0]);
			}
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(orderIds.size() > 0 && orderMap.size() > 0) {
			boolean result = deliveryOrderService.batchConfirmSendGoods(orderMap, orderIds,us.getMerId());
			resultMap.put("success", result);
			Utils.printInfoWithoutType(resultMap);
			return null;
		}
		resultMap.put("success", false);
		Utils.printInfoWithoutType(resultMap);
		return null;
	}
}
