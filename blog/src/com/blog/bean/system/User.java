package com.blog.bean.system;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.blog.bean.article.Article;

/**
 * @ProjectName:blog 
 * @ClassName:User  
 * @Description:用户类
 * @date: 2015-6-14下午09:14:55
 * @author: 徐半仙儿
 * @version: V1.0
 * @date:2015-6-14下午09:14:55
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{
	/** */
	private static final long serialVersionUID = 1259759131007751657L;
	private Integer userId;
	private String userName;
	private String passWord;
	/**1超级管理员2普通*/
	private Integer userLeavel;
	private Set<Article> articles;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST })
	public Set<Article> getArticles() {
		return articles;
	}

	@Column
	public String getPassWord() {
		return passWord;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getUserId() {
		return userId;
	}

	@Column
	public String getUserName() {
		return userName;
	}
	@Column
	public Integer getUserLeavel() {
		return userLeavel;
	}

	public void setUserLeavel(Integer userLeavel) {
		this.userLeavel = userLeavel;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
