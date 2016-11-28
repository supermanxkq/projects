package com.paySystem.ic.web.dto.order;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:MCIU_DS
 * @ClassName:OrderGroupRelaDTO
 * @Description:订单查询的ＤＴＯ
 * @date: 2014-10-10
 * @author: 赵瑞群
 * @version: V1.0
 */
public class OrderAndGoodsQueryDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 2853496053601399594L;

	
	/** 订单编号 */
	private String orderId;
	/** 商品编号 */
	private String goodsId;
	/** 商品名称 */
	private String goodsName;
	/**起始时间*/
	private String beginDate;
	/**截止时间*/
	private String endDate;
	/**商户ID*/
	private String merId;
	/**买家名称*/
	private String mem;
	/**订单状态*/
	private String orderStauts;
	/**评价状态*/
	private String criticalStatus;
	/**是否关闭*/
	private String isClose;
	
	public String getMem() {
		return mem;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	public String getOrderStauts() {
		return orderStauts;
	}
	public void setOrderStauts(String orderStauts) {
		this.orderStauts = orderStauts;
	}
	public String getCriticalStatus() {
		return criticalStatus;
	}
	public void setCriticalStatus(String criticalStatus) {
		this.criticalStatus = criticalStatus;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}

}
