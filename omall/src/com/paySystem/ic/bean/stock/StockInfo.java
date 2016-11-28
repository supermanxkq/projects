package com.paySystem.ic.bean.stock;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;
import com.paySystem.ic.bean.card.CardBIN;

/**
 * @ClassName:StockInfo
 * @Description:库存信息表
 * @date: 2013-12-6上午10:36:35
 * @author: 谢洪飞
 * @version: V1.0
 */
@Entity
@Table(name="S_StockInfo")
public class StockInfo implements Serializable {

	private static final long serialVersionUID = -6027693152747308944L;
	
	/** 流水号   */
	private Integer id;
    /** 所属机构  */
	private Organs organs;
	/** 所属商户  */
	private Merchants merchants;
	/** 入库总数 */
	private Integer inTotal;
	/** 出库总数  */
	private Integer outTotal;
	/** 现有库存 */
	private Integer existsTotal;
	/** 对应卡BIN */
	private CardBIN cardBin;
	/** 更新时间 */		
	private Date updateTime;
	
/*	@Id
	@SequenceGenerator(name="s_StockInfo_SEQ",sequenceName="s_StockInfo_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="s_StockInfo_SEQ")*/
	/**
	 * 添加MySql自增列支持
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(optional=true,cascade=CascadeType.REFRESH)
	@JoinColumn(name="organId")
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	// cascade表示级联。CascadeType.REFRESH级联刷新  
    // optional表示该对象可有可无，它的值为true表示该外键可以为null，它的值为false表示该外键为not null  	@OneToOne(optional=true,cascade=CascadeType.ALL)
	@ManyToOne(optional=true,cascade=CascadeType.REFRESH)
	@JoinColumn(name="merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column(columnDefinition="DECIMAL(15,2) default 0")
	public Integer getInTotal() {
		return inTotal;
	}
	public void setInTotal(Integer inTotal) {
		this.inTotal = inTotal;
	}
	@Column(columnDefinition="DECIMAL(15,2) default 0")
	public Integer getOutTotal() {
		return outTotal;
	}
	public void setOutTotal(Integer outTotal) {
		this.outTotal = outTotal;
	}
	@Column(columnDefinition="DECIMAL(15,2) default 0")
	public Integer getExistsTotal() {
		return existsTotal;
	}
	public void setExistsTotal(Integer existsTotal) {
		this.existsTotal = existsTotal;
	}
	@ManyToOne(optional=false, cascade=CascadeType.REFRESH)
	@JoinColumn(name="binId")
	public CardBIN getCardBin() {
		return cardBin;
	}
	public void setCardBin(CardBIN cardBin) {
		this.cardBin = cardBin;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	

}
