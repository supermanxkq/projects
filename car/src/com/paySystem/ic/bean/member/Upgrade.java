package com.paySystem.ic.bean.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/***
 * 升级规则信息表
 * 
 * 
 * @author 井建国
 *
 */
@Entity
@Table(name="C_Upgrade")
public class Upgrade {
	
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
	/**卡等级关联 */
	private CardLevel cardLevel;
	/**升级上限*/
	private Integer upper;

	
	
	@Id
	@Column(length = 10)
	public String getUpgId() {
		return upgId;
	}
	public void setUpgId(String upgId) {
		this.upgId = upgId;
	}
	@Column(columnDefinition="char(1)")
	public String getUpgType() {
		return upgType;
	}
	public void setUpgType(String upgType) {
		this.upgType = upgType;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "levelId")
	public CardLevel getCardLevel() {
		return cardLevel;
	}
	public void setCardLevel(CardLevel cardLevel) {
		this.cardLevel = cardLevel;
	}
	@Column(length = 9)
	public Integer getUpper() {
		return upper;
	}
	public void setUpper(Integer upper) {
		this.upper = upper;
	}
}
