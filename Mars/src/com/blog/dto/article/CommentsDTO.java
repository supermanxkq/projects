package com.blog.dto.article;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.blog.bean.article.Article;
import com.blog.dto.BaseDTO;
import com.google.gson.annotations.Expose;

/**
 * @ProjectName:blog
 * @ClassName:CommontsDTO
 * @Description:评论数据传输对象
 * @date: 2015-6-15下午10:30:56
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-15下午10:30:56
 */
public class CommentsDTO extends BaseDTO implements Serializable {
	/** */
	private static final long serialVersionUID = 396912988505301645L;
	@Expose
	private Integer commontsId;
	@Expose
	private String userName;
	@Expose
	private String content;
	/**评论时间*/
	@Expose
	private Date time;
	/** 评论对应的文章 */
	private Article article;
	/** 评论对应的回复 */
	private Set<ReplyDTO> replys;

	public Article getArticle() {
		return article;
	}

	public Integer getCommontsId() {
		return commontsId;
	}

	public String getContent() {
		return content;
	}

	public Set<ReplyDTO> getReplys() {
		return replys;
	}

	public Date getTime() {
		return time;
	}

	public String getUserName() {
		return userName;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setCommontsId(Integer commontsId) {
		this.commontsId = commontsId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setReplys(Set<ReplyDTO> replys) {
		this.replys = replys;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
