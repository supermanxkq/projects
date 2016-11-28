package com.paySystem.ic.web.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;

/**
 * 投诉后台处理映射类 此表不保存任何数据 因为SQL使用表关联查询 ，字段较多 所以创建这个实体
 * 
 * @ProjectName:omallBackstage
 * @ClassName:ComplaintHandle
 * @Description:
 * @date: 2014-11-20
 * @author: 孟凡岭
 * @version: V1.0
 */
public class ComplaintHandleDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	private long id;
	/** 投诉时间 **/
	private Date compTime;
	/** 投诉类型 **/
	private Integer compType;
	/** 投诉描述 **/
	private String compDesc;
	/** 会员编号 */
	private String memId;
	/**
	 * 投诉状态 0：投诉退款申请等待平台确认中 1：平台不同意协议，等待买家修改 3：投诉申请达成，已对卖家进行处罚 4：买家已退货，等待卖家确认收货
	 * 5：投诉关闭 6：投诉成功
	 * */
	private Integer compSta;
	/**
	 * 管理投诉状态 0：会员进行投诉 1：商家进行回复 2：平台处理投诉
	 */
	private Integer manComSta;
	/** 商户申诉说明 */
	private String storeComDesc;

	/** 处理人id */
	private String operator;
	/** 更新时间 */
	private Date updateTime;
	/** 商品名称 **/
	private String goodsName;
	/** 商品编号 **/
	private String goodsId;
	/** 被投诉的店铺 **/
	private String storeName;
	/** 投诉证明图片路径 **/
	private String[] picPath;
	/** 投诉都名称 **/
	private String userName;
	/** 扣除积分 **/
	private Integer points;
	/**
	 * 平台投诉处理方式 1："平台不同意协议，等待买家修改" 2："投诉申请达成，已对卖家进行处罚" 3："投诉申请达成，对卖家进行处罚"
	 * 5："投诉关闭"
	 **/
	private Integer handlWay;
	/** 平台处理意见 **/
	private Integer adminHandlSug;
	/** 平台处理意见说明 */
	private String handlSug;
	/** 投诉ID **/
	private Integer filedId;
	/** 违规案例ID **/
	private Integer vioId;
	/** 扣除分值 **/
	private double deductScore;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCompTime() {
		return compTime;
	}

	public void setCompTime(Date compTime) {
		this.compTime = compTime;
	}

	public Integer getCompType() {
		return compType;
	}

	public void setCompType(Integer compType) {
		this.compType = compType;
	}

	public String getCompDesc() {
		return compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Integer getCompSta() {
		return compSta;
	}

	public void setCompSta(Integer compSta) {
		this.compSta = compSta;
	}

	public Integer getManComSta() {
		return manComSta;
	}

	public void setManComSta(Integer manComSta) {
		this.manComSta = manComSta;
	}

	public String getStoreComDesc() {
		return storeComDesc;
	}

	public void setStoreComDesc(String storeComDesc) {
		this.storeComDesc = storeComDesc;
	}

	public String getHandlSug() {
		return handlSug;
	}

	public void setHandlSug(String handlSug) {
		this.handlSug = handlSug;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String[] getPicPath() {
		return picPath;
	}

	public void setPicPath(String[] picPath) {
		this.picPath = picPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getHandlWay() {
		return handlWay;
	}

	public void setHandlWay(Integer handlWay) {
		this.handlWay = handlWay;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getPoints() {
		return points;
	}

	public Integer getAdminHandlSug() {
		return adminHandlSug;
	}

	public void setAdminHandlSug(Integer adminHandlSug) {
		this.adminHandlSug = adminHandlSug;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getFiledId() {
		return filedId;
	}

	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}

	public Integer getVioId() {
		return vioId;
	}

	public void setVioId(Integer vioId) {
		this.vioId = vioId;
	}

	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}

}
