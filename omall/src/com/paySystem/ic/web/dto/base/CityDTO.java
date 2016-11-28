package com.paySystem.ic.web.dto.base;

public class CityDTO {
	/** 主键**/
	private Integer id;
	
	/** 地区号**/
	private String code;
	
	/** 名称**/
	private String name;
	
	/** 所在地code**/
	private String provinceCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
}
