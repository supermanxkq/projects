package com.paySystem.ic.web.dto.evaluation;

import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:MerCreditRoleDTO
 * @Description:商户信誉度标志规则DTO
 * @date: 2014-11-28
 * @author: 毛智东
 * @version: V1.0
 */
public class MerCreditRoleDTO extends BaseDTO {

	/**
	 * 信息ID
	 */
	private Integer mcrId;

	/**
	 * 最低分
	 */
	private BigDecimal minScores;

	/**
	 * 最高分
	 */
	private BigDecimal maxScores;

	/**
	 * 等级
	 */
	private Integer merLevel;

	/**
	 * 等级标志图片
	 */
	private byte[] leLogo;

	/**
	 * 操作人编号
	 */
	private String operatorId;

	/**
	 * 创建日期
	 */
	private Date createTime;

	/**
	 * 更新日期
	 */
	private Date updateTime;

	public Integer getMcrId() {
		return mcrId;
	}

	public void setMcrId(Integer mcrId) {
		this.mcrId = mcrId;
	}

	public BigDecimal getMinScores() {
		return minScores;
	}

	public void setMinScores(BigDecimal minScores) {
		this.minScores = minScores;
	}

	public BigDecimal getMaxScores() {
		return maxScores;
	}

	public void setMaxScores(BigDecimal maxScores) {
		this.maxScores = maxScores;
	}

	public Integer getMerLevel() {
		return merLevel;
	}

	public void setMerLevel(Integer merLevel) {
		this.merLevel = merLevel;
	}

	public byte[] getLeLogo() {
		return leLogo;
	}

	public void setLeLogo(byte[] leLogo) {
		this.leLogo = leLogo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

}
