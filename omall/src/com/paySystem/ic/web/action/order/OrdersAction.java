package com.paySystem.ic.web.action.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.service.order.OrdersService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.utils.ExportUtil;
import com.paySystem.ic.utils.NumberUtil;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.order.OrdersDTO;
import com.paySystem.ic.web.dto.system.UserSession;

/**  
* @Title: OrdersAction.java
* @Package: com.paySystem.ic.web.action.order
* @Description: 订单管理类
* @Author: luckygroup
* @Date: 2014-10-08
* @Version: V1.0  
*/
@Controller("/orders/orders")
@Scope("prototype")
public class OrdersAction extends BaseAction {
	private static final long serialVersionUID = 151416337290759697L;
	
	
	@Resource
	private OrdersService ordersService;
	
	@Resource
	FunctionsService functionsService;
	
	private OrdersDTO ordersDTO = new OrdersDTO();
	
	

	public OrdersDTO getOrdersDTO() {
		return ordersDTO;
	}

	public void setOrdersDTO(OrdersDTO ordersDTO) {
		this.ordersDTO = ordersDTO;
	}

	/**
	 * 物流列表查询
	 *@Title:list
	 *@Description: 根据列表界面条件对数据进行分页查询
	 *@param:@throws Exception
	 *@return:String
	 *@author: luckygroup
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
		
		this.getRequest().setAttribute("statusDic", OptionsValue.ORDERS_STATUS);
		this.getRequest().setAttribute("criticalStatusDic", OptionsValue.CRITICAL_STATUS);
		ordersDTO.setMerId(us.getMerId());
		/** 设置排序规则 **/
		LinkedHashMap<String, String> orderby
        	= new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
		    && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else { 
			orderby.put("orderTime", "desc"); 
		}
		ordersDTO.setOrderBy(orderby);
		ordersDTO.setPageNum(pageNum);
		
		/** 默认查询 待付款状态订单 **/
		if(null == ordersDTO.getStatus()) {
			ordersDTO.setStatus(0);
		}
		
		/** 设置active的tab**/
		this.getRequest().setAttribute("activeTab", ordersDTO.getActiveTab());
		
		QueryResult<OrdersDTO> queryResult = ordersService.getOrderPage(ordersDTO);
		ordersDTO.setTotalPage(queryResult.getTotalrecord()%ordersDTO.getPageNum() > 0?queryResult.getTotalrecord()/ordersDTO.getPageNum()+1:queryResult.getTotalrecord()/ordersDTO.getPageNum());
		if(ordersDTO.getActiveTab() == 0) {
			this.getRequest().setAttribute("orderListDfk", queryResult);
		} else if(ordersDTO.getActiveTab() == 1){
			this.getRequest().setAttribute("orderListDfh", queryResult);
		}else if(ordersDTO.getActiveTab() == 2){
			this.getRequest().setAttribute("orderListYfh", queryResult);
		}else if(ordersDTO.getActiveTab() == 3){
			this.getRequest().setAttribute("orderListTkz", queryResult);
		}else if(ordersDTO.getActiveTab() == 4){
			this.getRequest().setAttribute("orderListXpj", queryResult);
		}else if(ordersDTO.getActiveTab() == 5){
			this.getRequest().setAttribute("orderListYcg", queryResult);
		}else if(ordersDTO.getActiveTab() == 6){
			this.getRequest().setAttribute("orderListYgb", queryResult);
		}else if(ordersDTO.getActiveTab() == 7){
			this.getRequest().setAttribute("orderListLs", queryResult);
		}
		return "list";
	}
	

	/**
	 * 
	 * @Title:closeOrder
	 * @Description:关闭订单
	 * @Params:@return
	 * @Return:String
	 * @author:luckygroup
	 * @Date:2014-8-24下午5:44:16
	 */

	public String closeOrder() {

		try {

			
			OrdersDTO ordersDTO = ordersService.CloseOrder(this.id);

			functionsService.saveFunction("订单管理", 3,
					"关闭订单：" + ordersDTO.getOrderId());

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}
	
	/**
	 * 
	*@Title:freeFreight
	*@Description:订单免运费
	*@Params:@return
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-19下午4:57:49
	 */
	public String freeFreight() {

		try {

			
			ordersService.freeFreight(this.ids);

			functionsService.saveFunction("订单管理", 3,
					"免运费：" + this.ids);

			this.ajaxResult = "ajaxsuccess";

		} catch (Exception e) {

			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}

		return this.ajaxResult;
	}
	
	/**
	 * 
	*@Title:updatePriceUI
	*@Description:进入修改订单价格页面
	*@Params:@return
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-19下午4:58:05
	 */
	public String  updatePriceUI(){
				
		try {
			this.setMethod("updatePrice");
			UserSession us = Utils.getUserSession();
			String orderId = ordersDTO.getOrderId();
			ordersDTO = ordersService.queryUpdatePrice(orderId);
			return "updatePriceUI";

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 
	*@Title:updatePrice
	*@Description:修改订单价格
	*@Params:@param ordersDTO
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午4:58:30
	 */
	public String  updatePrice() {
				
		try{
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			ordersService.updatePrice(ordersDTO);
			return "successclose";
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 
	*@Title:extendDayUI
	*@Description:跳转到延长时间页面
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午9:37:20
	 */
	public String  extendDayUI() throws Exception{
		ordersDTO = ordersService.queryOrderSendTime(ordersDTO.getOrderId());
		this.setMethod("extendDay");
		return "extendDaysUI";
	}
	
	/**
	 * 
	*@Title:extendDay
	*@Description:延长收货时间
	*@Params:@throws Exception
	*@Return:void
	*@author:luckygroup
	*@Date:2014-10-19下午9:39:07
	 */
	public String extendDay(){
		try{
			this.getRequest().setAttribute("result",
					this.getText("operation.success.notice"));
			ordersService.extendTakeOverTime(ordersDTO);
			return "successclose";
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	/**
	 * 
	*@Title:orderUI
	*@Description:跳转到订单详情
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-20下午10:34:14
	 */
	public String orderUI() throws Exception{
		String orderId = ordersDTO.getOrderId();
		ordersDTO = ordersService.queryOrderDetails(orderId);
		this.getRequest().setAttribute("orderGoodList", ordersService.queryGoodsByOrderId(orderId));
		this.getRequest().setAttribute("orderStatusStr", Utils.getOptionsIntegerName(
				OptionsValue.ORDERS_STATUS,ordersDTO.getStatus()));
		return "orderUI";
	}
	
	/**
	 * 
	*@Title:sendOrderUI
	*@Description:跳转到发货订单详情
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-20下午10:34:39
	 */
	public String sendOrderUI() throws Exception{
		String orderId = ordersDTO.getOrderId();
		ordersDTO = ordersService.queryOrderDetails(orderId);
		this.getRequest().setAttribute("orderGoodList", ordersService.queryGoodsByOrderId(orderId));
		this.getRequest().setAttribute("orderStatusStr", Utils.getOptionsIntegerName(
				OptionsValue.ORDERS_STATUS,ordersDTO.getStatus()));
		return "sendOrderUI";
	}
	
	
	/**
	 * 
	*@Title:export
	*@Description:导出订单报表
	*@Params:@return
	*@Params:@throws Exception
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-20下午10:34:39
	 */
	
	public String export(){
		String title="订单报表";
		
		try {
			setFileName(this.getRequest(),this.getResponse(),title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str="";
		ExportUtil util=new ExportUtil();
		List<String> headers=new ArrayList<String>();
		headers.add("订单编号");
		
		headers.add("买家");
		headers.add("交易状态");
		headers.add("实收款");
		headers.add("成交时间");
		/**获取要导出的集合*/
		UserSession us = Utils.getUserSession();
		ordersDTO.setMerId(us.getMerId());
		/** 设置排序规则 **/
		LinkedHashMap<String, String> orderby
        	= new LinkedHashMap<String, String>();
		if (StringUtils.isNotBlank(this.getOrderProperty())
		    && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderby.put(this.getOrderProperty(), this.getOrderDirection());
		} else { 
			orderby.put("orderTime", "desc"); 
		}
		ordersDTO.setOrderBy(orderby);		
		/** 默认查询 待付款状态订单 **/
		if(null == ordersDTO.getStatus()) {
			ordersDTO.setStatus(0);
		}
		
		/** 设置active的tab**/
		this.getRequest().setAttribute("activeTab", ordersDTO.getActiveTab());
		ordersDTO.setExport(1);
		
		try {
			QueryResult<OrdersDTO> queryResult = ordersService.getOrderPage(ordersDTO);
			List<List<String>> lists = new ArrayList<List<String>>();
			List<OrdersDTO> dto=queryResult.getResultlist();
		 if(dto!=null){
			for(int i=0;i<dto.size();i++){
				OrdersDTO orderDTO=dto.get(i);
				List<String> list=new ArrayList<String>();
				list=findGoodsStringList(list,orderDTO);
				lists.add(list);
			}
		   }
			str=util.createXls(headers, lists, title, this.getResponse());
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 
	*@Title:findGoodsStringList
	*@Description:生成导出用的数据
	*@Params:@strings 列表字符串 ordersDTO表单DTO
	*@Params:@throws Exception
	*@Return:String
	*@author:luckygroup
	*@Date:2014-10-20下午10:34:39
	 */
	private List<String> findGoodsStringList(List<String> strings,
			OrdersDTO ordersDTO) {
		strings.add(Utils.getString(ordersDTO.getOrderId()));
		
		strings.add(Utils.getString(ordersDTO.getMemName()));
		strings.add(Utils.getOptionsIntegerName(
				OptionsValue.ORDERS_STATUS,ordersDTO.getStatus()));
		strings.add(NumberUtil
				.numberFormat("#,##0.0000", ordersDTO.getPaidAmt()));
		
		strings.add(Utils.getString(ordersDTO.getOrderTimeStr()));
		return strings;
	}

}
