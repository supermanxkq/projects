package com.paySystem.ic.bean.stock;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.paySystem.ic.bean.card.CardBIN;

/**
 * @ClassName:ModStock
 * @Description:库存变动实体
 * @date: 2013-12-6下午02:00:25
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="S_ModStock")
public class ModStock implements Serializable {

	private static final long serialVersionUID = -4195660940303134201L;
	/** 流水号 */
	private String id;
	
	/** 出库方类型 
	 *  0：机构
	 *  1：商户 
	 */
	private Integer outStype;
	/** 入库方类型 
	 *  0：机构
	 *  1：商户 
	 */
	private Integer inStype;
	/** 出库机构/商户号 */
	private String outId;
	
	/**
	 * 入库机构/商户编号
	 */
	private String inId;
	
	/** 创建时间 */
	private Date createTime;
	/** 审核时间 */
	private Date auditTime;
	/** 卡BIN */
	private CardBIN cardBin;
	/** 起始卡号 */
	private String beginCardNo;
	/** 数量 */
	private Integer cardCount;
	/** 申请操作人 */
	private String proposer;
	/** 入库状态 
	 *  0：未入库 
	 *  1：已入库
	 *  2:已售出
	 */
	private Integer status;
	/**
	 * 操作类型
	 *  0总部入库
	 *  1发卡机构入库
	 *  2商户入库
	 *  3商户互相调拨
	 *  4发卡机构售卡
	 *  5商户售卡
	 *  6发卡机构售卡退回
	 *  7商户售卡退回
	 *  8会员退卡
	 *  9商户退卡
	 *  10发卡机构换卡
	 *  11商户换卡
	 */
	private Integer flag;
	/**
	 * 乐观锁版本控制 
	 */
	private Integer verson;
	/**
	 * 审核操作人
	 */
	private String checkMen;
	/** 商户Id */
	private String merId;
	/** 机构ID */
	private String organId;
	
	@Column(length=15)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	@Column(length=20)
	public String getCheckMen() {
		return checkMen;
	}
	public void setCheckMen(String checkMen) {
		this.checkMen = checkMen;
	}
	public ModStock() {
		super();
	}
	public ModStock(String id) {
		this.id = id;
	}
	@Id
	@Column(length=15)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column
	public Integer getOutStype() {
		return outStype;
	}
	public void setOutStype(Integer outStype) {
		this.outStype = outStype;
	}
	
	@Column(length=15)
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	@Column
	public Integer getInStype() {
		return inStype;
	}
	public void setInStype(Integer inStype) {
		this.inStype = inStype;
	}
	@Column(length=15)
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@ManyToOne(optional=false,cascade=CascadeType.REFRESH)
	@JoinColumn(name="binId")
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	@Column(length=19)
	public String getBeginCardNo() {
		return beginCardNo;
	}
	public void setBeginCardNo(String beginCardNo) {
		this.beginCardNo = beginCardNo;
	}
	@Column
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	@Column(length=20)
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	@Column
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Column
	public Integer getVerson() {
		return verson;
	}
	public void setVerson(Integer verson) {
		this.verson = verson;
	}
	
	

}
