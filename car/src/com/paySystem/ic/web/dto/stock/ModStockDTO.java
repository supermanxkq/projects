package com.paySystem.ic.web.dto.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:ModStockDTO
 * @Description:库存变动信息DTO类
 * @date: 2013-12-11下午04:35:16
 * @author: 谢洪飞
 * @version: V1.0
 */
public class ModStockDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 9155757040671051308L;

	/** 流水号 */
	private String id;
	/**
	 * 
	 */
	private List<String> ids;
	
	/** 出库方类型 
	 *  0：机构
	 *  1：商户 
	 */
	private Integer outStype;
	/** 入库方类型 
	 *  0：机构
	 *  1：商户 
	 */
	private Integer inStype;
	/** 出库机构/商户号 */
	private String outId;
	/**
	 * 入库机构/商户编号
	 */
	private String inId;
	/** 创建时间 */
	private Date createTime;
	
	/**
	 * 创建时间（String类型）
	 */
	private String createTimeStr;
	
	/**
	 * 审核时间（String类型）
	 */
	private String auditTimeStr;
	
	/** 审核时间 */
	private Date auditTime;
	/** 卡BIN */
	private CardBIN cardBin;
	/** 起始卡号 */
	private String beginCardNo;
	/** 起始卡号s*/
	private List<String> beginCardNos;
	/** 数量 */
	private Integer cardCount;
	private List<Integer> cardCounts;
	/** 申请操作人 */
	private String proposer;
	/** 入库状态 
	 *  0：已入库 
	 *  1：未入库
	 */
	private Integer status;
	/**
	 * 操作类型
	 *  0总部入库
	 *  1发卡机构入库
	 *  2商户入库
	 *  3商户互相调拨
	 *  4发卡机构售卡
	 *  5商户售卡
	 *  6发卡机构售卡退回
	 *  7商户售卡退回
	 *  8会员退卡
	 *  9商户退卡
	 *  10发卡机构换卡
	 *  11商户换卡
	 */
	private Integer flag;
	/**
	 * 乐观锁版本控制 
	 */
	private Integer verson;
	/**
	 *卡号 
	 */
	private String cardNo;
	/**
	 * 查询类别
	 */
	private Integer queryType;
	/**
	 * 卡BIN号
	 */
	private String cardBinNo;
	/**
	 * 机构参数
	 */
	private String organId;

	
	//入库方向
	private String fx;
	
	

	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}

	

	/**
	 * 审核操作人
	 */
	private String checkMen;
	/**
	 * 商户名称
	 */
	private String merName;
	/**
	 * 机构名称
	 */
	private String name;
	/** 
	 * 商户Id
	 */
	private String merId;
	/**
	 * 卡BIN号s
	 */
	private List<String> cardBinNos;
	/**
	 * 卡bin名称
	 */
	private String binName;
	private List<String> binNames;
	
	/**
	 * 操作发生时间段-开始时间
	 */
	private String beginDate;
	/**
	 * 操作发生时间
	 */
	private String endDate;
	
	/**
	 * 截止卡号
	 */
	private String endNo;
	/**
	 * 截止卡号s
	 */
	private List<String> endNos;
	/**
	 * 
	 */
	private String endNoStr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public Integer getOutStype() {
		return outStype;
	}
	public void setOutStype(Integer outStype) {
		this.outStype = outStype;
	}
	public Integer getInStype() {
		return inStype;
	}
	public void setInStype(Integer inStype) {
		this.inStype = inStype;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getInId() {
		return inId;
	}
	
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getAuditTimeStr() {
		return auditTimeStr;
	}
	public void setAuditTimeStr(String auditTimeStr) {
		this.auditTimeStr = auditTimeStr;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	public String getBeginCardNo() {
		return beginCardNo;
	}
	public void setBeginCardNo(String beginCardNo) {
		this.beginCardNo = beginCardNo;
	}
	public List<String> getBeginCardNos() {
		return beginCardNos;
	}
	public void setBeginCardNos(List<String> beginCardNos) {
		this.beginCardNos = beginCardNos;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public List<Integer> getCardCounts() {
		return cardCounts;
	}
	public void setCardCounts(List<Integer> cardCounts) {
		this.cardCounts = cardCounts;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getVerson() {
		return verson;
	}
	public void setVerson(Integer verson) {
		this.verson = verson;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	public String getCardBinNo() {
		return cardBinNo;
	}
	public void setCardBinNo(String cardBinNo) {
		this.cardBinNo = cardBinNo;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getCheckMen() {
		return checkMen;
	}
	public void setCheckMen(String checkMen) {
		this.checkMen = checkMen;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public List<String> getCardBinNos() {
		return cardBinNos;
	}
	public void setCardBinNos(List<String> cardBinNos) {
		this.cardBinNos = cardBinNos;
	}
	public String getBinName() {
		return binName;
	}
	public void setBinName(String binName) {
		this.binName = binName;
	}
	public List<String> getBinNames() {
		return binNames;
	}
	public void setBinNames(List<String> binNames) {
		this.binNames = binNames;
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
	public String getEndNo() {
		return endNo;
	}
	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}
	public List<String> getEndNos() {
		return endNos;
	}
	public void setEndNos(List<String> endNos) {
		this.endNos = endNos;
	}
	public String getEndNoStr() {
		return endNoStr;
	}
	public void setEndNoStr(String endNoStr) {
		this.endNoStr = endNoStr;
	}
	
}
