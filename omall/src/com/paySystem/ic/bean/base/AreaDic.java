package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:AreaDic
 * @Description:省和市表
 * @date: 2014-11-3下午05:45:40
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "b_areadic")
public class AreaDic implements Serializable {
	private static final long serialVersionUID = -8534916831392231402L;
	/** 主键 **/
	private Integer fCode;
	/** 名称 **/
	private String fName;
	/** 类型 **/
	private String fType;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getfCode() {
		return fCode;
	}
	@Column
	public String getfName() {
		return fName;
	}
	@Column
	public String getfType() {
		return fType;
	}

	public void setfCode(Integer fCode) {
		this.fCode = fCode;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

}
