package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omal
 * @ClassName:ComplaintFiledDTO
 * @Description:投诉申请的DTO
 * @date: 2014-11-12
 * @author: 王楠
 * @version: V1.0
 */
public class ComplaintFiledDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -7402742008710980419L;

	/**投诉申请Id*/
	private Integer filedId;
	/**订单编号*/
	private String orderId;
	/**会员编号*/
	private String memId;
	/**投诉类型
	 *0：对商品进行投诉
	 *1：对商家进行投诉 
	 :2：对商家服务进行投诉 
	 */
	private Integer compType;
	/**投诉理由说明*/
	private String compDesc;
	/**投诉的店铺编号*/
	private String storeId;
	/**商户申诉说明*/
	private String storeComDesc;
	/**处理意见*/
	private String handlSug;
	/**处理人id*/
	private String operator;
	/**投诉申请时间*/
	private Date compTime;
	/**投诉信息id*/
    private Integer complaintId;
    /**投诉状态
     *0：投诉退款申请等待平台确认中 
     *1：平台不同意协议，等待买家修改 
     *3：投诉申请达成，已对卖家进行处罚 
     *4：买家已退货，等待卖家确认收货 
     *5：投诉关闭
     *6：投诉成功 
     * */
    private Integer compSta;
    /**更新时间*/
    private Date updateTime;
    /**图片id*/
	private Integer compPicId ;
	/**图片路径*/
	private String picPath;
	private String merId;
	public Integer getCompPicId() {
		return compPicId;
	}
	public void setCompPicId(Integer compPicId) {
		this.compPicId = compPicId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Integer getFiledId() {
		return filedId;
	}
	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getCompType() {
		return compType;
	}
	public void setCompType(Integer compType) {
		this.compType = compType;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreComDesc() {
		return storeComDesc;
	}
	public void setStoreComDesc(String storeComDesc) {
		this.storeComDesc = storeComDesc;
	}
	public String getHandlSug() {
		return handlSug;
	}
	public void setHandlSug(String handlSug) {
		this.handlSug = handlSug;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCompTime() {
		return compTime;
	}
	public void setCompTime(Date compTime) {
		this.compTime = compTime;
	}
	public Integer getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	public Integer getCompSta() {
		return compSta;
	}
	public void setCompSta(Integer compSta) {
		this.compSta = compSta;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
    
}
