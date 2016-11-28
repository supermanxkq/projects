package com.paySystem.ic.bean.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @ClassName:MerScoMo
 * @Description:商户评分模型实体Bean
 * @date: 2014-09-23
 * @author: 张军磊
 * @version: V1.0n
 */
@Entity
@Table(name = "B_MerCreditRole")
public class MerScoMo implements Serializable {

	private static final long serialVersionUID = -1283257237395313878L;
	/** 商户评分模型ID */
	private Integer merScoMoId;
	/** 等级名称 */
	private String moName;
	/** 最大分数 */
	private Integer maxSco;
	/** 最小分数 */
	private Integer minSco;
	/** 显示图片 */
	private String showPic;
	/** 更新时间 */
	private Date updateTime;
	/** 操作员ID */
	private String operatorId;
	/** 使用状态 */
	private Integer status;
	/** 创建时间 **/
	private Date createTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mcrId")
	public Integer getMerScoMoId() {
		return merScoMoId;
	}

	public void setMerScoMoId(Integer merScoMoId) {
		this.merScoMoId = merScoMoId;
	}

	@Column(length = 50, name = "merLevel")
	public String getMoName() {
		return moName;
	}

	public void setMoName(String moName) {
		this.moName = moName;
	}

	@Column(name = "maxScores")
	public Integer getMaxSco() {
		return maxSco;
	}

	public void setMaxSco(Integer maxSco) {
		this.maxSco = maxSco;
	}

	@Column(name = "minScores")
	public Integer getMinSco() {
		return minSco;
	}

	public void setMinSco(Integer minSco) {
		this.minSco = minSco;
	}

	@Column(length = 255, name = "leLogo")
	public String getShowPic() {
		return showPic;
	}

	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length = 15)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
