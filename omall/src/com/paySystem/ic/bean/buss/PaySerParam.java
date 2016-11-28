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
 * @ClassName:PaySerParam
 * @Description:支付接口服务器参数实体信息
 * @date: 2014-7-9下午03:21:34
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity(name="s_payparam")
@Table
public class PaySerParam implements Serializable{

	private static final long serialVersionUID = -942745845143033717L;

	/**服务器参数ID*/
	private Integer psId;
	
	/**名称*/
	private String psName;
	
	/**所属支付机构*/
	private Integer payOrgId;
	
	/**Url地址*/
	private String psUrl;
	
	/**账户号*/
	private String accountNo;
	
	/**口令*/
	private String psPwd;
	
	/**密钥*/
	private String secretKey;
	
	/**
	 * 手续费率
	 */
	private BigDecimal feeRate;
	
	/**支持交易的币种*/
	private Integer currency;
	
	/**支持货到付款标志*/
	private Integer codSign;
	
	/**在线支付标志*/
	private Integer payOnlineSign;
	
	/**创建时间*/
	private Date createTime;
	
	/**是否启用*/
	private Integer isEnable;
	
	/**图像路径*/
	private String payImgPath;
	
	/**描述*/
	private String descr;

	
	
	
	public Integer getPayOrgId() {
		return payOrgId;
	}

	public void setPayOrgId(Integer payOrgId) {
		this.payOrgId = payOrgId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPsId() {
		return psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	@Column(length=60)
	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	@Column(length=150)
	public String getPsUrl() {
		return psUrl;
	}

	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}

	@Column(length=60)
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(length=30)
	public String getPsPwd() {
		return psPwd;
	}

	public void setPsPwd(String psPwd) {
		this.psPwd = psPwd;
	}

	@Column(length=32)
	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(columnDefinition="char(1)")
	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Column(length=255)
	public String getPayImgPath() {
		return payImgPath;
	}

	public void setPayImgPath(String payImgPath) {
		this.payImgPath = payImgPath;
	}

	@Column(columnDefinition="char(1)")
	public Integer getCodSign() {
		return codSign;
	}

	public void setCodSign(Integer codSign) {
		this.codSign = codSign;
	}

	@Column(columnDefinition="char(1)")
	public Integer getPayOnlineSign() {
		return payOnlineSign;
	}

	public void setPayOnlineSign(Integer payOnlineSign) {
		this.payOnlineSign = payOnlineSign;
	}

	@Column(length=255)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(columnDefinition="DECIMAL(6,2)")
	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	
	
	
}
