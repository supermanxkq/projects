package com.paySystem.ic.web.dto.member;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemCardConnDTO
 * @Description:会员与卡关联表DTO
 * @date: 2014-11-24
 * @author: 毛智东
 * @version: V1.0
 */
public class MemCardConnDTO extends BaseDTO {

	/** 主键id **/
	private Integer connId;

	/** 会员编号 **/
	private Integer memId;

	/** 卡号 **/
	private String CardNo;

	/** 账户类型 0支付平台卡 1银行卡 **/
	private Integer accountType;

	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

}
