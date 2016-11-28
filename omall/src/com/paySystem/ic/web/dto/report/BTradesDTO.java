package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName: omproject
 * @ClassName: BTradesDTO
 * @Description: 业务交易表DTO
 * @date: 2014-11-7 上午10:26:59
 * @author: 王少辉
 * @version: V1.0
 */
public class BTradesDTO extends BaseDTO implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6019224074545917354L;

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
	 * RCP:预付卡；UPC:银行卡；ALC:支付宝；
	 */
	private String payType;

	/**
	 * 支付渠道号。 NUP:数字王府井渠道；UP : 银联渠道；RC ：自营预付卡渠道； ALP : 支付宝渠道；ICBC :
	 * 工商银行渠道；ABC:农业银行渠道；
	 */
	private String channelNo;

	/**
	 * 支付平台交易返回码
	 */
	private String payRetNo;

	/**
	 * 交易时间
	 */
	private Date tradesTime;

	/**
	 * 交易开始时间，界面查询用
	 */
	private String beginDate;

	/**
	 * 交易结束时间，界面查询用
	 */
	private String endDate;

	/**
	 * 1、付款；2、退款；3、转账；
	 */
	private Integer tradesType;

	/**
	 * mac校验码（Message Authentication Code），通过计算整条md5值并储存
	 */
	private String mac;

	/**
	 * @return String 交易流水号
	 */
	public String getTradesId() {
		return tradesId;
	}

	/**
	 * @param tradesId
	 *            交易流水号
	 */
	public void setTradesId(String tradesId) {
		this.tradesId = tradesId;
	}

	/**
	 * @return Integer 交易状态：1、正常；2、支付异常；
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            交易状态：1、正常；2、支付异常；
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return String 支付账户号(即：卡号)
	 */
	public String getPayAccNo() {
		return payAccNo;
	}

	/**
	 * @param payAccNo
	 *            支付账户号(即：卡号)
	 */
	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	/**
	 * @return String 支付账户名称(即：卡名称融联预付款)
	 */
	public String getPayAccName() {
		return payAccName;
	}

	/**
	 * @param payAccName
	 *            支付账户名称(即：卡名称融联预付款)
	 */
	public void setPayAccName(String payAccName) {
		this.payAccName = payAccName;
	}

	/**
	 * @return String RMB:人民币；USD:美元
	 */
	public String getCurrType() {
		return currType;
	}

	/**
	 * @param currType
	 *            RMB:人民币；USD:美元
	 */
	public void setCurrType(String currType) {
		this.currType = currType;
	}

	/**
	 * @return BigDecimal 支付金额 (元)
	 */
	public BigDecimal getPayAmt() {
		return payAmt;
	}

	/**
	 * @param payAmt
	 *            支付金额 (元)
	 */
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	/**
	 * @return String 电商会员编号
	 */
	public String getOmemId() {
		return omemId;
	}

	/**
	 * @param omemId
	 *            电商会员编号
	 */
	public void setOmemId(String omemId) {
		this.omemId = omemId;
	}

	/**
	 * @return String 所支付的订单号 ( 电商订单号 )
	 */
	public String getPayOrderId() {
		return payOrderId;
	}

	/**
	 * @param payOrderId
	 *            所支付的订单号 ( 电商订单号 )
	 */
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	/**
	 * @return String 商户账户号（支付到 预留先不填）
	 */
	public String getMerAccNo() {
		return merAccNo;
	}

	/**
	 * @param merAccNo
	 *            商户账户号（支付到 预留先不填）
	 */
	public void setMerAccNo(String merAccNo) {
		this.merAccNo = merAccNo;
	}

	/**
	 * @return String 商户账户名称（支付到 预留先不填）
	 */
	public String getMerAccName() {
		return merAccName;
	}

	/**
	 * @param merAccName
	 *            商户账户名称（支付到 预留先不填）
	 */
	public void setMerAccName(String merAccName) {
		this.merAccName = merAccName;
	}

	/**
	 * @return String RCP:预付卡；UPC:银行卡；ALC:支付宝；
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            RCP:预付卡；UPC:银行卡；ALC:支付宝；
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return String 支付渠道号。 NUP:数字王府井渠道；UP : 银联渠道；RC ：自营预付卡渠道； ALP : 支付宝渠道；ICBC
	 *         : 工商银行渠道；ABC:农业银行渠道；
	 */
	public String getChannelNo() {
		return channelNo;
	}

	/**
	 * @param channelNo
	 *            支付渠道号。 NUP:数字王府井渠道；UP : 银联渠道；RC ：自营预付卡渠道； ALP : 支付宝渠道；ICBC :
	 *            工商银行渠道；ABC:农业银行渠道；
	 */
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	/**
	 * @return String 支付平台交易返回码
	 */
	public String getPayRetNo() {
		return payRetNo;
	}

	/**
	 * @param payRetNo
	 *            支付平台交易返回码
	 */
	public void setPayRetNo(String payRetNo) {
		this.payRetNo = payRetNo;
	}

	/**
	 * @return Date 交易时间
	 */
	public Date getTradesTime() {
		return tradesTime;
	}

	/**
	 * @param tradesTime
	 *            交易时间
	 */
	public void setTradesTime(Date tradesTime) {
		this.tradesTime = tradesTime;
	}

	/**
	 * @return String 交易开始时间，界面查询用
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            交易开始时间，界面查询用
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return String 交易结束时间，界面查询用
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            交易结束时间，界面查询用
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return Integer 1、付款；2、退款；3、转账；
	 */
	public Integer getTradesType() {
		return tradesType;
	}

	/**
	 * @param tradesType
	 *            1、付款；2、退款；3、转账；
	 */
	public void setTradesType(Integer tradesType) {
		this.tradesType = tradesType;
	}

	/**
	 * @return String mac校验码（Message Authentication Code），通过计算整条md5值并储存
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac
	 *            mac校验码（Message Authentication Code），通过计算整条md5值并储存
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

}