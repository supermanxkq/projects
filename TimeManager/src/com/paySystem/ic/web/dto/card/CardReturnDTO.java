package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

public class CardReturnDTO extends BaseDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/***退卡订单编号**/
	private String retId;
	/***退卡卡号集合**/
	private List<CardsDTO> cardNoList;
	/***卡号**/
	private String cardNo;
	/***卡状态**/
	private Integer status;
	/***卡状态名称**/
	private String statusName;
	/***退卡数量**/
	private Integer reCount;
	/***卡余额**/
	private BigDecimal allAmt;
	/***实际退款金额**/
	private BigDecimal relAmt; 
	/***退款方式**/
	private Integer payWay;
	/***退款方式**/
	private String payWayName;
	/***退卡人名称**/
	private String memName;
	/***退卡人电话**/
	private String telNo;
	/***退卡类型**/
	private String retTypeName;
	/***退卡类型**/
	private Integer retType;
	/***操作人**/
	private String proposer;
	/***操作时间**/
	private Date operTime;
	/***审核人**/
	private String auditId;
	/***审核时间**/
	private Date auditTime;
	/***退卡描述**/
	private String descr;
	/***退卡明细账号**/
	private List<CradReturnDetailDTO> cradReturnDetailDTO;
	/***订单状态**/
	private Integer retOrderStatus;
	/***订单状态**/
	private String retOrderStatusName;
	/***退卡积分**/
	private BigDecimal allPnt;
	/***持卡人证件类型**/
	private Integer perType;
	/***持卡人证件类型**/
	private String perTypeName;
	/****持卡人证件编号**/
	private String personId;
	/***显示卡号**/
	private String cardsNoShow;
	
	public String getRetId() {
		return retId;
	}
	public void setRetId(String retId) {
		this.retId = retId;
	}
	public List<CardsDTO> getCardNoList() {
		return cardNoList;
	}
	public void setCardNoList(List<CardsDTO> cardNoList) {
		this.cardNoList = cardNoList;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getReCount() {
		return reCount;
	}
	public void setReCount(Integer reCount) {
		this.reCount = reCount;
	}
	public BigDecimal getAllAmt() {
		return allAmt;
	}
	public void setAllAmt(BigDecimal allAmt) {
		this.allAmt = allAmt;
	}
	public BigDecimal getRelAmt() {
		return relAmt;
	}
	public void setRelAmt(BigDecimal relAmt) {
		this.relAmt = relAmt;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public Integer getRetType() {
		return retType;
	}
	public void setRetType(Integer retType) {
		this.retType = retType;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public List<CradReturnDetailDTO> getCradReturnDetailDTO() {
		return cradReturnDetailDTO;
	}
	public void setCradReturnDetailDTO(List<CradReturnDetailDTO> cradReturnDetailDTO) {
		this.cradReturnDetailDTO = cradReturnDetailDTO;
	}
	public Integer getRetOrderStatus() {
		return retOrderStatus;
	}
	public void setRetOrderStatus(Integer retOrderStatus) {
		this.retOrderStatus = retOrderStatus;
	}
	public BigDecimal getAllPnt() {
		return allPnt;
	}
	public void setAllPnt(BigDecimal allPnt) {
		this.allPnt = allPnt;
	}
	public Integer getPerType() {
		return perType;
	}
	public void setPerType(Integer perType) {
		this.perType = perType;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPayWayName() {
		return payWayName;
	}
	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}
	public String getRetTypeName() {
		return retTypeName;
	}
	public void setRetTypeName(String retTypeName) {
		this.retTypeName = retTypeName;
	}
	public String getRetOrderStatusName() {
		return retOrderStatusName;
	}
	public void setRetOrderStatusName(String retOrderStatusName) {
		this.retOrderStatusName = retOrderStatusName;
	}
	public String getPerTypeName() {
		return perTypeName;
	}
	public void setPerTypeName(String perTypeName) {
		this.perTypeName = perTypeName;
	}
	public String getCardsNoShow() {
		return cardsNoShow;
	}
	public void setCardsNoShow(String cardsNoShow) {
		this.cardsNoShow = cardsNoShow;
	}
}
