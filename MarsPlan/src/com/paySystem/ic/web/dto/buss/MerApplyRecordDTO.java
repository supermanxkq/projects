package com.paySystem.ic.web.dto.buss;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:PayParamDTO
 * @Description:活动管理信息DTO类
 * @date: 2014-8-21下午05:36:24
 * @author: 赵瑞群
 * @version: V1.0
 */
public class MerApplyRecordDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**申请记录编号*/
    private Integer recordId;
    /**活动信息ID*/
    private Integer proid;
    /**商户编号*/
    private String merid;
    /**状态
		1：未审核
		2：审核通过
		3：审核不通过
		9：清退
    */
    private String status;
    
    /**审核意见*/
    private String descr;
    
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    /**操作人Id*/
    private String operatorId;
    
    public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public String getMerid() {
		return merid;
	}
	public void setMerid(String merid) {
		this.merid = merid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
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
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	

	
	
	
	
}
