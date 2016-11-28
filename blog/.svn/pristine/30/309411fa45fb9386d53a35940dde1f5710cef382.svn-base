package com.blog.dto.article;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.blog.bean.article.Comments;
import com.blog.bean.blogtype.BlogType;
import com.blog.bean.system.User;
import com.blog.dto.BaseDTO;

public class ArticleDTO extends BaseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3077939892741272807L;
	/** 文章id */
	private Integer articleId;
	/** 文章title */
	private String title;
	/** 文章内容 */
	private String content;
	/** 发表时间 */
	private Date contentDate;
	/** 编辑者 */
	private User user;
	/** 博客类型 */
	private BlogType blogType;
	/** 赞 */
	private Integer good;
	/** 评论 */
	private Set<Comments> commonts;
	/**状态 */
	private Integer status;
	/**阅读次数*/
	private long readTimes;
	/**文章类型编号*/
	private Integer blogTypeId;
	/**文章类型名称*/
	private String blogTypeName;
	/**文章类型下文章数目*/
	private Integer blogTypeCount;
	
	private String contentDateString;
	private String count;
	
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getContentDateString() {
		return contentDateString;
	}

	public void setContentDateString(String contentDateString) {
		this.contentDateString = contentDateString;
	}

	public long getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(long readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public Set<Comments> getCommonts() {
		return commonts;
	}

	public String getContent() {
		return content;
	}

	public Date getContentDate() {
		return contentDate;
	}

	public Integer getGood() {
		return good;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public User getUser() {
		return user;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public void setCommonts(Set<Comments> commonts) {
		this.commonts = commonts;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContentDate(Date contentDate) {
		this.contentDate = contentDate;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getBlogTypeId() {
		return blogTypeId;
	}

	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}

	public String getBlogTypeName() {
		return blogTypeName;
	}

	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}

	public Integer getBlogTypeCount() {
		return blogTypeCount;
	}

	public void setBlogTypeCount(Integer blogTypeCount) {
		this.blogTypeCount = blogTypeCount;
	}

}
