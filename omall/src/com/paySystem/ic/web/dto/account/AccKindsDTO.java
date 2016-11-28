package com.paySystem.ic.web.dto.account;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class AccKindsDTO extends BaseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2230951605058473844L;

	private Integer kindId; //类型编号
	private String kindName; //类型名称
	private String orgId; //机构编号
	private String orgName;	
	private String merId; //商户号	
	private String merName;
	private Integer accSign; //消费类型    0：金额；1：按次数
	private Integer saleTimesLimit; //当日消费次数显示(0)表示无限制
	private String beginDate; //有效起始日期
	private String endDate; //有效终止日期  
	private Integer status; //类型启用状态  0：删除；1：启用；2： 禁用
	private String updateTime; //更新时间
	private String descr; //类型描述
	private Integer withDraType;//Withdrawals是否可提现： 0：否  1：是
	private Integer transAccType;//Transfer accounts是否可转账： 0：否    1：是
	private Integer consType;//Consumption是否允许消费 0：否    1：是
	private Integer rechargeType;//Recharge是否可充值  0：否    1：是
	
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public Integer getAccSign() {
		return accSign;
	}
	public void setAccSign(Integer accSign) {
		this.accSign = accSign;
	}
	public Integer getSaleTimesLimit() {
		return saleTimesLimit;
	}
	public void setSaleTimesLimit(Integer saleTimesLimit) {
		this.saleTimesLimit = saleTimesLimit;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "AccKindDTO [OrgId=" + orgId + ", accSign=" + accSign
				+ ", beginDate=" + beginDate + ", descr=" + descr
				+ ", endDate=" + endDate + ", kindId=" + kindId + ", kindName="
				+ kindName + ", merId=" + merId + ", merName=" + merName
				+ ", orgName=" + orgName + ", saleTimesLimit=" + saleTimesLimit
				+ ", status=" + status + ", updateTime=" + updateTime + "]";
	}
	public void setWithDraType(Integer withDraType) {
		this.withDraType = withDraType;
	}
	public Integer getWithDraType() {
		return withDraType;
	}
	public void setTransAccType(Integer transAccType) {
		this.transAccType = transAccType;
	}
	public Integer getTransAccType() {
		return transAccType;
	}
	
	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}
	public Integer getRechargeType() {
		return rechargeType;
	}
	public void setConsType(Integer consType) {
		this.consType = consType;
	}
	public Integer getConsType() {
		return consType;
	}
	
}
