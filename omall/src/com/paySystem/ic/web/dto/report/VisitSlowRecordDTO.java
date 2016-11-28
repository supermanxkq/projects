package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import sun.security.util.BigInt;

import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitSlowRecordDTO
 * @Description:访客流量统计DTO
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
public class VisitSlowRecordDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**编号Id*/
	private Integer visitId;
	/**会员编号*/
	private String memId;
	/**访客Ip*/
	private String visitIp;
	/**访问时间*/
	private Date accessTime;
	/**地区*/
	private String area;
	/**访问来源*/
	private String visitSource;
	/**入口页面*/
	private String entrancePage;
	/**开始日期*/
	private String beginDate;
	/**结束日期*/
	private String endDate;
	/**访客总数*/
    private BigInteger totalMember;
	/**访客IP*/
    private BigInteger totalIp;
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getVisitSource() {
		return visitSource;
	}
	public void setVisitSource(String visitSource) {
		this.visitSource = visitSource;
	}
	public String getEntrancePage() {
		return entrancePage;
	}
	public void setEntrancePage(String entrancePage) {
		this.entrancePage = entrancePage;
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
	public BigInteger getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(BigInteger totalMember) {
		this.totalMember = totalMember;
	}
	public BigInteger getTotalIp() {
		return totalIp;
	}
	public void setTotalIp(BigInteger totalIp) {
		this.totalIp = totalIp;
	}
	
}
