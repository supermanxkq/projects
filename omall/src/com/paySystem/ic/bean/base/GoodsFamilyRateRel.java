/**  
 * @Title: GoodsFamilyRateRel.java
 * @Package: com.paySystem.ic.bean.base
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-9 下午03:40:34
 * @Version: V1.0  
 */
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

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyRateRel
 * @Description:商品分类 & 手续费率关联表
 * @date: 2014-12-9
 * @author: 孟凡岭
 * @version: V1.0
 */
@Entity
@Table(name = "B_GoodsFamilyRateRel")
public class GoodsFamilyRateRel implements Serializable {
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
	/** 商品分类编号 **/
	private Integer familyId;
	/** 手续费率 **/
	private BigDecimal chargeRate;
	/** 操作人Id **/
	private String operatorId;
	/** 操作日期 **/
	private Date updateTime;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}
	@Column(columnDefinition="char(1)")
	public Integer getPayMentType() {
		return payMentType;
	}

	public void setPayMentType(Integer payMentType) {
		this.payMentType = payMentType;
	}
	@Column
	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	@Column(columnDefinition="decimal(6,4) default 0")
	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}
	@Column(length=15)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	@Column(columnDefinition="timestamp")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
