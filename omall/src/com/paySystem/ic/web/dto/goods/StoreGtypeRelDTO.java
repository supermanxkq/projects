package com.paySystem.ic.web.dto.goods;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * @ProjectName:omall
 * @ClassName:StoreGtypeRelDTO
 * @Description:店铺基本设置表和商品分类表关联数据传输对象
 * @date: 2014-11-6上午10:58:48
 * @author: 徐凯强
 * @version: V1.0
 */
public class StoreGtypeRelDTO extends BaseDTO implements Serializable {

	/***/
	private static final long serialVersionUID = -1537133702552784367L;

	/**
	 * 分类ID
	 */
	private Integer familyId;
	/**
	 * 操作人
	 */
	private int operatorId;
	/**
	 * 操作时间
	 */
	private Date operatorTime;
	/**
	 * 主键
	 */
	private int sTid;
	/**
	 * 店铺ID
	 */
	private Integer storeId;
	/** 商品分类编号字符串 */
	private String familiesIds;

	public String getFamiliesIds() {
		return familiesIds;
	}

	public void setFamiliesIds(String familiesIds) {
		this.familiesIds = familiesIds;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public int getsTid() {
		return sTid;
	}

	public void setsTid(int sTid) {
		this.sTid = sTid;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

}
