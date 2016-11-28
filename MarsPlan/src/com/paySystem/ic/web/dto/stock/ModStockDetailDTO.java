package com.paySystem.ic.web.dto.stock;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.card.CardNo;
import com.paySystem.ic.bean.stock.ModStock;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:ModStockDetailDTO
 * @Description:库存变动明细DTO
 * @date: 2013-12-14下午05:46:01
 * @author: 谢洪飞
 * @version: V1.0
 */
public class ModStockDetailDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 8250304214678057397L;
	
	/** 流水号  */
	private Integer id;
	/** 库存变动信息 */
	private ModStock modStock;
	/** 变动的卡 */
	private CardNo cardNo;
	/** 变动流水号*/
	private String modStockId;
	/** 卡号 */
	private String cardNoId;
	/** 入库状态 */
	private Integer stockStatus;
	/** 库存变动流向 */
	private Integer stockFlag;
	/**入库状态描述*/
	private String stockStatusDesc;
	/**入库流向描述*/
	private String stockFlagDesc;
	
	/**开始时间*/
	private Date beginDate;
	/**结束时间*/
	private Date endDate;
	
	

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStockStatusDesc() {
		return stockStatusDesc;
	}

	public void setStockStatusDesc(String stockStatusDesc) {
		this.stockStatusDesc = stockStatusDesc;
	}

	public String getStockFlagDesc() {
		return stockFlagDesc;
	}

	public void setStockFlagDesc(String stockFlagDesc) {
		this.stockFlagDesc = stockFlagDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModStock getModStock() {
		return modStock;
	}

	public void setModStock(ModStock modStock) {
		this.modStock = modStock;
	}

	public CardNo getCardNo() {
		return cardNo;
	}

	public void setCardNo(CardNo cardNo) {
		this.cardNo = cardNo;
	}

	public String getModStockId() {
		return modStockId;
	}

	public void setModStockId(String modStockId) {
		this.modStockId = modStockId;
	}

	public String getCardNoId() {
		return cardNoId;
	}

	public void setCardNoId(String cardNoId) {
		this.cardNoId = cardNoId;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Integer getStockFlag() {
		return stockFlag;
	}

	public void setStockFlag(Integer stockFlag) {
		this.stockFlag = stockFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
