package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: GoodsFormat.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品规格
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:25:46
 * @Version: V1.0  
 */

@Entity
@Table(name="b_format")
public class GoodsFormat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8823108017488778462L;
	
	/** 编号 */
	private Integer formatId;
	
	/** 规格名称 */
	private String formatName;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	@Column(length=60,nullable=false)
	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	
	

}
