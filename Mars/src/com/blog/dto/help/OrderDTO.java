package com.blog.dto.help;

import java.io.Serializable;

import com.blog.dto.BaseDTO;
public class OrderDTO extends BaseDTO implements Serializable{
	/** */
	private static final long serialVersionUID = -7363311538603295296L;
	// 支付类型//必填，不能修改
	private String payment_type;
	// 服务器异步通知页面路径
	private String notify_url;
	// 页面跳转同步通知页面路径
	private String return_url;
	// 商户订单号--必填
	private String out_trade_no;
	// 订单名称--必填
	private String subject;
	// 付款金额--必填
	private String price;
	// 商品数量
	private String quantity;
	// 物流费用--必填，即运费
	private String logistics_fee;
	// 物流类型--必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
	private String logistics_type;
	// 物流支付方式//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
	private String logistics_payment = "SELLER_PAY";
	// 订单描述
	private String body;
	// 商品展示地址
	private String show_url;
	// 收货人姓名
	private String receive_name;
	// 收货人地址
	private String receive_address;
	// 收货人邮编
	private String receive_zip;
	// 收货人电话号码
	private String receive_phone;
	// 收货人手机号码
	private String receive_mobile;

	public String getBody() {
		return body;
	}

	public String getLogistics_fee() {
		return logistics_fee;
	}

	public String getLogistics_payment() {
		return logistics_payment;
	}

	public String getLogistics_type() {
		return logistics_type;
	}

	
	public String getNotify_url() {
		return notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	
	public String getPayment_type() {
		return payment_type;
	}

	
	public String getPrice() {
		return price;
	}

	
	public String getQuantity() {
		return quantity;
	}

	
	public String getReceive_address() {
		return receive_address;
	}

	
	public String getReceive_mobile() {
		return receive_mobile;
	}

	
	public String getReceive_name() {
		return receive_name;
	}

	
	public String getReceive_phone() {
		return receive_phone;
	}

	
	public String getReceive_zip() {
		return receive_zip;
	}

	
	public String getReturn_url() {
		return return_url;
	}

	
	public String getShow_url() {
		return show_url;
	}

	
	public String getSubject() {
		return subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setLogistics_fee(String logisticsFee) {
		logistics_fee = logisticsFee;
	}

	public void setLogistics_payment(String logisticsPayment) {
		logistics_payment = logisticsPayment;
	}

	public void setLogistics_type(String logisticsType) {
		logistics_type = logisticsType;
	}

	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setReceive_address(String receiveAddress) {
		receive_address = receiveAddress;
	}

	public void setReceive_mobile(String receiveMobile) {
		receive_mobile = receiveMobile;
	}

	public void setReceive_name(String receiveName) {
		receive_name = receiveName;
	}

	public void setReceive_phone(String receivePhone) {
		receive_phone = receivePhone;
	}

	public void setReceive_zip(String receiveZip) {
		receive_zip = receiveZip;
	}

	public void setReturn_url(String returnUrl) {
		return_url = returnUrl;
	}

	public void setShow_url(String showUrl) {
		show_url = showUrl;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}	
