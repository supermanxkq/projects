package com.paySystem.ic.bean.evaluation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ProjectName: omall
 * @ClassName: MerCreditTotal
 * @Description: 商户评价累计表
 * @date: 2014-10-29 下午12:51:01
 * @author: 王少辉
 * @version: V1.0
 */
@Entity
@Table(name = "B_MerCreditTotal")
public class MerCreditTotal implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 信息ID
	 */
	private Integer mctId;

	/**
	 * 商户编号
	 */
	private String merId;

	/**
	 * 评价次数
	 */
	private Integer pjcount;

	/**
	 * 好评/中评/差评总计（信誉度）
	 */
	private BigDecimal goodsappTotal;

	/**
	 * 信誉度信息表ID
	 */
	private Integer creditImgId;

	/**
	 * 宝贝描述相符总计
	 */
	private BigDecimal goodsMatchTotal;

	/**
	 * 服务态度总计
	 */
	private BigDecimal serviceAttTotal;

	/**
	 * 发货速度总计
	 */
	private BigDecimal sendSpeadTotal;

	/**
	 * 物流发货速度
	 */
	private BigDecimal logisticsTotal;

	/**
	 * 送件人员态度
	 */
	private BigDecimal courierTotal;

	/**
	 * 创建日期
	 */
	private Date createTime;

	/**
	 * 更新日期
	 */
	private Date updateTime;

	/**
	 * @return Integer 信息ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	public Integer getMctId() {
		return mctId;
	}
	
	
	/**
	 * 物流发货速度
	 */
	private BigDecimal wlsendSpeadTotal;

	/**
	 * 送件人员态度
	 */
	private BigDecimal sjserviceAttTotal;

	/**
	 * @param mctId
	 *            信息ID
	 */
	public void setMctId(Integer mctId) {
		this.mctId = mctId;
	}

	/**
	 * @return String 商户编号
	 */
	@Column(length = 15)
	public String getMerId() {
		return merId;
	}

	/**
	 * @param merId
	 *            商户编号
	 */
	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * @return Integer 评价次数
	 */
	public Integer getPjcount() {
		return pjcount;
	}

	/**
	 * @param pjcount
	 *            评价次数
	 */
	public void setPjcount(Integer pjcount) {
		this.pjcount = pjcount;
	}

	/**
	 * @return BigDecimal 好评/中评/差评总计（信誉度）
	 */
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getGoodsappTotal() {
		return goodsappTotal;
	}

	/**
	 * @param goodsappTotal
	 *            好评/中评/差评总计（信誉度）
	 */
	public void setGoodsappTotal(BigDecimal goodsappTotal) {
		this.goodsappTotal = goodsappTotal;
	}

	/**
	 * @return Integer 信誉度信息表ID
	 */
	public Integer getCreditImgId() {
		return creditImgId;
	}

	/**
	 * @param creditImgId
	 *            信誉度信息表ID
	 */
	public void setCreditImgId(Integer creditImgId) {
		this.creditImgId = creditImgId;
	}

	/**
	 * @return BigDecimal 宝贝描述相符总计
	 */
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getGoodsMatchTotal() {
		return goodsMatchTotal;
	}

	/**
	 * @param goodsMatchTotal
	 *            宝贝描述相符总计
	 */
	public void setGoodsMatchTotal(BigDecimal goodsMatchTotal) {
		this.goodsMatchTotal = goodsMatchTotal;
	}

	/**
	 * @return BigDecimal 服务态度总计
	 */
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getServiceAttTotal() {
		return serviceAttTotal;
	}

	/**
	 * @param serviceAttTotal
	 *            服务态度总计
	 */
	public void setServiceAttTotal(BigDecimal serviceAttTotal) {
		this.serviceAttTotal = serviceAttTotal;
	}

	/**
	 * @return BigDecimal 发货速度总计
	 */
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getSendSpeadTotal() {
		return sendSpeadTotal;
	}

	/**
	 * @param sendSpeadTotal
	 *            发货速度总计
	 */
	public void setSendSpeadTotal(BigDecimal sendSpeadTotal) {
		this.sendSpeadTotal = sendSpeadTotal;
	}

	/**
	 * @return Integer 创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Integer 更新日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getLogisticsTotal() {
		return logisticsTotal;
	}

	public void setLogisticsTotal(BigDecimal logisticsTotal) {
		this.logisticsTotal = logisticsTotal;
	}

	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getCourierTotal() {
		return courierTotal;
	}

	public void setCourierTotal(BigDecimal courierTotal) {
		this.courierTotal = courierTotal;
	}
	
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getWlsendSpeadTotal() {
		return wlsendSpeadTotal;
	}
	
	public void setWlsendSpeadTotal(BigDecimal wlsendSpeadTotal) {
		this.wlsendSpeadTotal = wlsendSpeadTotal;
	}
	@Column(columnDefinition = "DECIMAL(16,2)")
	public BigDecimal getSjserviceAttTotal() {
		return sjserviceAttTotal;
	}

	public void setSjserviceAttTotal(BigDecimal sjserviceAttTotal) {
		this.sjserviceAttTotal = sjserviceAttTotal;
	}
	
	
}
