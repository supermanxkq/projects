package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:PayParamDTO
 * @Description:物流公司信息DTO类
 * @date: 2014-7-21下午05:36:24
 * @author: 赵瑞群
 * @version: V1.0
 */
public class LogisticsDTO extends BaseDTO implements Serializable{

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

	public Integer getLogistId() {
		return logistId;
	}

	public void setLogistId(Integer logistId) {
		this.logistId = logistId;
	}

	public String getLogistName() {
		return logistName;
	}

	public void setLogistName(String logistName) {
		this.logistName = logistName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperatorId() {
		return OperatorId;
	}

	public void setOperatorId(String operatorId) {
		OperatorId = operatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
