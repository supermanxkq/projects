package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName: omall_back
 * @ClassName: FreightSetDTO
 * @Description: 运费设置
 * @date: 2014-11-14
 * @author: 廖晓远 
 * @version: V1.0
 */
public class FreightSetDTO implements Serializable {

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

	public Integer getFsId() {
		return fsId;
	}

	public void setFsId(Integer fsId) {
		this.fsId = fsId;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public BigDecimal getTradeFreightMoney() {
		return tradeFreightMoney;
	}

	public void setTradeFreightMoney(BigDecimal tradeFreightMoney) {
		this.tradeFreightMoney = tradeFreightMoney;
	}

	public BigDecimal getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(BigDecimal freightMoney) {
		this.freightMoney = freightMoney;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}



