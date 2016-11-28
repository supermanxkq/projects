package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 
* @ClassName: SaleOrdersDetail
* @Description: 售卡订单明细DTO类
* @author lily
* @date 2013-12-20 上午09:02:09
* @version V1.0
 */
public class SaleOrderDetailDTO extends BaseDTO implements Serializable {

	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = 585328025515685566L;
	
	private Integer saleDetailId;
	private String orderId;
	private String cardNo;
	private String isSuccess;
	private Date createTime;

	public Integer getSaleDetailId() {
		return saleDetailId;
	}
	public void setSaleDetailId(Integer saleDetailId) {
		this.saleDetailId = saleDetailId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SaleOrderDetailDTO [cardNo=" + cardNo + ", createTime="
				+ createTime + ", isSuccess=" + isSuccess + ", orderId="
				+ orderId + ", saleDetailId=" + saleDetailId + "]";
	}

	
	
}
