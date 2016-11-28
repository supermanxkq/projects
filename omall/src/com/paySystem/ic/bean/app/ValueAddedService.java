package com.paySystem.ic.bean.app;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:App端需要 加油站增值服务信息
 * @author: 张国法
 * @version: V1.0
 */
@Entity
@Table(name="b_service")
public class ValueAddedService implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/** app用户Id号  */
	private Integer serviceId;
	
	/** app用户名称 */
	private String serviceName;
	
	/** 用户密码 */
	private String status;
	/** 身份验证 */
	private String type;

	/** 商户编号 */
	private String merId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	@Column(length=15)
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Column(length=2)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(length=2)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(length=15)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}	
	
}
