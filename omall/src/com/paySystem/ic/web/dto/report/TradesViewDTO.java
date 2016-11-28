package com.paySystem.ic.web.dto.report;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:TradesViewDTO
 * @Description:交易报表DTO类
 * @date: 2014-1-3上午11:54:44
 * @author: 谢洪飞
 * @version: V1.0
 */
public class TradesViewDTO extends BaseDTO {
	/**
	 * 交易表信息
	 */
	
	/**流水ID号*/
	private String tradeId;
	/** 前流水ID号 */
	private String preTradeId;
	/**交易状态 ：
	 * P：处理中
	   C：完成
	   R：冲正
	   r: 冲正中
	 * */
	private String status;
	/** 交易所在商户 */
	private String merId;
	/** 交易发生终端 */
	private String termId ;
	
	/** 交易发生卡号 */
	private String cardNo;
   
	/**POS流水号*/
	private String traceNo;
	/** 批次号 */
	private String batchId;
	/** 交易时间 */
	private Date placedtime;
	/** 发生交易卡所属卡BIN */
	private String cardBin;
	/** 原价 */
	private BigDecimal retailAmt;
	/** 实际交易金额/积分*/
	private BigDecimal tradeAmt;
	/** 交易产生积分*/
	private BigDecimal points;
	/** 手续费*/
	private BigDecimal sxf;
	/** 结算金额(交易金额 — 手续费) */
	private BigDecimal proxyAmt;
	/** 
	 * 
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
	 * 
	 * */
	private Integer tradeType;
	/** 交易状态 
	 * 0 正常
	   1 已冲正
	   2 已退货
	   3 已撤销
	 * 
	 * */
	private Integer flag;
	/** 操作员号 */
	private String operId;
    /** 创建日期 */
	private Date createTime;
	private Date updateTime;
	/** 是否允许退货 0 不允许，1允许*/
	private Integer allowRetSign;
	/**所属会员*/
	private String memId;
	/** 交易账户类型
	 * 0现金
	   1积分
	 *  */
	private Integer accTypeId;
	/** 卡所属机构号*/
	private String orgId;
	/** 卡所属机构名称 */
	private String orgName;
	/** 会员号*/
	private String holdMemId;
	/** 终端名称 */
	private String termName;
	/** 商户名称*/
	private String merRealName;
	/** 商户所属发卡机构*/
	private String morganId;
	/** 机构名称 */
	private String morganName;
	
	/**查询条件 -- 开始时间*/
	private String beginDate;
	/**查询条件 -- 结束时间*/
	private String endDate;
	private String memRealName;
	  /**
     * 会员单笔消费金额预警
     * */
	private BigDecimal singleConsWorn;
	/**
	 * 会员单笔充值金额预警
	 * */
	private BigDecimal singleRechWorn;
	/**
	 * 账户类型名称
	 * */
	private String kindName;
	
	public String getMemRealName() {
		return memRealName;
	}
	public void setMemRealName(String memRealName) {
		this.memRealName = memRealName;
	}
	public TradesViewDTO(){}
	/**
	 *  有参构造
	 */
	public TradesViewDTO(String tradeId, String preTradeId, String status,
			String merId, String termId, String cardNo, String traceNo,
			String batchId, Date placedtime, String cardBin,
			BigDecimal retailAmt, BigDecimal tradeAmt, BigDecimal points,
			BigDecimal sxf, BigDecimal proxyAmt, Integer tradeType,
			Integer flag, String operId, Date createTime, Date updateTime,
			Integer allowRetSign, String memId, Integer accTypeId,
			String orgId, String orgName, String holdMemId, String termName,
			String merRealName, String morganId) {
		super();
		this.tradeId = tradeId;
		this.preTradeId = preTradeId;
		this.status = status;
		this.merId = merId;
		this.termId = termId;
		this.cardNo = cardNo;
		this.traceNo = traceNo;
		this.batchId = batchId;
		this.placedtime = placedtime;
		this.cardBin = cardBin;
		this.retailAmt = retailAmt;
		this.tradeAmt = tradeAmt;
		this.points = points;
		this.sxf = sxf;
		this.proxyAmt = proxyAmt;
		this.tradeType = tradeType;
		this.flag = flag;
		this.operId = operId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.allowRetSign = allowRetSign;
		this.memId = memId;
		this.accTypeId = accTypeId;
		this.orgId = orgId;
		this.orgName = orgName;
		this.holdMemId = holdMemId;
		this.termName = termName;
		this.merRealName = merRealName;
		this.morganId = morganId;
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
	public String getMorganName() {
		return morganName;
	}
	public void setMorganName(String morganName) {
		this.morganName = morganName;
	}
	

	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getPreTradeId() {
		return preTradeId;
	}
	public void setPreTradeId(String preTradeId) {
		this.preTradeId = preTradeId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getPlacedtime() {
		return placedtime;
	}
	public void setPlacedtime(Date placedtime) {
		this.placedtime = placedtime;
	}

	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public BigDecimal getRetailAmt() {
		return retailAmt;
	}
	public void setRetailAmt(BigDecimal retailAmt) {
		this.retailAmt = retailAmt;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getPoints() {
		return points;
	}
	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getSxf() {
		return sxf;
	}
	public void setSxf(BigDecimal sxf) {
		this.sxf = sxf;
	}

	public BigDecimal getProxyAmt() {
		return proxyAmt;
	}
	public void setProxyAmt(BigDecimal proxyAmt) {
		this.proxyAmt = proxyAmt;
	}

	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAllowRetSign() {
		return allowRetSign;
	}
	public void setAllowRetSign(Integer allowRetSign) {
		this.allowRetSign = allowRetSign;
	}

	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Integer getAccTypeId() {
		return accTypeId;
	}
	public void setAccTypeId(Integer accTypeId) {
		this.accTypeId = accTypeId;
	}

	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getMorganId() {
		return morganId;
	}
	public void setMorganId(String morganId) {
		this.morganId = morganId;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getHoldMemId() {
		return holdMemId;
	}
	public void setHoldMemId(String holdMemId) {
		this.holdMemId = holdMemId;
	}

	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getMerRealName() {
		return merRealName;
	}
	public void setMerRealName(String merRealName) {
		this.merRealName = merRealName;
	}
	public BigDecimal getSingleConsWorn() {
		return singleConsWorn;
	}
	public void setSingleConsWorn(BigDecimal singleConsWorn) {
		this.singleConsWorn = singleConsWorn;
	}
	public BigDecimal getSingleRechWorn() {
		return singleRechWorn;
	}
	public void setSingleRechWorn(BigDecimal singleRechWorn) {
		this.singleRechWorn = singleRechWorn;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	
}
