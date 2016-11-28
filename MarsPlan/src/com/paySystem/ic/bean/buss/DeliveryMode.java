package com.paySystem.ic.bean.buss;

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

/**  
 * @ClassName: DeliveryMode.java
 * @Description:配送方式
 * @Author:yanwuyang 
 * @Date: 2014-7-19 下午10:57:53
 * @Version: V1.0  
 */

@Entity(name="b_delivery_mode")
@Table
public class DeliveryMode implements Serializable {

	private static final long serialVersionUID = -146124860806177137L;
	
	/*** 方式ID */
	private Integer dmId;
	
	/*** 配送方式名称*/
	private String dmName;
	
	/*** 物流公司 */
	private Integer logisticsComId;
	
	/*** 物流名称 */
	private String logisticsComName;
	
	/*** 配送方式描述 */
	private String descr;
	
	/*** 状态 */
	private Integer status;
	
	/*** 保价费用 */
	private BigDecimal enterCost;
	
	/*** 货到付款*/
	private Integer cashOnDeliverySign;
	
	/*** 配送方式用户数 */
	private Integer useCount;
	
	/*** 创建时间 */
	private Date createTime;
	
	/*** 更新时间*/
	private Date updateTime;
	
	/*** 操作人 */
	private String operatorId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	@Column(length=30)
	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	@Column
	public Integer getLogisticsComId() {
		return logisticsComId;
	}

	public void setLogisticsComId(Integer logisticsComId) {
		this.logisticsComId = logisticsComId;
	}
	
	@Column(length=255)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition="DECIMAL(6,4)")
	public BigDecimal getEnterCost() {
		return enterCost;
	}

	public void setEnterCost(BigDecimal enterCost) {
		this.enterCost = enterCost;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCashOnDeliverySign() {
		return cashOnDeliverySign;
	}

	public void setCashOnDeliverySign(Integer cashOnDeliverySign) {
		this.cashOnDeliverySign = cashOnDeliverySign;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length = 20)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(updatable=false,columnDefinition="int default 0")
	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	@Column(length = 50)
	public String getLogisticsComName() {
		return logisticsComName;
	}

	public void setLogisticsComName(String logisticsComName) {
		this.logisticsComName = logisticsComName;
	}


}

