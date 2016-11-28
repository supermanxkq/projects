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
/***
	 * 
	 * @ClassName:Bail
	 * @Description:保证金表
	 * @date: 2014-5-14下午02:21:33
	 * @author: 井建国
	 * @version: V1.0
	 */
	@Entity
	@Table(name = "B_BAIL")
	public class Bail implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1781073735536614648L;

			/***保证金编号**/
			private Integer  bailId;
			
			/**加油站编号***/
			private String merOrgId;
			
			/**保证金***/
			private BigDecimal bailAmt;
			
			/**购油比率**/
			private BigDecimal buyOilRate;
			
			/**平台炼油厂编号**/
			private Integer orgOilId;
			
			/**合同编号**/
			private String contractNo;
			
			/**交保时间**/
			private Date payTime;
			
			/**合作时间**/
			private Date coopTime;
			
			/**更新时间**/
			private Date updateTime;
			
			/**合作状态**/
			private Integer coopStatus;
			
			/**交保单位级别**/
			private Integer typeSign;
			
			/**交保描述**/
			private String descr;
			
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY )
			@Column
			public Integer getBailId() {
				return bailId;
			}
			public void setBailId(Integer bailId) {
				this.bailId = bailId;
			}
			
			@Column(length = 15)
			public String getMerOrgId() {
				return merOrgId;
			}
			public void setMerOrgId(String merOrgId) {
				this.merOrgId = merOrgId;
			}
			
			@Column(columnDefinition = "DECIMAL(15,2)")
			public BigDecimal getBailAmt() {
				return bailAmt;
			}
			public void setBailAmt(BigDecimal bailAmt) {
				this.bailAmt = bailAmt;
			}
			
			@Column(columnDefinition = "DECIMAL(6,4)")
			public BigDecimal getBuyOilRate() {
				return buyOilRate;
			}
			public void setBuyOilRate(BigDecimal buyOilRate) {
				this.buyOilRate = buyOilRate;
			}
			
			@Column(length = 8 )
			public Integer getOrgOilId() {
				return orgOilId;
			}
			public void setOrgOilId(Integer orgOilId) {
				this.orgOilId = orgOilId;
			}
			
			@Column(length = 30)
			public String getContractNo() {
				return contractNo;
			}
			public void setContractNo(String contractNo) {
				this.contractNo = contractNo;
			}
		
			@Temporal(TemporalType.TIMESTAMP)
			@Column
			public Date getPayTime() {
				return payTime;
			}
			public void setPayTime(Date payTime) {
				this.payTime = payTime;
			}
			
			@Temporal(TemporalType.TIMESTAMP)
			@Column
			public Date getCoopTime() {
				return coopTime;
			}
			public void setCoopTime(Date coopTime) {
				this.coopTime = coopTime;
			}
			
			@Temporal(TemporalType.TIMESTAMP)
			@Column
			public Date getUpdateTime() {
				return updateTime;
			}
			public void setUpdateTime(Date updateTime) {
				this.updateTime = updateTime;
			}
			
			@Column(columnDefinition = "char(1)")
			public Integer getCoopStatus() {
				return coopStatus;
			}
			public void setCoopStatus(Integer coopStatus) {
				this.coopStatus = coopStatus;
			}
			
			@Column(columnDefinition = "char(1)")
			public Integer getTypeSign() {
				return typeSign;
			}
			public void setTypeSign(Integer typeSign) {
				this.typeSign = typeSign;
			}
			
			@Column(length = 255)
			public String getDescr() {
				return descr;
			}
			public void setDescr(String descr) {
				this.descr = descr;
			}
			
			
		}
