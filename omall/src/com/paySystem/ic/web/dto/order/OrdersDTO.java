package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import com.paySystem.ic.utils.OptionsValue;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:OrderGroupRelaDTO
 * @Description:订单查询的ＤＴＯ
 * @date: 2014-10-10
 * @author: 赵瑞群
 * @version: V1.0
 */
public class OrdersDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 2853496053601399594L;

	
	/** 订单编号 */
	private String orderId;
	/** 商户Id */
	private String merId;
	/**
	 * 订单状态 0:代付款 1：待确定 2：订单确认 3：待发货 4：已发货 5：订单撤销 6：订单成功 7：订单失败  (8:退货中 9：退货成功 )
	 */
	private Integer status;
	/** 邮费 */
	private BigDecimal postAmt;
	/** 实际支付金额 */
	private BigDecimal paidAmt;
	/** 买家 */
	private String memId;
	/** (买家昵称) */
	private String memName;
	/**评价状态
	 * 0:未评价   1：已评价
	 */
	private Integer criticalStatus;
	/** 下单时间及交易完成时间 */
	private Date orderTime;
	/** 下单时间及交易完成时间 str*/
	private Date orderTimeStr;
	
	/** 发货时间 str*/
	private Date sendTimeStr;
	
	/** 发货时间 */
	private Date sendTime;

	/** (操作时间时间 每次操作订单的时间 )*/
	private Date operTime;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	/** (延长收货天数)*/
	private Integer extendDays;
	/** （商品名称） */
	private String goodsName;
	/** （单价） */
	private BigDecimal unitPrice;
	/** 数量 */
	private Integer qty;
	/** （是否关闭 0 否 1 是） */
	private Integer isClose;
	/**
	 * 排序
	 */
	private LinkedHashMap<String,String> orderBy;
	/** 被激活的tab**/
	private int activeTab = 0;
	
	/** 总页数**/
	private long totalPage = 0;
	
	/** 订单字符串逗号分隔**/
	private String orderIdStr;
	
	/**
	 * 格式化日期
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * 订单金额
	 */
	private BigDecimal orderAmt;
	/**
	 * 收货地址
	 */
	private String address;
	
	/**(买家真实姓名)*/
	private String memRealName;
	/**(买家邮件)*/
	private String mail;
	/**(交易号)*/
	private String payTradeId;
	/**(支付账号)*/
    private String payAccount;
    /**留言*/
    private String leaveWord;
    /**买家电话*/
	private String memTele;
	
	/**导出操作 1 导出操作 0 非导出操作*/
	private Integer export;
	/**配送方式*/
	private String deliveryWay;
	/**订单状态值*/
	private String orderStatusStr;
    
	
	public String getOrderStatusStr() {
		return Utils.getOptionsIntegerName(
				OptionsValue.ORDERS_STATUS,status);
	}
	public String getDeliveryWay() {
		return deliveryWay;
	}
	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}
	public Integer getExport() {
		return export;
	}
	public void setExport(Integer export) {
		this.export = export;
	}
	public String getMemTele() {
		return memTele;
	}
	public void setMemTele(String memTele) {
		this.memTele = memTele;
	}
	public String getMemRealName() {
		return memRealName;
	}
	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPayTradeId() {
		return payTradeId;
	}
	public void setPayTradeId(String payTradeId) {
		this.payTradeId = payTradeId;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getLeaveWord() {
		return leaveWord;
	}
	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getOrderIdStr() {
		return orderIdStr;
	}
	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}
	public Integer getIsClose() {
		return isClose;
	}
	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getPostAmt() {
		return postAmt;
	}
	public void setPostAmt(BigDecimal postAmt) {
		this.postAmt = postAmt;
	}
	public BigDecimal getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getCriticalStatus() {
		return criticalStatus;
	}
	public void setCriticalStatus(Integer criticalStatus) {
		this.criticalStatus = criticalStatus;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public Integer getExtendDays() {
		return extendDays;
	}
	public void setExtendDays(Integer extendDays) {
		this.extendDays = extendDays;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public LinkedHashMap<String, String> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(LinkedHashMap<String, String> orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getOrderTimeStr() {
		if(null != orderTime) {
			return sdf.format(orderTime);
		}
		return null;
	}
	
	public String getSendTimeStr() {
		if(null != sendTime) {
			return sdf.format(sendTime);
		}
		return null;
	}

}
