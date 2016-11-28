package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;
public class SaleCardRecordDTO extends BaseDTO implements Serializable{

	/**
	* @Fields serialVersionUID 
	* @author lily
	* @date 2013-12-26 上午10:28:12
	*/
	private static final long serialVersionUID = 1975220231653891076L;
	
	private String cardNo;//卡号
	private String binId;
	private String binName;//卡名称
	private String orgId; // 发卡机构代码
	private String orgName;
	private String initAmt; //面值(售卡时卡片初始金额￥)
	private String saleOrderId; //售卡订单编号 8位日期+流水号
	private String createTime; //创建时间
	private Integer status;  //订单状态0：待审核；	1：待修改；2：等待确认；(针对支票到账)	3：订单成功；4：订单失败；
	private Date from; //起始日期
	private Date to; //终止日期  
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSaleOrderId() {
		return saleOrderId;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public String getInitAmt() {
		return initAmt;
	}
	public void setInitAmt(String initAmt) {
		this.initAmt = initAmt;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}


	
	
	
}
