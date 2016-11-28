package com.paySystem.ic.web.dto.report;

import java.io.Serializable;
import java.math.BigDecimal;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:TermConsTotalDTO
 * @Description:TODO
 * @date: 2014-3-4下午04:40:26
 * @author: 张亚运
 * @version: V1.0
 */
public class TermConsTotalDTO  extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -1726100219044051907L;

	/**
	 * 流水ID号
	 * */
	private String tradeId;

	/**
	 * 总交易金额
	 */
	private BigDecimal consAmt;
	/**
	 * 总手续费
	 * */
	private BigDecimal consCommis;
	
	/**
	 * 交易笔数
	 */
	private BigDecimal tradesNum;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 终端ID
	 */
	private String termId;
	/**
	 * 终端名称
	 */
	private String termName;
	/**
	 * 交易起期
	 */
	private String beginDate;
	/**
	 * 交易止期
	 */
	private String endDate;
	/** 
	 * 交易类型 
	 * 1 退货
	   2 充值
	   3 充值冲正
	   4 充值撤销
	　  5 充值撤销冲正
	　  13 修改密码
	　  14 查询卡状态
	　  15 消费
	　  16 消费冲正
	　  17 消费撤销
	　  18 消费撤销冲正
	  　20 积分消费
	  　21 积分消费冲正
	  　22 积分消费撤销
	  　23 积分消费撤销冲正
	 * */
	private Integer tradeType;
	/**
	 * 商户id
	 */
	private String merId;
	
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
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
	public BigDecimal getConsAmt() {
		return consAmt;
	}
	public void setConsAmt(BigDecimal consAmt) {
		this.consAmt = consAmt;
	}
	public BigDecimal getConsCommis() {
		return consCommis;
	}
	public void setConsCommis(BigDecimal consCommis) {
		this.consCommis = consCommis;
	}
	public BigDecimal getTradesNum() {
		return tradesNum;
	}
	public void setTradesNum(BigDecimal tradesNum) {
		this.tradesNum = tradesNum;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
}
