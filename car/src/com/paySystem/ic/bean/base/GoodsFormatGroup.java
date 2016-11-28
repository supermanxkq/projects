package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: Goods.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 商品规格分组
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:28:49
 * @Version: V1.0  
 */

@Entity
@Table(name="b_formatgroup")
public class GoodsFormatGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -994177680781550136L;
	
	/** 分组编号 */
	private Integer fgroupId;
	
	/** 规格分组名称 */
	private String fgroupName;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getFgroupId() {
		return fgroupId;
	}

	public void setFgroupId(Integer fgroupId) {
		this.fgroupId = fgroupId;
	}

	@Column(length=60,nullable=false)
	public String getFgroupName() {
		return fgroupName;
	}

	public void setFgroupName(String fgroupName) {
		this.fgroupName = fgroupName;
	}
	
	

}
