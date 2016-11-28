package com.paySystem.ic.web.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.paySystem.ic.web.dto.BaseDTO;


/***
 * 卡等级信息表
 * @author 井建国
 *
 */
public class CardLevelDTO  extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**卡等级编号  */
	private String levelId;
	/** 卡等级名称 */
	private String levelName;
	/** 卡等级类型 */
	private Integer levelSign;
	/** 上级级别 */
	private String perLevelId;
	/** 开卡送积分额度 */
	private Integer newPoint;
	/** 卡级别等级状态 */
	private Integer status;
	/** 卡等级所属机构 Id */
	private String organId;
	/** 卡等级所属机构 Id */
	private String name;
	/**卡等级所属商户名称  */
	private String merName;
	/**卡等级所属商户名称  */
	private String merId;
	/**卡等级修改更新时间 */
	private Date updateTime;
	/**卡等级相应的描述  */
	private String descr;
	/**现金账户折扣积分Id */
	private String caccDisPntId;
	/**现金账户*/
	private Integer caccTypeId;
	/**现金消费折扣      0表示不赠送 */
	private BigDecimal cpayDiscount;
	/**现金消费送积分比率     0表示不赠送*/
	private BigDecimal cppointRate;
	/**现金充值送积分比率    0表示不赠送 */
	private BigDecimal crpointRate;
	/**现金账户折扣积分Id */
	private String saccDisPntId;
	/**积分账户*/
	private Integer saccTypeId;
	/**积分消费折扣      0表示不赠送 */
	private BigDecimal spayDiscount;
	/**积分消费送积分比率     0表示不赠送*/
	private BigDecimal sppointRate;
	/**积分充值送积分比率    0表示不赠送 */
	private BigDecimal srpointRate;
	/**商户折扣积分 */
	private String merDisPntId;
	/**账户类型关联   暂时用Id，不关联     0：现金账户   1：积分账户*/
	private String accTypeId;
	/**消费折扣 */
	private BigDecimal payDiscount;
	/**消费积分比率 */
	private BigDecimal ppointRate;
	/**充值积分比率 */
	private BigDecimal rpointRate;
	/**升级规则流水账号 */
	private String upgId;
	/***
	 *升级方式:
	 * 1: 充值累计
	 * 2：卡片消费累计
	 * 3：卡片单次充值
	 * 4: 卡片余额
	 */
	private String upgType;

	/**升级上限*/
	private Integer upper;

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getLevelSign() {
		return levelSign;
	}

	public void setLevelSign(Integer levelSign) {
		this.levelSign = levelSign;
	}

	public String getPerLevelId() {
		return perLevelId;
	}

	public void setPerLevelId(String perLevelId) {
		this.perLevelId = perLevelId;
	}

	public Integer getNewPoint() {
		return newPoint;
	}

	public void setNewPoint(Integer newPoint) {
		this.newPoint = newPoint;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCaccDisPntId() {
		return caccDisPntId;
	}

	public void setCaccDisPntId(String caccDisPntId) {
		this.caccDisPntId = caccDisPntId;
	}

	public String getSaccDisPntId() {
		return saccDisPntId;
	}

	public void setSaccDisPntId(String saccDisPntId) {
		this.saccDisPntId = saccDisPntId;
	}

	public Integer getCaccTypeId() {
		return caccTypeId;
	}

	public void setCaccTypeId(Integer caccTypeId) {
		this.caccTypeId = caccTypeId;
	}

	
	public Integer getSaccTypeId() {
		return saccTypeId;
	}

	public void setSaccTypeId(Integer saccTypeId) {
		this.saccTypeId = saccTypeId;
	}


	public String getMerDisPntId() {
		return merDisPntId;
	}

	public void setMerDisPntId(String merDisPntId) {
		this.merDisPntId = merDisPntId;
	}

	public String getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(String accTypeId) {
		this.accTypeId = accTypeId;
	}

	

	public BigDecimal getCpayDiscount() {
		return cpayDiscount;
	}

	public void setCpayDiscount(BigDecimal cpayDiscount) {
		this.cpayDiscount = cpayDiscount;
	}

	public BigDecimal getCppointRate() {
		return cppointRate;
	}

	public void setCppointRate(BigDecimal cppointRate) {
		this.cppointRate = cppointRate;
	}

	public BigDecimal getCrpointRate() {
		return crpointRate;
	}

	public void setCrpointRate(BigDecimal crpointRate) {
		this.crpointRate = crpointRate;
	}

	public BigDecimal getSpayDiscount() {
		return spayDiscount;
	}

	public void setSpayDiscount(BigDecimal spayDiscount) {
		this.spayDiscount = spayDiscount;
	}

	public BigDecimal getSppointRate() {
		return sppointRate;
	}

	public void setSppointRate(BigDecimal sppointRate) {
		this.sppointRate = sppointRate;
	}

	public BigDecimal getSrpointRate() {
		return srpointRate;
	}

	public void setSrpointRate(BigDecimal srpointRate) {
		this.srpointRate = srpointRate;
	}

	public BigDecimal getPayDiscount() {
		return payDiscount;
	}

	public void setPayDiscount(BigDecimal payDiscount) {
		this.payDiscount = payDiscount;
	}

	public BigDecimal getPpointRate() {
		return ppointRate;
	}

	public void setPpointRate(BigDecimal ppointRate) {
		this.ppointRate = ppointRate;
	}

	public BigDecimal getRpointRate() {
		return rpointRate;
	}

	public void setRpointRate(BigDecimal rpointRate) {
		this.rpointRate = rpointRate;
	}

	public String getUpgId() {
		return upgId;
	}

	public void setUpgId(String upgId) {
		this.upgId = upgId;
	}

	public String getUpgType() {
		return upgType;
	}

	public void setUpgType(String upgType) {
		this.upgType = upgType;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}
	
	
}
