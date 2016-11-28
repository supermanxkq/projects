package com.paySystem.ic.bean.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omal
 * @ClassName:ComplaintInfor
 * @Description:投诉信息记录表
 * @date: 2014-11-12
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="B_ComplaintInfor")
public class ComplaintInfor implements Serializable{

	private static final long serialVersionUID = -5720357918316618593L;
	
	/**投诉信息id*/
    private Integer complaintId;
    /**订单编号*/
    private String orderId;
    /**投诉申请编号*/
    private Integer filedId;
    /**投诉状态
     *0：投诉退款申请等待平台确认中 
     *1：平台不同意协议，等待买家修改 
     *3：投诉申请达成，已对卖家进行处罚 
     *4：买家已退货，等待卖家确认收货 
     *5：投诉关闭
     *6：投诉成功 
     * */
    private Integer compSta;
    /**管理投诉状态
     *0：会员进行投诉 
     *1：商家进行回复 
     *2：平台处理投诉 
     */
    private Integer manComSta;
    /**商户申诉说明*/
	private String storeComDesc;
	/**处理意见*/
	private String handlSug;
	/** 平台处理方式 **/
	private Integer handlWay;
	/**处理人id*/
	private String operator;
    /**更新时间*/
    private Date updateTime;
    /**扣除分值**/
    private double deductScore;
    /**违规条例**/
    private Integer vioRugleId;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	@Column(length=16)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getManComSta() {
		return manComSta;
	}
	public void setManComSta(Integer manComSta) {
		this.manComSta = manComSta;
	}
	@Column
	public Integer getFiledId() {
		return filedId;
	}
	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getCompSta() {
		return compSta;
	}
	public void setCompSta(Integer compSta) {
		this.compSta = compSta;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length=255)
	public String getStoreComDesc() {
		return storeComDesc;
	}
	public void setStoreComDesc(String storeComDesc) {
		this.storeComDesc = storeComDesc;
	}
	@Column(length=255)
	public String getHandlSug() {
		return handlSug;
	}
	public void setHandlSug(String handlSug) {
		this.handlSug = handlSug;
	}
	@Column(length=15)
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(length=5,scale=2)
	public double getDeductScore() {
		return deductScore;
	}
	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}
	@Column
	public Integer getVioRugleId() {
		return vioRugleId;
	}

	public void setVioRugleId(Integer vioRugleId) {
		this.vioRugleId = vioRugleId;
	}
	@Column(length=1)
	public Integer getHandlWay() {
		return handlWay;
	}
	public void setHandlWay(Integer handlWay) {
		this.handlWay = handlWay;
	}
	
}
