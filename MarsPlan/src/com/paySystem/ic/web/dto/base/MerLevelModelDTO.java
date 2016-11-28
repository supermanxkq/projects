package com.paySystem.ic.web.dto.base;

import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:MerLevelModelDTO
 * @Description:商户等级模型DTO类
 * @date: 2014-5-23下午08:57:27
 * @author: 谢洪飞
 * @version: V1.0
 */
public class MerLevelModelDTO extends BaseDTO {


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
	
	/**考核项目名称*/
	private String coopWayName;
	
	/**考核项目权重*/
	private Integer weight;

	public Integer getLevelModelId() {
		return levelModelId;
	}

	public void setLevelModelId(Integer levelModelId) {
		this.levelModelId = levelModelId;
	}

	public String getLevelModeName() {
		return levelModeName;
	}

	public void setLevelModeName(String levelModeName) {
		this.levelModeName = levelModeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperaId() {
		return operaId;
	}

	public void setOperaId(String operaId) {
		this.operaId = operaId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCoopWayId() {
		return coopWayId;
	}

	public void setCoopWayId(Integer coopWayId) {
		this.coopWayId = coopWayId;
	}

	public String getCoopWayName() {
		return coopWayName;
	}

	public void setCoopWayName(String coopWayName) {
		this.coopWayName = coopWayName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	
}
