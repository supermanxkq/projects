package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.paySystem.ic.web.dto.BaseDTO;

public class SaleOrderDTO extends BaseDTO implements Serializable {

	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = -5980705390656531632L;

	private String saleOrderId; //售卡订单编号 8位日期+流水号
	//private String deptType;//归属单位类型0：发卡机构；1：商户
	//private String OrgMerId;//归属单位代码 	发卡机构：8位；商户：15位
	private String orgId; // 发卡机构代码
	private String orgName; 
	private String merId; //商户代码
	private String merName;
	private String preOrganId;
	private String binId; //卡BIN
	private String binName; 
	private String beginCardNo; //起始卡号
	private Integer cardQty; //数量
	private Integer status;  //订单状态0：待审核；	1：待修改；2：等待确认；(针对支票到账)	3：订单成功；4：订单失败；
	private BigDecimal orderAmt; //订单总金额(￥)单张卡片初始金额*卡片数量
	private BigDecimal paidAmt; //实际支付金额(￥)
	private Integer paidType; //订单付款方式 0：现金；1：转账；2：支票
	private BigDecimal initAmt; //面值(售卡时卡片初始金额￥)
	private Integer activateSign;  //添加订单时卡片是否激活处理 0：未激活；1：激活
	private String reviewStatus; //是否需要审核	0：需要审核；1：不需要审核；2：已修改；
	private String memId; //购卡人
	private String memName; //购卡人
	private String operatorId;  //操作人ID
	private String operatorName;
	private String createTime; //创建时间
	private Date enanbleTime;//激活时间
	private String beginDate; //开始时间
	private String endDate; //结束时间
	private Date from;
	private Date to;
	private List<String> cardNo;//批量售卡卡号集合
	private String levelId;//卡等级Id
	private Integer newPoint;//卡积分
	private String telNo;
	private String payMenName;
	private String payMenId;
	private String descr;//备注
	private String enableDesc;//激活描述
	/**实名制标志**/
	private Integer realNameSign;
	/**订单类型  1 售卡  2充值**/
	private Integer toaccountType;
	/**订单类名称  1 售卡  2充值**/
	private String toaccountName;
	
	/** 企业编号 */
	private Integer companyId;
	/** 企业名称 */
	private String companyName;
	/** 企业电话 */
	private String comTele;
	/** 企业Email地址 */
	private String comEmail;
	/** 企业联系人 */
	private String comConPer;
	/** 企业人数 */
	private Integer compNum;
	/** 企业联系人电话 */
	private String comConPerTele;
	/**
	 * 卡标志*/
	private Integer cardSingn;
	@Override
	public String toString() {
		return "SaleOrderDTO [activateSign=" + activateSign + ", beginCardNo="
				+ beginCardNo + ", binId=" + binId + ", binName=" + binName
				+ ", buyMemID=" + payMenId + ", buyMemName=" + payMenName
				+ ", cardQty=" + cardQty + ", createTime=" + createTime
				+ ", initAmt=" + initAmt + ", merId=" + merId + ", merName="
				+ merName + ", operatorId=" + operatorId + ", operatorName="
				+ operatorName + ", orderAmt=" + orderAmt + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", paidAmt=" + paidAmt
				+ ", paidType=" + paidType + ", reviewStatus=" + reviewStatus
				+ ", saleOrderId=" + saleOrderId + ", status=" + status + "]";
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getPreOrganId() {
		return preOrganId;
	}

	public void setPreOrganId(String preOrganId) {
		this.preOrganId = preOrganId;
	}

	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
	}

	public String getBinName() {
		return binName;
	}

	public void setBinName(String binName) {
		this.binName = binName;
	}

	public String getBeginCardNo() {
		return beginCardNo;
	}

	public void setBeginCardNo(String beginCardNo) {
		this.beginCardNo = beginCardNo;
	}

	public Integer getCardQty() {
		return cardQty;
	}

	public void setCardQty(Integer cardQty) {
		this.cardQty = cardQty;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public Integer getPaidType() {
		return paidType;
	}

	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}

	public BigDecimal getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	public Integer getActivateSign() {
		return activateSign;
	}

	public void setActivateSign(Integer activateSign) {
		this.activateSign = activateSign;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
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

	public Date getEnanbleTime() {
		return enanbleTime;
	}

	public void setEnanbleTime(Date enanbleTime) {
		this.enanbleTime = enanbleTime;
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

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public List<String> getCardNo() {
		return cardNo;
	}

	public void setCardNo(List<String> cardNo) {
		this.cardNo = cardNo;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public Integer getNewPoint() {
		return newPoint;
	}

	public void setNewPoint(Integer newPoint) {
		this.newPoint = newPoint;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
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

	public Integer getRealNameSign() {
		return realNameSign;
	}

	public void setRealNameSign(Integer realNameSign) {
		this.realNameSign = realNameSign;
	}

	public Integer getToaccountType() {
		return toaccountType;
	}

	public void setToaccountType(Integer toaccountType) {
		this.toaccountType = toaccountType;
	}

	public String getToaccountName() {
		return toaccountName;
	}

	public void setToaccountName(String toaccountName) {
		this.toaccountName = toaccountName;
	}

	public String getPayMenName() {
		return payMenName;
	}

	public void setPayMenName(String payMenName) {
		this.payMenName = payMenName;
	}

	public String getPayMenId() {
		return payMenId;
	}

	public void setPayMenId(String payMenId) {
		this.payMenId = payMenId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getComTele() {
		return comTele;
	}

	public void setComTele(String comTele) {
		this.comTele = comTele;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public String getComConPer() {
		return comConPer;
	}

	public void setComConPer(String comConPer) {
		this.comConPer = comConPer;
	}

	public Integer getCompNum() {
		return compNum;
	}

	public void setCompNum(Integer compNum) {
		this.compNum = compNum;
	}

	public String getComConPerTele() {
		return comConPerTele;
	}

	public void setComConPerTele(String comConPerTele) {
		this.comConPerTele = comConPerTele;
	}

	public Integer getCardSingn() {
		return cardSingn;
	}

	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}

	
	
}
