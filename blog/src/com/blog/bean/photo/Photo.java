package com.blog.bean.photo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_photo")
public class Photo implements Serializable{

	/** */
	private static final long serialVersionUID = 8516492439561977307L;
	private Integer id;
	private Date createDate;
	private String imgSrc;
	
	@Column
	public Date getCreateDate() {
		return createDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	@Column
	public String getImgSrc() {
		return imgSrc;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}
