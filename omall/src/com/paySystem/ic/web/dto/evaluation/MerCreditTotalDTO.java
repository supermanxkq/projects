package com.paySystem.ic.web.dto.evaluation;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MerCreditTotalDTO
 * @Description:商户评价累计表DTO
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
public class MerCreditTotalDTO extends BaseDTO {

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

	public Integer getMctId() {
		return mctId;
	}

	public void setMctId(Integer mctId) {
		this.mctId = mctId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Integer getPjcount() {
		return pjcount;
	}

	public void setPjcount(Integer pjcount) {
		this.pjcount = pjcount;
	}

	public BigDecimal getGoodsappTotal() {
		return goodsappTotal;
	}

	public void setGoodsappTotal(BigDecimal goodsappTotal) {
		this.goodsappTotal = goodsappTotal;
	}

	public Integer getCreditImgId() {
		return creditImgId;
	}

	public void setCreditImgId(Integer creditImgId) {
		this.creditImgId = creditImgId;
	}

	public BigDecimal getGoodsMatchTotal() {
		return goodsMatchTotal;
	}

	public void setGoodsMatchTotal(BigDecimal goodsMatchTotal) {
		this.goodsMatchTotal = goodsMatchTotal;
	}

	public BigDecimal getServiceAttTotal() {
		return serviceAttTotal;
	}

	public void setServiceAttTotal(BigDecimal serviceAttTotal) {
		this.serviceAttTotal = serviceAttTotal;
	}

	public BigDecimal getSendSpeadTotal() {
		return sendSpeadTotal;
	}

	public void setSendSpeadTotal(BigDecimal sendSpeadTotal) {
		this.sendSpeadTotal = sendSpeadTotal;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getLogisticsTotal() {
		return logisticsTotal;
	}

	public void setLogisticsTotal(BigDecimal logisticsTotal) {
		this.logisticsTotal = logisticsTotal;
	}

	public BigDecimal getCourierTotal() {
		return courierTotal;
	}

	public void setCourierTotal(BigDecimal courierTotal) {
		this.courierTotal = courierTotal;
	}

}
