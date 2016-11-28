package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:MemIntegral
 * @Description:会员商城积分信息表
 * @date: 2014-9-17
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "C_MemIntegral")
public class MemIntegral implements Serializable{

	private static final long serialVersionUID = -4078359356380539065L;

	/**编号*/
	private String memIntId;
	/**会员编号*/
	private String memId;
	/**入账总数*/
	private BigDecimal inAmt;
	/**出账总数*/
	private BigDecimal outAmt;
	/**可用积分*/
	private BigDecimal availableAmt;
	/**过期积分*/
	private BigDecimal expiredAmt;
	/**将过期积分*/
	private BigDecimal willExpAmt;
	/**更新时间*/
	private Date updateTime;
	@Id
    @Column(length = 15)
	public String getMemIntId() {
		return memIntId;
	}
	public void setMemIntId(String memIntId) {
		this.memIntId = memIntId;
	}
	@Column(length=10)
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getInAmt() {
		return inAmt;
	}
	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getAvailableAmt() {
		return availableAmt;
	}
	public void setAvailableAmt(BigDecimal availableAmt) {
		this.availableAmt = availableAmt;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getExpiredAmt() {
		return expiredAmt;
	}
	public void setExpiredAmt(BigDecimal expiredAmt) {
		this.expiredAmt = expiredAmt;
	}
	@Column(columnDefinition="DECIMAL(13,2)")
	public BigDecimal getWillExpAmt() {
		return willExpAmt;
	}
	public void setWillExpAmt(BigDecimal willExpAmt) {
		this.willExpAmt = willExpAmt;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
