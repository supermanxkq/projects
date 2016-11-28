package com.paySystem.ic.bean.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omal
 * @ClassName:UAccounts
 * @Description:线上账户信息表
 * @date: 2014-10-28
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "U_Account")
public class Accounts implements Serializable{

	private static final long serialVersionUID = 8890577957407222298L;

	/**账户编号*/
	private String onAccId;
	/**账户类型*/
	private Integer onAccTypeId;
	/**会员编号*/
	private String memId;
	/**入账总数*/
	private BigDecimal onInAmt;
	/**出账总数*/
	private BigDecimal onOutAmt;
	/**当前积分总额*/
	private BigDecimal pointsNum;
	/**更新时间*/
	private Date updateTime;
	@Id
	@Column(length = 15)
	public String getOnAccId() {
		return onAccId;
	}
	public void setOnAccId(String onAccId) {
		this.onAccId = onAccId;
	}
	@Column
	public Integer getOnAccTypeId() {
		return onAccTypeId;
	}
	public void setOnAccTypeId(Integer onAccTypeId) {
		this.onAccTypeId = onAccTypeId;
	}
	@Column(length=10)
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getOnInAmt() {
		return onInAmt;
	}
	public void setOnInAmt(BigDecimal onInAmt) {
		this.onInAmt = onInAmt;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getOnOutAmt() {
		return onOutAmt;
	}
	public void setOnOutAmt(BigDecimal onOutAmt) {
		this.onOutAmt = onOutAmt;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(BigDecimal pointsNum) {
		this.pointsNum = pointsNum;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
