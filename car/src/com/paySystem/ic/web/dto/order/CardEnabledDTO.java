package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class CardEnabledDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	//激活订单编号
	private String enabledId;
	//订单编号
	private String orderId;
	//所属机构Id
	private String organId;
	//所属商户
	private String merId;
	//所属机构名称
	private String organName;
	//激活状态
	private Integer status;
	//激活操作人
	private String operator;
	//创建时间
	private Date createTime;
	//激活时间
	private Date enanbleTime;
	//激活描述
	private String enableDesc;
	
	public String getEnabledId() {
		return enabledId;
	}
	public void setEnabledId(String enabledId) {
		this.enabledId = enabledId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEnanbleTime() {
		return enanbleTime;
	}
	public void setEnanbleTime(Date enanbleTime) {
		this.enanbleTime = enanbleTime;
	}
	public String getEnableDesc() {
		return enableDesc;
	}
	public void setEnableDesc(String enableDesc) {
		this.enableDesc = enableDesc;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
}
