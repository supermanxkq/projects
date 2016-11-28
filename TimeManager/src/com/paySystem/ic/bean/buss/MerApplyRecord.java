package com.paySystem.ic.bean.buss;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 	商户活动信息关联bean
 * @ClassName:MerPromotion
 * @Description:商户活动信息关联
 * @date: 2014-8-18 上午08:29:36
 * @author: luckygoup
 * @version: V1.0
 */
@Entity
@Table(name = "B_MerApplyRecord")
public class MerApplyRecord implements Serializable {
	
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
     private byte[] descr;
     /**创建时间*/
     private Date createTime;
     /**更新时间*/
     private Date updateTime;
     /**操作人Id*/
     private String operatorId;
     

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
    
    @Column(name="proid", nullable=false)

    public Integer getProid() {
        return this.proid;
    }
    
    public void setProid(Integer proid) {
        this.proid = proid;
    }
    
    @Column(name="merid", nullable=false, length=15)

    public String getMerid() {
        return this.merid;
    }
    
    public void setMerid(String merid) {
        this.merid = merid;
    }
     
    @Lob
    @Basic(fetch=FetchType.LAZY)
	@Column(name = "descr",columnDefinition="BLOB")
	public byte[] getDescr() {
		return this.descr;
	}

	public void setDescr(byte[] descr) {
		this.descr = descr;
	}
	
    @Temporal(TemporalType.DATE)
    @Column(name="createTime", nullable=false, length=10)

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="updateTime", nullable=false, length=10)

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="operatorId", nullable=false, length=30)

    public String getOperatorId() {
        return this.operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Column(name="status", nullable=false, length=1)
    public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
