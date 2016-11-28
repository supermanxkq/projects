package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:ReceivingInfoDTO
 * @Description:收货信息实体的dto
 * @date: 2014-9-17
 * @author: 王楠
 * @version: V1.0
 */
public class ReceivingInfoDTO  extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 8256895990902388645L;

	/**收货编号*/
	private Integer recId;
	/**会员实体*/
	private Member member;
	/**会员编号*/
	private String memId;
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
	
	public Integer getRecId() {
		return recId;
	}
	public void setRecId(Integer recId) {
		this.recId = recId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
