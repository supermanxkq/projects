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
 * @ClassName:MerSettTotal
 * @Description:商户结算统计实体
 * @date: 2013-12-24下午01:11:46
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="R_MerSettTotal")
public class MerSettTotal implements Serializable {

	private static final long serialVersionUID = 4912040676023318675L;
	
	
	/** 结算信息记录号 */
	private Integer id;
	/** 记录所属商户 */	
	private Merchants merchants;
	/** 结算标志
	 *         0:未结算
	           1:已结算 */
	private Integer flag;
	/** 本周期起始时间  */
	private Date beginTime;
	/**本周期结束时间*/
	private Date endTime;
	/** 消费总金额  */
	private BigDecimal consAmt;
	/**消费总手续费*/
	private BigDecimal consCommis;
	/**退货总金额*/
	private BigDecimal returnAmt;
	/** 退货商品 - 原交易手续费 */
	private BigDecimal returnCommis;
	/** 充值总金额 */
	private BigDecimal rechAmt;
	/** 上次结余 */
	private BigDecimal lastBalance;
	/** 本次结算金额 */
	private BigDecimal supportSettAmt;
	/** 实际交付金额 */
	private BigDecimal actualSettAmt;
	/** 本次结余 */
	private BigDecimal thisTimeBalance;
	/** 结算时间 */
	private Date settTime;
	/** 操作人 Id*/
	private String operator;
	/** 更新时间 */
	private Date updateTime;
	
	
/*	@Id
	@SequenceGenerator(name="R_MerSettTotal_SEQ",sequenceName="R_MerSettTotal_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="R_MerSettTotal_SEQ")*/
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
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="merId")                                                                                                                                                                        
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column(columnDefinition="char(1) default '0'")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getConsAmt() {
		return consAmt;
	}
	public void setConsAmt(BigDecimal consAmt) {
		this.consAmt = consAmt;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getConsCommis() {
		return consCommis;
	}
	public void setConsCommis(BigDecimal consCommis) {
		this.consCommis = consCommis;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getReturnAmt() {
		return returnAmt;
	}
	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}
	@Column(columnDefinition="DECIMAL(16,6)")
	public BigDecimal getReturnCommis() {
		return returnCommis;
	}
	public void setReturnCommis(BigDecimal returnCommis) {
		this.returnCommis = returnCommis;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getLastBalance() {
		return lastBalance;
	}
	public void setLastBalance(BigDecimal lastBalance) {
		this.lastBalance = lastBalance;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getSupportSettAmt() {
		return supportSettAmt;
	}
	public void setSupportSettAmt(BigDecimal supportSettAmt) {
		this.supportSettAmt = supportSettAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getActualSettAmt() {
		return actualSettAmt;
	}
	public void setActualSettAmt(BigDecimal actualSettAmt) {
		this.actualSettAmt = actualSettAmt;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getThisTimeBalance() {
		return thisTimeBalance;
	}
	public void setThisTimeBalance(BigDecimal thisTimeBalance) {
		this.thisTimeBalance = thisTimeBalance;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getSettTime() {
		return settTime;
	}
	public void setSettTime(Date settTime) {
		this.settTime = settTime;
	}
	
	@Column(length=20)
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(columnDefinition="DECIMAL(15,2)")
	public BigDecimal getRechAmt() {
		return rechAmt;
	}
	public void setRechAmt(BigDecimal rechAmt) {
		this.rechAmt = rechAmt;
	}
	
	
	
}
