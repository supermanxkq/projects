package com.paySystem.ic.web.dto.member;

import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
 * @ProjectName:omal
 * @ClassName:CustomServiceDTO
 * @Description:客服表DTO
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */

public class CustomServiceDTO extends BaseDTO {
	/**
	 * 主键
	 */
	private long id;
	/**
	 * QQ号
	 */
	private String qq;
	/**
	 * 职务，如：售前 售后 客服 技术支持
	 */
	private String name;
	/**
	 * 所属商户
	 */
	private String storeId;
	/** 添加时间 **/
	private Date createTime;
	/** 所属商户 **/
	private String merId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

}
