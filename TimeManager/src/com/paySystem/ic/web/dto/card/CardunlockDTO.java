package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.card.Cards;
import com.paySystem.ic.web.dto.BaseDTO;


/**
 * @作者  赵巧鹤
 * @类名称 CardUnlockDTO 卡解挂DTO
 * @项目名称 mciu
 * @创建时间 2013-12-14 下午03:21:54
 */
public class CardunlockDTO extends BaseDTO implements Serializable{

private static final long serialVersionUID = -1324112849666702193L;
	
	
	/**
	 * 解挂id
	 * */
	private Integer unlkId ;
	
	/**
	 * 审核状态
	 * */
	private Integer checkstatus;
	
	/**
	 * 申请操作人
	 * */
	private String proposer;
	
	/**
	 * 审核操作人
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
	 * 身份证号码
	 * */
	private String personId;
	
	/**
	 * 申请解挂描述
	 * */
	private String descApp;
	
	/**
	 * 审核解挂描述
	 * */
	private String descAudit;
	
	/**
	 * 商户号
	 * 
	 * */
	private String  merId;
	
	/**
	 * 卡对象
	 * */
	private Cards cards;
	
	
	/**
	 * 卡号
	 * 
	 * */
   
	private String cardNo;
	
	/**
	 * 卡号显示
	 * */
	private String cardNoView;
	
	/**
	 * 机构
	 * */
	
	private String organId;
	
	/**
	 * 会员姓名
	 * */
	private String merName;
	/**
	 * 会员证件号
	 * */
	private String memIdNum;
	
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getCardNoView() {
		return cardNoView;
	}

	public void setCardNoView(String cardNoView) {
		this.cardNoView = cardNoView;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getUnlkId() {
		return unlkId;
	}

	public void setUnlkId(Integer unlkId) {
		this.unlkId = unlkId;
	}

	public Integer getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
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

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDescApp() {
		return descApp;
	}

	public void setDescApp(String descApp) {
		this.descApp = descApp;
	}

	public String getDescAudit() {
		return descAudit;
	}

	public void setDescAudit(String descAudit) {
		this.descAudit = descAudit;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
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
	
	
}
