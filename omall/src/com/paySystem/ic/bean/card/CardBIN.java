package com.paySystem.ic.bean.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.paySystem.ic.bean.base.Organs;

/**
 * @author 赵巧鹤
 * @ClassName ：CardBIN 卡BIN表
 * @project_name：mciu
 * @createTime：2013-12-4 下午04:04:50
 * 
 * 
 */
@Entity
@Table(name = "c_bin")
public class CardBIN implements Serializable {

	private static final long serialVersionUID = 6693965150652975115L;
	/** 卡BIN号 */
	private String binId;
	/** 卡名称 */
	private String binName;
	/**
	 * 卡标志 0、CPU卡 1、磁条卡 2、CPU卡脱机
	 * */
	private Integer binSign;

	/**
	 * 卡类别 0、专卡 1、通卡
	 * */
	private Integer binType;
	/** 面值 */
	private BigDecimal initAmt;
	/** 卡内额度上限 */
	private BigDecimal limitAmt;
	/** 单笔消费金额上限 */
	private BigDecimal singleConsAmt;
	/** 单笔充值金额上限 */
	private BigDecimal singleRechAmt;
	/** 日累计交易次数上限 */
	private Integer dayConsTimes;
	/** 日累计交易金额上限 */
	private BigDecimal dayConsAmtLimt;

	/** 描述 */
	private String descr;
	/**
	 * 状态 1、启用 0、禁用 9、删除
	 */
	private Integer status;
	/** 修改时间 */
	private Date updateTime;
	/** 创建时间 */
	private Date createTime;

	private Organs organs;
	private Set<CardNo> cardNo = new HashSet<CardNo>();
    /**
     * 单笔消费金额预警
     * */
	private BigDecimal singleConsWorn;
	/**
	 * 单笔充值金额预警
	 * */
	private BigDecimal singleRechWorn;
	/**
	 * 该卡BIN下属卡片
	 * 显示卡号长度
	 * */
	private Integer dispNoLen;
	
	public CardBIN() {
		super();
	}

	public CardBIN(String binId) {
		this.binId = binId;
	}

	@Id
	@Column(length = 6)
	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
	}

	@Column(length = 60)
	public String getBinName() {
		return binName;
	}

	public void setBinName(String binName) {
		this.binName = binName;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getBinSign() {
		return binSign;
	}

	public void setBinSign(Integer binSign) {
		this.binSign = binSign;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getBinType() {
		return binType;
	}

	public void setBinType(Integer binType) {
		this.binType = binType;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getLimitAmt() {
		return limitAmt;
	}

	public void setLimitAmt(BigDecimal limitAmt) {
		this.limitAmt = limitAmt;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getSingleConsAmt() {
		return singleConsAmt;
	}

	public void setSingleConsAmt(BigDecimal singleConsAmt) {
		this.singleConsAmt = singleConsAmt;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getSingleRechAmt() {
		return singleRechAmt;
	}

	public void setSingleRechAmt(BigDecimal singleRechAmt) {
		this.singleRechAmt = singleRechAmt;
	}

	@Column(columnDefinition = "DECIMAL(9)")
	public Integer getDayConsTimes() {
		return dayConsTimes;
	}

	public void setDayConsTimes(Integer dayConsTimes) {
		this.dayConsTimes = dayConsTimes;
	}

	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getDayConsAmtLimt() {
		return dayConsAmtLimt;
	}

	public void setDayConsAmtLimt(BigDecimal dayConLimtAmt) {
		this.dayConsAmtLimt = dayConLimtAmt;
	}

	@Column(columnDefinition = "char(1)")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(length = 225)
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}

	public void setOrgans(Organs organs) {
		this.organs = organs;
	}

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE,
			CascadeType.PERSIST }, mappedBy = "cardBIN", fetch = FetchType.EAGER)
	@OrderBy(value = "cardNo ASC")
	public Set<CardNo> getCardNo() {

		return cardNo;
	}

	public void setCardNo(Set<CardNo> cardNo) {
		this.cardNo = cardNo;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getSingleConsWorn() {
		return singleConsWorn;
	}

	public void setSingleConsWorn(BigDecimal singleConsWorn) {
		this.singleConsWorn = singleConsWorn;
	}
	@Column(columnDefinition = "DECIMAL(13,2)")
	public BigDecimal getSingleRechWorn() {
		return singleRechWorn;
	}

	public void setSingleRechWorn(BigDecimal singleRechWorn) {
		this.singleRechWorn = singleRechWorn;
	}
	@Column
	public Integer getDispNoLen() {
		return dispNoLen;
	}

	public void setDispNoLen(Integer dispNoLen) {
		this.dispNoLen = dispNoLen;
	}
  
}
