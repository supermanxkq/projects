package com.paySystem.ic.web.dto.goods;

import java.util.Date;

/**
 *商户违规表bean
 * @ClassName: MerUnrule
 * @Description: 商户违规类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
public class MerUnruleDTO {
	
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

	public long getMerIllId() {
		return merIllId;
	}

	public void setMerIllId(long merIllId) {
		this.merIllId = merIllId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getUnRuleBehald() {
		return unRuleBehald;
	}

	public void setUnRuleBehald(int unRuleBehald) {
		this.unRuleBehald = unRuleBehald;
	}

	public int getPhType() {
		return phType;
	}

	public void setPhType(int phType) {
		this.phType = phType;
	}

	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}

	public Date getPunishTime() {
		return punishTime;
	}

	public void setPunishTime(Date punishTime) {
		this.punishTime = punishTime;
	}

	public int getUnruleWay() {
		return unruleWay;
	}

	public void setUnruleWay(int unruleWay) {
		this.unruleWay = unruleWay;
	}
	
}
