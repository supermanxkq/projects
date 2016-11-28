package com.paySystem.ic.bean.stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfig
 * @Description:商城业务参数配置实体
 * @date: 2014-10-13
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="B_BussParamsConfig")
public class BussParamsConfig implements Serializable{

	private static final long serialVersionUID = -544551050833707210L;
	
	/**参数配置编号*/
	private Integer paramsId;
	/**自动确认收货期限*/
    private Integer limtReceAuto;
    /**统一售后保障期限*/
    private Integer limtAfterSale;
    /**发送业务提醒  0：否 1：是*/
    private Integer sendBusinessRecom;
    /**商家发货提醒   0：否  1：是*/
    private Integer noDeliveMerRecom;
    /**消费赠送积分比率*/
    private BigDecimal rate;
    /**最大延迟发货天数*/
    private Integer maxDelayDays;
    /**操作人*/
    private String operMan;
    /**操作时间*/
    private Date operTime;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getParamsId() {
		return paramsId;
	}
	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}
	@Column(length=8)
	public Integer getLimtReceAuto() {
		return limtReceAuto;
	}
	public void setLimtReceAuto(Integer limtReceAuto) {
		this.limtReceAuto = limtReceAuto;
	}
	@Column(length=8)
	public Integer getLimtAfterSale() {
		return limtAfterSale;
	}
	public void setLimtAfterSale(Integer limtAfterSale) {
		this.limtAfterSale = limtAfterSale;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getSendBusinessRecom() {
		return sendBusinessRecom;
	}
	public void setSendBusinessRecom(Integer sendBusinessRecom) {
		this.sendBusinessRecom = sendBusinessRecom;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getNoDeliveMerRecom() {
		return noDeliveMerRecom;
	}
	public void setNoDeliveMerRecom(Integer noDeliveMerRecom) {
		this.noDeliveMerRecom = noDeliveMerRecom;
	}
	@Column(columnDefinition = "DECIMAL(6,4)")
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	@Column(length=8)
	public Integer getMaxDelayDays() {
		return maxDelayDays;
	}
	public void setMaxDelayDays(Integer maxDelayDays) {
		this.maxDelayDays = maxDelayDays;
	}
	@Column(length=20)
	public String getOperMan() {
		return operMan;
	}
	public void setOperMan(String operMan) {
		this.operMan = operMan;
	}
	@Column
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
    
    
}
