package com.paySystem.ic.web.action.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.paySystem.ic.bean.PageView;
import com.paySystem.ic.bean.QueryResult;
import com.paySystem.ic.bean.goods.Goods;
import com.paySystem.ic.bean.order.Order;
import com.paySystem.ic.bean.order.OrderDetail;
import com.paySystem.ic.bean.shopcar.ShopCar;
import com.paySystem.ic.service.goods.GoodsService;
import com.paySystem.ic.service.order.OrderDetailService;
import com.paySystem.ic.service.order.OrderService;
import com.paySystem.ic.service.record.FunctionsService;
import com.paySystem.ic.service.shopcar.ShopCarService;
import com.paySystem.ic.utils.DateTimeTool;
import com.paySystem.ic.utils.Globals;
import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.action.BaseAction;
import com.paySystem.ic.web.dto.ListInfoDTO;
import com.paySystem.ic.web.dto.order.OrderDTO;

@Controller("/order/order")
@Scope("prototype")
public class OrderAction extends BaseAction {

	private static final long serialVersionUID = 1157838270801154543L;
	private Order order;
	private OrderDetail orderDetail = new OrderDetail();
	private OrderDTO orderDTO = new OrderDTO();
	@Resource
	private OrderService orderService;
	@Resource
	private FunctionsService functionsService;
	@Resource
	private ShopCarService shopCarService;
	@Resource
	private OrderDetailService orderDetailService;
	@Resource
	private GoodsService goodsService;
	private List<Goods> goods = new ArrayList<>();
	private float totalPrice = 0.0f;
	private List<ShopCar> list = new ArrayList<>();
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public String list() {
		this.getSession().setAttribute("orderstatus", OptionsValue.ORDERSTATUS);
		return "list";
	}

	public String jsonPageList() throws Exception {
		/**
		 * 查询结果排序参数设定
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		/** 判断排序参数是否有值 */
		if (StringUtils.isNotBlank(this.getOrderProperty()) && StringUtils.isNotBlank(this.getOrderDirection())) {
			orderBy.put(this.getOrderProperty(), this.getOrderDirection());
		} else {
			orderBy.put("id", "desc");
		}
		/** 获取queryResult中的值 */
		QueryResult<OrderDTO> result = orderService.queryAll((orderDTO.getPage() - 1) * pageNum, pageNum, orderDTO,
				orderBy);
		/** 获取queryResult中的集合 */
		List<OrderDTO> orderDTOs = result.getResultlist();
		/** 字符串信息集合 */
		List<List<String>> lists = new ArrayList<List<String>>();
		/** QueryResult对象的getResultlist方法获取SalesSummaryDTO集合 */
		for (int i = 0; i < orderDTOs.size(); i++) {
			/** 向页面赋值 */
			OrderDTO orderDTO = orderDTOs.get(i);
			List<String> strings = new ArrayList<String>();
			QueryResult<OrderDetail> queryResult = orderDetailService.queryOrderDetailByOrderId(orderDTO.getId());
			/** 添加到字符串集合中去 */
			strings.add(orderDTO.getId().toString());
			/** 商品名称 */
			String goodsNameList = "";
			float totalPrice = 0.0f;
			for (OrderDetail orderDetail : queryResult.getResultlist()) {
				Goods goods = goodsService.find(orderDetail.getGoodsId());
				totalPrice = totalPrice + (goods.getPrice() * orderDetail.getCount());
				goodsNameList += goods.getGoodsName() + "<br/>";
			}
			strings.add(goodsNameList);
			strings.add(orderDTO.getCustomer());
			if (orderDTO.getCustomerPhoneNumber() != null && !orderDTO.getCustomerPhoneNumber().equals("")) {
				strings.add(orderDTO.getCustomerPhoneNumber());
			} else {
				strings.add("无");
			}
			strings.add(totalPrice + "元");
			strings.add(Utils.getOptionsIntegerName(OptionsValue.ORDERSTATUS, orderDTO.getStatus()));
			strings.add(DateTimeTool.dateFormat("", orderDTO.getCreateTime()));
			String operation = "";
			if (orderDTO.getStatus() != 9) {
				operation += "<a href=javascript:deleteData('order/order!delete','" + orderDTO.getId()
						+ "') title='删除'>" + Globals.IMG_DELETE + "</a>&nbsp;";
				operation += "<a href=order/order!checkUI?orderDTO.id=" + orderDTO.getId()
						+ " target='_blank' title='打印'>" + Globals.IMG_PRINT + "</a>&nbsp;";
				if (orderDTO.getStatus() == 3) {
					operation += "<a href=javascript:verifyData('order/order!payComplete','" + orderDTO.getId()
							+ "') target='_blank' title='付款'>" + Globals.IMG_PAY + "</a>&nbsp;";
				}
				strings.add(operation);
			} else {
				operation += "<a href=order/order!checkUI?orderDTO.id=" + orderDTO.getId() + " title='查看'>"
						+ Globals.IMG_VIEW + "</a>";
				strings.add(operation);
			}

			lists.add(strings);
		}
		PageView pageView = new PageView(orderDTO.getPage(), result.getTotalrecord());
		/** 实例化ListInfoDTO */
		ListInfoDTO listInfoDTO = new ListInfoDTO(lists, getPageHTML(pageView));
		return Utils.printInfo(listInfoDTO);
	}

	public String checkUI() {
		order = orderService.find(orderDTO.getId());
		QueryResult<OrderDetail> orderDetailsQueryResult = orderDetailService.queryOrderDetailByOrderId(order.getId());
		orderDetails = orderDetailsQueryResult.getResultlist();
		for (OrderDetail orderDetail : orderDetails) {
			totalPrice += orderDetail.getPrice() * orderDetail.getCount();
		}
		return "checkUI";
	}

	public String editUI() {
		this.getRequest().setAttribute("orderstatus", OptionsValue.ORDERSTATUS);// 状态
		this.setMethod("updateData");
		try {
			// 通过编号查找数据库中的数据
			order = orderService.find(orderDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String updateData() {
		order.setCreateTime(new Date());
		orderService.update(order);
		return "list";
	}

	public String confirmOrder() {
		this.setMethod("addSave");
		this.getRequest().setAttribute("orderstatus", OptionsValue.ORDERSTATUS);
		try {
			list = shopCarService.queryNormalShopCar();
			for (ShopCar shopCar : list) {
				totalPrice += shopCar.getPrice() * shopCar.getCount();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "confirmOrder";
	}

	public String delete() {
		try {
			Order order = orderService.find(Integer.parseInt(this.getId()));
			order.setStatus(9);
			orderService.update(order);
			functionsService.saveFunction("订单管理", 3, "删除订单：" + this.getId());
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public String payComplete() {
		try {
			Order order = orderService.find(Integer.parseInt(this.getId()));
			order.setStatus(1);
			orderService.update(order);
			this.ajaxResult = "ajaxsuccess";
		} catch (Exception e) {
			e.printStackTrace();
			this.ajaxResult = "ajaxfailure";
			this.msgResult = e.getMessage();
		}
		return this.ajaxResult;
	}

	public String addUI() {
		this.setMethod("addSave");
		this.getSession().setAttribute("orderstatus", OptionsValue.ORDERSTATUS);
		return "input";
	}

	public String addSave() {
		try {
			order.setCreateTime(new Date());
			Order order2 = orderService.addSave(order);
			QueryResult<ShopCar> queryResult = shopCarService.getScrollData();
			for (ShopCar shopCar : queryResult.getResultlist()) {
				int status = goodsService.find(shopCar.getGoodsId()).getStatus();
				// 如果商品的状态不是删除或者下架，才可以生成订单
				if (status != 9 && status != 2) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setCount(shopCar.getCount());
					orderDetail.setGoodsId(shopCar.getGoodsId());
					orderDetail.setOrderId(order2.getId());
					orderDetail.setGoodsName(shopCar.getGoodsName());
					orderDetail.setPrice(shopCar.getPrice());
					orderDetailService.addSave(orderDetail);
					Goods goods = goodsService.find(shopCar.getGoodsId());
					goods.setCount(goods.getCount() - shopCar.getCount());
					goodsService.update(goods);
					shopCarService.delete(shopCar.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderDTO getOrderDTO() {
		return orderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}

	public List<ShopCar> getList() {
		return list;
	}

	public void setList(List<ShopCar> list) {
		this.list = list;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
