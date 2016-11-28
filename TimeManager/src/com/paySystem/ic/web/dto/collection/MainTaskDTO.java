package com.paySystem.ic.web.dto.collection;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:MainTaskDTO
 * @Description:主任务数据传输对象
 * @date: 2014-10-18下午10:02:43
 * @author: 徐凯强
 * @version: V1.0
 */
public class MainTaskDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 3380445115780513689L;

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

	/** 创建时间字符串 */
	private String createTimeString;
	/** 截至日期字符串 */
	private String byDateString;
	/** 模糊查询开始时间 */
	private String beginDate;
	/** 模糊查询结束时间 */
	private String endDate;
	private String merId;
	private String organId;
	/** 用户名 */
	private String userName;
	/** 批量添加任务的字符串 */
	private String tasksString;
	/** 提醒方式 */
	private Integer remindWay;
	/** 收信手机号 */
	private String toPhone;
	/** 未完成，已完成，已延迟百分比 */
	long[] percents = new long[3];
	/** 判断方法标志1.查询今日待办null查询收集箱 */
	private Integer flag;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public long[] getPercents() {
		return percents;
	}

	public void setPercents(long[] percents) {
		this.percents = percents;
	}

	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public Integer getRemindWay() {
		return remindWay;
	}

	public void setRemindWay(Integer remindWay) {
		this.remindWay = remindWay;
	}

	public String getTasksString() {
		return tasksString;
	}

	public void setTasksString(String tasksString) {
		this.tasksString = tasksString;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getByDateString() {
		return byDateString;
	}

	public void setByDateString(String byDateString) {
		this.byDateString = byDateString;
	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public Integer getMainTaskId() {
		return mainTaskId;
	}

	public void setMainTaskId(Integer mainTaskId) {
		this.mainTaskId = mainTaskId;
	}

	public String getMainTaskName() {
		return mainTaskName;
	}

	public void setMainTaskName(String mainTaskName) {
		this.mainTaskName = mainTaskName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getEstimatedTimeHour() {
		return estimatedTimeHour;
	}

	public void setEstimatedTimeHour(Integer estimatedTimeHour) {
		this.estimatedTimeHour = estimatedTimeHour;
	}

	public Integer getEstimatedTimeMinu() {
		return estimatedTimeMinu;
	}

	public void setEstimatedTimeMinu(Integer estimatedTimeMinu) {
		this.estimatedTimeMinu = estimatedTimeMinu;
	}

	public Date getByDate() {
		return byDate;
	}

	public void setByDate(Date byDate) {
		this.byDate = byDate;
	}
}
