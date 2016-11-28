package com.paySystem.ic.bean.evaluation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: omall
 * @ClassName: MerCreditRole
 * @Description: 商户信誉度标志规则表
 * @date: 2014-10-29 下午03:14:56
 * @author: 王少辉
 * @version: V1.0
 */
@Entity
@Table(name = "B_MerCreditRole")
public class MerCreditRole implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

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

	/**
	 * @return Integer 信息ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10, nullable = false)
	public Integer getMcrId() {
		return mcrId;
	}

	/**
	 * @param mcrId
	 *            信息ID
	 */
	public void setMcrId(Integer mcrId) {
		this.mcrId = mcrId;
	}

	/**
	 * @return BigDecimal 最低分
	 */
	@Column(columnDefinition = "DECIMAL(16,2)", nullable = false)
	public BigDecimal getMinScores() {
		return minScores;
	}

	/**
	 * @param minScores
	 *            最低分
	 */
	public void setMinScores(BigDecimal minScores) {
		this.minScores = minScores;
	}

	/**
	 * @return BigDecimal 最高分
	 */
	@Column(columnDefinition = "DECIMAL(16,2)", nullable = false)
	public BigDecimal getMaxScores() {
		return maxScores;
	}

	/**
	 * @param maxScores
	 *            最高分
	 */
	public void setMaxScores(BigDecimal maxScores) {
		this.maxScores = maxScores;
	}

	/**
	 * @return Integer 等级
	 */
	@Column(length = 4, nullable = false)
	public Integer getMerLevel() {
		return merLevel;
	}

	/**
	 * @param merLevel
	 *            等级
	 */
	public void setMerLevel(Integer merLevel) {
		this.merLevel = merLevel;
	}

	/**
	 * @return byte[] 等级标志图片
	 */
	public byte[] getLeLogo() {
		return leLogo;
	}

	/**
	 * @param leLogo
	 *            等级标志图片
	 */
	public void setLeLogo(byte[] leLogo) {
		this.leLogo = leLogo;
	}

	/**
	 * @return String 操作人编号
	 */
	@Column(length = 20, nullable = false)
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId
	 *            操作人编号
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * @return Date 创建日期
	 */
	@Column(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Date 更新日期
	 */
	@Column(nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
