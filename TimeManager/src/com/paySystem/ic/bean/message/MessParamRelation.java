package com.paySystem.ic.bean.message;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:MessParamUsing
 * @Description:参数使用关系表 实体
 * @date: 2014-3-19上午11:43:59
 * @author: 张亚运
 * @version: V1.0
 */

@Entity
@Table(name="B_MessParamRelation")
public class MessParamRelation implements Serializable{

	/**
	 * 流水号
	 */
	private static final long serialVersionUID = -4766252504840849133L;
	/**
	 * 记录Id
	 */
	private String mprId;
	/**
	 * 短信参数Id
	 */
	private String mfpId;
	/**
	 * 关系使用状态
	 * 0 删除；1 使用；2 禁用
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
	private Date beginTime;
	/**
	 * 截止时间
	 */
	private Date endTime;
	/**
	 * 操作人
	 */
	private String proposer;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 参数描述
	 */
	private String mprDesc;
	
	
	@Id
	@Column(length=8)
	public String getMprId() {
		return mprId;
	}
	public void setMprId(String mprId) {
		this.mprId = mprId;
	}
	
	@Column(length=8)
	public String getMfpId() {
		return mfpId;
	}
	public void setMfpId(String mfpId) {
		this.mfpId = mfpId;
	}
	
	@Column(columnDefinition="CHAR(1)")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(length=15)
	public String getOrgMerId() {
		return orgMerId;
	}
	public void setOrgMerId(String orgMerId) {
		this.orgMerId = orgMerId;
	}
	
	@Column()
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(length=20)
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column
	public String getMprDesc() {
		return mprDesc;
	}
	public void setMprDesc(String mprDesc) {
		this.mprDesc = mprDesc;
	}
	
	
}
