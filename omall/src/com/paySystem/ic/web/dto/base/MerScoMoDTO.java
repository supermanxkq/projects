package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ClassName:MerScoMo
 * @Description:商户评分模型实体Bean
 * @date: 2014-09-23
 * @author: 张军磊
 * @version: V1.0n
 */

public class MerScoMoDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 7829692676635695351L;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMerScoMoId() {
		return merScoMoId;
	}

	public void setMerScoMoId(Integer merScoMoId) {
		this.merScoMoId = merScoMoId;
	}

	public String getMoName() {
		return moName;
	}

	public void setMoName(String moName) {
		this.moName = moName;
	}

	public Integer getMaxSco() {
		return maxSco;
	}

	public void setMaxSco(Integer maxSco) {
		this.maxSco = maxSco;
	}

	public Integer getMinSco() {
		return minSco;
	}

	public void setMinSco(Integer minSco) {
		this.minSco = minSco;
	}

	public String getShowPic() {
		return showPic;
	}

	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
