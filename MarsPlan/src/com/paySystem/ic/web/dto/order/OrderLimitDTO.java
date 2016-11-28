package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class OrderLimitDTO extends BaseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**订单审核限额编号**/
	private String orderLimitId;
	/**订单审核限额标志 0：所属机构  1：所属商户 **/
	private Integer deptSign;
	/**所属机构**/
	private String organId;
	/**所属机构名称***/
	private String name ;
	/**所属商户**/
	private String merId;
	/**所属商户名称****/
	private String merName;
	/**卡Bin编号***/
	private String binId;
	/**订单审核限额**/
	private BigDecimal orderLimitAmt;
	/**订单审核限额状态 0：启用；1：禁用；2：删除**/
	private Integer status;
	/**创建人编号**/
	private String operatorId;
	/**创建时间**/
	private Date createTime;
	/**更新时间**/
	private Date updateTime;
	/***审核限额名称***/
	private String orderLName;
	/***所属机构**/
	private String orgMerId;
	/**备注信息****/
	private String descr;
	
	public String getOrderLimitId() {
		return orderLimitId;
	}
	public void setOrderLimitId(String orderLimitId) {
		this.orderLimitId = orderLimitId;
	}
	public Integer getDeptSign() {
		return deptSign;
	}
	public void setDeptSign(Integer deptSign) {
		this.deptSign = deptSign;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public BigDecimal getOrderLimitAmt() {
		return orderLimitAmt;
	}
	public void setOrderLimitAmt(BigDecimal orderLimitAmt) {
		this.orderLimitAmt = orderLimitAmt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrderLName() {
		return orderLName;
	}
	public void setOrderLName(String orderLName) {
		this.orderLName = orderLName;
	}
	public String getOrgMerId() {
		return orgMerId;
	}
	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
}
