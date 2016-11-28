package com.paySystem.ic.web.dto.goods;

/**  
* @Title: PlatHandleRecordDTO.java
* @Package: com.paySystem.ic.web.dto.goods
* @Description:  平台处理商品记录类DTO
* @Author: Jacky
* @Date: 2014-08-06
* @Version: V1.0  
*/
public class PlatHandleRecordDTO {
	
	/** 商品编号ID*/
	private Long goodsId;
	
	/** 状态*/
	private Integer phType;
	
	/**处理原因 */
	private String phReason;
	
	/**违反条例 */
	private String vioRugleId;
	
	/**处理意见说明 */
	private String phDescr;
	
	/** 处理人*/
	private String operatorId;
	
	/** 扣除分值*/
	private double deductScore;
	
	/** 商品违规类型类型*/
	private Integer goodPunishType;
	

	public Integer getGoodPunishType() {
		return goodPunishType;
	}

	public void setGoodPunishType(Integer goodPunishType) {
		this.goodPunishType = goodPunishType;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getPhType() {
		return phType;
	}

	public void setPhType(Integer phType) {
		this.phType = phType;
	}

	public String getPhReason() {
		return phReason;
	}

	public void setPhReason(String phReason) {
		this.phReason = phReason;
	}

	public String getVioRugleId() {
		return vioRugleId;
	}

	public void setVioRugleId(String vioRugleId) {
		this.vioRugleId = vioRugleId;
	}

	public String getPhDescr() {
		return phDescr;
	}

	public void setPhDescr(String phDescr) {
		this.phDescr = phDescr;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}
	
}
