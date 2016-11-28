package com.paySystem.ic.bean.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "B_ComplaintHandle")
public class ComplaintHandle implements Serializable {
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
	/** 平台处理意见说明 */
	private String handlSug;
	/** 处理人id */
	private String operator;
	/** 更新时间 */
	private Date updateTime;
	/** 商品名称 **/
	private String goodsName;
	private String goodsId;
	/** 被投诉的店铺 **/
	private String storeName;
	/** 投诉者名称 **/
	private String userName;
	/** 投诉ID **/
	private Integer filedId;
	/**扣除分值**/
	private double deductScore;
	/**规则案例**/
	private Integer vioId;
	/** 平台处理方式 **/
	private Integer handlWay;

	@Id
	@Column(length = 3)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(columnDefinition = "timestamp")
	public Date getCompTime() {
		return compTime;
	}

	public void setCompTime(Date compTime) {
		this.compTime = compTime;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getCompType() {
		return compType;
	}

	public void setCompType(Integer compType) {
		this.compType = compType;
	}

	@Column
	public String getCompDesc() {
		return compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	@Column(length = 16)
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getCompSta() {
		return compSta;
	}

	public void setCompSta(Integer compSta) {
		this.compSta = compSta;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getManComSta() {
		return manComSta;
	}

	public void setManComSta(Integer manComSta) {
		this.manComSta = manComSta;
	}

	@Column
	public String getStoreComDesc() {
		return storeComDesc;
	}

	public void setStoreComDesc(String storeComDesc) {
		this.storeComDesc = storeComDesc;
	}

	@Column
	public String getHandlSug() {
		return handlSug;
	}

	public void setHandlSug(String handlSug) {
		this.handlSug = handlSug;
	}

	@Column(length = 20)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(columnDefinition = "timestamp")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(length = 50)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(length = 100)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(length = 30)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length = 16)
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Column
	public Integer getFiledId() {
		return filedId;
	}

	@Column
	public void setFiledId(Integer filedId) {
		this.filedId = filedId;
	}

	@Column(length=5,scale=2)
	public double getDeductScore() {
		return deductScore;
	}

	public void setDeductScore(double deductScore) {
		this.deductScore = deductScore;
	}
	@Column
	public Integer getVioId() {
		return vioId;
	}

	public void setVioId(Integer vioId) {
		this.vioId = vioId;
	}
	@Column(columnDefinition="char(1)")
	public Integer getHandlWay() {
		return handlWay;
	}

	public void setHandlWay(Integer handlWay) {
		this.handlWay = handlWay;
	}



}
