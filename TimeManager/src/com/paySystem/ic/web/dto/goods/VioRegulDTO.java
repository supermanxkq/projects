package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;


/**  
 * @Title: VioRegul.java
 * @Package: com.paySystem.ic.bean.goods
 * @Description: 违规案例
 * @Author: Jacky 
 * @Date: 2014-8-27 下午4:41:45
 * @Version: V1.0  
 */
public class VioRegulDTO implements Serializable {

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
	
	public Integer getUnruleId() {
		return unruleId;
	}
	public void setUnruleId(Integer unruleId) {
		this.unruleId = unruleId;
	}
	public Integer getUnruleTypeId() {
		return unruleTypeId;
	}
	public void setUnruleTypeId(Integer unruleTypeId) {
		this.unruleTypeId = unruleTypeId;
	}
	public String getUnruleWay() {
		return unruleWay;
	}
	public void setUnruleWay(String unruleWay) {
		this.unruleWay = unruleWay;
	}
	
	

}
