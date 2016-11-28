package com.paySystem.ic.web.dto.stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:BussParamsConfigDTO
 * @Description:商城业务参数DTO
 * @date: 2014-10-14
 * @author: 王楠
 * @version: V1.0
 */
public class BussParamsConfigDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = 992604260093865448L;
	
	/**参数配置编号*/
	private Integer paramsId;
	/**自动确认收货期限*/
    private Integer limtReceAuto;
    /**统一售后保障期限*/
    private Integer limtAfterSale;
    /**发送业务提醒  0：否 1：是*/
    private Integer sendBusinessRecom;
    /**商家发货提醒   0：否  1：是*/
    private Integer noDeliveMerRecom;
    /**消费赠送积分比率*/
    private BigDecimal rate;
    /**最大延迟发货天数*/
    private Integer maxDelayDays;
    /**操作人*/
    private String operMan;
    /**操作时间*/
    private Date operTime;
    /**规则编号*/
	private Integer mgcId;
	/**登录成长值*/
	private Integer loginValue;
	/**实名认证成长值*/
	private Integer realNameAuthValue;
	/**服务评价成长值*/
	private Integer serviceEvaluValue;
	/**商品评价成长值*/
	private Integer goodsEvaluValue;
	/**晒单成长值*/
	private Integer baskValue;
	/**推荐客户成长值*/
	private Integer recomClientValue;
	/**晒活动照片成长值*/
	private Integer baskPhotosValue;
	/**晒活动视频成长值*/
	private Integer baskVideoValue;
	/**邮箱认证成长值*/
	private Integer emailAuthValue;
	/**手机认证成长值*/
	private Integer phoneAuthValue;
	public Integer getParamsId() {
		return paramsId;
	}
	public void setParamsId(Integer paramsId) {
		this.paramsId = paramsId;
	}
	public Integer getLimtReceAuto() {
		return limtReceAuto;
	}
	public void setLimtReceAuto(Integer limtReceAuto) {
		this.limtReceAuto = limtReceAuto;
	}
	public Integer getLimtAfterSale() {
		return limtAfterSale;
	}
	public void setLimtAfterSale(Integer limtAfterSale) {
		this.limtAfterSale = limtAfterSale;
	}
	public Integer getSendBusinessRecom() {
		return sendBusinessRecom;
	}
	public void setSendBusinessRecom(Integer sendBusinessRecom) {
		this.sendBusinessRecom = sendBusinessRecom;
	}
	public Integer getNoDeliveMerRecom() {
		return noDeliveMerRecom;
	}
	public void setNoDeliveMerRecom(Integer noDeliveMerRecom) {
		this.noDeliveMerRecom = noDeliveMerRecom;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Integer getMaxDelayDays() {
		return maxDelayDays;
	}
	public void setMaxDelayDays(Integer maxDelayDays) {
		this.maxDelayDays = maxDelayDays;
	}
	public String getOperMan() {
		return operMan;
	}
	public void setOperMan(String operMan) {
		this.operMan = operMan;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public Integer getMgcId() {
		return mgcId;
	}
	public void setMgcId(Integer mgcId) {
		this.mgcId = mgcId;
	}
	public Integer getLoginValue() {
		return loginValue;
	}
	public void setLoginValue(Integer loginValue) {
		this.loginValue = loginValue;
	}
	public Integer getRealNameAuthValue() {
		return realNameAuthValue;
	}
	public void setRealNameAuthValue(Integer realNameAuthValue) {
		this.realNameAuthValue = realNameAuthValue;
	}
	public Integer getServiceEvaluValue() {
		return serviceEvaluValue;
	}
	public void setServiceEvaluValue(Integer serviceEvaluValue) {
		this.serviceEvaluValue = serviceEvaluValue;
	}
	public Integer getGoodsEvaluValue() {
		return goodsEvaluValue;
	}
	public void setGoodsEvaluValue(Integer goodsEvaluValue) {
		this.goodsEvaluValue = goodsEvaluValue;
	}
	public Integer getBaskValue() {
		return baskValue;
	}
	public void setBaskValue(Integer baskValue) {
		this.baskValue = baskValue;
	}
	public Integer getRecomClientValue() {
		return recomClientValue;
	}
	public void setRecomClientValue(Integer recomClientValue) {
		this.recomClientValue = recomClientValue;
	}
	public Integer getBaskPhotosValue() {
		return baskPhotosValue;
	}
	public void setBaskPhotosValue(Integer baskPhotosValue) {
		this.baskPhotosValue = baskPhotosValue;
	}
	public Integer getBaskVideoValue() {
		return baskVideoValue;
	}
	public void setBaskVideoValue(Integer baskVideoValue) {
		this.baskVideoValue = baskVideoValue;
	}
	public Integer getEmailAuthValue() {
		return emailAuthValue;
	}
	public void setEmailAuthValue(Integer emailAuthValue) {
		this.emailAuthValue = emailAuthValue;
	}
	public Integer getPhoneAuthValue() {
		return phoneAuthValue;
	}
	public void setPhoneAuthValue(Integer phoneAuthValue) {
		this.phoneAuthValue = phoneAuthValue;
	}
	

}
