package com.paySystem.ic.bean.article;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paySystem.ic.utils.Utils;

/**
 * @ProjectName:MarsPlan
 * @ClassName:ReadTimes
 * @Description:阅读次数
 * @date: Apr 21, 20162:12:47 PM
 * @author: bruce
 * @version: V1.0
 */
@Entity
@Table(name = "t_readtimes")
public class ReadTimes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8906487479419859541L;
	// 编号
	private Integer id;
	// ip地址
	private String ip;
	// 文章编号
	private Integer articleId;

	public ReadTimes(Integer articleId) {
		this.articleId = articleId;
		setIp(Utils.getIpAddr());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}
