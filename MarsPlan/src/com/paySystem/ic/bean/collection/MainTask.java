package com.paySystem.ic.bean.collection;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:TimeManager
 * @ClassName:MainTask
 * @Description:主任务实体类
 * @date: 2014-10-18下午04:52:56
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "t_maintask")
public class MainTask implements Serializable {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 3552995468677095819L;
	/** 主任务编号 */
	private Integer mainTaskId;
	/** 主任务名称 */
	private String mainTaskName;
	/** 任务状态 0已完成1启用9删除 */
	private Integer status;
	/** 评论 */
	private String comments;
	/** 任务描述 */
	private String descr;
	/** 创建时间 */
	private Date createTime;
	/** 优先级0无1低2中3高 */
	private Integer level;
	/** 预估时间小时 */
	private Integer estimatedTimeHour;
	/** 预估时间分钟 */
	private Integer estimatedTimeMinu;
	/** 截止日期 */
	private Date byDate;
	/** 商户编号 */
	private String merId;

	private String organId;
	/** 用户名 */
	private String userName;
	/**提醒方式*/
	private Integer remindWay;

	@Column
	public Date getByDate() {
		return byDate;
	}

	@Column
	public String getComments() {
		return comments;
	}

	@Column
	public Date getCreateTime() {
		return createTime;
	}

	@Column(length = 255)
	public String getDescr() {
		return descr;
	}

	@Column(length = 2)
	public Integer getEstimatedTimeHour() {
		return estimatedTimeHour;
	}

	@Column(length = 2)
	public Integer getEstimatedTimeMinu() {
		return estimatedTimeMinu;
	}

	@Column(length = 1)
	public Integer getLevel() {
		return level;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getMainTaskId() {
		return mainTaskId;
	}

	@Column
	public String getMainTaskName() {
		return mainTaskName;
	}

	@Column
	public String getMerId() {
		return merId;
	}

	@Column
	public String getOrganId() {
		return organId;
	}
	@Column(length = 1)
	public Integer getRemindWay() {
		return remindWay;
	}

	@Column(length = 1)
	public Integer getStatus() {
		return status;
	}

	@Column
	public String getUserName() {
		return userName;
	}

	public void setByDate(Date byDate) {
		this.byDate = byDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setEstimatedTimeHour(Integer estimatedTimeHour) {
		this.estimatedTimeHour = estimatedTimeHour;
	}

	public void setEstimatedTimeMinu(Integer estimatedTimeMinu) {
		this.estimatedTimeMinu = estimatedTimeMinu;
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

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public void setRemindWay(Integer remindWay) {
		this.remindWay = remindWay;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
