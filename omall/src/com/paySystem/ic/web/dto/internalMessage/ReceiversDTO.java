package com.paySystem.ic.web.dto.internalMessage;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:Receivers
 * @Description:站内信收信者数据传输对象
 * @date: 2014-11-20下午04:08:26
 * @author: 徐凯强
 * @version: V1.0
 */
public class ReceiversDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;

	/** 站内信记录编号 */
	private Integer internalMessageId;
	/** 收信人名称 */
	private String receiverName;
	/** 收信者编号 */
	private Integer receiversId;
	/** 会员编号 */
	private Integer memId;
	/** 商户编号 */
	private String merId;
	/** 状态 0：未读； 1：已读； 默认为未读 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getInternalMessageId() {
		return internalMessageId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public Integer getReceiversId() {
		return receiversId;
	}

	public void setInternalMessageId(Integer internalMessageId) {
		this.internalMessageId = internalMessageId;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setReceiversId(Integer receiversId) {
		this.receiversId = receiversId;
	}
}
