package com.paySystem.ic.bean.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:MemGrowValue
 * @Description:会员成长值规则信息表
 * @date: 2014-10-13
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="M_MemGrowValue")
public class MemGrowValue implements Serializable{

	private static final long serialVersionUID = -2687342634932803535L;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getMgcId() {
		return mgcId;
	}
	public void setMgcId(Integer mgcId) {
		this.mgcId = mgcId;
	}
	@Column(length=8)
	public Integer getLoginValue() {
		return loginValue;
	}
	public void setLoginValue(Integer loginValue) {
		this.loginValue = loginValue;
	}
	@Column(length=8)
	public Integer getRealNameAuthValue() {
		return realNameAuthValue;
	}
	public void setRealNameAuthValue(Integer realNameAuthValue) {
		this.realNameAuthValue = realNameAuthValue;
	}
	@Column(length=8)
	public Integer getServiceEvaluValue() {
		return serviceEvaluValue;
	}
	public void setServiceEvaluValue(Integer serviceEvaluValue) {
		this.serviceEvaluValue = serviceEvaluValue;
	}
	@Column(length=8)
	public Integer getGoodsEvaluValue() {
		return goodsEvaluValue;
	}
	public void setGoodsEvaluValue(Integer goodsEvaluValue) {
		this.goodsEvaluValue = goodsEvaluValue;
	}
	@Column(length=8)
	public Integer getBaskValue() {
		return baskValue;
	}
	public void setBaskValue(Integer baskValue) {
		this.baskValue = baskValue;
	}
	@Column(length=8)
	public Integer getRecomClientValue() {
		return recomClientValue;
	}
	public void setRecomClientValue(Integer recomClientValue) {
		this.recomClientValue = recomClientValue;
	}
	@Column(length=8)
	public Integer getBaskPhotosValue() {
		return baskPhotosValue;
	}
	public void setBaskPhotosValue(Integer baskPhotosValue) {
		this.baskPhotosValue = baskPhotosValue;
	}
	@Column(length=8)
	public Integer getBaskVideoValue() {
		return baskVideoValue;
	}
	public void setBaskVideoValue(Integer baskVideoValue) {
		this.baskVideoValue = baskVideoValue;
	}
	@Column(length=8)
	public Integer getEmailAuthValue() {
		return emailAuthValue;
	}
	public void setEmailAuthValue(Integer emailAuthValue) {
		this.emailAuthValue = emailAuthValue;
	}
	@Column(length=8)
	public Integer getPhoneAuthValue() {
		return phoneAuthValue;
	}
	public void setPhoneAuthValue(Integer phoneAuthValue) {
		this.phoneAuthValue = phoneAuthValue;
	}
	
}
