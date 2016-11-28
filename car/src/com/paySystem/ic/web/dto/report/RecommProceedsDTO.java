package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class RecommProceedsDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 推荐人编号 */
	private String recommId;
	/** 推荐人姓名 */
	private String recommName;
	/** 推荐人手机号 */
	private String recommTeleNo;
	/**
	 * 结算状态 0：已结算 1：未结算
	 */
	private Integer status;
	/**
	 * 推荐人类型 0：个人 1：企业
	 */
	private Integer recommType;

	/** 推荐人数 */
	private Integer recommNum;

	/** 收益金额 */
	private BigDecimal proceedAmt;

	/** 结算时间 */
	private Date settTime;
	/** 结算起期 */
	private String beginDate;
	/** 结算止期 */
	private String endDate;

	public String getRecommId() {
		return recommId;
	}

	public void setRecommId(String recommId) {
		this.recommId = recommId;
	}

	public String getRecommName() {
		return recommName;
	}

	public void setRecommName(String recommName) {
		this.recommName = recommName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRecommTeleNo() {
		return recommTeleNo;
	}

	public void setRecommTeleNo(String recommTeleNo) {
		this.recommTeleNo = recommTeleNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRecommType() {
		return recommType;
	}

	public void setRecommType(Integer recommType) {
		this.recommType = recommType;
	}

	public Integer getRecommNum() {
		return recommNum;
	}

	public void setRecommNum(Integer recommNum) {
		this.recommNum = recommNum;
	}

	public BigDecimal getProceedAmt() {
		return proceedAmt;
	}

	public void setProceedAmt(BigDecimal proceedAmt) {
		this.proceedAmt = proceedAmt;
	}

	public Date getSettTime() {
		return settTime;
	}

	public void setSettTime(Date settTime) {
		this.settTime = settTime;
	}

}
