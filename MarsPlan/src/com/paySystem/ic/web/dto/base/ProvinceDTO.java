package com.paySystem.ic.web.dto.base;

/**  
 * @Title: ProvinceDTO.java
 * @Package: com.paySystem.ic.web.dto.base
 * @Description: 省DTO
 * @Author: Jacky
 * @Date: 2014-10-10 下午10:25:46
 * @Version: V1.0  
 */
public class ProvinceDTO {
	
	/** 主键**/
	private Integer id;
	
	/** 地区号**/
	private String code;
	
	/** 名称**/
	private String name;

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
	
}
