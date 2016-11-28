package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class DrivrCardRecordDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1324112849666702193L;

	/**
	 * 记录编号
	 */
	private Integer recordId;
	/**
	 * 司机编号
	 */
	private String driverId;

	/**
	 * 运单编号
	 */
	private String trackNo;

	/**
	 * 到达时间
	 */
	private Date ArrivalTime;
	/**
	 * 终端编号
	 */
	private String termId;
	/**终端名字**/
	private String termName;
	
	/** 起始时间 */
	private String beginTime;
	/** 结束时间 */
	private String endTime;
	/** 司机名称 */
	private String driName;
	/** 上次到达的地点 */
	private String toPlace;
	/** 手机号码 */
	private String telph;
	/** 司机姓名 **/
	private String memRealName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getTrackNo() {
		return trackNo;
	}

	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	public Date getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDriName() {
		return driName;
	}

	public void setDriName(String driName) {
		this.driName = driName;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public String getTelph() {
		return telph;
	}

	public void setTelph(String telph) {
		this.telph = telph;
	}

	public String getMemRealName() {
		return memRealName;
	}

	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}


}
