/**  
 * @Title: GoodsFamilyAdvertRelationDTO.java
 * @Package: com.paySystem.ic.web.dto.base
 * @Description: TODO
 * @Author: A18ccms A18ccms_gmail_com  
 * @Date: 2014-12-3 上午10:15:40
 * @Version: V1.0  
 */
package com.paySystem.ic.web.dto.base;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omallBackstage
 * @ClassName:GoodsFamilyAdvertRelationDTO
 * @Description:TODO
 * @date: 2014-12-3
 * @author: 孟凡岭
 * @version: V1.0
 */
public class GoodsFamilyAdvertRelationDTO extends BaseDTO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 关联Id */
	private Integer relId;

	/** 分类Id */
	private Integer familyId;

	/**
	 * 广告主体标志 0:商品广告 1:店铺广告 2:品牌广告 3:活动广告
	 * */
	private Integer advertContentSign;

	/**
	 * 内容Id
	 */
	private Integer objectId;

	/** 广告图片路径 */
	private String advertPicPath;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 操作员Id */
	private String operatorId;

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public Integer getAdvertContentSign() {
		return advertContentSign;
	}

	public void setAdvertContentSign(Integer advertContentSign) {
		this.advertContentSign = advertContentSign;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getAdvertPicPath() {
		return advertPicPath;
	}

	public void setAdvertPicPath(String advertPicPath) {
		this.advertPicPath = advertPicPath;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
