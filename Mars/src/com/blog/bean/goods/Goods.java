package com.blog.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_goods")
public class Goods implements Serializable {

	private static final long serialVersionUID = 8516492439561977307L;
	private Integer id;
	private String name;
	private String content;
	private Integer status;
	private Integer price;
	private String imgsrc;
	private String description;

	public String getContent() {
		return content;
	}

	@Lob
	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column
	public String getImgsrc() {
		return imgsrc;
	}

	@Column
	public String getName() {
		return name;
	}

	@Column
	public Integer getPrice() {
		return price;
	}

	@Column
	public Integer getStatus() {
		return status;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
