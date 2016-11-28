package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omallproject
 * @ClassName:ITradesDTO
 * @Description:积分消费记录DTO
 * @date: 2014-11-11
 * @author: 毛智东
 * @version: V1.0
 */
public class ITradesDTO extends BaseDTO implements Serializable {

	/** 序列号 **/
	private static final long serialVersionUID = -5104182361491260253L;

	/**
	 * 交易流水号
	 */
	private String tradesId;

	/**
	 * 交易状态：1、正常；2、支付异常；
	 */
	private Integer status;

	/**
	 * 支付账户号(即：卡号)
	 */
	private String payAccNo;

	/**
	 * 支付账户名称(即：卡名称融联预付款)
	 */
	private String payAccName;

	/**
	 * RMB:人民币；USD:美元
	 */
	private String currType;

	/**
	 * 支付金额 (元)
	 */
	private BigDecimal payAmt;

	/**
	 * 电商会员编号
	 */
	private String omemId;

	/**
	 * 所支付的订单号 ( 电商订单号 )
	 */
	private String payOrderId;

	/**
	 * 商户账户号（支付到 预留先不填）
	 */
	private String merAccNo;

	/**
	 * 商户账户名称（支付到 预留先不填）
	 */
	private String merAccName;

	/**
	 * 支付类型：积分消费：IC
	 */
	private String payType;

	/**
	 * 交易时间
	 */
	private Date tradesTime;

	/**
	 * 1、付款；2、退款；3、转账；
	 */
	private Integer tradesType;

	/**
	 * mac校验码（Message Authentication Code），通过计算整条md5值并储存
	 */
	private String mac;

	/** 查询条件：交易流水号 **/
	private String paramTradesId;

	/** 查询条件：会员编号 **/
	private String paramMemId;

	/** 查询条件：开始时间 **/
	private String paramBeginTime;

	/** 查询条件：结束时间 **/
	private String paramEndTime;

	public String getParamTradesId() {
		return paramTradesId;
	}

	public void setParamTradesId(String paramTradesId) {
		this.paramTradesId = paramTradesId;
	}

	public String getParamMemId() {
		return paramMemId;
	}

	public void setParamMemId(String paramMemId) {
		this.paramMemId = paramMemId;
	}

	public String getParamBeginTime() {
		return paramBeginTime;
	}

	public void setParamBeginTime(String paramBeginTime) {
		this.paramBeginTime = paramBeginTime;
	}

	public String getParamEndTime() {
		return paramEndTime;
	}

	public void setParamEndTime(String paramEndTime) {
		this.paramEndTime = paramEndTime;
	}

	public String getTradesId() {
		return tradesId;
	}

	public void setTradesId(String tradesId) {
		this.tradesId = tradesId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPayAccNo() {
		return payAccNo;
	}

	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	public String getPayAccName() {
		return payAccName;
	}

	public void setPayAccName(String payAccName) {
		this.payAccName = payAccName;
	}

	public String getCurrType() {
		return currType;
	}

	public void setCurrType(String currType) {
		this.currType = currType;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	public String getOmemId() {
		return omemId;
	}

	public void setOmemId(String omemId) {
		this.omemId = omemId;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getMerAccNo() {
		return merAccNo;
	}

	public void setMerAccNo(String merAccNo) {
		this.merAccNo = merAccNo;
	}

	public String getMerAccName() {
		return merAccName;
	}

	public void setMerAccName(String merAccName) {
		this.merAccName = merAccName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getTradesTime() {
		return tradesTime;
	}

	public void setTradesTime(Date tradesTime) {
		this.tradesTime = tradesTime;
	}

	public Integer getTradesType() {
		return tradesType;
	}

	public void setTradesType(Integer tradesType) {
		this.tradesType = tradesType;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
