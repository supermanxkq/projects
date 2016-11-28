package com.blog.web.action.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.blog.alipay.config.AlipayConfig;
import com.blog.alipay.util.AlipayNotify;
import com.blog.alipay.util.AlipaySubmit;
import com.blog.bean.QueryResult;
import com.blog.bean.goods.Goods;
import com.blog.bean.help.Order;
import com.blog.service.goods.GoodsService;
import com.blog.service.order.OrderService;
import com.blog.util.MailUtils;
import com.blog.util.OrderUtils;
import com.blog.web.action.BaseAction;

/**
 * 登录Action类
 * 
 * @version 2013-9-1 上午11:12:59
 */

@Controller("/shop/shop")
@Scope("prototype")
public class ShopAction extends BaseAction {
	private static final long serialVersionUID = 6527405819743413855L;
	@Resource
	private GoodsService goodsService;
	@Resource
	private OrderService orderService;
	private List<Goods> goodsList;
	private Goods goods;
	private String contentType = "text/html;charset=utf-8";
	private Order order;

	/**
	 *@MethodName:toShopPage
	 *@Description:跳转到商城页面
	 *@author:徐半仙儿
	 *@return String
	 *@date:2015-11-28下午08:18:30
	 */
	public String toShopPage() {
		QueryResult<Goods> goodsQueryResult = goodsService.queryAll();
		goodsList = goodsQueryResult.getResultlist();
		return "list";
	}

	public String detail() {
		String out_trade_no = OrderUtils.getOrderNo();
		this.getRequest().setAttribute("out_trade_no", out_trade_no);
		goods = goodsService.find(goods.getId());
		return "detail";
	}

	/**
	 *@MethodName:createHelp
	 *@Description:创建订单
	 *@author:徐半仙儿
	 *@return void
	 *@date:2015-10-14下午11:20:32
	 */
	public void createOrder() {
		order.setPayment_type("1");
		order.setNotify_url("http://www.xukaiqiang.com/shop/shop!changeStatus");
		order
				.setReturn_url("http://www.xukaiqiang.com/shop/shop!toPaySuccessPage");
		order.setQuantity("1");
		order.setLogistics_fee("0.00");
		order.setLogistics_type("EXPRESS");
		order.setLogistics_payment("SELLER_PAY");
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_partner_trade_by_buyer");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", order.getPayment_type());
		sParaTemp.put("notify_url", order.getNotify_url());
		sParaTemp.put("return_url", order.getReturn_url());
		sParaTemp.put("out_trade_no", order.getOut_trade_no());
		sParaTemp.put("subject", order.getSubject());
		sParaTemp.put("price", order.getPrice());
		sParaTemp.put("quantity", order.getQuantity());
		sParaTemp.put("logistics_fee", order.getLogistics_fee());
		sParaTemp.put("logistics_type", order.getLogistics_type());
		sParaTemp.put("logistics_payment", order.getLogistics_payment());
		sParaTemp.put("body", order.getBody());
		if(!(order.getSubject().equals("捐助"))){
		sParaTemp.put("show_url",
				"http://www.xukaiqiang.com/shop/shop!detail?goods.id="
						+ goods.getId());
		}else{
			sParaTemp.put("show_url",
					"http://www.xukaiqiang.com/help/help!toHelpPage?columnCode=4");
		}
		sParaTemp.put("receive_name", "无");
		sParaTemp.put("receive_address", "无");
		sParaTemp.put("receive_zip", "无");
		sParaTemp.put("receive_phone", "无");
		sParaTemp.put("receive_mobile", "无");

		// 创建订单
		try {
			// 等待买家付款
			order.setStatus(0);
			// 商品描述
			order.setBody("友情捐助");
			// 收货地址
			order.setReceive_address("无");
			if(!(order.getSubject().equals("捐助"))){
			order
					.setShow_url("http://www.xukaiqiang.com/shop/shop!detail?goods.id="
							+ goods.getId());
			}else{
				order.setShow_url("http://www.xukaiqiang.com/help/help!toHelpPage?columnCode=4");
			}
			orderService.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

		// 指定输出内容类型和编码
		ServletActionContext.getResponse().setContentType(contentType);
		// 获取输出流，然后使用
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.print(sHtmlText);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeStatus() {
		HttpServletRequest request = this.getRequest();
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");
			Order order = orderService.find(out_trade_no);
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("WAIT_BUYER_PAY")) {
					// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款

					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					order.setStatus(0);
					order.setTrade_no(trade_no);
					orderService.update(order);
					out.println("success"); // 请不要修改或删除
				} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
					// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货

					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					order.setStatus(1);
					order.setTrade_no(trade_no);
					MailUtils.sendMail(order.getSubject(), order.getBody()+"   买家已付款，请发货。<a href='http://www.xukaiqiang.com/order/order!queryAll'>确认发货</a>", "994028591@qq.com");
					orderService.update(order);
					out.println("success"); // 请不要修改或删除
				} else if (trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
					// 该判断表示卖家已经发了货，但买家还没有做确认收货的操作

					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					order.setStatus(2);
					order.setTrade_no(trade_no);
					orderService.update(order);
					out.println("success"); // 请不要修改或删除
				} else if (trade_status.equals("TRADE_FINISHED")) {
					// 该判断表示买家已经确认收货，这笔交易完成

					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					order.setStatus(3);
					order.setTrade_no(trade_no);
					orderService.update(order);
					out.println("success"); // 请不要修改或删除
				} else {
					order.setStatus(4);
					order.setTrade_no(trade_no);
					orderService.update(order);
					out.println("success"); // 请不要修改或删除
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toPaySuccessPage() {
		return "paysuccess";
	}

	public String sendGoodsShop() {
		HttpServletResponse response = this.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
				 order=orderService.find(order.getOut_trade_no());
					// 判断该笔订单是否在商户网站中已经做过处理
					// trade_no支付宝交易号
					// 物流公司名称
					// 物流发货单号
					// 物流运输类型
					// 物流公司名称
					String logistics_name = "物流公司名称";
					// 物流运输类型
					String transport_type = "EXPRESS";

					// 把请求参数打包成数组
					Map<String, String> sParaTemp = new HashMap<String, String>();
					sParaTemp.put("service", "send_goods_confirm_by_platform");
					sParaTemp.put("partner", AlipayConfig.partner);
					sParaTemp.put("_input_charset", AlipayConfig.input_charset);
					sParaTemp.put("trade_no", order.getTrade_no());
					sParaTemp.put("logistics_name", logistics_name);
					// sParaTemp.put("invoice_no", out_trade_no);
					sParaTemp.put("transport_type", transport_type);

					// 建立请求
					String sHtmlText = AlipaySubmit.buildRequest("", "",
							sParaTemp);
					out.println(sHtmlText);
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
				// 该页面可做页面美工编辑
				// ////////////////////////////////////////////////////////////////////////////////////////
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sendsuccess";
	}


	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
