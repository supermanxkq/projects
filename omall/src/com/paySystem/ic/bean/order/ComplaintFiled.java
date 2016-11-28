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
 * @ClassName:ComplaintFiled
 * @Description:投诉申请表
 * @date: 2014-11-12
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="B_ComplaintFiled")
public class ComplaintFiled implements Serializable{

	private static final long serialVersionUID = 949099554063590202L;

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
	/**投诉状态
	 * 此处只显示投诉完成的唯一状态 
	 */
	private Integer compSta;
	/**投诉的店铺编号*/
	private String storeId;
	
	/**投诉申请时间*/
	private Date compTime;
	/**更新时间**/
	private Date updateTime;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getFiledId() {
		return filedId;
	}
	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}
	@Column(length=16)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Column(length=10)
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Column(columnDefinition = "char(1)")
	public Integer getCompType() {
		return compType;
	}
	public void setCompType(Integer compType) {
		this.compType = compType;
	}
	@Column(length=255)
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	@Column(columnDefinition="char(1)")
	public Integer getCompSta() {
		return compSta;
	}
	public void setCompSta(Integer compSta) {
		this.compSta = compSta;
	}
	@Column(length=15)
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Column(columnDefinition="timestamp")
	public Date getCompTime() {
		return compTime;
	}
	public void setCompTime(Date compTime) {
		this.compTime = compTime;
	}
	@Column(columnDefinition="timestamp")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
