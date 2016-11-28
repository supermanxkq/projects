package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRuleDTO
 * @Description:会员消费积分规则实体DTO
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
public class MemConsPntsRuleDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**编号*/
	private Integer mcpId;
	/**注册会员积分倍数值*/
	private BigDecimal regisPntsValue;
	/**普通会员积分倍数值*/
	private BigDecimal commPntsValue;
	/**白银会员积分倍数值*/
	private BigDecimal silverPntsValue;
	/**黄金会员积分倍数值*/
	private BigDecimal goldPntsValue;
	/**钻石会员积分倍数值*/
	private BigDecimal diamondPntsValue;
	/**兑换人民币数*/
	private Integer moneyNum; 
	/**兑换积分数*/
	private Integer pointsNum;
	public Integer getMcpId() {
		return mcpId;
	}
	public void setMcpId(Integer mcpId) {
		this.mcpId = mcpId;
	}
	public BigDecimal getRegisPntsValue() {
		return regisPntsValue;
	}
	public void setRegisPntsValue(BigDecimal regisPntsValue) {
		this.regisPntsValue = regisPntsValue;
	}
	public BigDecimal getCommPntsValue() {
		return commPntsValue;
	}
	public void setCommPntsValue(BigDecimal commPntsValue) {
		this.commPntsValue = commPntsValue;
	}
	public BigDecimal getSilverPntsValue() {
		return silverPntsValue;
	}
	public void setSilverPntsValue(BigDecimal silverPntsValue) {
		this.silverPntsValue = silverPntsValue;
	}
	public BigDecimal getGoldPntsValue() {
		return goldPntsValue;
	}
	public void setGoldPntsValue(BigDecimal goldPntsValue) {
		this.goldPntsValue = goldPntsValue;
	}
	public BigDecimal getDiamondPntsValue() {
		return diamondPntsValue;
	}
	public void setDiamondPntsValue(BigDecimal diamondPntsValue) {
		this.diamondPntsValue = diamondPntsValue;
	}
	public Integer getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(Integer moneyNum) {
		this.moneyNum = moneyNum;
	}
	public Integer getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(Integer pointsNum) {
		this.pointsNum = pointsNum;
	}
	
}
