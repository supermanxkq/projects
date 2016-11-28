package com.paySystem.ic.web.dto.card;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.CardBIN;
import com.paySystem.ic.web.dto.BaseDTO;

public class CardNoDTO extends BaseDTO implements Serializable{

private static final long serialVersionUID =-1324112849666702193L;

/**
 * 卡号Id
 * */
private String cardNo;

/**
 * 卡名称
 * */
private String cardName;
/**
 * 批次号
 * */
private String generaId;
/**
 * 卡的状态
 * 0、新卡
 * 1、已导出
 * 2、等待确认
 * 3已入库
 * */
private Integer status;
/**
 * 密码状态
 * 0、密码不启用
 * 1、密码启用
 * */
private Integer passSign;
/**
 * 初始密码
 * */
private String beginPwd;
/**
 * 卡序列号
 * */
private String seriNo;


/**
 * 创建时间
 * */
private Date createTime;

/**
 * PINBLOCK生成密码用的
 * */
private String pinblock;
/**
 * a磁道信息
 * */
private String track;
/**
 * 卡BIN
 * */
private CardBIN cardBIN;
/**
 * 机构
 * */
private Organs organs;
/**
 * 显示卡号
 * */
private String cardViewNo;

public String getCardNo() {
	return cardNo;
}
public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}
public String getCardName() {
	return cardName;
}
public void setCardName(String cardName) {
	this.cardName = cardName;
}
public String getGeneraId() {
	return generaId;
}
public void setGeneraId(String generaId) {
	this.generaId = generaId;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getPassSign() {
	return passSign;
}
public void setPassSign(Integer passSign) {
	this.passSign = passSign;
}
public String getBeginPwd() {
	return beginPwd;
}
public void setBeginPwd(String beginPwd) {
	this.beginPwd = beginPwd;
}
public String getSeriNo() {
	return seriNo;
}
public void setSeriNo(String seriNo) {
	this.seriNo = seriNo;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getPinblock() {
	return pinblock;
}
public void setPinblock(String pinblock) {
	this.pinblock = pinblock;
}
public String getTrack() {
	return track;
}
public void setTrack(String track) {
	this.track = track;
}
public CardBIN getCardBIN() {
	return cardBIN;
}
public void setCardBIN(CardBIN cardBIN) {
	this.cardBIN = cardBIN;
}
public Organs getOrgans() {
	return organs;
}
public void setOrgans(Organs organs) {
	this.organs = organs;
}
public String getCardViewNo() {
	return cardViewNo;
}
public void setCardViewNo(String cardViewNo) {
	this.cardViewNo = cardViewNo;
}



}
