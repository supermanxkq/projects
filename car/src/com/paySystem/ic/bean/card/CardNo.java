package com.paySystem.ic.bean.card;

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

import com.paySystem.ic.bean.base.Organs;

/**
 * @author ：Administrator 赵巧鹤
 * @ClassName ：CardNo 卡号表
 * @project_name：mciu
 * @createTime：2013-11-29 下午04:01:40
 * @updateTime：2013-11-29 下午04:01:40
 * 
 */
@Entity
@Table(name = "C_CardNo")
public class CardNo implements Serializable {

	private static final long serialVersionUID = -4506400550862793061L;

	/**
	 * 卡号Id
	 * */
	private String cardNo;

	/**
	 * 批次号
	 * */
	private String generaId;
	/**
	 * 卡的状态 0、新卡 1、已导出 2、等待确认 3、已入库 9、已删除
	 * */
	private Integer status;
	/**
	 * 密码状态 0、密码不启用 1、密码启用
	 * */
	private Integer passSign;

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
	 * 验证码
	 * */
	private String ccv;
	/**
	 * 显示卡号
	 * */
   private String cardViewNo;
   
	public CardNo() {
		super();
	}

	public CardNo(String cardNo) {
		super();
		this.cardNo = cardNo;
	}

	@Id
	@Column(length = 19)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(length = 12)
	public String getGeneraId() {
		return generaId;
	}

	public void setGeneraId(String generaId) {
		this.generaId = generaId;
	}

	@Column(columnDefinition = "char(2)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getPassSign() {
		return passSign;
	}

	public void setPassSign(Integer passSign) {
		this.passSign = passSign;
	}

	@Column(length = 16)
	public String getSeriNo() {
		return seriNo;
	}

	public void setSeriNo(String seriNo) {
		this.seriNo = seriNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length = 16)
	public String getPinblock() {
		return pinblock;
	}

	public void setPinblock(String pinblock) {
		this.pinblock = pinblock;
	}

	@Column(length = 40)
	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "binId")
	public CardBIN getCardBIN() {
		return cardBIN;
	}

	public void setCardBIN(CardBIN cardBIN) {
		this.cardBIN = cardBIN;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	@Column(columnDefinition = "char(3)")
	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	@Column(length = 19)
	public String getCardViewNo() {
		return cardViewNo;
	}

	public void setCardViewNo(String cardViewNo) {
		this.cardViewNo = cardViewNo;
	}


 
}
