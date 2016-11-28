package com.blog.bean.blogtype;

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
@Entity
@Table(name="t_blogtype")
public class BlogType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5724280072125223661L;
	private Integer blogTypeId;
	private String name;
	private Set<Article> articles;
	
	
	
	@OneToMany(mappedBy = "blogType", cascade = { CascadeType.PERSIST })
	public Set<Article> getArticles() {
		return articles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getBlogTypeId() {
		return blogTypeId;
	}



	@Column
	public String getName() {
		return name;
	}



	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}



	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}


	public void setName(String name) {
		this.name = name;
	}

}
