package com.paySystem.ic.bean.message;

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
 * @ClassName:MessFeeParam
 * @Description:TODO
 * @date: 2014-3-19上午11:43:00
 * @author: 张亚运
 * @version: V1.0
 */

@Entity
@Table(name="B_MessFeeParam")
public class MessFeeParam implements Serializable{
	
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 4730378880757297542L;
	/**
	 * 参数记录Id
	 */
	private String mfpId;
	/**
	 * 参数名称
	 */
	private String messName;
	/**
	 * 短信服务类型
	 * 0:条；1:月
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
	 * 机构商户标志
	 * 0 机构；1 商户
	 */
	private Integer orgMerSign;
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
	private String mfpDesc;
	
	
	@Id
	@Column(length=8)
	public String getMfpId() {
		return mfpId;
	}
	public void setMfpId(String mfpId) {
		this.mfpId = mfpId;
	}
	
	@Column(length=60)
	public String getMessName() {
		return messName;
	}
	public void setMessName(String messName) {
		this.messName = messName;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column
	public String getMfpDesc() {
		return mfpDesc;
	}
	public void setMfpDesc(String mfpDesc) {
		this.mfpDesc = mfpDesc;
	}
	
	
	
	
}
