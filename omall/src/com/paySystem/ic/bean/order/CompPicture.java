package com.paySystem.ic.bean.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omal
 * @ClassName:CompPicture
 * @Description:投诉图片资源表
 * @date: 2014-11-13
 * @author: 王楠
 * @version: V1.0
 */
 @Entity
 @Table(name="B_CompPic")
public class CompPicture implements Serializable{

	private static final long serialVersionUID = -3649224080511451851L;

	/**图片id*/
	private Integer compPicId ;
	/**图片路径*/
	private String picPath;
	/**投诉信息id*/
	private Integer complaintId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getCompPicId() {
		return compPicId;
	}
	public void setCompPicId(Integer compPicId) {
		this.compPicId = compPicId;
	}
	@Column(length=255)
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Column(length=10)
	public Integer getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	
}
