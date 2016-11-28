package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.web.dto.BaseDTO;

/***
 *商户消费分析报表
 * 
 * @author 解文侠
 * 
 */
public class MerConsumeAnalyzeDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 交易所在商户编号 */
	private String merId;
	/** 实际交易金额/积分 */
	private BigDecimal tradeAmt;
	/** 商户名称 */
	private String merRealName;
	/** 页面时间存放 */
	private String stringDate;
	/** 统计分析方式 */
	private Integer sign;
	/** 交易起期 */
	private String beginDate;
	/** 交易止期 */
	private String endDate;
	/** 交易笔数 */
	private BigDecimal tradesNum;
	/** 所按条件: 0 :按季度 1：按月份 2：按天数 */
	private Integer status;
	/** 交易所在商户 */
	private Merchants merchants;
	/** 季度/月份/天数 **/
	private String yearly;

	public String getYearly() {
		return yearly;
	}

	public void setYearly(String yearly) {
		this.yearly = yearly;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getMerRealName() {
		return merRealName;
	}

	public void setMerRealName(String merRealName) {
		this.merRealName = merRealName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTradesNum() {
		return tradesNum;
	}

	public void setTradesNum(BigDecimal tradesNum) {
		this.tradesNum = tradesNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Merchants getMerchants() {
		return merchants;
	}

	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	

}
