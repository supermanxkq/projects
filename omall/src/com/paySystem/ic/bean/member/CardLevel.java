package com.paySystem.ic.bean.member;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.paySystem.ic.bean.base.Merchants;
import com.paySystem.ic.bean.base.Organs;

/***
 * 卡等级信息表
 * @author 井建国
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="C_cardLevel")
public class CardLevel  implements Serializable{
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
	/** 卡等级所属机构  */
	private Organs organs;
	/**卡等级所属商户名称  */
	private Merchants merchants;
	/**卡等级修改更新时间 */
	private Date updateTime;
	/**卡等级相应的描述  */
	private String descr;
	
	public CardLevel() {
		super();
	}
	public CardLevel(String levelId) {
		this.levelId = levelId;
	}
	@Id
	@Column(length = 10)
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	@Column(length = 20)
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Column(columnDefinition="char(1)")
	public Integer getLevelSign() {
		return levelSign;
	}
	public void setLevelSign(Integer levelSign) {
		this.levelSign = levelSign;
	}
	@Column(length = 10)
	public String getPerLevelId() {
		return perLevelId;
	}
	public void setPerLevelId(String perLevelId) {
		this.perLevelId = perLevelId;
	}
	@Column(length = 9)
	public Integer getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(Integer newCardPoint) {
		this.newPoint = newCardPoint;
	}
	@Column(columnDefinition="char(1)")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "organId")
	public Organs getOrgans() {
		return organs;
	}
	public void setOrgans(Organs organs) {
		this.organs = organs;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "merId")
	public Merchants getMerchants() {
		return merchants;
	}
	public void setMerchants(Merchants merchants) {
		this.merchants = merchants;
	}
	@Column(length = 10)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length = 255)
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	
}