package com.paySystem.ic.bean.app;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:AppUser
 * @Description:App端需要 app用户信息
 * @date: 2014-5-26下午03:53:23
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_schedule")
public class Schedule implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/** 预约Id号  */
	private Integer yId;
	/** 会员编号 */
	private String memId;
	private String merId;
	/** app用户名称 */
	private String title;
	//审核通过，未通过，待审核，已取消等状态
	private String status;
	private String distance;
	/** 预约时间 */
	private String date;
	/** 预约地点*/
	private String address;
	private String phone;
	//洗车、打蜡、维修、保养
	private String flg;
	//车型
	private String cx;
	//车牌
	private String cp;
	
	/** 创建时间 */
	private String createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getyId() {
		return yId;
	}
	public void setyId(Integer yId) {
		this.yId = yId;
	}
	
	@Column(length=15)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(length=20)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	
	}
	@Column(length=15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}
	@Column(length=60)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(length=20)
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	@Column(length=20)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	@Column(length=100)
	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=11)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=2)
	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}
	@Column(length=20)
	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}
	@Column(length=20)
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	@Column(length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
