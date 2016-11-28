package com.blog.bean.leavemessage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 留言类
 * 
 */
@Entity
@Table(name = "leavemessage")
public class LeaveMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2143146458653158871L;

	private int leaveMessageId;// 留言编号
	private String leaveMessageName;// 留言者
	private Date leaveMessageDate;// 留言的日期
	private String leaveMessageContent;// 留言内容
	private String leaveHeadPhoto;// 留言者的头像

	@Column
	public String getLeaveHeadPhoto() {
		return leaveHeadPhoto;
	}

	public void setLeaveHeadPhoto(String leaveHeadPhoto) {
		this.leaveHeadPhoto = leaveHeadPhoto;
	}

	private LeaveMessageReply leaveMessageReply;

	// 双向一对一关联以A类的b属性配置来进行关联
	@OneToOne(mappedBy = "leaveMessage", cascade = CascadeType.ALL)
	public LeaveMessageReply getLeaveMessageReply() {
		return leaveMessageReply;
	}

	public void setLeaveMessageReply(LeaveMessageReply leaveMessageReply) {
		this.leaveMessageReply = leaveMessageReply;
	}

	@Column
	public String getLeaveMessageName() {
		return leaveMessageName;
	}

	public void setLeaveMessageName(String leaveMessageName) {
		this.leaveMessageName = leaveMessageName;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getLeaveMessageId() {
		return leaveMessageId;
	}

	public void setLeaveMessageId(int leaveMessageId) {
		this.leaveMessageId = leaveMessageId;
	}

	@Column
	public Date getLeaveMessageDate() {
		return leaveMessageDate;
	}

	public void setLeaveMessageDate(Date leaveMessageDate) {
		this.leaveMessageDate = leaveMessageDate;
	}

	@Column
	@Type(type="text")
	public String getLeaveMessageContent() {
		return leaveMessageContent;
	}

	public void setLeaveMessageContent(String leaveMessageContent) {
		this.leaveMessageContent = leaveMessageContent;
	}
}
