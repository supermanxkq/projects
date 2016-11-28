package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @ClassName:MerOrgFuncSwitch
 * @Description:机构商户功能开关实体Bean
 * @date: 2013-11-29下午03:41:52
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="B_Func_Mer_Org")
public class MerOrgFuncSwitch implements Serializable {

	private static final long serialVersionUID = -2833631421111544180L;
	/** 功能表记录流水号 */
	private String fmoId;
	/** 机构商户标志   0：机构；1：商户 */
	private Integer orgMercSign;
	/** 设置的机构 */
	private Organs organs;
	/** 设置的商户  */
	private Merchants merchants;
	/** 是否开通代理商  0:否；1是 */
	private Integer openAgencySign;
	/** 是否开通业务员 0：否；1是*/
	private Integer openBussManSign;
	/** 是否开通短消息 */
	private Integer openMessSign;
	/** 是否开通优惠活动功能  */
	private Integer openPreferSign;
	/** 是否开通生日赠送功能 */
	private Integer openBriGiftSign;
	/** 是否开通节日赠送 */
	private Integer openHolGiftSign;
	/** 是否优惠券 */
	private Integer openPreferTSign;
	/** 会员信息加密  */
	private Integer enciVipMess;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
	@Id
	@Column(length=15)
	public String getFmoId() {
		return fmoId;
	}
	public void setFmoId(String fmoId) {
		this.fmoId = fmoId;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOrgMercSign() {
		return orgMercSign;
	}
	public void setOrgMercSign(Integer orgMercSign) {
		this.orgMercSign = orgMercSign;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenAgencySign() {
		return openAgencySign;
	}
	public void setOpenAgencySign(Integer openAgencySign) {
		this.openAgencySign = openAgencySign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenMessSign() {
		return openMessSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenBussManSign() {
		return openBussManSign;
	}
	
	public void setOpenBussManSign(Integer openBussManSign) {
		this.openBussManSign = openBussManSign;
	}
	public void setOpenMessSign(Integer openMessSign) {
		this.openMessSign = openMessSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenPreferSign() {
		return openPreferSign;
	}
	public void setOpenPreferSign(Integer openPreferSign) {
		this.openPreferSign = openPreferSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenBriGiftSign() {
		return openBriGiftSign;
	}
	public void setOpenBriGiftSign(Integer openBriGiftSign) {
		this.openBriGiftSign = openBriGiftSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenHolGiftSign() {
		return openHolGiftSign;
	}
	public void setOpenHolGiftSign(Integer openHolGiftSign) {
		this.openHolGiftSign = openHolGiftSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getOpenPreferTSign() {
		return openPreferTSign;
	}
	public void setOpenPreferTSign(Integer openPreferTSign) {
		this.openPreferTSign = openPreferTSign;
	}
	@Column(columnDefinition="char(1)")
	public Integer getEnciVipMess() {
		return enciVipMess;
	}
	public void setEnciVipMess(Integer enciVipMess) {
		this.enciVipMess = enciVipMess;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
