package com.paySystem.ic.web.dto.message;

import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:messageDTO
 * @Description:短信参数,使用关系DTO
 * @date: 2014-3-17下午04:55:28
 * @author: 张亚运
 * @version: V1.0
 */
/**
 * @ClassName:MessageDTO
 * @Description:TODO
 * @date: 2014-3-27上午09:28:52
 * @author: 张亚运
 * @version: V1.0
 */
public class MessageDTO extends BaseDTO{
	
	/**
	 * 参数id
	 */
	private String mfpId;
	/**
	 * 使用关系id
	 */
	private String mprId;
	/**
	 * 参数名称
	 */
	private String messName;
	/**
	 * 服务类型
	 * 0:按条收费
	 * 1:按月收费
	 */
	private Integer messType;
	/**
	 * 短信条数
	 */
	private Integer messPeriod;
	/**
	 * 费用
	 */
	private BigDecimal messFee;
	/**
	 * 最低条数
	 */
	private Integer miniPeriod;
	/**
	 * 单条费用
	 */
	private BigDecimal singleFee;
	
	/**
	 * 参数使用状态
	 * 0:删除
	 * 1:启用
	 * 2:禁用
	 */
	private Integer useSign;
	/**
	 * 操作人
	 */
	private String proposer;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 参数描述
	 */
	private String mfpDesc;
	/**
	 * 关系使用状态
	 */
	private Integer state;
	/**
	 * 机构/商户编号
	 */
	private String orgMerId;
	/**
	 * 所属机构编号
	 */
	private String parentOrgId;
	/**
	 * 开始时间
	 */
	private String beginTime;
	/**
	 * 结束时间
	 */
	private String endTime;	
	/**
	 * 机构编号
	 */
	private String orgId;
	/**
	 * 商户编号
	 */
	private String merId;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 机构商户名称
	 */
	private String orgMerName;
	/**
	 * 使用期限
	 */
	private Integer useLives;
	/**
	 * 是否使用帮助页面
	 * 0 否       1 是
	 */
	private Integer helpSign;
	
	public String getMfpId() {
		return mfpId;
	}
	public void setMfpId(String mfpId) {
		this.mfpId = mfpId;
	}
	
	public String getMprId() {
		return mprId;
	}
	public void setMprId(String mprId) {
		this.mprId = mprId;
	}
	public String getMessName() {
		return messName;
	}
	public void setMessName(String messName) {
		this.messName = messName;
	}
	public Integer getMessType() {
		return messType;
	}
	public void setMessType(Integer messType) {
		this.messType = messType;
	}
	
	public Integer getMessPeriod() {
		return messPeriod;
	}
	public void setMessPeriod(Integer messPeriod) {
		this.messPeriod = messPeriod;
	}
	public BigDecimal getMessFee() {
		return messFee;
	}
	public void setMessFee(BigDecimal messFee) {
		this.messFee = messFee;
	}
	public Integer getMiniPeriod() {
		return miniPeriod;
	}
	public void setMiniPeriod(Integer miniPeriod) {
		this.miniPeriod = miniPeriod;
	}
	public BigDecimal getSingleFee() {
		return singleFee;
	}
	public void setSingleFee(BigDecimal singleFee) {
		this.singleFee = singleFee;
	}
	public Integer getUseSign() {
		return useSign;
	}
	public void setUseSign(Integer useSign) {
		this.useSign = useSign;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getMfpDesc() {
		return mfpDesc;
	}
	public void setMfpDesc(String mfpDesc) {
		this.mfpDesc = mfpDesc;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOrgMerId() {
		return orgMerId;
	}
	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
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
	public String getOrgMerName() {
		return orgMerName;
	}
	public void setOrgMerName(String orgMerName) {
		this.orgMerName = orgMerName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public Integer getUseLives() {
		return useLives;
	}
	public void setUseLives(Integer useLives) {
		this.useLives = useLives;
	}
	public Integer getHelpSign() {
		return helpSign;
	}
	public void setHelpSign(Integer helpSign) {
		this.helpSign = helpSign;
	}
	
}
