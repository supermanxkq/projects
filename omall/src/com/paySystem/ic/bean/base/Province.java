package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * @Title: Province.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 省
 * @Author: Jacky
 * @Date: 2014-10-10 下午10:25:46
 * @Version: V1.0  
 */

@Entity
@Table(name="d_province")
public class Province implements Serializable {
	private static final long serialVersionUID = 3390751475587497088L;
	
	/** 主键**/
	private Integer id;
	
	/** 地区号**/
	private String code;
	
	/** 名称**/
	private String name;

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
	
}
