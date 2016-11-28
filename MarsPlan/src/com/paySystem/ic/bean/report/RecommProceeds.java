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

@Entity
@Table(name="R_RecommProceeds")
public class RecommProceeds implements Serializable{

	private static final long serialVersionUID = 1L;
    
	/**推荐人编号*/
	private String recommId;
	/**结算状态
	 * 0：已结算
	 * 1：未结算
	 */
	private Integer status;
	/**推荐人类型
	 * 0：个人
	 * 1：企业
	 */
	private Integer recommType;
	
	/**推荐人数*/
	private Integer recommNum;
	
	/**收益金额*/
	private BigDecimal proceedAmt;
	
	/**结算时间*/
	private Date settTime;

	@Id
	@Column
	public String getRecommId() {
		return recommId;
	}

	public void setRecommId(String recommId) {
		this.recommId = recommId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getRecommType() {
		return recommType;
	}

	public void setRecommType(Integer recommType) {
		this.recommType = recommType;
	}

	@Column(columnDefinition = "int")
	public Integer getRecommNum() {
		return recommNum;
	}

	public void setRecommNum(Integer recommNum) {
		this.recommNum = recommNum;
	}

	@Column(columnDefinition = "DECIMAL(10,2)")
	public BigDecimal getProceedAmt() {
		return proceedAmt;
	}

	public void setProceedAmt(BigDecimal proceedAmt) {
		this.proceedAmt = proceedAmt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getSettTime() {
		return settTime;
	}

	public void setSettTime(Date settTime) {
		this.settTime = settTime;
	}
}
