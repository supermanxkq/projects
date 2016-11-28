/**
 * 
 */
package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @author:赵巧鹤
 * @projectName: MCIU20131123
 * @className :CardChangDTO
 * @description :TODO
 * @date: 2014-3-10下午05:25:09
 * @param :
 * 
 */


public class CardChangDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -1324112849666702193L;
	
	/**
	 * 主键换卡ID
	 * */
	private Integer changId;
	/**
	 * 旧卡号
	 * */
	private String oldNo;
	/**
	 * 旧卡号
	 * */
	private String newNo;
	/**
	 * 申请人
	 * */
	private String proposer;
	/**
	 * 审核人
	 * */
	private String auditt;
	/**
	 * 申请时间
	 * */
	private Date operTime;
	/**
	 * 审核时间
	 * */
	private Date auditTime;
	/**
	 * 卡BIN
	 * */
	private String cardBIN;
	/**
	 * 换卡原因 0、损坏 1、挂失 2、其他
	 * */
	private Integer chagReason;
	/**
	 * 备注
	 * */
	private String descr;
	/**
	 * 审核状态 0、未审核 1、审核通过 2、审核未通过
	 * */
	private Integer checkStatus;
	/**
	 * 审核描述
	 * */
	private String checkDesc;
	/**
	 * 机构ID
	 * */
	private String orgId;
	/**
	 * 卡对象
	 * */
	private Cards cards;
	/**
	 * 旧短卡号
	 * */
	private String oldNoView;
	/**
	 * 新短卡号
	 * */
	private String newNoView;
	/**
	 * 卡号
	 * */
	private String cardNo;
	/**
	 * 卡BIN
	 * */
	private String binId;
	/**
	 * 持卡人
	 * */
	private String merName;
	/**
	 * 身份证
	 * */
	private String memIdNum;
	/**
	 * 账户号集合
	 * */
	private  List<String> accIds;
	
	/**
	 * 账户号
	 * */
	private String accId;
	/**
	 * 账户类型
	 * */
	private Integer typeId;
	/**
	 * 账户名称
	 * */
	private String typeName;
	/**
	 * 卡余额
	 * */
	private BigDecimal balance;

    /**
     * 账户集合
     * */
	private List<String> typeNames;
	/**
	 * 余额
	 * */
	private List<String> balances;
	
	public Integer getChangId() {
		return changId;
	}
	public void setChangId(Integer changId) {
		this.changId = changId;
	}
	public String getOldNo() {
		return oldNo;
	}
	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}
	public String getNewNo() {
		return newNo;
	}
	public void setNewNo(String newNo) {
		this.newNo = newNo;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getAuditt() {
		return auditt;
	}
	public void setAuditt(String auditt) {
		this.auditt = auditt;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getCardBIN() {
		return cardBIN;
	}
	public void setCardBIN(String cardBIN) {
		this.cardBIN = cardBIN;
	}
	public Integer getChagReason() {
		return chagReason;
	}
	public void setChagReason(Integer chagReason) {
		this.chagReason = chagReason;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Cards getCards() {
		return cards;
	}
	public void setCards(Cards cards) {
		this.cards = cards;
	}
	public String getOldNoView() {
		return oldNoView;
	}
	public void setOldNoView(String oldNoView) {
		this.oldNoView = oldNoView;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMemIdNum() {
		return memIdNum;
	}
	public void setMemIdNum(String memIdNum) {
		this.memIdNum = memIdNum;
	}
	public List<String> getAccIds() {
		return accIds;
	}
	public void setAccIds(List<String> accIds) {
		this.accIds = accIds;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getNewNoView() {
		return newNoView;
	}
	public void setNewNoView(String newNoView) {
		this.newNoView = newNoView;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<String> getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(List<String> typeNames) {
		this.typeNames = typeNames;
	}
	public List<String> getBalances() {
		return balances;
	}
	public void setBalances(List<String> balances) {
		this.balances = balances;
	}
	
}
