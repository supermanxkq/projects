package com.paySystem.ic.web.dto.report;


import java.math.BigDecimal;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.web.dto.BaseDTO;

public class MerConsTotalDTO extends BaseDTO {

	
	/** 记录所属商户 */	
	private Merchants merchants;
	/** 结算标志
	 *         0:未结算
	           1:已结算 */
	private Integer flag;
	/**起始时间  */
	private String beginTime;
	/**结束时间*/
	private String endTime;
	/** 消费总交易金额  */
	private BigDecimal consAmt;
	/**消费总手续费*/
	private BigDecimal consCommis;
	/**消费交易笔数*/
	private Integer consCount;
	
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
	
	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	//-------------------
	private String merId;
	
	private String merName;


	public Merchants getMerchants() {
		return merchants;
	}

	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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



	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}


	public void setConsCount(Integer consCount) {
		this.consCount = consCount;
	}

	public Integer getConsCount() {
		return consCount;
	}
	
	
	

}
