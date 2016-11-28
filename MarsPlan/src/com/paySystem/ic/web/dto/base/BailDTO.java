package com.paySystem.ic.web.dto.base;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;
/***
 * 
 * @ClassName:BailDTO
 * @Description:保证金值传输对象
 * @date: 2014-5-14下午06:50:42
 * @author: 井建国
 * @version: V1.0
 */
public class BailDTO extends BaseDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/***保证金编号**/
	private Integer  bailId;
	
	/**加油站编号***/
	private String merOrgId;
	
	/**加油站编号***/
	private String merOrgName;
	
	/**保证金***/
	private BigDecimal bailAmt;
	
	/**购油比率**/
	private BigDecimal buyOilRate;
	
	/**平台炼油厂编号**/
	private Integer orgOilId;
	
	/**平台炼油厂名称***/
	private String orgOilName;
	
	/**平台加油站法人代表**/
	private String conPerName;
	
	/**合作方式***/
	private Integer coopWay;
	
	/**合作方式***/
	private String coopWayName;
	
	/***上级经销商***/
	private String perMerId;
	
	/**上级经销商名称**/
	private String perMerName;
	
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
	
	/**合作状态**/
	private String coopStatusName;
	
	/**交保单位级别**/
	private Integer typeSign;
	
	/**交保单位级别名称**/
	private String typeSignName;
	
	/**交保描述**/
	private String descr;

	public Integer getBailId() {
		return bailId;
	}

	public void setBailId(Integer bailId) {
		this.bailId = bailId;
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

	public BigDecimal getBailAmt() {
		return bailAmt;
	}

	public void setBailAmt(BigDecimal bailAmt) {
		this.bailAmt = bailAmt;
	}

	public BigDecimal getBuyOilRate() {
		return buyOilRate;
	}

	public void setBuyOilRate(BigDecimal buyOilRate) {
		this.buyOilRate = buyOilRate;
	}

	public Integer getOrgOilId() {
		return orgOilId;
	}

	public void setOrgOilId(Integer orgOilId) {
		this.orgOilId = orgOilId;
	}

	public String getOrgOilName() {
		return orgOilName;
	}

	public void setOrgOilName(String orgOilName) {
		this.orgOilName = orgOilName;
	}

	public String getConPerName() {
		return conPerName;
	}

	public void setConPerName(String conPerName) {
		this.conPerName = conPerName;
	}

	public Integer getCoopWay() {
		return coopWay;
	}

	public void setCoopWay(Integer coopWay) {
		this.coopWay = coopWay;
	}

	public String getPerMerId() {
		return perMerId;
	}

	public void setPerMerId(String perMerId) {
		this.perMerId = perMerId;
	}

	public String getPerMerName() {
		return perMerName;
	}

	public void setPerMerName(String perMerName) {
		this.perMerName = perMerName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCoopTime() {
		return coopTime;
	}

	public void setCoopTime(Date coopTime) {
		this.coopTime = coopTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCoopStatus() {
		return coopStatus;
	}

	public void setCoopStatus(Integer coopStatus) {
		this.coopStatus = coopStatus;
	}

	public Integer getTypeSign() {
		return typeSign;
	}

	public void setTypeSign(Integer typeSign) {
		this.typeSign = typeSign;
	}
	

	public String getTypeSignName() {
		return typeSignName;
	}

	public void setTypeSignName(String typeSignName) {
		this.typeSignName = typeSignName;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCoopWayName() {
		return coopWayName;
	}

	public void setCoopWayName(String coopWayName) {
		this.coopWayName = coopWayName;
	}

	public String getCoopStatusName() {
		return coopStatusName;
	}

	public void setCoopStatusName(String coopStatusName) {
		this.coopStatusName = coopStatusName;
	}
	
}
