package com.paySystem.ic.bean.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.base.Merchants;

/**
 * @ClassName:MerSettDay
 * @Description:商户日终跑批记录实体
 * @date: 2013-12-24下午01:34:04
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="R_MerSettDay")
public class MerSettDay implements Serializable{

	private static final long serialVersionUID = 2614411505460309585L;
	
	/** 日跑批结算信息记录号 */
	private Integer id;
	/** 统计的商户  */
	private Merchants merchants;
	/** 本机构卡充值金额 */
	private BigDecimal ownOrgRech;
	/** 本机构卡消费金额  */
	private BigDecimal ownOrgConsAmt;
	/** 本机构卡手续费 */
	private BigDecimal ownOrgCommis;
	/** 本机构卡退货金额 */
	private BigDecimal ownOrgRetAmt;
	/** 本机构卡：退货商品 - 原交易手续费 */
	private BigDecimal ownOrgRetCommis;
	/** 本机构卡：结算金额*/
	private BigDecimal ownOrgProxyAmt;
	/** 其它机构消费金额 */
	private BigDecimal othOrgConsAmt;
	/** 其它机构退货金额 */
	private BigDecimal othOrgRetAmt;
	/** 其它机构消费手续费  */
	private BigDecimal othOrgCommis;
	/**其它机构卡：退货商品 - 原交易手续费*/
	private BigDecimal othOrgRetCommis;
	/** 其它机构卡结算金额*/
	private BigDecimal othOrgProxyAmt;
	/** 总结算金额 :涉及到其它间机构结算时用到*/
	private BigDecimal totalProxyAmt;
	/** 结算业务发生时间 */
	private Date transDate;
	/** 跑批时间  */
	private Date createTime;
	
/*	@Id
	@SequenceGenerator(name="R_MerSettDay_SEQ",sequenceName="R_MerSettDay_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="R_MerSettDay_SEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional = true)
	@JoinColumn(name="merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOwnOrgRech() {
		return ownOrgRech;
	}
	public void setOwnOrgRech(BigDecimal ownOrgRech) {
		this.ownOrgRech = ownOrgRech;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOwnOrgConsAmt() {
		return ownOrgConsAmt;
	}
	public void setOwnOrgConsAmt(BigDecimal ownOrgConsAmt) {
		this.ownOrgConsAmt = ownOrgConsAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOwnOrgRetAmt() {
		return ownOrgRetAmt;
	}
	public void setOwnOrgRetAmt(BigDecimal ownOrgRetAmt) {
		this.ownOrgRetAmt = ownOrgRetAmt;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getOwnOrgCommis() {
		return ownOrgCommis;
	}
	public void setOwnOrgCommis(BigDecimal ownOrgCommis) {
		this.ownOrgCommis = ownOrgCommis;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getOwnOrgRetCommis() {
		return ownOrgRetCommis;
	}
	public void setOwnOrgRetCommis(BigDecimal ownOrgRetCommis) {
		this.ownOrgRetCommis = ownOrgRetCommis;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOthOrgConsAmt() {
		return othOrgConsAmt;
	}
	public void setOthOrgConsAmt(BigDecimal othOrgConsAmt) {
		this.othOrgConsAmt = othOrgConsAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOthOrgRetAmt() {
		return othOrgRetAmt;
	}
	public void setOthOrgRetAmt(BigDecimal othOrgRetAmt) {
		this.othOrgRetAmt = othOrgRetAmt;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getOthOrgCommis() {
		return othOrgCommis;
	}
	public void setOthOrgCommis(BigDecimal othOrgCommis) {
		this.othOrgCommis = othOrgCommis;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getOthOrgRetCommis() {
		return othOrgRetCommis;
	}
	public void setOthOrgRetCommis(BigDecimal othOrgRetCommis) {
		this.othOrgRetCommis = othOrgRetCommis;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, length = 10)
	public Date getTransDate() {
		return this.transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOwnOrgProxyAmt() {
		return ownOrgProxyAmt;
	}

	public void setOwnOrgProxyAmt(BigDecimal ownOrgProxyAmt) {
		this.ownOrgProxyAmt = ownOrgProxyAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getOthOrgProxyAmt() {
		return othOrgProxyAmt;
	}
	public void setOthOrgProxyAmt(BigDecimal othOrgProxyAmt) {
		this.othOrgProxyAmt = othOrgProxyAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getTotalProxyAmt() {
		return totalProxyAmt;
	}
	public void setTotalProxyAmt(BigDecimal totalProxyAmt) {
		this.totalProxyAmt = totalProxyAmt;
	}
	
	
	

}
