package com.paySystem.ic.bean.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:Coupons
 * @Description:app端信息需要
 * @date: 2014-5-27下午04:45:10
 * @author: 王月
 * @version: V1.0
 */
@Entity
@Table(name="b_coupons")
public class Coupons implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer couponsId;//优惠劵id
	private String couponsTitle;//优惠券标题
	private String couponsContent;//优惠券内容
	private Date createTime;//创建时间
	private Integer status;//使用状态
	private Integer oilStationId;//加油站ID
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getCouponsId() {
		return couponsId;
	}
	public void setCouponsId(Integer couponsId) {
		this.couponsId = couponsId;
	}
	
	@Column(length=50)
	public String getCouponsTitle() {
		return couponsTitle;
	}
	public void setCouponsTitle(String couponsTitle) {
		this.couponsTitle = couponsTitle;
	}
	
	@Column(length=200)
	public String getCouponsContent() {
		return couponsContent;
	}
	public void setCouponsContent(String couponsContent) {
		this.couponsContent = couponsContent;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(length=15)
	public Integer getOilStationId() {
		return oilStationId;
	}
	public void setOilStationId(Integer oilStationId) {
		this.oilStationId = oilStationId;
	}
}