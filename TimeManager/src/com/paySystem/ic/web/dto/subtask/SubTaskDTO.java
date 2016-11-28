package com.paySystem.ic.web.dto.subtask;

import java.io.Serializable;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:TimeManager
 * @ClassName:SubTaskDTO
 * @Description:子任务数据传输对象
 * @date: 2014-10-21下午10:46:45
 * @author: 徐凯强
 * @version: V1.0
 */
public class SubTaskDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 3380445115780513689L;

	/** 子任务编号 */
	private Integer subTaskId;
	/** 子任务名称 */
	private String subTaskName;
	/**
	 * 状态0已完成1启用 9删除
	 */
	private Integer status;
	/** 主任务外键 */
	private Integer mainTaskId;

	public Integer getSubTaskId() {
		return subTaskId;
	}

	public void setSubTaskId(Integer subTaskId) {
		this.subTaskId = subTaskId;
	}

	public String getSubTaskName() {
		return subTaskName;
	}

	public void setSubTaskName(String subTaskName) {
		this.subTaskName = subTaskName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMainTaskId() {
		return mainTaskId;
	}

	public void setMainTaskId(Integer mainTaskId) {
		this.mainTaskId = mainTaskId;
	}
}
