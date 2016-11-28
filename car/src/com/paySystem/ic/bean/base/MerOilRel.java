package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:MerOilRel
 * @Description:商户油品关联表
 * @date: 2014-5-19下午03:24:51
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_meroilrel")
public class MerOilRel implements Serializable {
	
	
	private static final long serialVersionUID = 1175121330181549796L;


	/**	关系编号 */
	private Integer moId;
	
	/** 商户机构编号 */
	private String merOrgId;
	
	/** 油品类型编号 */
	private Integer oilTypeId;
	
	/** 商户机构标志
	 *  0:机构
	 *  1:商户
	 */
	private Integer merOrgSign;
	
	/** 售油价 */
	private BigDecimal saleOil;
	
	/** 日产量 */
	private BigDecimal oilDaily;
	
	/** 最大储量 */
	private BigDecimal oilStorage;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getMoId() {
		return moId;
	}

	
	public void setMoId(Integer moId) {
		this.moId = moId;
	}
	
	@Column(length=15)
	public String getMerOrgId() {
		return merOrgId;
	}

	public void setMerOrgId(String merOrgId) {
		this.merOrgId = merOrgId;
	}
	@Column
	public Integer getOilTypeId() {
		return oilTypeId;
	}

	public void setOilTypeId(Integer oilTypeId) {
		this.oilTypeId = oilTypeId;
	}
	@Column
	public Integer getMerOrgSign() {
		return merOrgSign;
	}

	public void setMerOrgSign(Integer merOrgSign) {
		this.merOrgSign = merOrgSign;
	}

	@Column(columnDefinition="DECIMAL(10,3)")
	public BigDecimal getSaleOil() {
		return saleOil;
	}

	public void setSaleOil(BigDecimal saleOil) {
		this.saleOil = saleOil;
	}

	@Column(columnDefinition="DECIMAL(10,3)")
	public BigDecimal getOilDaily() {
		return oilDaily;
	}

	public void setOilDaily(BigDecimal oilDaily) {
		this.oilDaily = oilDaily;
	}
	
	@Column(columnDefinition="DECIMAL(10,3)")
	public BigDecimal getOilStorage() {
		return oilStorage;
	}

	public void setOilStorage(BigDecimal oilStorage) {
		this.oilStorage = oilStorage;
	}
	
	
	

}
