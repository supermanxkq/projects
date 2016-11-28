package com.paySystem.ic.web.VO.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;

public class  SaleOrderVO  extends BaseDTO implements Serializable {

	/**
	 * @Fields serialVersionUID
	 * @author lily
	 * @date 2014-1-13 下午03:31:51
	 */
	private static final long serialVersionUID = 8140568232939787198L;
	// 主表
	private String saleOrderId; // 售卡订单编号 8位日期+流水号
	private Integer status; // 订单状态0：待审核； 1：待修改；2：等待确认；(针对支票到账) 3：订单成功；4：订单失败；
	private String statusStr;
	private String orderAmt; // 订单总金额(￥)单张卡片初始金额*卡片数量
	private String paidAmt; // 实际支付金额(￥)
	private String paidType; // 订单付款方式 0：现金；1：转账；2：支票
	private String paidTypeStr;
	private String initAmt; // 面值(售卡时卡片初始金额￥)
	private String activateSign; // 添加订单时卡片是否激活处理 0：未激活；1：激活
	private String reviewStatus; // 是否需要审核 0：需要审核；1：不需要审核；2：已修改；
	private Member member; // 购卡人
	private String memId; // 购卡人ID
	private String memName;
	private String operatorId; // 操作人ID
	private String operatorName;
	private String createTime; // 创建时间
	private Integer cardQty;// 购卡数量
	private Date revrecTime;// 审核时间
	private Date enanbleTime;//激活时间
	private String bankName;//
	private String bankAcc;//
	private String accHolder;//
	private String beginCardNo;//起始卡号
	// 附表
	private String saleLevelId; // 售卡时卡等级ID
	private BigDecimal newPoint; // 开卡送积分
	private String saleLevelName;
	private String bcSign; // 售卡时是否加入商圈
	private String realNameSign; // 售卡时是否实
	private Member holdMem;
	private String holdMemID; // 持卡人ID
	private String holdMemName;
	private String descr; // 订单描述
	private String enableDesc;//激活描述
	private String cardNoStr;
	private String payMen;
	private String ordreType;
	private List<String> cardNoList;// Map<卡号，初始金额>

	@Override
	public String toString() {
		return "SaleOrderVO [activateSign=" + activateSign + ", bcSign="
				+ bcSign + ", buyMem=" + member + ", buyMemID=" + memId
				+ ", buyMemName=" + memName + ", cardNoList=" + cardNoList
				+ ", cardNoStr=" + cardNoStr + ", createTime=" + createTime
				+ ", descr=" + descr + ", holdMem=" + holdMem + ", holdMemID="
				+ holdMemID + ", holdMemName=" + holdMemName + ", initAmt="
				+ initAmt + ", newPoint=" + newPoint + ", operatorId="
				+ operatorId + ", operatorName=" + operatorName + ", orderAmt="
				+ orderAmt + ", paidAmt=" + paidAmt + ", paidType=" + paidType
				+ ", realNameSign=" + realNameSign + ", reviewStatus="
				+ reviewStatus + ", saleLevelId=" + saleLevelId
				+ ", saleLevelName=" + saleLevelName + ", saleOrderId="
				+ saleOrderId + ", status=" + status + ", statusStr="
				+ statusStr + "]";
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getPaidType() {
		return paidType;
	}

	public void setPaidType(String paidType) {
		this.paidType = paidType;
	}

	public String getPaidTypeStr() {
		return paidTypeStr;
	}

	public void setPaidTypeStr(String paidTypeStr) {
		this.paidTypeStr = paidTypeStr;
	}

	public String getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(String initAmt) {
		this.initAmt = initAmt;
	}

	public String getActivateSign() {
		return activateSign;
	}

	public void setActivateSign(String activateSign) {
		this.activateSign = activateSign;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCardQty() {
		return cardQty;
	}

	public void setCardQty(Integer cardQty) {
		this.cardQty = cardQty;
	}

	public Date getRevrecTime() {
		return revrecTime;
	}

	public void setRevrecTime(Date revrecTime) {
		this.revrecTime = revrecTime;
	}

	public Date getEnanbleTime() {
		return enanbleTime;
	}

	public void setEnanbleTime(Date enanbleTime) {
		this.enanbleTime = enanbleTime;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	public String getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}

	public String getBeginCardNo() {
		return beginCardNo;
	}

	public void setBeginCardNo(String beginCardNo) {
		this.beginCardNo = beginCardNo;
	}

	public String getSaleLevelId() {
		return saleLevelId;
	}

	public void setSaleLevelId(String saleLevelId) {
		this.saleLevelId = saleLevelId;
	}

	public BigDecimal getNewPoint() {
		return newPoint;
	}

	public void setNewPoint(BigDecimal newPoint) {
		this.newPoint = newPoint;
	}

	public String getSaleLevelName() {
		return saleLevelName;
	}

	public void setSaleLevelName(String saleLevelName) {
		this.saleLevelName = saleLevelName;
	}

	public String getBcSign() {
		return bcSign;
	}

	public void setBcSign(String bcSign) {
		this.bcSign = bcSign;
	}

	public String getRealNameSign() {
		return realNameSign;
	}

	public void setRealNameSign(String realNameSign) {
		this.realNameSign = realNameSign;
	}

	public Member getHoldMem() {
		return holdMem;
	}

	public void setHoldMem(Member holdMem) {
		this.holdMem = holdMem;
	}

	public String getHoldMemID() {
		return holdMemID;
	}

	public void setHoldMemID(String holdMemID) {
		this.holdMemID = holdMemID;
	}

	public String getHoldMemName() {
		return holdMemName;
	}

	public void setHoldMemName(String holdMemName) {
		this.holdMemName = holdMemName;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getEnableDesc() {
		return enableDesc;
	}

	public void setEnableDesc(String enableDesc) {
		this.enableDesc = enableDesc;
	}

	public String getCardNoStr() {
		return cardNoStr;
	}

	public void setCardNoStr(String cardNoStr) {
		this.cardNoStr = cardNoStr;
	}

	public String getPayMen() {
		return payMen;
	}

	public void setPayMen(String payMen) {
		this.payMen = payMen;
	}

	public List<String> getCardNoList() {
		return cardNoList;
	}

	public void setCardNoList(List<String> cardNoList) {
		this.cardNoList = cardNoList;
	}

	public String getOrdreType() {
		return ordreType;
	}

	public void setOrdreType(String ordreType) {
		this.ordreType = ordreType;
	}
	
	
}
