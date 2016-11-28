package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSet
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
@Entity
@Table(name="B_FreightSet")
public class FreightSet implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer fsId;
	/**
	 * 每笔订单设定金额
	 */
	private BigDecimal tradeMoney;
	/**
	 * 订单不够所收运费
	 */
	private BigDecimal tradeFreightMoney;
	/**
	 * 固定运费
	 */
	private BigDecimal freightMoney;
	/**
	 * 运费类型 0： 满多少免运费或者运费多少  1：每订单运费多少.
	 */
	private Integer type;
	/**
	 * 商户ID 平台为机构编号
	 */
	private String merId;
	/**
	 * 创建时间或者更新时间
	 */
	private Date updateTime;
	/**
	 * 商城默认设置0：不是1：是
	 */
	private Integer isSys;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getFsId() {
		return fsId;
	}

	public void setFsId(Integer fsId) {
		this.fsId = fsId;
	}

	@Column(columnDefinition = "DECIMAL(6,2)")
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	@Column(columnDefinition = "DECIMAL(5,2)")
	public BigDecimal getTradeFreightMoney() {
		return tradeFreightMoney;
	}

	public void setTradeFreightMoney(BigDecimal tradeFreightMoney) {
		this.tradeFreightMoney = tradeFreightMoney;
	}

	@Column(columnDefinition = "DECIMAL(5,2)")
	public BigDecimal getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(BigDecimal freightMoney) {
		this.freightMoney = freightMoney;
	}

	@Column(columnDefinition="char(1)")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(length=15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(columnDefinition="char(1)")
	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
}



