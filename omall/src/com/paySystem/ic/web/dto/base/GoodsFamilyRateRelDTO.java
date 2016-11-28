/**  
 * @Title: GoodsFamilyRateRel.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-9 下午03:40:34
 * @Version: V1.0  
 */
package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyRateRel
 * @Description:商品分类 & 手续费率关联表
 * @date: 2014-12-9
 * @author: 孟凡岭
 * @version: V1.0
 */
public class GoodsFamilyRateRelDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 关联表id **/
	private Integer relId;
	/**
	 * 支付方式编号 暂定为枚举文件中添加 1 - 融芯宝支付 2 - 网银支付 3 - 快捷支付 4 - 支付宝支付 5 - 其它第三方支付
	 **/
	private Integer payMentType;
	/** 融芯宝 **/
	private Integer rswinPay;
	/** 融芯宝费率 **/
	private BigDecimal rswinPayValue;
	/** 网银支付 **/
	private Integer wyPay;
	/** 网银支付费率 **/
	private BigDecimal wyPayValue;
	/** 快捷支付 **/
	private Integer fastPay;
	/** 快捷支付费率 **/
	private BigDecimal fastPayValue;
	/** 支付宝支付 **/
	private Integer aliPay;
	/** 支付宝支付费率 **/
	private BigDecimal aliPayValue;
	/** 第三方支付 **/
	private Integer otherPay;
	/** 第三方支付费率 **/
	private BigDecimal otherPayValue;
	/** 商品分类编号 **/
	private Integer familyId;
	/** 手续费率 **/
	private BigDecimal chargeRate;
	/** 操作人Id **/
	private String operatorId;
	/** 操作日期 **/
	private Date updateTime;

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public Integer getPayMentType() {
		return payMentType;
	}

	public void setPayMentType(Integer payMentType) {
		this.payMentType = payMentType;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRswinPay() {
		return rswinPay;
	}

	public void setRswinPay(Integer rswinPay) {
		this.rswinPay = rswinPay;
	}

	public BigDecimal getRswinPayValue() {
		return rswinPayValue;
	}

	public void setRswinPayValue(BigDecimal rswinPayValue) {
		this.rswinPayValue = rswinPayValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getWyPay() {
		return wyPay;
	}

	public void setWyPay(Integer wyPay) {
		this.wyPay = wyPay;
	}

	public BigDecimal getWyPayValue() {
		return wyPayValue;
	}

	public void setWyPayValue(BigDecimal wyPayValue) {
		this.wyPayValue = wyPayValue;
	}

	public Integer getFastPay() {
		return fastPay;
	}

	public void setFastPay(Integer fastPay) {
		this.fastPay = fastPay;
	}

	public BigDecimal getFastPayValue() {
		return fastPayValue;
	}

	public void setFastPayValue(BigDecimal fastPayValue) {
		this.fastPayValue = fastPayValue;
	}

	public Integer getAliPay() {
		return aliPay;
	}

	public void setAliPay(Integer aliPay) {
		this.aliPay = aliPay;
	}

	public BigDecimal getAliPayValue() {
		return aliPayValue;
	}

	public void setAliPayValue(BigDecimal aliPayValue) {
		this.aliPayValue = aliPayValue;
	}

	public Integer getOtherPay() {
		return otherPay;
	}

	public void setOtherPay(Integer otherPay) {
		this.otherPay = otherPay;
	}

	public BigDecimal getOtherPayValue() {
		return otherPayValue;
	}

	public void setOtherPayValue(BigDecimal otherPayValue) {
		this.otherPayValue = otherPayValue;
	}

}
