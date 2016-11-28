package com.paySystem.ic.bean.buss;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 活动管理实体bean
 * @ClassName:Promotion
 * @Description:活动管理实体类
 * @date: 2014-8-18 上午08:29:36
 * @author: luckygoup
 * @version: V1.0
 */
@Entity
@Table(name = "b_promotion")
public class Promotion implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	/**活动编号*/
	private Integer proid;
	
	/**活动名称*/
	private String proname;
	
	/**活动主办方*/
	private String proHost;
	
	/**状态
		1：未招募
		2：招募中
		3：审核阶段
		4：活动中
		5：已结束
		6：已终止
		9：已删除
	*/
	private String status;
	
	/**活动性质
		1：折扣促销
		2：抽奖促销
		3：会员制促销
		4.赠品促销
	*/
	private String proType;
	
	/**活动名目
	1：季节性活动
	2：开业促销活动
	3：节庆促销活动
	4：新品上市促销
	5：庆典促销活动
	9：其它促销活动
	*/
	private String proItem;
	
	/**活动类型
	0：平台活动
	1：商户活动
	*/
	private String hostSign;
	
	/**开始时间*/
	private Date beginTime;
	
	/**结束时间*/
	private Date endTime;
	
	/**活动规则说明*/
	private byte[] descr;
	
	/**活动图片路径*/
	private String proImg;
	
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;
	
	/**操作人Id*/
	private String operatorId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getProid() {
		return this.proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	@Column(name = "proname", nullable = false, length = 30)
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	@Column(name = "proHost", nullable = false, length = 15)
	public String getProHost() {
		return this.proHost;
	}

	public void setProHost(String proHost) {
		this.proHost = proHost;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "proType", nullable = false, length = 1)
	public String getProType() {
		return this.proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	@Column(name = "proItem", nullable = false, length = 1)
	public String getProItem() {
		return this.proItem;
	}

	public void setProItem(String proItem) {
		this.proItem = proItem;
	}

	@Column(name = "hostSign", nullable = false, length = 1)
	public String getHostSign() {
		return this.hostSign;
	}

	public void setHostSign(String hostSign) {
		this.hostSign = hostSign;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "beginTime", nullable = false, length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endTime", nullable = false, length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Lob
    @Basic(fetch=FetchType.LAZY)
	@Column(name = "descr",columnDefinition="BLOB")
	public byte[] getDescr() {
		return this.descr;
	}

	public void setDescr(byte[] descr) {
		this.descr = descr;
	}

	@Column(name = "proImg", length = 200)
	public String getProImg() {
		return this.proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createTime", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updateTime", nullable = false, length = 10)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "operatorId", nullable = false, length = 30)
	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
}
