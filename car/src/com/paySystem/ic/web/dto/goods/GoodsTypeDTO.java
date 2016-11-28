package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;
public class GoodsTypeDTO  extends BaseDTO implements Serializable{

	//
	private static final long serialVersionUID = 7339902691115878742L;
	// 编号
	private Integer id;
	// 名称
	private String name;
	// 状态
	private Integer status;

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
