package com.paySystem.ic.bean.internalMessage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:Receivers
 * @Description:站内信收信者实体
 * @date: 2014-11-20下午04:08:26
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "b_Receivers")
public class Receivers implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;

	/** 站内信记录编号 */
	private Integer internalMessageId;
	/** 会员编号 */
	private Integer memId;
	/** 商户编号 */
	private String merId;
	/** 收信人名称 */
	private String receiverName;
	/** 收信者编号 */
	private Integer receiversId;
	/** 状态 0：未读； 1：已读； 默认为未读 */
	private Integer status;
	
	
	@Column
	public Integer getInternalMessageId() {
		return internalMessageId;
	}

	@Column(length = 10)
	public Integer getMemId() {
		return memId;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	@Column
	public String getReceiverName() {
		return receiverName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getReceiversId() {
		return receiversId;
	}
	@Column(length = 1)
	public Integer getStatus() {
		return status;
	}

	public void setInternalMessageId(Integer internalMessageId) {
		this.internalMessageId = internalMessageId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setReceiversId(Integer receiversId) {
		this.receiversId = receiversId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
