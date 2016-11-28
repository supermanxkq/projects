package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName:omall
 * @ClassName:MemConsPntsRule
 * @Description:会员消费积分规则信息表
 * @date: 2014-10-17
 * @author: 王楠
 * @version: V1.0
 */
@Entity
@Table(name="M_MemConsPntRule")
public class MemConsPntsRule implements Serializable{

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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	public Integer getMcpId() {
		return mcpId;
	}
	public void setMcpId(Integer mcpId) {
		this.mcpId = mcpId;
	}
	@Column(columnDefinition = "DECIMAL(4,2)")
	public BigDecimal getRegisPntsValue() {
		return regisPntsValue;
	}
	public void setRegisPntsValue(BigDecimal regisPntsValue) {
		this.regisPntsValue = regisPntsValue;
	}
	@Column(columnDefinition = "DECIMAL(4,2)")
	public BigDecimal getCommPntsValue() {
		return commPntsValue;
	}
	public void setCommPntsValue(BigDecimal commPntsValue) {
		this.commPntsValue = commPntsValue;
	}
	@Column(columnDefinition = "DECIMAL(4,2)")
	public BigDecimal getSilverPntsValue() {
		return silverPntsValue;
	}
	public void setSilverPntsValue(BigDecimal silverPntsValue) {
		this.silverPntsValue = silverPntsValue;
	}
	@Column(columnDefinition = "DECIMAL(4,2)")
	public BigDecimal getGoldPntsValue() {
		return goldPntsValue;
	}
	public void setGoldPntsValue(BigDecimal goldPntsValue) {
		this.goldPntsValue = goldPntsValue;
	}
	@Column(columnDefinition = "DECIMAL(4,2)")
	public BigDecimal getDiamondPntsValue() {
		return diamondPntsValue;
	}
	public void setDiamondPntsValue(BigDecimal diamondPntsValue) {
		this.diamondPntsValue = diamondPntsValue;
	}
	@Column(length=6)
	public Integer getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(Integer moneyNum) {
		this.moneyNum = moneyNum;
	}
	@Column(length=6)
	public Integer getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(Integer pointsNum) {
		this.pointsNum = pointsNum;
	}

}
