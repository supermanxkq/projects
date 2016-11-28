package com.paySystem.ic.bean.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: GoodsFormatGroupRela.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: 规格分组关联
 * @Author: yanwuyang 
 * @Date: 2014-8-19 下午10:32:01
 * @Version: V1.0  
 */

@Entity
@Table(name="b_formatgrouprela")
public class GoodsFormatGroupRela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6326872227647366705L;
	
	/**编号 */
	private Integer fgrId;
	
	/** 规格编号（规格表Id*/
	private Integer formatId;
	
	/**规格分组id（规格分组表Id */
	private Integer fgroupId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getFgrId() {
		return fgrId;
	}

	public void setFgrId(Integer fgrId) {
		this.fgrId = fgrId;
	}

	@Column(nullable=false)
	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	@Column(nullable=false)
	public Integer getFgroupId() {
		return fgroupId;
	}

	public void setFgroupId(Integer fgroupId) {
		this.fgroupId = fgroupId;
	}
	
	
	
	
	

}
