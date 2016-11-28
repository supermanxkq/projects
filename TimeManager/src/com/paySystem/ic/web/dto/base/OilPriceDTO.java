package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:OilPriceDTO
 * @Description:油价信息DTO
 * @date: 2014-5-15下午02:44:33
 * @author: 张亚运
 * @version: V1.0
 */
public class OilPriceDTO extends BaseDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/**油价编号*/
	private Integer oilId;
	
	/**商户机构号*/
	private String merOrgId;
	
	/**商户机构名称*/
	private String merOrgName;
	
	/**商户机构标识*/
	private Integer merOrgSign;
	
	/**油品类型ID*/
	private Integer oilTypeId;
	
	/**油品名称*/
	private String oilTypeName;
	
	/**进油价*/
	private BigDecimal inPrice;
	
	/**售油价格*/
	private BigDecimal salePrice;
	
	/**发改委售价*/
	private BigDecimal govPrice;
	
	/**创建时间*/
	private String createTime;
	
	/**操作人ID*/
	private String operId;
	
	/**更新时间*/
	private String updateTime;
	
	/**起始时间*/
	private String beginTime;
	
	/**结束时间*/
	private String endTime;
	
	/**修改描述*/
	private String descr;

	public Integer getOilId() {
		return oilId;
	}

	public void setOilId(Integer oilId) {
		this.oilId = oilId;
	}

	public String getMerOrgId() {
		return merOrgId;
	}

	public void setMerOrgId(String merOrgId) {
		this.merOrgId = merOrgId;
	}

	public String getMerOrgName() {
		return merOrgName;
	}

	public void setMerOrgName(String merOrgName) {
		this.merOrgName = merOrgName;
	}

	public Integer getMerOrgSign() {
		return merOrgSign;
	}

	public void setMerOrgSign(Integer merOrgSign) {
		this.merOrgSign = merOrgSign;
	}

	public Integer getOilTypeId() {
		return oilTypeId;
	}

	public void setOilTypeId(Integer oilTypeId) {
		this.oilTypeId = oilTypeId;
	}

	public String getOilTypeName() {
		return oilTypeName;
	}

	public void setOilTypeName(String oilTypeName) {
		this.oilTypeName = oilTypeName;
	}

	public BigDecimal getInPrice() {
		return inPrice;
	}

	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getGovPrice() {
		return govPrice;
	}

	public void setGovPrice(BigDecimal govPrice) {
		this.govPrice = govPrice;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	

}
