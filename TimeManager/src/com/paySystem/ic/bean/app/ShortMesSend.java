package com.paySystem.ic.bean.app;

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
 * @ClassName:AppUser
 * @Description:App端需要 短信信息
 * @date: 2014-5-26下午03:53:23
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="b_sms")
public class ShortMesSend implements Serializable {
	

	private static final long serialVersionUID = 1L;

	/** 短信Id号  */
	private Integer smsId;
	
	/** 短信名称 */
	private String smsTitle;
	
	/** 短信内容 */
	private String smsContent;
	
	private BigDecimal totalPrice;
	
	private Integer num;
	
	private String userType;

	/** 加油站会员编号 */
	private String oilId;
	
	/** 创建时间 */
	private Date createTime;
	
	private Date updateTime;
	
	private Integer smsType;
	
	private Integer smsStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getSmsId() {
		return smsId;
	}

	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}	

	@Column(length=30)
	public String getSmsTitle() {
		return smsTitle;
	}

	public void setSmsTitle(String smsTitle) {
		this.smsTitle = smsTitle;
	}

	@Column(length=200)
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	@Column(length=15)
	public String getOilId() {
		return oilId;
	}

	public void setOilId(String oilId) {
		this.oilId = oilId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(columnDefinition = "char(1)")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getSmsType() {
		return smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}
	@Column(columnDefinition="DECIMAL(10,2)")
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Column(columnDefinition="char(6)")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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
	public Integer getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(Integer smsStatus) {
		this.smsStatus = smsStatus;
	}
	
	
	
}
