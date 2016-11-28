package com.paySystem.ic.web.dto.report;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class MessConsTotalDTO extends BaseDTO{

	
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 4730378880757297542L;
	/**
	 * 短信费用记录Id
	 */
	private String mctId;
	/**
	 * 商家ID
	 */
	private String merId;
	/**
	 * 短信服务类型
	 */
	private Integer messType;
	/**
	 * 信息费用
	 */
	private BigDecimal messFee;
	/**
	 * 条数
	 */
    private Integer messPeriod;
	/**
	 * 参数使用状态
	 * 0 删除；1 启用 ；2 禁用
	 */
	private Integer useSign;
	/**
	 * 平台商户标志
	 * 0 平台；1 商户
	 */
	private Integer orgMerSign;
	/**
	 * 操作人
	 */
	private String proposer;
	/**
	 * 更新时间
	 */
	private String createTime;
	
	private String beginDate;
	
	private String endDate;
	
	public String getMctId() {
		return mctId;
	}
	public void setMctId(String mctId) {
		this.mctId = mctId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public Integer getMessType() {
		return messType;
	}
	public void setMessType(Integer messType) {
		this.messType = messType;
	}
	public BigDecimal getMessFee() {
		return messFee;
	}
	public void setMessFee(BigDecimal messFee) {
		this.messFee = messFee;
	}
	public Integer getMessPeriod() {
		return messPeriod;
	}
	public void setMessPeriod(Integer messPeriod) {
		this.messPeriod = messPeriod;
	}
	public Integer getUseSign() {
		return useSign;
	}
	public void setUseSign(Integer useSign) {
		this.useSign = useSign;
	}
	public Integer getOrgMerSign() {
		return orgMerSign;
	}
	public void setOrgMerSign(Integer orgMerSign) {
		this.orgMerSign = orgMerSign;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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


	
}
