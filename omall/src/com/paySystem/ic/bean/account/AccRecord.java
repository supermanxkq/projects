package com.paySystem.ic.bean.account;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 赵巧鹤
 * @projectName MCIU20131123
 * @className AccRecord
 * @description 账户冻结解冻记录日志的实体类
 * @date 2014-2-11上午10:07:25
 * @param
 * 
 */
@Entity
@Table(name = "C_FrThAccInfo")
public class AccRecord implements Serializable {

	private static final long serialVersionUID = -8150076666514632125L;

	/**
	 * 操作记录号
	 * */
	private Integer ftAccId;
	/**
	 * 账户号
	 * */
	private String accId;


	/**
	 * 审核状态
	 * 0、未审核
	 * 1、审核通过
	 * 2、审核未通过
	 * */
	private Integer checkStatus;
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
	 * 卡BIN
	 * */
	private String binId;
	/**
	 * 卡BIN名称
	 * */
	private String binName;
	/**
	 * 卡号
	 * */
	private String cardNo;
	/**
	 * 账户类型Id
	 * */
	private Integer accTId;
	/**
	 * 账户名称
	 * */
	private String accTName;
	/**
	 * 操作类型 0、账户冻结操作 1、账户解冻操作
	 * */
	private Integer operType;
	/**
	 * 机构
	 * */
	private String organId;

/*	@Id
	@SequenceGenerator(name = "ACCRECORDSEQ", sequenceName = "C_FrThAccInfo_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCRECORDSEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getFtAccId() {
		return ftAccId;
	}

	public void setFtAccId(Integer ftAccId) {
		this.ftAccId = ftAccId;
	}

	@Column(length = 15)
	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}



	@Column(length = 6)
	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
	}

	@Column(length = 20)
	public String getBinName() {
		return binName;
	}

	public void setBinName(String binName) {
		this.binName = binName;
	}

	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(columnDefinition = "char(2)")
	public Integer getAccTId() {
		return accTId;
	}

	public void setAccTId(Integer accTId) {
		this.accTId = accTId;
	}

	@Column(length = 20)
	public String getAccTName() {
		return accTName;
	}

	public void setAccTName(String accTName) {
		this.accTName = accTName;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(length = 300)
	public String getDescApp() {
		return descApp;
	}

	public void setDescApp(String descApp) {
		this.descApp = descApp;
	}

	@Column(length = 200)
	public String getDescAudit() {
		return descAudit;
	}

	public void setDescAudit(String descAudit) {
		this.descAudit = descAudit;
	}

	@Column(length = 20)
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	@Column(length = 20)
	public String getAuditt() {
		return auditt;
	}

	public void setAuditt(String auditt) {
		this.auditt = auditt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column()
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

}
