package com.paySystem.ic.web.dto.buss;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:PayParamDTO
 * @Description:支付参数DTO类
 * @date: 2014-7-9下午05:36:24
 * @author: 谢洪飞
 * @version: V1.0
 */
public class PaySerParamDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -2093661145112103985L;
	

	/**服务器参数ID*/
	private Integer psId;
	
	/**名称*/
	private String psName;
	
	/**所属支付机构*/
	private Integer payOrgId;
	
	/**所属支付机构名称*/
	private String payOrgName;
	
	/**Url地址*/
	private String psUrl;
	
	/**账户号*/
	private String accountNo;
	
	/**口令*/
	private String psPwd;
	
	/**密钥*/
	private String secretKey;
	
	/**支持交易的币种*/
	private Integer currency;
	
	/**创建时间*/
	private Date createTime;
	
	/**是否启用*/
	private Integer isEnable;
	
	/**图像路径*/
	private String payImgPath;
	
	/**
	 * 手续费率
	 */
	private BigDecimal feeRate;
	
	
	/**支持货到付款标志*/
	private Integer codSign;
	
	/**在线支付标志*/
	private Integer payOnlineSign;
	
	/**描述*/
	private String descr;
	
	
	public Integer getPsId() {
		return psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getPsUrl() {
		return psUrl;
	}

	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPsPwd() {
		return psPwd;
	}

	public void setPsPwd(String psPwd) {
		this.psPwd = psPwd;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getPayImgPath() {
		return payImgPath;
	}

	public void setPayImgPath(String payImgPath) {
		this.payImgPath = payImgPath;
	}

	public Integer getPayOrgId() {
		return payOrgId;
	}

	public void setPayOrgId(Integer payOrgId) {
		this.payOrgId = payOrgId;
	}

	public String getPayOrgName() {
		return payOrgName;
	}

	public void setPayOrgName(String payOrgName) {
		this.payOrgName = payOrgName;
	}

	public Integer getCodSign() {
		return codSign;
	}

	public void setCodSign(Integer codSign) {
		this.codSign = codSign;
	}

	public Integer getPayOnlineSign() {
		return payOnlineSign;
	}

	public void setPayOnlineSign(Integer payOnlineSign) {
		this.payOnlineSign = payOnlineSign;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}
	
	
	
}
