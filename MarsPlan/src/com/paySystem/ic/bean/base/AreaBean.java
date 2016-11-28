package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: Area.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 地区
 * @Author: Jacky
 * @Date: 2014-10-10 下午10:25:46
 * @Version: V1.0  
 */
@Entity
@Table(name="d_area")
public class AreaBean implements Serializable {
	private static final long serialVersionUID = -8534916831392231402L;
	
	/** 主键**/
	private Integer id;
	
	/** 编码**/
	private String code;
	
	/** 名称**/
	private String name;
	
	/** 所在地区code**/
	private String cityCode;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length=6,nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=20,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=6,nullable=false)
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
