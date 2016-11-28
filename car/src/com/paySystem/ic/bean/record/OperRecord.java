package com.paySystem.ic.bean.record;

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

import org.apache.commons.lang3.StringUtils;

/**
 * 操作日志表
 * @author DELL
 *
 */
@Entity
@Table(name = "L_Operators")
public class OperRecord implements Serializable {
	private static final long serialVersionUID = 6693965150652975115L;
	/** 流水号 */ 
	private Integer id;
	/** 时间 */
	private Date operTime;
	/** 操作员ID */
	private String operId;
	/** 使用的IP */
	private String loginIP;
	/** 模块名 */
	private String moduleName;
	/** 备注 */
	private String memo;
	/** 机构ID */
	private String organId;
	/** 商户ID */
	private String merId;
	/** 级别：0总部1发卡机构2商户 */
	private Integer flag;
	/*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="L_Operators_seq")
    @SequenceGenerator(name="L_Operators_seq",sequenceName="L_Operators_seq",allocationSize=1)*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Column(length = 20)
	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}
	@Column(length = 20)
	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	
	@Column(length = 40)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(length = 50)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(length = 8)
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		if(StringUtils.isNotBlank(organId)){
			this.flag = 1;
		}
		this.organId = organId;
	}
	@Column(length = 15)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		if(StringUtils.isNotBlank(merId)){
			this.flag = 2;
		}
		this.merId = merId;
	}
	@Column
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
