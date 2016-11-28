package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.paySystem.ic.bean.member.Member;
import com.paySystem.ic.web.dto.BaseDTO;
/**
 * 
* @ClassName: AsaleOrderDTO
* @Description: 售卡订单辅助信息DTO
* @author lily
* @date 2013-12-16 下午04:26:59
* @version V1.0
 */
public class AsaleOrderDTO extends BaseDTO implements Serializable {

	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = -2165218449698972343L;

	private String saleOrderId; //售卡订单编号8位日期+流水号

	private String saleLevelId; //售卡时卡等级ID
	private BigDecimal newPoint; //开卡送积分
	private String saleLevelName;
	private String bcSign; //售卡时是否加入商圈
	private String realNameSign; //售卡时是否实名
	private Member holdMem;
	private String holdMemID;  //持卡人ID
	private String holdMemName;
	private String descr;  //订单描述
	private String cardNoStr;
	private List<String> cardNoList;//Map<卡号，初始金额>
    private Integer cardSingn;//卡标志
    
	public String getSaleOrderId() {
		return saleOrderId;
	}
	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public String getSaleLevelId() {
		return saleLevelId;
	}
	public void setSaleLevelId(String saleLevelId) {
		this.saleLevelId = saleLevelId;
	}
	public String getSaleLevelName() {
		return saleLevelName;
	}
	public void setSaleLevelName(String saleLevelName) {
		this.saleLevelName = saleLevelName;
	}
	
	public BigDecimal getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(BigDecimal newPoint) {
		this.newPoint = newPoint;
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
	@Override
	public String toString() {
		return "SaleOrderDetailDTO [bcSign=" + bcSign + ", descr=" + descr
				+ ", holdMemID=" + holdMemID + ", holdMemName=" + holdMemName
				+ ", newPoint=" + newPoint + ", realNameSign=" + realNameSign
				+ ", saleLevelId=" + saleLevelId + ", saleLevelName="
				+ saleLevelName + ", saleOrderId=" + saleOrderId + "]";
	}
	public void setCardNoList(List<String> cardNoList) {
		this.cardNoList = cardNoList;
	}
	public List<String> getCardNoList() {
		return cardNoList;
	}
	public void setCardNoStr(String cardNoStr) {
		this.cardNoStr = cardNoStr;
	}
	public String getCardNoStr() {
		return cardNoStr;
	}
	public Integer getCardSingn() {
		return cardSingn;
	}
	public void setCardSingn(Integer cardSingn) {
		this.cardSingn = cardSingn;
	}
	
 
	
}
