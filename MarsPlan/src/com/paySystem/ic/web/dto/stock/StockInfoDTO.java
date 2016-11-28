package com.paySystem.ic.web.dto.stock;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:StockInfoDTO
 * @Description:库存信息DTO类
 * @date: 2013-12-11下午04:38:04
 * @author: 谢洪飞
 * @version: V1.0
 */
public class StockInfoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 6548730273336703715L;
	
	/** 流水号   */
	private Integer Id;
    /** 所属机构  */
	private Organs organs;
	/**
	 * 所属机构ID
	 * @author lily
	 * @date 2013-12-24 下午06:39:50 
	 */
	private String orgId;
	/**
	 * 所属机构名称
	 * @author lily
	 * @date 2013-12-24 下午06:39:50
	 */
	private String orgName;
	/** 所属商户  */
	private Merchants merchants;
	/**
	 * 所属商户名称
	 * @author lily
	 * @date 2013-12-24 下午06:39:50
	 */
	private String merId;
	/**
	 * 所属商户名称
	 * @author lily
	 * @date 2013-12-24 下午06:39:50
	 */
	private String merName;
	/** 入库总数 */
	private Integer InTotal;
	/** 出库总数  */
	private Integer outTotal;
	/** 现有库存 */
	private Integer existsTotal;
	/** 对应卡BIN */
	private CardBIN cardBin;
	/** 更新时间 */		
	private Date updateTime;
	
	private String cardBinName;
	/**单位性质*/
	private String dwxz;
	
	
	
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getCardBinName() {
		return cardBinName;
	}
	public void setCardBinName(String cardBinName) {
		this.cardBinName = cardBinName;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Merchants getMerchants() {
		return merchants;
	}
	
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	public Integer getInTotal() {
		return InTotal;
	}
	public void setInTotal(Integer inTotal) {
		InTotal = inTotal;
	}
	public Integer getOutTotal() {
		return outTotal;
	}
	public void setOutTotal(Integer outTotal) {
		this.outTotal = outTotal;
	}
	public Integer getExistsTotal() {
		return existsTotal;
	}
	public void setExistsTotal(Integer existsTotal) {
		this.existsTotal = existsTotal;
	}
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
	

}
