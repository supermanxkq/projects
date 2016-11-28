//package com.blog.bean.article;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.google.gson.annotations.Expose;
//
///**
// * @ProjectName:blog
// * @ClassName:Reply
// * @Description:回复实体类
// * @date: 2015-6-15下午09:54:54
// * @author: 徐半仙儿
// * @version: V1.0
// * @date:2015-6-15下午09:54:54
// */
//@Entity
//@Table(name = "t_reply")
//public class Reply implements Serializable {
//	/** */
//	private static final long serialVersionUID = -8776358163759023564L;
//	/** 编号 */
//	private Integer id;
//	/** 回复者用户名 */
//	private String userName;
//	/** 回复内容 */
//	private String content;
//	/** 回复时间 */
//	private Date time;
//	/** 被回复者名称 */
//	private String replyToUserName;
//	/** 回复对应的评论 */
//	private Comments commonts;
//
//	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
//	@JoinColumn(name = "commontsId")
//	public Comments getCommonts() {
//		return commonts;
//	}
//
//	@Lob
//	public String getContent() {
//		return content;
//	}
//
//	@Id
//	@Column
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Integer getId() {
//		return id;
//	}
//
//	@Column
//	public String getReplyToUserName() {
//		return replyToUserName;
//	}
//
//	@Column
//	public Date getTime() {
//		return time;
//	}
//
//	@Column
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setCommonts(Comments commonts) {
//		this.commonts = commonts;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public void setReplyToUserName(String replyToUserName) {
//		this.replyToUserName = replyToUserName;
//	}
//
//	public void setTime(Date time) {
//		this.time = time;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//}
