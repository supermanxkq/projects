package com.paySystem.ic.bean.evaluation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**  
 * @Title: MerCreditRecord.java
 * @Package: com.paySystem.ic.bean.evaluation
 * @Description: 评论记录表
 * @Author: yanwuyang 
 * @Date: 2014-10-20 下午4:57:32
 * @Version: V1.0  
 */

@Entity
@Table(name="B_MerCreditRecord")
public class MerCreditRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**信息ID */
	private Integer mcrId;
	/**商户编号 */
	private String merId;
	/**订单ID */
	private String orderId;
	/**商品ID */
	private Integer	goodsId;
	/**评价类型(好评/中评/差评)类型 */
	private Integer appType;
	/**好评分数 */
	private Integer goodsapprise;
	/**宝贝描述相符 */
	private Integer goodsMatch;
	/** 服务态度*/
	private Integer serviceAtt;
	/** 发货速度*/
	private Integer sendSpead;
	/**会员ID（评价人ID） */
	private String memId;
	/**创建日期 */
	private String createTIme;
	/**更新日期 */
	private String updateTIme;
	/** 评价方式自动/手动)类型*/
	private Integer mcrType;
	/** 评价与被评价的标记 1 是给卖家的评价 2 是给买家的评价*/
	private Integer flag;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getMcrId() {
		return mcrId;
	}

	public void setMcrId(Integer mcrId) {
		this.mcrId = mcrId;
	}

	@Column(length=15,nullable=false)
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(nullable=false)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(nullable=false)
	public Integer getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(columnDefinition = "char(1)",nullable=false)
	public Integer getAppType() {
		return appType;
	}
	
	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	@Column(columnDefinition = "Decimal(1,0)",nullable=false)
	public Integer getGoodsapprise() {
		return goodsapprise;
	}

	public void setGoodsapprise(Integer goodsapprise) {
		this.goodsapprise = goodsapprise;
	}

	@Column(columnDefinition = "Decimal(1,0)",nullable=false)
	public Integer getGoodsMatch() {
		return goodsMatch;
	}

	public void setGoodsMatch(Integer goodsMatch) {
		this.goodsMatch = goodsMatch;
	}

	@Column(columnDefinition = "Decimal(1,0)",nullable=false)
	public Integer getServiceAtt() {
		return serviceAtt;
	}

	public void setServiceAtt(Integer serviceAtt) {
		this.serviceAtt = serviceAtt;
	}

	@Column(columnDefinition = "Decimal(1,0)",nullable=false)
	public Integer getSendSpead() {
		return sendSpead;
	}

	public void setSendSpead(Integer sendSpead) {
		this.sendSpead = sendSpead;
	}
	
	@Column(length=15,nullable=false)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(nullable=false)
	public String getCreateTIme() {
		return createTIme;
	}

	public void setCreateTIme(String createTIme) {
		this.createTIme = createTIme;
	}

	@Column(nullable=false)
	public String getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(String updateTIme) {
		this.updateTIme = updateTIme;
	}

	@Column(columnDefinition = "char(1)",nullable=false)
	public Integer getMcrType() {
		return mcrType;
	}

	public void setMcrType(Integer mcrType) {
		this.mcrType = mcrType;
	}

	@Column(columnDefinition = "char(1)",nullable=false)
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
