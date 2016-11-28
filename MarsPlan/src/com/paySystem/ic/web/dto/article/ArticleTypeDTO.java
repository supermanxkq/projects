package com.paySystem.ic.web.dto.article;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class ArticleTypeDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5724280072125223661L;
	private Integer id;
	private String name;

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

}
