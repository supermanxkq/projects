package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ProjectName:car
 * @ClassName:GoodsType
 * @Description:商品信息实体类
 * @date: 2016年2月7日下午1:04:08
 * @author: 徐凯强
 * @version: V1.0
 * @date:2016年2月7日下午1:04:08
 */
@Entity
@Table(name = "t_goodsType")
public class GoodsType   implements Serializable{

	//
	private static final long serialVersionUID = 7339902691115878742L;
	// 编号
	private Integer id;
	// 名称
	private String name;
	// 状态
	private Integer status;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	@Column
	public Integer getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
