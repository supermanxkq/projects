package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

public class RechargeDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/***充值订单编号*/
	private String recOrderId ;
	/***充值账号 */
	private String accId;
	/***订单金额 */
	private BigDecimal orderAmt;
	/***实际支付金额*/
	private BigDecimal realityAmt;
	/*** 支付类型*/
	private Integer paidType;
	/*** 支付类型*/
	private String paidTypeName;
	/***充值人 */
	private String memId;
	/***充值人 */
	private String memName;
	/***充值起始卡号*/
	private String cardNo;
	/***操作员*/
	private String operatorName;
	/***充值时间*/
	private Date createTime;
	/***充值描述*/
	private String descr;
	/***数量*/
	private Integer cardNum;
	/***订单状态*/
	private Integer status;
	/***订单状态*/
	private String statusName;
	/***账户类型*/
	private Integer accType;
	/***账户类型*/
	private String accTypeName;
	/***所属机构/商户标志*/
	private String orgMerNo;
	/***部门类型*/
	private String deptType;
	/***订单明细流水账账号**/
	private String rechDetailId;
	/***批量售卡的卡号集合**/
	private List<String> cardNoList; 
	
	private List<RechargeDTO> rechDetailList;
	private String cardNoShow;
	
	public String getRecOrderId() {
		return recOrderId;
	}
	public void setRecOrderId(String recOrderId) {
		this.recOrderId = recOrderId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}
	public BigDecimal getRealityAmt() {
		return realityAmt;
	}
	public void setRealityAmt(BigDecimal realityAmt) {
		this.realityAmt = realityAmt;
	}
	public Integer getPaidType() {
		return paidType;
	}
	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}
	public String getPaidTypeName() {
		return paidTypeName;
	}
	public void setPaidTypeName(String paidTypeName) {
		this.paidTypeName = paidTypeName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getCardNum() {
		return cardNum;
	}
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getAccType() {
		return accType;
	}
	public void setAccType(Integer accType) {
		this.accType = accType;
	}
	public String getAccTypeName() {
		return accTypeName;
	}
	public void setAccTypeName(String accTypeName) {
		this.accTypeName = accTypeName;
	}
	public String getOrgMerNo() {
		return orgMerNo;
	}
	public void setOrgMerNo(String orgMerNo) {
		this.orgMerNo = orgMerNo;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public String getRechDetailId() {
		return rechDetailId;
	}
	public void setRechDetailId(String rechDetailId) {
		this.rechDetailId = rechDetailId;
	}
	public List<RechargeDTO> getRechDetailList() {
		return rechDetailList;
	}
	public void setRechDetailList(List<RechargeDTO> rechDetailList) {
		this.rechDetailList = rechDetailList;
	}
	public String getCardNoShow() {
		return cardNoShow;
	}
	public void setCardNoShow(String cardNoShow) {
		this.cardNoShow = cardNoShow;
	}
	public List<String> getCardNoList() {
		return cardNoList;
	}
	public void setCardNoList(List<String> cardNoList) {
		this.cardNoList = cardNoList;
	}
	
}
