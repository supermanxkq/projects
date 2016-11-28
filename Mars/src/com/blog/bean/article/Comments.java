package com.blog.bean.article;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_commonts")
public class Comments implements Serializable {
	/** */
	private static final long serialVersionUID = -3091029618028284295L;
	private Integer commontsId;
	private String userName;
	private String content;
	private Date time;
	/** 评论对应的文章 */
	private Article article;
	/** 评论对应的回复 */
//	private Set<Reply> replys;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "articleId")
	public Article getArticle() {
		return article;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getCommontsId() {
		return commontsId;
	}

	@Lob
	public String getContent() {
		return content;
	}

//	@OneToMany(mappedBy = "commonts", cascade = { CascadeType.PERSIST })
//	public Set<Reply> getReplys() {
//		return replys;
//	}

	@Column
	public Date getTime() {
		return time;
	}

	@Column
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

//	public void setReplys(Set<Reply> replys) {
//		this.replys = replys;
//	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
