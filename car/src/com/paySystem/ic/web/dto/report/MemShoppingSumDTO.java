package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

public class MemShoppingSumDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 5825602753802228367L;

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

	public Integer getMemShopId() {
		return memShopId;
	}

	public void setMemShopId(Integer memShopId) {
		this.memShopId = memShopId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public BigDecimal getShoppingQty() {
		return shoppingQty;
	}

	public void setShoppingQty(BigDecimal shoppingQty) {
		this.shoppingQty = shoppingQty;
	}

	public BigDecimal getShoppingAmt() {
		return shoppingAmt;
	}

	public void setShoppingAmt(BigDecimal shoppingAmt) {
		this.shoppingAmt = shoppingAmt;
	}

	public BigDecimal getShoppingRank() {
		return shoppingRank;
	}

	public void setShoppingRank(BigDecimal shoppingRank) {
		this.shoppingRank = shoppingRank;
	}

}
