package com.paySystem.ic.bean.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.cxf.security.claims.authorization.Claim;

/**
 * @ProjectName:omal
 * @ClassName:CustomService
 * @Description:客服表
 * @date: 2014-11-13
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "M_CustomService")
public class CustomService {
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
	/**店铺号**/
	private String storeId;
	/**添加时间**/
	private Date createTime;
	/**所属商户  如果登录为Admin，merId和stroeId均为0**/
	private String merId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 15)
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Column(columnDefinition="timestamp")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length=15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}


}
