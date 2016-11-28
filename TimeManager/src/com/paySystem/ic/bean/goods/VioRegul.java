package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: VioRegul.java
 * @Package: com.paySystem.ic.bean.goods
 * @Description: 违规案例
 * @Author: Jacky 
 * @Date: 2014-8-27 下午4:41:45
 * @Version: V1.0  
 */
@Entity
@Table(name = "B_VioRegul")
public class VioRegul implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**违规条例编号 */
	private Integer unruleId;
	/**违规类型编号 */
	private Integer unruleTypeId;
	/** 违规案例*/
	private String unruleWay;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getUnruleId() {
		return unruleId;
	}
	public void setUnruleId(Integer unruleId) {
		this.unruleId = unruleId;
	}
	@Column(length=8,nullable=false)
	public Integer getUnruleTypeId() {
		return unruleTypeId;
	}
	public void setUnruleTypeId(Integer unruleTypeId) {
		this.unruleTypeId = unruleTypeId;
	}
	@Column(length=30,nullable=false)
	public String getUnruleWay() {
		return unruleWay;
	}
	public void setUnruleWay(String unruleWay) {
		this.unruleWay = unruleWay;
	}
	
	

}
