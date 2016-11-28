
package com.paySystem.ic.bean.base;

import java.io.Serializable;
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
 * @Title: Logistics.java
 * @Package: com.paySystem.ic.bean.logistics
 * @Description: 物流公司实体类
 * @Author: 赵瑞群
 * @Date: 2014-7-21 下午3:14:09
 * @Version: V1.0  
 */
@Entity
@Table(name="B_logistInfo")
public class Logistics implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**物流公司编号*/
	private Integer logistId;
	
	/**物流公司名称*/
	private String logistName;
	
	/**物流公司网址*/
	private String url;
	
	/**使用状态*/
	private Integer status;
	
	/**创建时间*/
	private Date  createTime;
	
	/**操作员id*/
	private String OperatorId;
	
	/**修改时间*/
	private Date updateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLogistId() {
		return logistId;
	}

	public void setLogistId(Integer logistId) {
		this.logistId = logistId;
	}

	@Column(length=60)
	public String getLogistName() {
		return logistName;
	}

	public void setLogistName(String logistName) {
		this.logistName = logistName;
	}

	@Column(length=200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length=20)
	public String getOperatorId() {
		return OperatorId;
	}

	public void setOperatorId(String operatorId) {
		OperatorId = operatorId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}



