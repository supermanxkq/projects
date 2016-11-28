package com.paySystem.ic.bean.internalMessage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:InternalMessage
 * @Description:站内信实体类
 * @date: 2014-11-20下午04:03:44
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "b_InternalMessage")
public class InternalMessage implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;

	/** 内容 */
	private String content;
	/** 站内信编号 */
	private Integer internalMessageId;
	/** 站内信收信人0：全部会员1：全部商户 */
	private Integer receiverFlag;
	/** 发件人 */
	private String sender;
	/** 发送时间 */
	private Date sendTime;
	/** 标题 */
	private String title;

	@Column(length = 255)
	public String getContent() {
		return content;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getInternalMessageId() {
		return internalMessageId;
	}

	@Column(length = 1)
	public Integer getReceiverFlag() {
		return receiverFlag;
	}

	@Column
	public String getSender() {
		return sender;
	}

	@Column
	public Date getSendTime() {
		return sendTime;
	}


	@Column
	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setInternalMessageId(Integer internalMessageId) {
		this.internalMessageId = internalMessageId;
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
