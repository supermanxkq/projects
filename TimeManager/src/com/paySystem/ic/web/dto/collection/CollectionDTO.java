package com.paySystem.ic.web.dto.collection;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:CollectionDTO
 * @Description:收集箱数据传输对象
 * @date: 2014-10-18下午10:02:43
 * @author: 徐凯强
 * @version: V1.0
 */
public class CollectionDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 3380445115780513689L;

	/** 主任务编号 */
	private Integer mainTaskId;
	/** 主任务名称 */
	private String mainTaskName;
	/** 子任务外键 */
	private Integer subTaskId;
	/** 任务状态 0已完成1启用9删除 */
	private Integer status;
	/** 评论外键 */
	private Integer commentsId;
	/** 任务描述 */
	private String descr;
	/** 创建时间 */
	private Date createTime;
	/** 优先级0无1低2中3高 */
	private Integer level;
	/** 所属项目外键 */
	private Integer projectId;
	/** 预估时间 */
	private Date estimatedTime;
	/** 所属情境外键 */
	private Integer situationId;

	public Integer getCommentsId() {
		return commentsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getDescr() {
		return descr;
	}

	public Date getEstimatedTime() {
		return estimatedTime;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getMainTaskId() {
		return mainTaskId;
	}

	public String getMainTaskName() {
		return mainTaskName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public Integer getSituationId() {
		return situationId;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getSubTaskId() {
		return subTaskId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setEstimatedTime(Date estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setMainTaskId(Integer mainTaskId) {
		this.mainTaskId = mainTaskId;
	}

	public void setMainTaskName(String mainTaskName) {
		this.mainTaskName = mainTaskName;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSubTaskId(Integer subTaskId) {
		this.subTaskId = subTaskId;
	}

}
