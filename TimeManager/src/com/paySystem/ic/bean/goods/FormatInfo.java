package com.paySystem.ic.bean.goods;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格实体bean
 * @ClassName: FormatInfo
 * @Description: 商品规格管理类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_FormatInfo")
public class FormatInfo implements Serializable {
	private static final long serialVersionUID = 241271659434331528L;

	/** 编号 **/
	private long fInfoId;
	
	/** 规格id**/
	private long formatId;
	
	/** 规格值**/
	private String formatValue;
	
	/** 商品id**/
	private long goodsId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(length=8,nullable=false)
	public long getfInfoId() {
		return fInfoId;
	}

	public void setfInfoId(long fInfoId) {
		this.fInfoId = fInfoId;
	}
	
	@Column(length=60,nullable=false)
	public String getFormatValue() {
		return formatValue;
	}

	public void setFormatValue(String formatValue) {
		this.formatValue = formatValue;
	}

	@Column(length=20,nullable=false)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length=20,nullable=false)
	public long getFormatId() {
		return formatId;
	}

	public void setFormatId(long formatId) {
		this.formatId = formatId;
	}

}
