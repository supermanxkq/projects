package com.paySystem.ic.bean.goods;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 平台处理商品记录表bean
 * @ClassName: PlatHandleRecord
 * @Description: 平台处理商品记录类
 * @date: 2014-08-06 上午02:29:36
 * @author: Jacky
 * @version: V1.0
 */
@Entity
@Table(name = "B_platHandleRecord")
public class PlatHandleRecord implements Serializable {
	private static final long serialVersionUID = -2782052502227273974L;
	
	/**主键id */
	private long phId;
	
	/** 商品编号ID*/
	private long goodsId;
	
	/** 状态*/
	private int phType;
	
	/**处理原因 */
	private String phReason;
	
	/**违反条例 */
	private String vioRugleId;
	
	/**处理意见说明 */
	private byte[] phDescr;
	
	/** 处理人*/
	private String operatorId;
	
	/**处理时间 */
	private Date createTime;
	
	/** 扣除分值*/
	private double deductScore;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public long getPhId() {
		return phId;
	}

	public void setPhId(long phId) {
		this.phId = phId;
	}

	@Column(length=20,nullable=false)
	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	@Column(length=1,nullable=false)
	public int getPhType() {
		return phType;
	}

	public void setPhType(int phType) {
		this.phType = phType;
	}

	@Column(length=255,nullable=false)
	public String getPhReason() {
		return phReason;
	}

	public void setPhReason(String phReason) {
		this.phReason = phReason;
	}

	@Column(length=16,nullable=false)
	public String getVioRugleId() {
		return vioRugleId;
	}

	public void setVioRugleId(String vioRugleId) {
		this.vioRugleId = vioRugleId;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "BLOB",nullable=false)
	public byte[] getPhDescr() {
		return phDescr;
	}

	public void setPhDescr(byte[] phDescr) {
		this.phDescr = phDescr;
	}

	@Column(length=15,nullable=false)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(nullable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length=5,scale=2)
	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}
	
}
