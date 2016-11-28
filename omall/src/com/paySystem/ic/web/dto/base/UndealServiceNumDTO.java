package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:UndealServiceNumDTO
 * @Description:商城后台首界面待处理业务统计数实体的DTO
 * @date: 2014-10-22
 * @author: 王楠
 * @version: V1.0
 */
public class UndealServiceNumDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 8106218743637655339L;
	
	/**编号*/
	private Integer dealId;
	/**商户号*/
	private String merId;
	/**等待发货的订单数*/
    private Integer noDelivNum;
    /**已发货的订单数*/
    private Integer postedNum;
    /**收到的投诉*/
    private Integer complaintsNum;
    /**下次结算日期*/
    private Date nextSettTime;
    /**等待买家付款的订单数*/
    private Integer noPaidNum;
    /**已成功的订单数*/
    private Integer completedNum;
    /**退款中的订单数*/
    private Integer returnNum;
    /**未阅读的商品评价数*/
    private Integer unReadNum;
    /**tab页跳转标志*/
    private Integer type;
    /**用户申请为商户的审核 */
    private Integer merCheckNum;
    
	public Integer getMerCheckNum() {
		return merCheckNum;
	}
	public void setMerCheckNum(Integer merCheckNum) {
		this.merCheckNum = merCheckNum;
	}
	public Integer getDealId() {
		return dealId;
	}
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public Integer getNoDelivNum() {
		return noDelivNum;
	}
	public void setNoDelivNum(Integer noDelivNum) {
		this.noDelivNum = noDelivNum;
	}
	public Integer getPostedNum() {
		return postedNum;
	}
	public void setPostedNum(Integer postedNum) {
		this.postedNum = postedNum;
	}
	public Integer getComplaintsNum() {
		return complaintsNum;
	}
	public void setComplaintsNum(Integer complaintsNum) {
		this.complaintsNum = complaintsNum;
	}
	public Date getNextSettTime() {
		return nextSettTime;
	}
	public void setNextSettTime(Date nextSettTime) {
		this.nextSettTime = nextSettTime;
	}
	public Integer getNoPaidNum() {
		return noPaidNum;
	}
	public void setNoPaidNum(Integer noPaidNum) {
		this.noPaidNum = noPaidNum;
	}
	public Integer getCompletedNum() {
		return completedNum;
	}
	public void setCompletedNum(Integer completedNum) {
		this.completedNum = completedNum;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public Integer getUnReadNum() {
		return unReadNum;
	}
	public void setUnReadNum(Integer unReadNum) {
		this.unReadNum = unReadNum;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	
    
}
