/**  
 * @ClassName: DeliveryModeDTO.java
 * @Description:
 * @Author:yanwuyang 
 * @Date: 2014-7-20 上午09:08:18
 * @Version: V1.0  
 */

package com.paySystem.ic.web.dto.buss;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

public class DeliveryModeDTO  extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 3615658143053408194L;
	
	/*** 方式ID */
	private Integer dmId;
	
	/*** 配送方式名称*/
	private String dmName;
	
	/*** 物流公司 */
	private Integer logisticsComId;
	
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

	/*** 物流名称 */
	private String logisticsComName;

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public Integer getLogisticsComId() {
		return logisticsComId;
	}

	public void setLogisticsComId(Integer logisticsComId) {
		this.logisticsComId = logisticsComId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getEnterCost() {
		return enterCost;
	}

	public void setEnterCost(BigDecimal enterCost) {
		this.enterCost = enterCost;
	}

	public Integer getCashOnDeliverySign() {
		return cashOnDeliverySign;
	}

	public void setCashOnDeliverySign(Integer cashOnDeliverySign) {
		this.cashOnDeliverySign = cashOnDeliverySign;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogisticsComName() {
		return logisticsComName;
	}

	public void setLogisticsComName(String logisticsComName) {
		this.logisticsComName = logisticsComName;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	
	
	
	

}

