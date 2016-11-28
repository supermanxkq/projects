package com.blog.bean.article;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.blog.bean.blogtype.BlogType;
import com.blog.bean.system.User;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "t_article")
public class Article implements Serializable{

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
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
	/**阅读次数*/
	private long readTimes;
	/** 评论 */
	@Expose
	private Set<Comments> commonts;
	private Integer status;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getArticleId() {
		return articleId;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "blogTypeId")
	public BlogType getBlogType() {
		return blogType;
	}

	@OneToMany(mappedBy = "article", cascade = { CascadeType.PERSIST })
	public Set<Comments> getCommonts() {
		return commonts;
	}

	@Lob
	public String getContent() {
		return content;
	}

	@Column
	public Date getContentDate() {
		return contentDate;
	}

	@Column
	public Integer getGood() {
		return good;
	}
	@Column
	public long getReadTimes() {
		return readTimes;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	@Column
	public String getTitle() {
		return title;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "userId")
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

	public void setReadTimes(long readTimes) {
		this.readTimes = readTimes;
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

}
