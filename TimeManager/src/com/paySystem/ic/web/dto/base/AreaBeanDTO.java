package com.paySystem.ic.web.dto.base;

public class AreaBeanDTO {
	
	/** 主键**/
	private Integer id;
	
	/** 编码**/
	private String code;
	
	/** 名称**/
	private String name;
	
	/** 所在地区code**/
	private String cityCode;

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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
