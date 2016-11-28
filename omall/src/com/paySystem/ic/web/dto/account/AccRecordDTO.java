
package com.paySystem.ic.web.dto.account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.inject.util.Strings;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @author 赵巧鹤
 * @projectName MCIU20131123
 * @className AccRecord
 * @description 账户冻结解冻DTO
 * @date 2014-2-11上午09:30:29
 * @param 
 * 
 */
public class AccRecordDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 2230951605058473844L;
	
	 /**
	  * 操作记录号
	  * */
	 private Integer ftAccId;
	 /**
	  * 账户号
	  * */
	 private String accId;
	
	 /**
	  * 卡BIN
	  * */
	 private String binId;
	 private String binIds;
	 /**
	  * 卡号
	  * */
	 private String cardNo;
	 /**
	  * 显示卡位数
	  * */
	 private String cardNoView;
	
	 /**
	  * 机构号
	  * */
	 private String organId;
	 /**
	  * 卡BIN名称
	  * */
	 private String binName;
	 private List<Strings> binNames;
	 /**
	  * 账户类型Id
	  * */
	 private Integer accTId;
	 /**
	  * 账户名称
	  * */
	private String accTName;
	
	 /**
	  * 申请描述
	  * */
	 private String descApp;
	 /**
	  * 审核描述
	  * */
	 private String descAudit;
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
	  * 审核状态
	  * */
	 private Integer checkStatus;
	 /**
	  * 操作类型
	  * 0、冻结操作
	  * 1、解冻操作
	  * */
	private Integer operType;
	/**
	 * 机构
	 * */
	
	
	public Integer getFtAccId() {
		return ftAccId;
	}
	public void setFtAccId(Integer ftAccId) {
		this.ftAccId = ftAccId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	
	public String getCardNoView() {
		return cardNoView;
	}
	public void setCardNoView(String cardNoView) {
		this.cardNoView = cardNoView;
	}
	public String getBinIds() {
		return binIds;
	}
	public void setBinIds(String binIds) {
		this.binIds = binIds;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public List<Strings> getBinNames() {
		return binNames;
	}
	public void setBinNames(List<Strings> binNames) {
		this.binNames = binNames;
	}
	
	public Integer getAccTId() {
		return accTId;
	}
	public void setAccTId(Integer accTId) {
		this.accTId = accTId;
	}
	
	public String getAccTName() {
		return accTName;
	}
	public void setAccTName(String accTName) {
		this.accTName = accTName;
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
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	@Override
	public String toString() {
		return "AccRecordDTO [accId=" + accId + ", accType=" 
				+ ", binId=" + binId + ", cardNo=" + cardNo + ", ftAccId="
				+ ftAccId + ", status=" + ", createTime=" 
				+ "]";
	}
	 
	 
}
