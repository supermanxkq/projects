package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:MerLevelModel
 * @Description: 商户等级模型
 * @date: 2014-5-23下午08:01:31
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_merlevelmodel")
public class MerLevelModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	/** 模型编号 */
	private Integer levelModelId;
	
	/** 模型名称 */
	private String levelModeName;
	
	/** 启用状态 */
	private Integer status;
	
	/** 操作人*/
	private String operaId;
	
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;
	
	/**考核项目:合作方式*/
	private Integer coopWayId ;
	
	/**考核项目名称：合作方式*/
	private String coopWayName;
	
	/**考核项目权重：合作方式*/
	private Integer coopWeight;
	
	/**考核项目：保证金*/
	private Integer bail;
	
	/**考核项目名称:保证金*/
	private String bailName;
	
	/**保证金权重*/
	private Integer bailWeight;
	
	/**购油数量*/
	private Integer buyOils;
	
	/**考核项目名称：购油数量*/
	private String buyOilsName;
	
	/**购油数量权重*/
	private Integer buyOilsWeight;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getLevelModelId() {
		return levelModelId;
	}

	public void setLevelModelId(Integer levelModelId) {
		this.levelModelId = levelModelId;
	}

	@Column(length=50)
	public String getLevelModeName() {
		return levelModeName;
	}

	public void setLevelModeName(String levelModeName) {
		this.levelModeName = levelModeName;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length=50)
	public String getOperaId() {
		return operaId;
	}

	public void setOperaId(String operaId) {
		this.operaId = operaId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column
	public Integer getCoopWayId() {
		return coopWayId;
	}

	public void setCoopWayId(Integer coopWayId) {
		this.coopWayId = coopWayId;
	}

	@Column(length=30)
	public String getCoopWayName() {
		return coopWayName;
	}

	public void setCoopWayName(String coopWayName) {
		this.coopWayName = coopWayName;
	}
	
	@Column
	public Integer getCoopWeight() {
		return coopWeight;
	}

	public void setCoopWeight(Integer coopWeight) {
		this.coopWeight = coopWeight;
	}

	@Column
	public Integer getBail() {
		return bail;
	}

	public void setBail(Integer bail) {
		this.bail = bail;
	}

	@Column(length=30)
	public String getBailName() {
		return bailName;
	}

	public void setBailName(String bailName) {
		this.bailName = bailName;
	}

	@Column
	public Integer getBailWeight() {
		return bailWeight;
	}

	public void setBailWeight(Integer bailWeight) {
		this.bailWeight = bailWeight;
	}

	@Column
	public Integer getBuyOils() {
		return buyOils;
	}

	public void setBuyOils(Integer buyOils) {
		this.buyOils = buyOils;
	}

	@Column
	public Integer getBuyOilsWeight() {
		return buyOilsWeight;
	}

	public void setBuyOilsWeight(Integer buyOilsWeight) {
		this.buyOilsWeight = buyOilsWeight;
	}

	@Column(length=30)
	public String getBuyOilsName() {
		return buyOilsName;
	}

	public void setBuyOilsName(String buyOilsName) {
		this.buyOilsName = buyOilsName;
	}



	
	
	
	

}
