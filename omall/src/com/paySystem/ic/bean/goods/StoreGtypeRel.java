package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: omall
 * @ClassName: StoreGtypeRel
 * @Description: 店铺商品分类关联表
 * @date: 2014-11-12
 * @author: 廖晓远 
 * @version: V1.0
 */
@Entity
@Table(name="B_StoreGtypeRel")
public class StoreGtypeRel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private int stId;
	/**
	 * 店铺ID
	 */
	private String storeId;
	/**
	 * 分类ID
	 */
	private Integer familyId;
	/**
	 * 操作时间
	 */
	private Date operatorTime;
	/**
	 * 操作人
	 */
	private int operatorId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public int getStId() {
		return stId;
	}
	public void setStId(int stId) {
		this.stId = stId;
	}
	@Column
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	@Column
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	@Column(length = 15)
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	@Column(length = 11)
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

}
