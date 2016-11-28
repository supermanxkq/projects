package com.paySystem.ic.bean.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:OrganSettTotal
 * @Description:机构结算报表查询
 * @date: 2014-3-12上午10:48:22
 * @author: 王月
 * @version: V1.0
 */
@Entity
@Table(name="R_OrganSettTotal")
public class OrganSettTotal implements Serializable{
	private static final long serialVersionUID = -8973801135979035525L;
	/**结算信息记录号*/
	private Integer organSettId;
	/**机构id*/
	private String organId;
	/**结算状态
	      0:未结算
	      1:已结算*/
	private String status;
	/** 操作员编号 */
	private String operator;
	/** 本机构卡充值金额 */
	private BigDecimal ownOrgRecharge;
	/** 本机构卡充值结算金额 */
	private BigDecimal ownOrgRechCount;
	/** 本机构卡消费金额 */
	private BigDecimal ownOrgConsume;
	/** 本机构卡退货金额 */
	private BigDecimal ownOrgRetGoods;
	/** 本机构卡总手续费 */
	private BigDecimal totalCommis;
	/** 本机构结算金额 */
	private BigDecimal ownSettle;
	/** 其它机构消费金额 */
	private BigDecimal othOrgConsume;
	/** 其它机构卡退货金额 */
	private BigDecimal othOrgRetGoods;
	/** 其它机构卡总手续费 */
	private BigDecimal othOrgRetCommis;
	/** 其它机构卡结算金额 */
	private BigDecimal otherSettle;
	/** 本机构卡对外消费金额 */
	private BigDecimal ownOutConsume;
	/** 本机构卡对外退货金额 */
	private BigDecimal ownOutRetGoods;
	/** 本机构卡对外总手续费 */
	private BigDecimal ownOutCommisT;
	/** 本机构卡对外结算金额 */
	private BigDecimal ownOutSettle;
	/** 总结算金额 */
	private BigDecimal totalSettAmt;
	/** 结算时间 */
	private Date settleTime;
	/** 更新时间 */
	private Date updateTime;
	
	
	
	
	public void setOrganSettId(Integer organSettId) {
		this.organSettId = organSettId;
	}
/*	@Id
	@SequenceGenerator(name="C_ORGANSETTTOTAL_SEQ",sequenceName = "C_ORGANSETTTOTAL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="C_ORGANSETTTOTAL_SEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getOrganSettId() {
		return organSettId;
	}
	
	@Column(columnDefinition = "varchar(8)")
	public void setOrganId(String organId) {
		this.organId = organId;
	}	
	public String getOrganId() {
		return organId;
	}
	
	@Column(columnDefinition="char(1) default '0'")
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOrgRecharge(BigDecimal ownOrgRecharge) {
		this.ownOrgRecharge = ownOrgRecharge;
	}
	public BigDecimal getOwnOrgRecharge() {
		return ownOrgRecharge;
	}
		
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOrgRechCount(BigDecimal ownOrgRechCount) {
		this.ownOrgRechCount = ownOrgRechCount;
	}
	public BigDecimal getOwnOrgRechCount() {
		return ownOrgRechCount;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOrgConsume(BigDecimal ownOrgConsume) {
		this.ownOrgConsume = ownOrgConsume;
	}
	public BigDecimal getOwnOrgConsume() {
		return ownOrgConsume;
	}
		
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOrgRetGoods(BigDecimal ownOrgRetGoods) {
		this.ownOrgRetGoods = ownOrgRetGoods;
	}
	public BigDecimal getOwnOrgRetGoods() {
		return ownOrgRetGoods;
	}
		
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setTotalCommis(BigDecimal totalCommis) {
		this.totalCommis = totalCommis;
	}
	public BigDecimal getTotalCommis() {
		return totalCommis;
	}
		
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setOwnSettle(BigDecimal ownSettle) {
		this.ownSettle = ownSettle;
	}
	public BigDecimal getOwnSettle() {
		return ownSettle;
	}
		
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOthOrgConsume(BigDecimal othOrgConsume) {
		this.othOrgConsume = othOrgConsume;
	}
	public BigDecimal getOthOrgConsume() {
		return othOrgConsume;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOthOrgRetGoods(BigDecimal othOrgRetGoods) {
		this.othOrgRetGoods = othOrgRetGoods;
	}
	public BigDecimal getOthOrgRetGoods() {
		return othOrgRetGoods;
	}
		
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setOthOrgRetCommis(BigDecimal othOrgRetCommis) {
		this.othOrgRetCommis = othOrgRetCommis;
	}
	public BigDecimal getOthOrgRetCommis() {
		return othOrgRetCommis;
	}
	
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setOtherSettle(BigDecimal otherSettle) {
		this.otherSettle = otherSettle;
	}
	public BigDecimal getOtherSettle() {
		return otherSettle;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOutConsume(BigDecimal ownOutConsume) {
		this.ownOutConsume = ownOutConsume;
	}
	public BigDecimal getOwnOutConsume() {
		return ownOutConsume;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public void setOwnOutRetGoods(BigDecimal ownOutRetGoods) {
		this.ownOutRetGoods = ownOutRetGoods;
	}
	public BigDecimal getOwnOutRetGoods() {
		return ownOutRetGoods;
	}
	
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setOwnOutCommisT(BigDecimal ownOutCommisT) {
		this.ownOutCommisT = ownOutCommisT;
	}
	public BigDecimal getOwnOutCommisT() {
		return ownOutCommisT;
	}
	
	@Column(columnDefinition="DECIMAL(16,6)")
	public void setOwnOutSettle(BigDecimal ownOutSettle) {
		this.ownOutSettle = ownOutSettle;
	}
	public BigDecimal getOwnOutSettle() {
		return ownOutSettle;
	}
	
	@Column(columnDefinition="DECIMAL(15,3)")
	public void setTotalSettAmt(BigDecimal totalSettAmt) {
		this.totalSettAmt = totalSettAmt;
	}
	public BigDecimal getTotalSettAmt() {
		return totalSettAmt;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}
	public Date getSettleTime() {
		return settleTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperator() {
		return operator;
	}

}


