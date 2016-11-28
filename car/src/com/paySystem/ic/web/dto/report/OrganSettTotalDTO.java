package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;


public class OrganSettTotalDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**机构id*/
	private String organId;
	/** 机构名称  */
	private String orgName;
	/**结算状态
        0:未结算
        1:已结算*/
    private Integer status;
    /** 本机构结算金额 */
	private BigDecimal ownSettle;
	/** 其它机构卡结算金额 */
	private BigDecimal otherSettle;
	/** 本机构卡对外结算金额 */
	private BigDecimal ownOutSettle;
	/** 总结算金额 */
	private BigDecimal totalSettAmt;
	/** 操作员编号 */
	private String operator;
	/** 结算时间 */
	private String settleTime;
	/** 更新时间 */
	private String updateTime;
	/**界面开始时间*/
	private String beginDate;
	/**界面结束时间*/
	private String endDate;
	
	
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setOwnSettle(BigDecimal ownSettle) {
		this.ownSettle = ownSettle;
	}
	public BigDecimal getOwnSettle() {
		return ownSettle;
	}
	public void setOtherSettle(BigDecimal otherSettle) {
		this.otherSettle = otherSettle;
	}
	public BigDecimal getOtherSettle() {
		return otherSettle;
	}
	public void setOwnOutSettle(BigDecimal ownOutSettle) {
		this.ownOutSettle = ownOutSettle;
	}
	public BigDecimal getOwnOutSettle() {
		return ownOutSettle;
	}
	public void setTotalSettAmt(BigDecimal totalSettAmt) {
		this.totalSettAmt = totalSettAmt;
	}
	public BigDecimal getTotalSettAmt() {
		return totalSettAmt;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgName() {
		return orgName;
	}
	
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
	public String getSettleTime() {
		return settleTime;
	}
	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperator() {
		return operator;
	}
	
	
}
