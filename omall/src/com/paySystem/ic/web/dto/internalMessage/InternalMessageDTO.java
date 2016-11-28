package com.paySystem.ic.web.dto.internalMessage;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessage
 * @Description:站内信数据传输对象
 * @date: 2014-11-20下午04:03:44
 * @author: 徐凯强
 * @version: V1.0
 */
public class InternalMessageDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;

	/** 内容 */
	private String content;
	/** 站内信编号 */
	private Integer internalMessageId;
	/** 会员编号 字符串，用来在会员中进行站内信的发送 */
	private String memId;
	/** 站内信收信人0：全部会员1：全部商户 */
	private Integer receiverFlag;
	/** 发件人 */
	private String sender;
	/** 发送时间 */
	private Date sendTime;
	/** 标题 */
	private String title;

	public String getContent() {
		return content;
	}

	public Integer getInternalMessageId() {
		return internalMessageId;
	}

	public String getMemId() {
		return memId;
	}

	public Integer getReceiverFlag() {
		return receiverFlag;
	}

	public String getSender() {
		return sender;
	}

	public Date getSendTime() {
		return sendTime;
	}


	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setInternalMessageId(Integer internalMessageId) {
		this.internalMessageId = internalMessageId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public void setReceiverFlag(Integer receiverFlag) {
		this.receiverFlag = receiverFlag;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
