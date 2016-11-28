package com.paySystem.ic.bean.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:TimeManager
 * @ClassName:Project
 * @Description:项目实体类
 * @date: 2014-10-18下午09:52:06
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "b_project")
public class Project implements Serializable {
	private static final long serialVersionUID = 2025590262398757450L;
	/** 项目编号 */
	private Integer projectId;
	/** 主要任务外键 */
	private Integer mainTaskId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getProjectId() {
		return projectId;
	}

	@Column
	public Integer getMainTaskId() {
		return mainTaskId;
	}

	public void setMainTaskId(Integer mainTaskId) {
		this.mainTaskId = mainTaskId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
