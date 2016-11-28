package com.paySystem.ic.web.dto.stock;
import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:ServiceListDTO
 * @Description:服务清单数据传输类
 * @date: 2014-7-22上午11:59:12
 * @author: 徐凯强
 * @version: V1.0
 */
public class ServiceListDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -4195660940303134201L;
	/** 添加人 */
	private String addMen;
	/**添加日期 */
	private Date addTime;
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
	/** 发布状态 */
	private Integer status;
	
	
	/**查询条件 -- 开始时间*/
	private String beginDate;
	/**查询条件 -- 结束时间*/
	private String endDate;
	
	
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

	public String getAddMen() {
		return addMen;
	}

	public Date getAddTime() {
		return addTime;
	}

	public String getDescr() {
		return descr;
	}

	public String getName() {
		return name;
	}

	public String getOrganID() {
		return organID;
	}

	public Integer getServId() {
		return servId;
	}

	public Integer getServType() {
		return servType;
	}

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

}
