package com.paySystem.ic.web.dto.code;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

public class CodeStyleDTO  extends BaseDTO implements Serializable {

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
