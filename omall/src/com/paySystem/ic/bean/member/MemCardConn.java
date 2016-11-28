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
 * @ClassName:MemCardConn
 * @Description:会员与卡关联表
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
@Entity
@Table(name = "C_memCardConn")
public class MemCardConn implements Serializable {

	/** 序列号 **/
	private static final long serialVersionUID = 1117533238634707512L;

	/** 主键id **/
	private Integer connId;

	/** 会员编号 **/
	private Integer memId;

	/** 卡号 **/
	private String CardNo;

	/** 账户类型 0支付平台卡 1银行卡 **/
	private Integer accountType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

	@Column(length = 10, nullable = false)
	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Column(length = 19)
	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

}
