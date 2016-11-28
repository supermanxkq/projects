package com.paySystem.ic.bean.stock;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:ServiceList
 * @Description:服务清单实体类
 * @date: 2014-7-22上午11:59:12
 * @author: 徐凯强
 * @version: V1.0
 */
@Entity
@Table(name = "B_ServiceList")
public class ServiceList implements Serializable {

	private static final long serialVersionUID = -4195660940303134201L;
	/** 添加人 */
	private String addMen;
	/**添加日期 */
	private Date addTime;
	/**更新日期 */
	private Date updateTime;
	/** 内容详情*/
	private String descr;
	/**标题 */
	private String name;
	/** 机构ID */
	private String organID;
	/** id */
	private Integer servId;
	/** 服务类型 */
	private Integer servType;
	/** 发布状态 1：已发布0：未发布 9:删除*/
	private Integer status;
	
	
	
    @Column(length = 20, nullable = true)
	public String getAddMen() {
		return addMen;
	}

	@Column(nullable = true)
	public Date getAddTime() {
		return addTime;
	}
	@Lob 
	public String getDescr() {
		return descr;
	}

	@Column(length = 50, nullable = true)
	public String getName() {
		return name;
	}

	@Column(length = 8, nullable = true)
	public String getOrganID() {
		return organID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 8)
	public Integer getServId() {
		return servId;
	}

	@Column(length = 1, nullable = true)
	public Integer getServType() {
		return servType;
	}

	@Column(length = 1, nullable = true)
	public Integer getStatus() {
		return status;
	}

	public void setAddMen(String addMen) {
		this.addMen = addMen;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrganID(String organID) {
		this.organID = organID;
	}

	public void setServId(Integer servId) {
		this.servId = servId;
	}

	public void setServType(Integer servType) {
		this.servType = servType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
