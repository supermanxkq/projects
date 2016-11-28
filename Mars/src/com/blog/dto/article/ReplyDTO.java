package com.blog.dto.article;

import java.io.Serializable;
import java.util.Date;

import com.blog.dto.BaseDTO;
import com.google.gson.annotations.Expose;

/**
 * @ProjectName:blog
 * @ClassName:Reply
 * @Description:回复实体类
 * @date: 2015-6-15下午09:54:54
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-15下午09:54:54
 */
public class ReplyDTO extends BaseDTO implements Serializable{
	/** */
	private static final long serialVersionUID = -3260892612955252724L;
	/** 编号 */
	@Expose
	private Integer id;
	/** 回复者用户名 */
	@Expose
	private String userName;
	/** 回复内容 */
	@Expose
	private String content;
	/** 回复时间 */
	@Expose
	private Date time;
	/** 被回复者名称 */
	@Expose
	private String replyToUserName;
	/** 回复对应的评论 */
	private CommentsDTO commonts;

	public CommentsDTO getCommonts() {
		return commonts;
	}

	public String getContent() {
		return content;
	}

	public Integer getId() {
		return id;
	}

	public String getReplyToUserName() {
		return replyToUserName;
	}

	public Date getTime() {
		return time;
	}

	public String getUserName() {
		return userName;
	}


	public void setCommonts(CommentsDTO commonts) {
		this.commonts = commonts;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setReplyToUserName(String replyToUserName) {
		this.replyToUserName = replyToUserName;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
