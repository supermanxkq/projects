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
 *商户违规表bean
 * @ClassName: MerUnrule
 * @Description: 商户违规类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_MerUnrule")
public class MerUnrule implements Serializable {
	private static final long serialVersionUID = 6053115884368013856L;
	
	/**编号 */
	private long merIllId;
	
	/**商户编号 */
	private String merId;
	
	/** 商品编号*/
	private long goodsId;
	
	/** 违规行为编号*/
	private int unRuleBehald;
	
	/** 违规商店和商品的处理状态*/
	private int phType;
	
	/** 扣除分值*/
	private double deductScore;
	
	/** 处罚时间*/
	private Date punishTime;
	
	/**违规案例 */
	private int unruleWay;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public long getMerIllId() {
		return merIllId;
	}

	public void setMerIllId(long merIllId) {
		this.merIllId = merIllId;
	}

	@Column(length=15,nullable=false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length=20,nullable=false)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length=8,nullable=false)
	public int getUnRuleBehald() {
		return unRuleBehald;
	}

	public void setUnRuleBehald(int unRuleBehald) {
		this.unRuleBehald = unRuleBehald;
	}

	@Column(length=1,nullable=false)
	public int getPhType() {
		return phType;
	}

	public void setPhType(int phType) {
		this.phType = phType;
	}

	@Column(length=5,nullable=false,scale=2)
	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}

	@Column(nullable=false)
	public Date getPunishTime() {
		return punishTime;
	}

	public void setPunishTime(Date punishTime) {
		this.punishTime = punishTime;
	}
	
	@Column(length=1,nullable=false)
	public int getUnruleWay() {
		return unruleWay;
	}

	public void setUnruleWay(int unruleWay) {
		this.unruleWay = unruleWay;
	}
}
