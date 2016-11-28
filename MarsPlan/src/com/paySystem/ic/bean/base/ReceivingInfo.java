package com.paySystem.ic.bean.base;

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

import com.paySystem.ic.bean.member.Member;

/**
 * @ProjectName:omall
 * @ClassName:ReceivingInfo
 * @Description:收货信息的实体
 * @date: 2014-9-17
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "M_ReceivingInfo")
public class ReceivingInfo implements Serializable{

	private static final long serialVersionUID = -4553627384871500870L;

	/**收货编号*/
	private Integer recId;
	/**会员实体*/
	private Member member;
	/**所在地区*/
	private String area;
	/**邮政编码*/
	private String zip;
	/**街道地址*/
	private String streetAddress;
	/**收货人姓名*/
	private String memName;
	/**手机号码*/
	private String phoneNo;
	/**创建时间*/
	private Date createTime;
	/**是否默认地址       0:否     1：是*/
	private Integer sign;
	/**维护更新时间*/
	private Date updateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRecId() {
		return recId;
	}
	public void setRecId(Integer recId) {
		this.recId = recId;
	}
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "memId")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Column(length=30)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(length=7)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Column(length=150)
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	@Column(length=30)
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	@Column(length=11)
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	  @Column(columnDefinition = "char(1)")
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
