package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName: omproject
 * @ClassName: ReMoReasonDTO
 * @Description: 退款申请表
 * @date: 2014-11-12 下午03:59:33
 * @author: 王少辉
 * @version: V1.0
 */
public class ReMoReasonDTO extends BaseDTO implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6503714003369981091L;
	
	/**
	 * 退款申请Id
	 */
	private Integer reMoReId;

	/**
	 * 订单编号
	 */
	private String orderId;

	/**
	 * 会员编号
	 */
	private String memId;

	/**
	 * 是否收到商品 1：已收到货 0：未收到货
	 */
	private Integer isTakeGoods;

	/**
	 * 退款理由 
	 * 0：不想买了 
	 * 1：未收到货 
	 * 2：衣服有瑕疵 
	 * 3：商品错发、漏发
	 * 4：收到的商品与描述不符
	 */
	private Integer reMoReason;

	/**
	 * 退款理由说明
	 */
	private String reMoDesc;

	/**
	 * 退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	private Integer reMoSta;
	
	/**
	 * 退款申请时间
	 */
	private Date reMoTime;

	/**
	 * 退款申请开始时间，界面查询用
	 */
	private String beginDate;
	
	/**
	 * 退款申请结束时间，界面查询用
	 */
	private String endDate;

	/**
	 * 申请退款金额
	 */
	private BigDecimal returnMoney;
	
	/**
	 * 申请退款金额（回显用）
	 */
	private String returnMoneyShow;

	/**
	 * 实际退款金额
	 */
	private BigDecimal actuReturnMoney;
	
	/**
	 * 店铺编号
	 */
	private String storeId;
	
	/**
	 * 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	private Integer status;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 0：查看；1：审核；
	 */
	private Integer action;

	/**
	 * @return Integer 退款申请Id
	 */
	public Integer getReMoReId() {
		return reMoReId;
	}

	/**
	 * @param reMoReId 退款申请Id
	 */
	public void setReMoReId(Integer reMoReId) {
		this.reMoReId = reMoReId;
	}

	/**
	 * @return String 订单编号
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId 订单编号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return String 会员编号
	 */
	public String getMemId() {
		return memId;
	}

	/**
	 * @param memId 会员编号
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}

	/**
	 * @return Integer 是否收到商品 1：已收到货 0：未收到货
	 */
	public Integer getIsTakeGoods() {
		return isTakeGoods;
	}

	/**
	 * @param isTakeGoods 是否收到商品 1：已收到货 0：未收到货
	 */
	public void setIsTakeGoods(Integer isTakeGoods) {
		this.isTakeGoods = isTakeGoods;
	}

	/**
	 * @return Integer 退款理由 
	 * 0：不想买了 
	 * 1：未收到货 
	 * 2：衣服有瑕疵 
	 * 3：商品错发、漏发
	 * 4：收到的商品与描述不符
	 */
	public Integer getReMoReason() {
		return reMoReason;
	}

	/**
	 * @param reMoReason 退款理由 
	 * 0：不想买了 
	 * 1：未收到货 
	 * 2：衣服有瑕疵 
	 * 3：商品错发、漏发
	 * 4：收到的商品与描述不符
	 */
	public void setReMoReason(Integer reMoReason) {
		this.reMoReason = reMoReason;
	}

	/**
	 * @return String 退款理由说明
	 */
	public String getReMoDesc() {
		return reMoDesc;
	}

	/**
	 * @param reMoDesc 退款理由说明
	 */
	public void setReMoDesc(String reMoDesc) {
		this.reMoDesc = reMoDesc;
	}

	/**
	 * @return Integer 退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	public Integer getReMoSta() {
		return reMoSta;
	}

	/**
	 * @param reMoSta 退款理由说明 （此处只显示退款完成的唯一状态）
	 */
	public void setReMoSta(Integer reMoSta) {
		this.reMoSta = reMoSta;
	}

	/**
	 * @return Date 退款申请时间
	 */
	public Date getReMoTime() {
		return reMoTime;
	}

	/**
	 * @param reMoTime 退款申请时间
	 */
	public void setReMoTime(Date reMoTime) {
		this.reMoTime = reMoTime;
	}

	/**
	 * @return String 退款申请开始时间，界面查询用
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate 退款申请开始时间，界面查询用
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return String 退款申请结束时间，界面查询用
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate 退款申请结束时间，界面查询用
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return BigDecimal 申请退款金额
	 */
	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	/**
	 * @param returnMoney 申请退款金额
	 */
	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	/**
	 * @return String 申请退款金额（回显用）
	 */
	public String getReturnMoneyShow() {
		return returnMoneyShow;
	}

	/**
	 * @param returnMoneyShow 申请退款金额（回显用）
	 */
	public void setReturnMoneyShow(String returnMoneyShow) {
		this.returnMoneyShow = returnMoneyShow;
	}

	/**
	 * @return BigDecimal 实际退款金额
	 */
	public BigDecimal getActuReturnMoney() {
		return actuReturnMoney;
	}

	/**
	 * @param actuReturnMoney 实际退款金额
	 */
	public void setActuReturnMoney(BigDecimal actuReturnMoney) {
		this.actuReturnMoney = actuReturnMoney;
	}

	/**
	 * @return String 店铺编号
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId 店铺编号
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return Integer 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status 申请状态
	 * 0：等待卖家审核
	 * 1：卖家审核通过
	 * 2：卖家审核拒绝
	 * 3：审核关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return Date 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return Integer 0：查看；1：审核；
	 */
	public Integer getAction() {
		return action;
	}

	/**
	 * @param action 0：查看；1：审核；
	 */
	public void setAction(Integer action) {
		this.action = action;
	}
	
}
