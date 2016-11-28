package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemIntegralDTO
 * @Description:会员商城积分信息表实体的DTO
 * @date: 2014-9-17
 * @author: 王楠
 * @version: V1.0
 */
public class MemIntegralDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -3792864545755721369L;

	/**积分编号*/
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
	
	public String getMemIntId() {
		return memIntId;
	}
	public void setMemIntId(String memIntId) {
		this.memIntId = memIntId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public BigDecimal getInAmt() {
		return inAmt;
	}
	public void setInAmt(BigDecimal inAmt) {
		this.inAmt = inAmt;
	}
	public BigDecimal getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}
	public BigDecimal getAvailableAmt() {
		return availableAmt;
	}
	public void setAvailableAmt(BigDecimal availableAmt) {
		this.availableAmt = availableAmt;
	}
	public BigDecimal getExpiredAmt() {
		return expiredAmt;
	}
	public void setExpiredAmt(BigDecimal expiredAmt) {
		this.expiredAmt = expiredAmt;
	}
	public BigDecimal getWillExpAmt() {
		return willExpAmt;
	}
	public void setWillExpAmt(BigDecimal willExpAmt) {
		this.willExpAmt = willExpAmt;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
