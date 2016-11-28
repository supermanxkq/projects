package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:BuyOilApplDTO
 * @Description:购油申请DTO
 * @date: 2014-5-26下午09:08:03
 * @author: 张亚运
 * @version: V1.0
 */
public class BuyOilApplDTO extends BaseDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	/**售卡订单编号 8位日期+流水号*/
	private String orderId;
	
	/**申请购油方编号*/
	private String orgMerId;
	
	/**申请购油方名称*/
	private String orgMerName;
	
	/**受理方编号*/
	private String acceptId;
	
	/**当前油价*/
	private BigDecimal oilPrice; 
	
	/**购油数量*/
	private BigDecimal oilQty;
	
	/**油品编号*/
	private String oilTypeId;
	
	/**油厂编号*/
	private String oilFactId;
	
	/**油品名称*/
	private String oilTypeName;
	
	/** 订单总金额(￥)当前油价*购油数量*/
	private BigDecimal orderAmt; 
	
	/**订单状态
	 * 0：待审核；
	 * 1：审核未通过； 
	 * 2：订单成功；
	 * 3：订单失败；
	 */
	private Integer status;
	
	/**订单申请人*/
	private String proposerName;
	
	/**订单申请人ID*/
	private String proposer;
	
	/**申请人电话*/
	private String telNo;
	
	/**操作人*/
	private String operId;
	
	/**创建时间*/
	private Date createTime;
	
	/**创建时间*/
	private String beginTime;
	
	/**创建时间*/
	private String endTime;
	
	/**申请描述*/
	private String descR;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgMerId() {
		return orgMerId;
	}

	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}

	public BigDecimal getOilPrice() {
		return oilPrice;
	}

	public void setOilPrice(BigDecimal oilPrice) {
		this.oilPrice = oilPrice;
	}

	public BigDecimal getOilQty() {
		return oilQty;
	}

	public void setOilQty(BigDecimal oilQty) {
		this.oilQty = oilQty;
	}

	public String getOilTypeId() {
		return oilTypeId;
	}

	public void setOilTypeId(String oilTypeId) {
		this.oilTypeId = oilTypeId;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescR() {
		return descR;
	}

	public void setDescR(String descR) {
		this.descR = descR;
	}

	public String getOrgMerName() {
		return orgMerName;
	}

	public void setOrgMerName(String orgMerName) {
		this.orgMerName = orgMerName;
	}

	public String getOilTypeName() {
		return oilTypeName;
	}

	public void setOilTypeName(String oilTypeName) {
		this.oilTypeName = oilTypeName;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public String getOilFactId() {
		return oilFactId;
	}

	public void setOilFactId(String oilFactId) {
		this.oilFactId = oilFactId;
	}

	public String getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	
	
}
