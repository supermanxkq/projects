package com.paySystem.ic.bean.report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.member.Member;

/**
 * @ProjectName:omall20140708DS
 * @ClassName:VisitorSlow
 * @Description:访客流量统计实体
 * @date: 2014-7-17
 * @author: 赵巧鹤
 * @version: V1.0
 */
@Entity
@Table(name="R_VisitorSlow")
public class VisitSlowRecord implements Serializable{


	private static final long serialVersionUID = 1L;
	/**编号Id*/
	private Integer visitId;
	/**会员编号*/
	private Member member;
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
	/**机构*/
	private String organId;
	/**商户*/
	private String merId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="memId") 
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	@Column
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column
	public String getVisitSource() {
		return visitSource;
	}
	public void setVisitSource(String visitSource) {
		this.visitSource = visitSource;
	}
	@Column
	public String getEntrancePage() {
		return entrancePage;
	}
	public void setEntrancePage(String entrancePage) {
		this.entrancePage = entrancePage;
	}
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	@Column(length =15)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	

}
