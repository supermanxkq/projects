package com.paySystem.ic.bean.report;

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
 * @ProjectName:MCIU_DS
 * @ClassName:MemShoppingSum
 * @Description:会员购物汇总表的实体Bean
 * @date: 2014-7-23
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name = "R_MemShoppingSum")
public class MemShoppingSum implements Serializable {

	private static final long serialVersionUID = -3327937828546704481L;

	/** 编号ID */
	private Integer memShopId;
	/** 商户编号 */
	public String merId;
	/** 会员编号 */
	private String memId;
	/** 会员昵称 */
	private String memName;
	/** 购物量 */
	private BigDecimal shoppingQty;
	/** 金额 */
	private BigDecimal shoppingAmt;
	/** 销量排名 */
	private BigDecimal shoppingRank;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getMemShopId() {
		return memShopId;
	}

	public void setMemShopId(Integer memShopId) {
		this.memShopId = memShopId;
	}

	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(length = 15)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(length = 50)
	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Column(columnDefinition = "int")
	public BigDecimal getShoppingQty() {
		return shoppingQty;
	}

	public void setShoppingQty(BigDecimal shoppingQty) {
		this.shoppingQty = shoppingQty;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getShoppingAmt() {
		return shoppingAmt;
	}

	public void setShoppingAmt(BigDecimal shoppingAmt) {
		this.shoppingAmt = shoppingAmt;
	}

	@Column(columnDefinition = "int")
	public BigDecimal getShoppingRank() {
		return shoppingRank;
	}

	public void setShoppingRank(BigDecimal shoppingRank) {
		this.shoppingRank = shoppingRank;
	}

}
