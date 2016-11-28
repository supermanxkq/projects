package com.blog.bean.leavemessage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 留言回复类
 * 
 */
@Entity
@Table(name = "leavemessagereply")
public class LeaveMessageReply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2143146458653158871L;

	private int leaveMessageReplyId;// 留言回复编号
	private String leaveMessageReplyName;// 回复留言者
	private Date leaveMessageReplyDate;// 回复留言的日期
	private String leaveMessageReplyContent;// 回复留言内容
	private LeaveMessage leaveMessage;

	@OneToOne
	@JoinColumn(name = "LeaveMessage", unique = true,updatable=false)
	public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	@Column
	@Type(type="text")  
	public String getLeaveMessageReplyContent() {
		return leaveMessageReplyContent;
	}

	@Column
	public Date getLeaveMessageReplyDate() {
		return leaveMessageReplyDate;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getLeaveMessageReplyId() {
		return leaveMessageReplyId;
	}

	@Column
	public String getLeaveMessageReplyName() {
		return leaveMessageReplyName;
	}

	public void setLeaveMessageReplyContent(String leaveMessageReplyContent) {
		this.leaveMessageReplyContent = leaveMessageReplyContent;
	}

	public void setLeaveMessageReplyDate(Date leaveMessageReplyDate) {
		this.leaveMessageReplyDate = leaveMessageReplyDate;
	}

	public void setLeaveMessageReplyId(int leaveMessageReplyId) {
		this.leaveMessageReplyId = leaveMessageReplyId;
	}

	public void setLeaveMessageReplyName(String leaveMessageReplyName) {
		this.leaveMessageReplyName = leaveMessageReplyName;
	}
}
