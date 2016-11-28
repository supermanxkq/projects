package com.paySystem.ic.bean.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:MessConsTotal
 * @date: 2014-11-13上午11:43:00
 * @author: 张国法
 * @version: V1.0
 */

@Entity
@Table(name="B_MessConsTotal")
public class MessConsTotal implements Serializable{
	
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
	private Date createTime;

	
	
	@Id
	@Column(length=8)
	public String getMctId() {
		return mctId;
	}
	public void setMctId(String mctId) {
		this.mctId = mctId;
	}
	
	@Column(length=18)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	@Column(columnDefinition="CHAR(1)")
	public Integer getMessType() {
		return messType;
	}


	public void setMessType(Integer messType) {
		this.messType = messType;
	}
	
	@Column(columnDefinition="DECIMAL(8,3)")
	public BigDecimal getMessFee() {
		return messFee;
	}
	public void setMessFee(BigDecimal messFee) {
		this.messFee = messFee;
	}
	
	@Column(columnDefinition="DECIMAL(8)")
	public Integer getMessPeriod() {
		return messPeriod;
	}
	public void setMessPeriod(Integer messPeriod) {
		this.messPeriod = messPeriod;
	}
	
	@Column(columnDefinition="CHAR(1)")
	public Integer getUseSign() {
		return useSign;
	}
	public void setUseSign(Integer useSign) {
		this.useSign = useSign;
	}
	
	@Column(columnDefinition="CHAR(1)")
	public Integer getOrgMerSign() {
		return orgMerSign;
	}
	public void setOrgMerSign(Integer orgMerSign) {
		this.orgMerSign = orgMerSign;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
