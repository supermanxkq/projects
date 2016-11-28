package com.paySystem.ic.web.dto.evaluation;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: omal
 * @ClassName: GriTotalDTO
 * @Description: 评价统计DTO
 * @date: 2014-10-27
 * @author: 廖晓远
 * @version: V1.0
 */
public class GriTotalDTO implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 评分汇总编号
	 */
	private long griTotalId;

	/**
	 * 商户编号
	 */
	private String merId;

	/**
	 * 评价（好评、中评、差评）总数
	 */
	private Integer allGriNum;

	/**
	 * 好评个数
	 */
	private int goodGriNum;

	/**
	 * 中评个数
	 */
	private int middleGriNum;

	/**
	 * 差评个数
	 */
	private int baDGirNum;

	/**
	 * 好评率（单位：%）
	 */
	private Integer goodGriRate;

	/**
	 * 中评率（单位：%）
	 */
	private Integer middleGriRate;

	/**
	 * 差评率（单位：%）
	 */
	private Integer baDGirRate;

	/**
	 * 服务态度平均分
	 */
	private float serviceAvg;

	/**
	 * 宝贝真实度平均分
	 */
	private float realAvg;

	/**
	 * 发货速度平均分
	 */
	private float postAvg;

	/**
	 * 评价时间
	 */
	private Date createTime;

	/**
	 * 综合评分
	 */
	private String sumAvg;

	public long getGriTotalId() {
		return griTotalId;
	}

	public void setGriTotalId(long griTotalId) {
		this.griTotalId = griTotalId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * @return int 评价（好评、中评、差评）总数
	 */
	public int getAllGriNum() {
		return allGriNum;
	}

	/**
	 * @param allGriNum
	 *            评价（好评、中评、差评）总数
	 */
	public void setAllGriNum(int allGriNum) {
		this.allGriNum = allGriNum;
	}

	public int getGoodGriNum() {
		return goodGriNum;
	}

	public void setGoodGriNum(int goodGriNum) {
		this.goodGriNum = goodGriNum;
	}

	public int getMiddleGriNum() {
		return middleGriNum;
	}

	public void setMiddleGriNum(int middleGriNum) {
		this.middleGriNum = middleGriNum;
	}

	public int getBaDGirNum() {
		return baDGirNum;
	}

	public void setBaDGirNum(int baDGirNum) {
		this.baDGirNum = baDGirNum;
	}

	/**
	 * @return Integer 好评率（单位：%）
	 */
	public Integer getGoodGriRate() {
		return goodGriRate;
	}

	/**
	 * @param goodGriRate
	 *            好评率（单位：%）
	 */
	public void setGoodGriRate(Integer goodGriRate) {
		this.goodGriRate = goodGriRate;
	}

	/**
	 * @return Integer 中评率（单位：%）
	 */
	public Integer getMiddleGriRate() {
		return middleGriRate;
	}

	/**
	 * @param middleGriRate
	 *            中评率（单位：%）
	 */
	public void setMiddleGriRate(Integer middleGriRate) {
		this.middleGriRate = middleGriRate;
	}

	/**
	 * @return Integer 差评率（单位：%）
	 */
	public Integer getBaDGirRate() {
		return baDGirRate;
	}

	/**
	 * @param baDGirRate
	 *            差评率（单位：%）
	 */
	public void setBaDGirRate(Integer baDGirRate) {
		this.baDGirRate = baDGirRate;
	}

	public float getServiceAvg() {
		return serviceAvg;
	}

	public void setServiceAvg(float serviceAvg) {
		this.serviceAvg = serviceAvg;
	}

	public float getRealAvg() {
		return realAvg;
	}

	public void setRealAvg(float realAvg) {
		this.realAvg = realAvg;
	}

	public float getPostAvg() {
		return postAvg;
	}

	public void setPostAvg(float postAvg) {
		this.postAvg = postAvg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSumAvg() {
		return sumAvg;
	}

	public void setSumAvg(String sumAvg) {
		this.sumAvg = sumAvg;
	}

}
