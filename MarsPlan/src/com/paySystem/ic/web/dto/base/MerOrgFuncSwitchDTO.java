package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.web.dto.BaseDTO;

/**
 *@ClassName:MerOrgFuncSwitchDTO
 *@Description:机构商户功能设置DTO类
 *@Date:2013-11-29下午11:48:25
 *@Author:谢工
 *@Version: V1.0
 */
public class MerOrgFuncSwitchDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -6577025123947049912L;

	/** 功能表记录流水号 */
	private String fmoId;
	/** 机构商户标志 0：机构；1：商户 */
	private Integer orgMercSign;
	/** 设置的机构 */
	private Organs organs;
	/** 设置的商户 */
	private Merchants merchants;
	/** 是否开通代理商 0:否；1是 */
	private Integer openAgencySign;
	/** 是否开通业务员 0：否；1是 */
	private Integer openBussManSign;
	/** 是否开通短消息 */
	private Integer openMessSign;
	/** 是否开通优惠活动功能 */
	private Integer openPreferSign;
	/** 是否开通生日赠送功能 */
	private Integer openBriGiftSign;
	/** 是否开通节日赠送 */
	private Integer openHolGiftSign;
	/** 是否优惠券 */
	private Integer openPreferTSign;
	/** 会员信息加密 */
	private Integer enciVipMess;
	
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	
	/** 查询条件 - 机构编号*/
	private String organId;
	
	/** 页面信息 - 机构名称*/
	private String organName;
	
	/** 页面信息 - 商户编号 */
	private String merId;
	/** 页面信息 - 商户名称*/
	private String merName;
	

	public String getFmoId() {
		return fmoId;
	}

	public void setFmoId(String fmoId) {
		this.fmoId = fmoId;
	}

	public Integer getOrgMercSign() {
		return orgMercSign;
	}

	public void setOrgMercSign(Integer orgMercSign) {
		this.orgMercSign = orgMercSign;
	}

	public Organs getOrgans() {
		return organs;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	public Merchants getMerchants() {
		return merchants;
	}

	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}

	public Integer getOpenAgencySign() {
		return openAgencySign;
	}

	public void setOpenAgencySign(Integer openAgencySign) {
		this.openAgencySign = openAgencySign;
	}

	public Integer getOpenBussManSign() {
		return openBussManSign;
	}

	public void setOpenBussManSign(Integer openBussManSign) {
		this.openBussManSign = openBussManSign;
	}

	public Integer getOpenMessSign() {
		return openMessSign;
	}

	public void setOpenMessSign(Integer openMessSign) {
		this.openMessSign = openMessSign;
	}

	public Integer getOpenPreferSign() {
		return openPreferSign;
	}

	public void setOpenPreferSign(Integer openPreferSign) {
		this.openPreferSign = openPreferSign;
	}

	public Integer getOpenBriGiftSign() {
		return openBriGiftSign;
	}

	public void setOpenBriGiftSign(Integer openBriGiftSign) {
		this.openBriGiftSign = openBriGiftSign;
	}

	public Integer getOpenHolGiftSign() {
		return openHolGiftSign;
	}

	public void setOpenHolGiftSign(Integer openHolGiftSign) {
		this.openHolGiftSign = openHolGiftSign;
	}

	public Integer getOpenPreferTSign() {
		return openPreferTSign;
	}

	public void setOpenPreferTSign(Integer openPreferTSign) {
		this.openPreferTSign = openPreferTSign;
	}

	public Integer getEnciVipMess() {
		return enciVipMess;
	}

	public void setEnciVipMess(Integer enciVipMess) {
		this.enciVipMess = enciVipMess;
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

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
    
	
}
